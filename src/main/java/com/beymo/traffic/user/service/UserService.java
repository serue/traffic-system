package com.beymo.traffic.user.service;

import com.beymo.traffic.shared.PageResponse;
import com.beymo.traffic.user.dto.UserRequest;
import com.beymo.traffic.user.dto.UserResponse;

import java.net.URI;

public interface UserService {
    PageResponse<UserResponse> getAllUsers(int page, int size);

    UserResponse createUser(UserRequest request);

    void deleteUser(Integer id);

    UserResponse updateUser(Integer id, UserRequest request);

    UserResponse getUserById(Integer id);
}
