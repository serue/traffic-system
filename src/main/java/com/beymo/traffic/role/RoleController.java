package com.beymo.traffic.role;
import com.beymo.traffic.shared.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PageResponse<Role>> getAllRoles(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(roleService.findAll(page, size));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{role-id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("role-id") Integer roleId) {
        return ResponseEntity.ok(roleService.findById(roleId));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody @Validated RoleRequest request) {
        var role = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{role-id}")
    public ResponseEntity<Role> updateRole(@PathVariable("role-id") Integer roleId, @RequestBody @Validated RoleRequest request) {
        var role = roleService.updateRole(request, roleId);
        return ResponseEntity.accepted().body(role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{role-id}")
    public ResponseEntity<?> deleteRole(@PathVariable("role-id") Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The role was successfully deleted");
    }
}
