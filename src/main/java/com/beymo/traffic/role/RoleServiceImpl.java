package com.beymo.traffic.role;

import com.beymo.traffic.shared.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger log = Logger.getLogger(RoleServiceImpl.class.getName());

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public PageResponse<Role> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Role> roles = roleRepository.findAll(pageable);
        List<Role> roleResponse = roles.stream()
                .toList();
        return new PageResponse<>(
                roleResponse,
                roles.getNumber(),
                roles.getSize(),
                roles.getTotalElements(),
                roles.getTotalPages(),
                roles.isFirst(),
                roles.isLast()
        );
    }

    @Override
    public Role findById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(()-> new RoleException("Role not found with id::"+roleId));
    }

    @Override
    public Role createRole(RoleRequest request) {
        return roleRepository.save(mapper.toRole(request));
    }

    @Override
    public Role updateRole(RoleRequest request, Integer roleId) {
        Role role = findById(roleId);
        return roleRepository.save(mapper.updateRole(role, request));
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);
    }
}
