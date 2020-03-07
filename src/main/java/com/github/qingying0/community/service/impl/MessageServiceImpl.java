package com.github.qingying0.community.service.impl;

import com.github.qingying0.community.dto.MessageDTO;
import com.github.qingying0.community.entity.Message;
import com.github.qingying0.community.exception.CustomCode;
import com.github.qingying0.community.exception.CustomException;
import com.github.qingying0.community.mapper.MessageMapper;
import com.github.qingying0.community.service.IMessageService;
import com.github.qingying0.community.utils.Constant;
import com.github.qingying0.community.utils.HostHolder;
import com.github.qingying0.community.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<MessageDTO> getByUserId(Long userId) {
        return messageMapper.selectByUserId(userId);
    }

    @Override
    public List<Message> getByUserChat(Long toId) {
        if(hostHolder.get() == null) {
            throw new CustomException(CustomCode.NO_LOGIN);
        }
        Long hostId = hostHolder.get().getId();
        String conversationId = toId < hostId ? toId + "_" + hostId : hostId + "_" + toId;
        Example messageExample = new Example(Message.class);
        Example.Criteria messageCriteria = messageExample.createCriteria();
        messageCriteria.andEqualTo("conversationId", conversationId);
        return messageMapper.selectByExample(messageExample);
    }

    @Override
    public void addMessage(Message message) {
        if(hostHolder.get() == null) {
            throw new CustomException(CustomCode.NO_LOGIN);
        }
        message.setFromId(hostHolder.get().getId());
        Long hostId = hostHolder.get().getId();
        Long toId = message.getToId();
        message.setConversationId(toId < hostId ? toId + "_" + hostId : hostId + "_" + toId);
        message.setStatus(Constant.MESSAGE_STATUS_UNREAD);
        message.setCreateTime(new Date());
        messageMapper.insert(message);
    }
}
