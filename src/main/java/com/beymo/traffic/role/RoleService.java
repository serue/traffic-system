package com.beymo.traffic.role;

import com.beymo.traffic.shared.PageResponse;

public interface RoleService {
    PageResponse<Role> findAll(int page, int size);

    Role findById(Integer roleId);

    Role createRole(RoleRequest request);

    Role updateRole(RoleRequest request, Integer roleId);

    void deleteRole(Integer roleId);
}
