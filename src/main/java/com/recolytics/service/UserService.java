package com.recolytics.service;

import com.recolytics.model.User;

public interface UserService {
    User register(User user);
    User getByEmail(String email);
}
