package com.beymo.traffic.user.controller;

import com.beymo.traffic.shared.PageResponse;
import com.beymo.traffic.user.dto.UserRequest;
import com.beymo.traffic.user.dto.UserResponse;
import com.beymo.traffic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getAllSchedulesByCourt(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.getAllUsers(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getUserById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateUser(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The user was successfully deleted");
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(request));
    }

}
