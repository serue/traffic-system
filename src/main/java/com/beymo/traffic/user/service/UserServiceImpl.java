package com.beymo.traffic.user.service;

import com.beymo.traffic.role.Role;
import com.beymo.traffic.role.RoleRepository;
import com.beymo.traffic.shared.PageResponse;
import com.beymo.traffic.user.dto.UserRequest;
import com.beymo.traffic.user.dto.UserResponse;
import com.beymo.traffic.user.exception.UserNotFoundException;
import com.beymo.traffic.user.mapper.UserMapper;
import com.beymo.traffic.user.model.User;
import com.beymo.traffic.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public PageResponse<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> userResponses = users.stream()
                .map(mapper::toUserResponse)
                .toList();
        return new PageResponse<>(
                userResponses,
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages(),
                users.isFirst(),
                users.isLast()
        );
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        List<Role> roles = roleRepository.findAllById(request.roleId())
                .stream().toList();
        var user = userRepository.save(mapper.toUser(request,roles));
        return mapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest request) {
        List<Role> roles = roleRepository.findAllById(request.roleId())
                .stream().toList();
        var user = userRepository.save(mapper.updateUser(request,roles));
        return mapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        return userRepository.findById(id).map(mapper::toUserResponse)
                .orElseThrow(()-> new UserNotFoundException("No user found with the provided id::"+id));
    }
}
