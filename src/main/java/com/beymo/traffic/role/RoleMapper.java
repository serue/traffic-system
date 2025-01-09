package com.beymo.traffic.role;

import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public Role updateRole(Role role, RoleRequest request) {
        if(request.name()!=null){
            role.setName(request.name());
        }
        return role;
    }

    public Role toRole(RoleRequest request) {
        Role role = new Role();
        role.setName("ROLE_"+request.name());
        return role;
    }
}
