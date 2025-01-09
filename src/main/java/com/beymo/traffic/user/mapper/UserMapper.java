package com.beymo.traffic.user.mapper;

import com.beymo.traffic.role.Role;
import com.beymo.traffic.user.dto.UserRequest;
import com.beymo.traffic.user.dto.UserResponse;
import com.beymo.traffic.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getForcenumber(),
                user.getEmail(),
                user.getRank(),
                user.getDateOfBirth(),
                user.getRoles(),
                user.isEnabled(),
                user.isAccountLocked()

        );
    }

    public User toUser(UserRequest request, List<Role> roles) {
        User user = new User();
        user.setFirstname(request.firstname());
        user.setLastname(request.lastname());
        user.setForcenumber(request.forcenumber());
        user.setEmail(request.email());
        user.setRank(request.rank());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(roles);
        user.setEnabled(true);
        user.setAccountLocked(false);
        return user;
    }

    public User updateUser(UserRequest request, List<Role> roles) {
        User user = new User();
        if(request.firstname() != null) {
            user.setFirstname(request.firstname());
        }
        if(request.lastname() != null) {
            user.setLastname(request.lastname());
        }
        if(request.forcenumber() != null) {
            user.setForcenumber(request.forcenumber());
        }
        if(request.email() != null) {
            user.setEmail(request.email());
        }
        if(request.rank() != null) {
            user.setRank(request.rank());
        }
        if(roles != null) {
            user.setRoles(roles);
        }
        return user;
    }
}
