package com.example.crudapp.controller;

import java.util.Optional;

import com.example.crudapp.model.UserEntity;
import com.example.crudapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public @ResponseBody String addNewUser(@RequestBody UserEntity data){
        UserEntity userEntity = new UserEntity();

        userEntity.setName(data.getName());
        userEntity.setEmail(data.getEmail());
        userEntity.setCity(data.getCity());
        userEntity.setStatus(data.getStatus());
        userRepository.save(userEntity);
        return "Saved";

    }

    @GetMapping(path="/getById/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") int id){
        Optional<UserEntity> user = null;
        if(id>0){
        System.out.println("=============="+id);
        user =  userRepository.findById(id);
        }
        return user;
    }

    @PostMapping(path="/updateUser/{id}")
    public UserEntity updateUser(@PathVariable("id") int id, @RequestBody UserEntity user){
        UserEntity entity = userRepository.findById(id).get();
        entity.setName(user.getName());
        entity.setCity(user.getCity());
        entity.setEmail(user.getEmail());
        entity.setStatus(user.getStatus());
        return userRepository.save(entity);
    }

    @GetMapping(path="/all")
    public Iterable<UserEntity> getAllUser(){
        return userRepository.findAll();
    }
    
    @PostMapping(path="/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userRepository.deleteById(id);
        System.out.println("Deleted");
    }
}
