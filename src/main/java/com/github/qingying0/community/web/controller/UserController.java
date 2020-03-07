package com.github.qingying0.community.web.controller;

import com.github.qingying0.community.dto.LikeDTO;
import com.github.qingying0.community.dto.MessageDTO;
import com.github.qingying0.community.dto.ResultDTO;
import com.github.qingying0.community.dto.UserDTO;
import com.github.qingying0.community.entity.Message;
import com.github.qingying0.community.entity.Question;
import com.github.qingying0.community.entity.Users;
import com.github.qingying0.community.exception.CustomCode;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.service.*;
import com.github.qingying0.community.utils.Constant;
import com.github.qingying0.community.utils.HostHolder;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IFollowService followService;

    @Autowired
    private HostHolder hostHolder;

    @GetMapping("/question")
    public String userQuestion(Long userId, Model model) {
        List<Question> listQuestion = questionService.getByUserId(userId);
        setUserInfo(userId, model);
        model.addAttribute("listQuestion", listQuestion);
        return "user/user_question";
    }

    @GetMapping("/letter")
    public String userMessage(Model model) {
        if(hostHolder.get() == null) {
            throw new CustomException(CustomCode.NO_LOGIN);
        }
        Long userId = hostHolder.get().getId();
        List<MessageDTO> listMessage = messageService.getByUserId(userId);
        for(MessageDTO messageDTO : listMessage) {
            Long otherUserId = messageDTO.getFromId().equals(userId) ? messageDTO.getToId() : messageDTO.getFromId();
            messageDTO.setOtherUserId(otherUserId);
            UserDTO user = usersService.getByUserId(otherUserId);
            messageDTO.setHeaderUrl(user.getHeaderUrl());
            messageDTO.setUsername(user.getUsername());
        }
        setUserInfo(userId, model);
        model.addAttribute("listMessage", listMessage);
        return "user/user_letter";
    }

    @GetMapping("/chat")
    public String userChat(Long userId, Model model){
        List<Message> listMessage = messageService.getByUserChat(userId);
        setUserInfo(userId, model);
        model.addAttribute("listMessage", listMessage);
        return "user/user_chat";
    }

    @GetMapping("/fans")
    public String userFans(Long userId, Model model) {
        List<UserDTO> listUser = followService.getUserFollowers(userId);
        setUserInfo(userId, model);
        model.addAttribute("listUser", listUser);
        return "user/user_fans";
    }

    @GetMapping("/follows")
    public String userFollows(Long userId, Model model) {
        List<UserDTO> listUser = followService.getUserFollowees(userId);
        setUserInfo(userId, model);
        model.addAttribute("listUser", listUser);
        return "user/user_follows";
    }

    @PostMapping("/like")
    @ResponseBody
    public ResultDTO userLike(Long userId,Integer entityType, Long entityId) {
        if(userId == null || entityType == null || entityId == null) {
            throw new CustomException("操作失败");
        }
        likeService.like(userId, entityType, entityId);
        Long likeCount = likeService.getLikeCount(entityType, entityId);
        Integer likeStatus = likeService.getLikeStatus(entityType, entityId);
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setCount(likeCount);
        likeDTO.setStatus(likeStatus);
        return ResultDTO.okOf(likeDTO);
    }

    @PostMapping("/follow")
    @ResponseBody
    public ResultDTO userFollow(Integer entityType, Long entityId) {
        if(entityType == null || entityId == null) {
            throw new CustomException("操作失败");
        }
        System.out.println("用户" + hostHolder.get().getId() + "关注" + entityId);
        followService.followEntity(entityType, entityId);
        return ResultDTO.okOf();
    }


    @PostMapping("/unFollow")
    @ResponseBody
    public ResultDTO userUnFollow(Integer entityType, Long entityId) {
        if(entityType == null || entityId == null) {
            throw new CustomException("操作失败");
        }
        System.out.println("用户" + hostHolder.get().getId() + "取消关注" + entityId);
        followService.unFollowEntity(entityType, entityId);
        return ResultDTO.okOf();
    }

    public void setUserInfo(Long userId, Model model) {
        UserDTO user = usersService.getByUserId(userId);
        model.addAttribute("toUser", user);
        // 收到的赞的数量
        model.addAttribute("likeCount", likeService.getUserLikeCount(userId));
        // 关注数量
        model.addAttribute("followeeCount", followService.getFolloweeCount(Constant.ENTITY_TYPE_USER, userId));
        // 粉丝数量
        model.addAttribute("followerCount", followService.getFollowerCount(Constant.ENTITY_TYPE_USER, userId));
        boolean isFollow = false;
        if(hostHolder.get() != null) {
            isFollow = followService.hasFollowed(Constant.ENTITY_TYPE_USER, userId);
        }
        // 是否已关注
        model.addAttribute("isFollow", isFollow);
    }


}
