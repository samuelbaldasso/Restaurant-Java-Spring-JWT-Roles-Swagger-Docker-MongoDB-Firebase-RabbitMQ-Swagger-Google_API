package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.UserDTO;
import com.sbaldass.combo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  public UserService userService;

  @GetMapping
  public List<UserDTO> getAll() throws Exception {
    return userService.findAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> getById(@PathVariable Long id) throws Exception {
    return userService.findUserById(id);
  }

  @PostMapping
  public User create(@RequestBody UserDTO userDTO) throws Exception {
      return userService.saveUser(userDTO);
  }

  @PutMapping("/{id}")
  public User putUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws Exception {
    return userService.alterUser(userDTO, id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws Exception {
    userService.deleteUser(id);
  }

}
