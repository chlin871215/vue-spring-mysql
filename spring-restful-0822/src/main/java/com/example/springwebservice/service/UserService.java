package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<String> getUniqueUser() {
        List<User> users = userRepository.findAll();
        List<String> names = users.stream()
                .map(User::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return names;
    }

    public Map<Integer, String> getUniqueUserNameMap() {
        List<User> users = userRepository.findAll();
        Map<Integer, String> map = users.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        return map;
    }

    public User getFirstUserByName(String name) {
        List<User> users = userRepository.findAll();
        User user = users.stream()
                .filter(user1 -> name.equals(user1.getName()))
                .findFirst()
                .get();
        return user;
    }

    public List<User> getUsersSortedByAgeAndId() {
        List<User> users = userRepository.findAll();
        List<User> userList =users.stream()
                .sorted(Comparator.comparing(User::getAge)
                        .thenComparing(User::getId))
                .collect(Collectors.toList());
        return userList;
    }

    public String getUsersToString(){
        List<User> users=userRepository.findAll();
        //StringBuilder寫法
        StringBuilder sb = new StringBuilder();
        for (User user:users){
            sb.append(user.getName());
            sb.append(",");
            sb.append(user.getAge());
            sb.append("|");
        }
        sb.setLength(sb.length()-1);//刪除字串最後多餘的｜
        //Stream如下
        String s = users.stream()
                .map(user->user.getName()+","+user.getAge())
                .collect(Collectors.joining("|"));
        return s;
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }
    public List<User> getAllUsers(int age, int filter) {
        if (filter == 1) {
            List<User> users = userRepository.findByAgeGreaterThanEqual(age);
            return users;
        } else if (filter == 2) {
            List<User> users = userRepository.findByOrderByAgeDesc();
            return users;
        } else {
            List<User> users = userRepository.findAll();
            return users;
        }
    }

    public List<User> findByNameAndAge(String name, int age) {
        List<User> users = userRepository.findByNameAndAge(name, age);
        return users;
    }

    public String createUser2(int id, String name, int age) {
        userRepository.createUser(id, name, age);
        return "OK";
    }

    public String updateUser2(int id, String name, int age) {
        userRepository.updateUser(id, name, age);
        return "OK";
    }

    public String deleteUser2(String name, int age) {
        userRepository.deleteUser(name, age);
        return "OK";
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id);
        return user;
    }

    public String createUser(CreateUserRequest request) {
        User user = new User();
        if (userRepository.findById(request.getId()) != null) {
            return "User already exist";
        }
        user.setId(request.getId());
        user.setName(request.getName());
        user.setAge(request.getAge());
        userRepository.save(user);
        return "OK";
    }

    public String updateUser(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id);
        if (user == null) {
            return "User doesn't exist";
        }
        user.setName(request.getName());
        user.setAge(request.getAge());
        userRepository.save(user);
        return "OK";
    }

    public String deleteUser(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return "User doesn't exist";
        }
        Long count = userRepository.deleteById(id);
        return "OK";
    }

} // Class end
