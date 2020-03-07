package com.github.qingying0.community.web.controller;

import com.github.qingying0.community.bo.UserBo;
import com.github.qingying0.community.bo.UserLoginBo;
import com.github.qingying0.community.dao.RedisDao;
import com.github.qingying0.community.dto.ResultDTO;
import com.github.qingying0.community.dto.UserDTO;
import com.github.qingying0.community.entity.Users;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.IUsersService;
import com.github.qingying0.community.utils.CommonUtils;
import com.github.qingying0.community.utils.RedisKeyUtils;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@Slf4j
public class PassPortController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private RedisDao redisDao;

    @Value("${server.servlet.context-path}")
    private String path;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@Validated UserLoginBo user, BindingResult result, Model model,
                        HttpSession session, HttpServletResponse response) {
        try {
            CommonUtils.validate(result);
            String kaptcha = (String) session.getAttribute("kaptcha");
            if (!user.getKaptcha().toLowerCase().equals(kaptcha.toLowerCase())) {
                throw new CustomException("验证码输入错误");
            }
            UserDTO userDTO = usersService.login(user);
            Cookie cookie = new Cookie("token", userDTO.getToken());
            cookie.setPath(path);
            response.addCookie(cookie);
        } catch (CustomException e) {
            model.addAttribute("message", e.getMessage());
            return "login";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@Validated UserBo userBo, BindingResult result, Model model) {
        try {
            CommonUtils.validate(result);
            Users user = new Users();
            BeanUtils.copyProperties(userBo, user);
            usersService.register(user);
        } catch (CustomException e) {
            model.addAttribute("message", e.getMessage());
            return "register";
        }
        model.addAttribute("message", "发送邮件成功，请去邮箱激活");
        return "register";
    }

    /**
     * 激活账号
     * @param userId
     * @param activationCode
     * @param model
     * @return
     */
    @GetMapping("/active")
    public String active(Long userId, String activationCode, Model model) {
        usersService.activeAccount(userId, activationCode);
        model.addAttribute("message", "激活成功");
        return "login";
    }

    /**
     * 生成验证码
     * @param response
     * @param httpSession
     */
    @GetMapping("/kaptcha")
    public void getKaptcha(HttpServletResponse response, HttpSession httpSession) {
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        httpSession.setAttribute("kaptcha", text);
        response.setContentType("image/png");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            log.error("生成验证码失败:" + e.getMessage());
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue("token") String token) {
        redisDao.del(RedisKeyUtils.getTokenKey(token));
        return "redirece:/";
    }
}
