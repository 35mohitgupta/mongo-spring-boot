package com.mht.mongodemo.repository;

import com.mht.mongodemo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{firstName: '?0'}")
    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByHobbiesIn(List<String> hobbies);

    @Query(value =  "{firstName: '?0'}", fields = "{firstName : 1, lastName: 1, hobbies: 1}")
    List<User> findFNameLNameAndHobbiesByFName(String firstName);

    @Update(value = "{ $push : {hobbies : '?1'}}")
    @Query(value = "{firstName : '?0'}")
    int addHobbies(String fname, String hobby);

    @Query(value = "{firstName : '?0'}", delete = true)
    Integer deleteByFirstName(String fname);

}
