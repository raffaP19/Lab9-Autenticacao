package br.ufpb.dcx.dsc.figurinhas.controller;


import br.ufpb.dcx.dsc.figurinhas.dto.UserDTO;
import br.ufpb.dcx.dsc.figurinhas.models.Album;
import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public  UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/user")
    List<User> listUsers(){
        return userService.listUsers();
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        return convertToDTO(user);
    }


    @PostMapping(path = "/user")
    UserDTO createUser(@Valid @RequestBody UserDTO userDTO){
        User u = convertToEntity(userDTO);
        User saved = userService.createUser(u);
        return convertToDTO(saved);
    }

    @PutMapping("/user/{userId}")
    public UserDTO updateTask(@PathVariable Long userId, @RequestBody UserDTO userDTO){

        User u = convertToEntity(userDTO);
        User userUpdated = userService.updateUser(userId, u);
        return convertToDTO(userUpdated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }


    private UserDTO convertToDTO(User u) {
        return modelMapper.map(u, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}