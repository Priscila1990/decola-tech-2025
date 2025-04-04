package me.dio.service.impl;

import me.dio.Repository.UserRepository;
import me.dio.service.UserService;
import me.dio.model.User;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class UserSeviceImpl implements UserService {

    private final UserRepository userRepository;

    public UserSeviceImpl(UserRepository userRepository) {
       this.userRepository=userRepository;
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("Está conta já existe.");
        }
        return userRepository.save(userToCreate);
    }

}
