package com.example.springwebservice.controller.dto;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.controller.dto.response.StatusResponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> findUserByNameAndAge(@RequestParam String name,@RequestParam int age){
        List<User> users = userService.findByNameAndAge(name, age);
        return users;
    }

    @PostMapping()
    public StatusResponse createUser2(@RequestBody CreateUserRequest createUserRequest){
        String response = userService.createUser2(createUserRequest.getId(), createUserRequest.getName(), createUserRequest.getAge());
        return new StatusResponse(response);
    }

    @PutMapping("/{id}")
    public StatusResponse updateUser2(@PathVariable int id,@RequestBody UpdateUserRequest updateUserRequest){
        String response = userService.updateUser2(id,updateUserRequest.getName(),updateUserRequest.getAge());
        return new StatusResponse(response);
    }

    @DeleteMapping()
    public StatusResponse deleteUser2(@RequestParam String name,@RequestParam int age){
        String response = userService.deleteUser2(name, age);
        return new StatusResponse(response);
    }


}
