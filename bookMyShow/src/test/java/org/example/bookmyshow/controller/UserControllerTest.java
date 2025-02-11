package org.example.bookmyshow.controller;    

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.example.bookmyshow.dto.LoginRequestDTO;
import org.example.bookmyshow.dto.LoginResponseDTO;
import org.example.bookmyshow.dto.ResponseStatus;
import org.example.bookmyshow.dto.SignupUserRequestDTO;
import org.example.bookmyshow.dto.SignupUserResponseDTO;
import org.example.bookmyshow.exception.UserExistsException;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.model.User;
import org.example.bookmyshow.repository.UserRepository;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void testSignupUserSuccess() throws Exception {
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.SUCCESS, signupUserResponseDTO.getResponseStatus());
        assertEquals(email, signupUserResponseDTO.getEmail(), "Email should match");
        assertEquals(name, signupUserResponseDTO.getName(), "Name should match");
        User user = userRepository.findAll().stream().findFirst().orElse(null);
        assertNotNull(user);
        assertEquals(email, user.getEmail(), "Email should match");
        assertEquals(name, user.getName(), "Name should match");
        assertNotNull(user.getPassword(), "Password should not be null");
        assertNotEquals(password, user.getPassword(), "Password should be encrypted");
    }

    @Test
    public void testSignupUser_SameUser_RegisteringTwice_Failure() throws Exception {
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.SUCCESS, signupUserResponseDTO.getResponseStatus(),
                "Response status should be SUCCESS");
        assertEquals(email, signupUserResponseDTO.getEmail(), "Email should match");
        assertEquals(name, signupUserResponseDTO.getName(), "Name should match");

        signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.FAILURE, signupUserResponseDTO.getResponseStatus(),
                "Response status should be FAILURE");
        assertNull(signupUserResponseDTO.getEmail(), "Email should be null");
        assertNull(signupUserResponseDTO.getName(), "Name should be null");
    }

    @Test
    public void testSignupUserSuccess_AndLoginSuccess() throws Exception{
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.SUCCESS, signupUserResponseDTO.getResponseStatus());
        assertEquals(email, signupUserResponseDTO.getEmail());
        assertEquals(name, signupUserResponseDTO.getName());

        LoginRequestDTO LoginRequestDTO = new LoginRequestDTO();
        LoginRequestDTO.setEmail(email);
        LoginRequestDTO.setPassword(password);
        LoginResponseDTO LoginResponseDTO = userController.login(LoginRequestDTO);
        assertTrue(LoginResponseDTO.isLoggedInFlag());
        assertEquals(ResponseStatus.SUCCESS, LoginResponseDTO.getResponseStatus());
    }

    @Test
    public void testSignupUserSuccess_AndLoginWithIncorrectPassword()
            throws Exception{
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.SUCCESS, signupUserResponseDTO.getResponseStatus());
        assertEquals(email, signupUserResponseDTO.getEmail());
        assertEquals(name, signupUserResponseDTO.getName());

        LoginRequestDTO LoginRequestDTO = new LoginRequestDTO();
        LoginRequestDTO.setEmail(email);
        LoginRequestDTO.setPassword(password + "1");
        LoginResponseDTO LoginResponseDTO = userController.login(LoginRequestDTO);
        assertFalse(LoginResponseDTO.isLoggedInFlag());
        assertEquals(ResponseStatus.SUCCESS, LoginResponseDTO.getResponseStatus());
    }

    @Test
    public void testSignupUser_UserAlreadyExists() throws Exception {
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        // User registering for the first time
        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);

        // User registering for the second time
        signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.FAILURE, signupUserResponseDTO.getResponseStatus());
        assertNull(signupUserResponseDTO.getEmail());
        assertNull(signupUserResponseDTO.getName());
    }

    @Test
    public void testLogin_UserNotSignedUp() throws Exception {
        LoginRequestDTO LoginRequestDTO = new LoginRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        LoginRequestDTO.setEmail(email);
        LoginRequestDTO.setPassword(password);

        LoginResponseDTO LoginResponseDTO = userController.login(LoginRequestDTO);
        assertFalse(LoginResponseDTO.isLoggedInFlag());
        assertEquals(ResponseStatus.FAILURE, LoginResponseDTO.getResponseStatus());
    }
}
