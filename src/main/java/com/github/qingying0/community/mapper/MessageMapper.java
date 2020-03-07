package com.github.qingying0.community.mapper;

import com.github.qingying0.community.dto.MessageDTO;
import com.github.qingying0.community.entity.Message;
import com.github.qingying0.community.mymapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends MyMapper<Message> {


    List<MessageDTO> selectByUserId(Long userId);
}
