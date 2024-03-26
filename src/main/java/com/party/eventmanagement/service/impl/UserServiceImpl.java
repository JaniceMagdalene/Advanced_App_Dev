package com.party.eventmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.party.eventmanagement.dto.request.UserRequest;
import com.party.eventmanagement.dto.request.UserUpdateRequest;
import com.party.eventmanagement.model.User;
import com.party.eventmanagement.repository.UserRepo;
import com.party.eventmanagement.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Override
    public void createUser(UserRequest userRequest)
    {
    User user =new User();
   user.setName(userRequest.getName());
   user.setEmail(userRequest.getEmail());
   user.setPassword(userRequest.getPassword());
   userRepo.save(user);     
    }
    @Override
    public void updateUser(long userUid,UserUpdateRequest userUpdateRequest){
        User existingUser=userRepo.findById(userUid)
        .orElseThrow(() -> new RuntimeException("User Not Found"));
        existingUser.setName(userUpdateRequest.getName());
        existingUser.setEmail(userUpdateRequest.getEmail());
        existingUser.setPassword(userUpdateRequest.getPassword());

    }
    @Override
    public void deleteUser(long userUid) {
       userRepo.deleteById(userUid);
    }
    
}