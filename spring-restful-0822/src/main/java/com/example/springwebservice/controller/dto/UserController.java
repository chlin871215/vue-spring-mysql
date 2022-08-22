package com.example.springwebservice.controller.dto;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.controller.dto.response.StatusResponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:8081")
public class UserController {

    @Autowired
    UserService userService;

    /*@GetMapping("/user")
    public List<User> getAllUserList() {
        List<User> users = this.userService.getAllUsers();
        return users;
    }
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    /*
    @GetMapping()
    public List<User> getAllUsers(@RequestParam int age, @RequestParam int filter) {
        List<User> users = this.userService.getAllUsers(age, filter);
        return users;
    }
     */
    @GetMapping("/user/unique")
    public List<String> getUserUnique() {
        List<String> users = this.userService.getUniqueUser();
        return users;
    }

    @GetMapping("/user/map")
    public Map<Integer, String> getUserByMap() {
        Map<Integer, String> users = this.userService.getUniqueUserNameMap();
        return users;
    }

    @GetMapping("/user/first")
    public User getFirstUserByName(@RequestParam String name) {
        User user = this.userService.getFirstUserByName(name);
        return user;
    }

    @GetMapping("/user/sorted")
    public List<User> getUsersSortedByAgeAndId() {
        List<User> users = userService.getUsersSortedByAgeAndId();
        return users;
    }

    @GetMapping("/user/string")
    public String getUsersToString() {
        String response = userService.getUsersToString();
        return response;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @PostMapping("/users")
    public StatusResponse createUser(@RequestBody CreateUserRequest request) {
        String response = userService.createUser(request);
        return new StatusResponse(response);
    }

    @PutMapping("/users/{id}")
    public StatusResponse updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        String response = userService.updateUser(id, request);
        return new StatusResponse(response);
    }

    @DeleteMapping("/users/{id}")
    public StatusResponse deleteUser(@PathVariable int id) {
        String response = userService.deleteUser(id);
        return new StatusResponse(response);
    }

} // Class end
