package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.bo.UserLoginBo;
import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.UserDTO;
import com.github.qingying0.community.entity.Users;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.mapper.UsersMapper;
import com.github.qingying0.community.service.IUsersService;
import com.github.qingying0.community.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qingying0
 * @since 2020-03-02
 */
@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private IdWorker idWorker;

    @Value("${custom.url}")
    private String url;

    @Value("${server.port}")
    private String port;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private FreemarkerUtils freemarkerUtils;

    @Autowired
    private RedisDao redisDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserDTO login(UserLoginBo user) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", user.getUsername());
        Users loginUser = usersMapper.selectOneByExample(userExample);
        if(loginUser == null) {
            throw new CustomException("该用户不存在");
        }
        if(loginUser.getPassword().equals(CommonUtils.MD5(user.getPassword() + loginUser.getSalt()))) {
            throw new CustomException("密码错误");
        }
        if(loginUser.getStatus() == 0) {
            throw new CustomException("账号未激活");
        }
        String token = String.valueOf(idWorker.nextId());
        Long expire = user.isRememberme() ? 7L * 60 *60 * 24 : 3L *  60 *60 * 24;
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(loginUser, userDTO);
        userDTO.setToken(token);
        redisDao.set(RedisKeyUtils.getTokenKey(token), userDTO, expire);
        return userDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void register(Users user) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", user.getUsername());
        Users registerUser = usersMapper.selectOneByExample(userExample);
        if(registerUser != null) {
            throw new CustomException("用户名已经注册");
        }
        userExample = new Example(Users.class);
        userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("email", user.getEmail());
        registerUser = usersMapper.selectOneByExample(userExample);
        if(registerUser != null) {
            throw new CustomException("邮箱已经注册");
        }
        user.setCreateTime(new Date());
        user.setId(idWorker.nextId());
        user.setSalt(CommonUtils.generateUUID().substring(0, 5));
        user.setPassword(CommonUtils.MD5(user.getPassword()) + user.getSalt());
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommonUtils.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        System.out.println("register user" + user);
        Map<String, String> param = new HashMap<>();
        param.put("url", "http://" + url + ":" + port + "/active?activationCode=" + user.getActivationCode() + "&userId=" + user.getId());
        param.put("email", user.getEmail());
        String activeHtml = freemarkerUtils.processHtml(param);
        usersMapper.insert(user);
        mailClient.sendMail(user.getEmail(), "激活邮箱", activeHtml);
    }

    @Override
    public void activeAccount(Long userId, String activationCode) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("id", userId);
        userCriteria.andEqualTo("activationCode", activationCode);
        Users user = new Users();
        user.setStatus(1);
        usersMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public UserDTO getByUserId(Long userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
