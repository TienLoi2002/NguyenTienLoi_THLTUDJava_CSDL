package com.example.nguyentienloi_sql.services;

import com.example.nguyentienloi_sql.entity.User;
import com.example.nguyentienloi_sql.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public void save(User user) {
        userRepository.save(user);
    }
}
