package net.thmaster.hms.service;

import jakarta.validation.Valid;
import net.thmaster.hms.model.dto.UserDTO;
import net.thmaster.hms.model.req.LoginRequest;
import net.thmaster.hms.model.req.RegisterRequest;
import net.thmaster.hms.model.req.UserInfoRequest;
import net.thmaster.hms.model.req.query.UserQueryRequest;

import java.util.List;

/**
 * 用户 Service
 *
 * @author master
 */
public interface UserService {

    UserDTO register(RegisterRequest register);

    UserDTO get(Long userId);

    List<UserDTO> list(UserQueryRequest query);

    UserDTO update(Long userId, UserInfoRequest info);

    void delete(Long userId);

    UserDTO login(LoginRequest login);

}
