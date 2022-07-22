package com.stock.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService user;



    @GetMapping(value  = "api/user/{userName}")
    @ResponseBody
    public UserEntity getUser(@PathVariable("userName") String userName) {

        return user.getUser(userName);
    }

    @PostMapping("api/adduser")
    public UserEntity postUser(@RequestBody String userName) {
        user.saveUser(UserEntity.builder().userName(userName).build());
        return UserEntity.builder().userName(userName).build();
    }

    @DeleteMapping("api/deleteuser")
    public void deleteUser(@RequestBody String userName) {
        user.deleteUser(userName);
    }
    // delete user

    //update user

}
