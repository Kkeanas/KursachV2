package com.project.kursachv2.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    // Основные функции для получения данных из бд
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public User getUserById(long id) {
        return userRepository.getReferenceById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(long id) {
        User user = getUserById(id);
        userRepository.deleteById(id);
        return user;
    }

    public User updateUser(User user, long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public User convertDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
    // Дополнительные функции
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }
}
