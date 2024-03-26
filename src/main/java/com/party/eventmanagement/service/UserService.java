package com.party.eventmanagement.service;

import com.party.eventmanagement.dto.request.UserRequest;
import com.party.eventmanagement.dto.request.UserUpdateRequest;

public interface UserService {
    void createUser(UserRequest userRequest);
    void updateUser(long userUid, UserUpdateRequest userUpdateRequest);
    void deleteUser(long userUid);
    }