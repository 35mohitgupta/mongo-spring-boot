package com.mht.mongodemo.controller;

import com.mht.mongodemo.entity.User;
import com.mht.mongodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public User getUserByUserId(@PathVariable String id){
        return userService.getUserByUserId(id);
    }

    @GetMapping("/user/fn/{firstName}")
    public List<User> getUserByFirstName(@PathVariable String firstName){
        return userService.getUserByFirstName(firstName);
    }

    @GetMapping("/user/ln/{lastName}")
    public List<User> getUserByLastName(@PathVariable String lastName){
        return userService.getUserByLastName(lastName);
    }

    @PutMapping("/user/hobbies")
    public List<User> getUserByHobbies(@RequestBody List<String> hobbies){
        return userService.getUserByHobbies(hobbies);
    }

    @GetMapping("/user/hobbies/{firstName}")
    public Map<String, List<String>> getHobbiesByfirstName(@PathVariable String firstName){
        return userService.getHobbiesByName(firstName);
    }

    @PutMapping("/user/lastName")
    public List<User> updateLastName(@RequestBody LinkedHashMap<String,String> updateRequest){
        return userService.updateLastName(updateRequest);
    }

    @PatchMapping("/user/hobbies/")
    public String addNewHobby(@RequestBody LinkedHashMap<String,String> updateRequest){
        int count = 0;
        for(Map.Entry<String,String> entry : updateRequest.entrySet()){
            count += userService.addNewHobby(entry.getKey(), entry.getValue());
        }
        return count + " records updated";
    }

    @DeleteMapping("/user/{firstName}")
    public String deleteUserByFirstName(@PathVariable String firstName){
        int recordsDeleted = userService.deleteUserByFName(firstName);
        return "Deleted "+recordsDeleted + " records";
    }

    @GetMapping("/user/city/{city}")
    public List<User> findByCity(@PathVariable String city){
        return userService.findByAddressCity(city);
    }
}
