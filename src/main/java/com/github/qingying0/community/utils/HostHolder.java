package com.github.qingying0.community.utils;

import com.github.qingying0.community.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    private ThreadLocal<UserDTO> userThreadLocal = new ThreadLocal<>();

    public void set(UserDTO userDTO) {
        userThreadLocal.set(userDTO);
    }

    public UserDTO get() {
        return userThreadLocal.get();
    }

    public void clear() {
        userThreadLocal.remove();
    }
}
