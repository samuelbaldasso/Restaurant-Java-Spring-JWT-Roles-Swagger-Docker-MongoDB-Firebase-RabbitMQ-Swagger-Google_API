package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.UserDTO;
import com.sbaldass.combo.repositories.RoleRepository;
import com.sbaldass.combo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role userRole = roleRepository.findByName(userDto.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found."));
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }


    public User alterUser(UserDTO userDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(existingUser);
    }


    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public List<UserDTO> findAllUsers() throws Exception{
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO convertToDTO(User usuario){
        UserDTO usuarioDTO = new UserDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setPassword(usuario.getPassword());
        return usuarioDTO;
    }

    public User convertToEntity(User usuario){
        User usuarioDTO = new User();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setPassword(usuario.getPassword());
        return usuarioDTO;
    }

    public void deleteUser(Long id) throws Exception{
        userRepository.deleteById(id);
    }

}
