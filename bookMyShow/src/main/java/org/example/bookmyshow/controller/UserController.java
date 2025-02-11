package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.*;
import org.example.bookmyshow.model.User;

import org.example.bookmyshow.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupUserResponseDTO signupUser(SignupUserRequestDTO requestDTO) throws Exception{
        SignupUserResponseDTO responseDTO = new SignupUserResponseDTO();
        User user = userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setName(user.getName());
        responseDTO.setUserId(user.getId());
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO) throws Exception {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        if(userService.login(requestDTO.getEmail(), requestDTO.getPassword())){
            responseDTO.setLoggedInFlag(true);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }else{
            responseDTO.setLoggedInFlag(false);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
