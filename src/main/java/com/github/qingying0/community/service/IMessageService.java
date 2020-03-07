package com.github.qingying0.community.service;

import com.github.qingying0.community.dto.MessageDTO;
import com.github.qingying0.community.entity.Message;

import java.util.List;

public interface IMessageService {
    List<MessageDTO> getByUserId(Long userId);

    List<Message> getByUserChat(Long toId);

    void addMessage(Message message);
}
