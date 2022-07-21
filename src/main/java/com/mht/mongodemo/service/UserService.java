package com.mht.mongodemo.service;

import com.mht.mongodemo.entity.Address;
import com.mht.mongodemo.entity.User;
import com.mht.mongodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserByUserId(String id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUserByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    public List<User> getUserByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    public List<User> getUserByHobbies(List<String> hobbies){
        return userRepository.findByHobbiesIn(hobbies);
    }

    public Map<String, List<String>> getHobbiesByName(String firstName){
        List<User> users = userRepository.findFNameLNameAndHobbiesByFName(firstName);
        Map<String, List<String>> resMap = new HashMap<>();
        return users.stream().collect(Collectors.toMap(user -> user.getFirstName() + " "+user.getLastName(),
                user -> user.getHobbies()));
    }

    public List<User> updateLastName(Map<String, String> updateRequest){
        List<User> users = new ArrayList<>();
        updateRequest.forEach((id,newLastName) -> {
            User user =  userRepository.findById(id).orElseThrow(NoSuchElementException::new);
            user.setLastName(newLastName);
            users.add(userRepository.save(user));
        });
        return users;
    }

    public int addNewHobby(String fname,String hobby){
        return userRepository.addHobbies(fname,hobby);
    }

    public Integer deleteUserByFName(String fname){
        return userRepository.deleteByFirstName(fname);
    }

    public List<User> findByAddressCity(String city){
        User user = new User();
        Address address = new Address();
        address.setCity(city);
        user.setAddress(address);
        Example<User> userExample =   Example.of(user);
        return userRepository.findAll(userExample);
    }
}
