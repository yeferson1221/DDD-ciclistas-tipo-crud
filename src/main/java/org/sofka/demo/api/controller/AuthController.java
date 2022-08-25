package org.sofka.demo.api.controller;


import org.sofka.demo.domain.User;
import org.sofka.demo.repository.UserRepository;
import org.sofka.demo.repository.UserRepositoryIpm;
import org.sofka.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User userLogin = userRepository.idCredential(user);
        if (userLogin != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(userLogin.getId()), userLogin.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

}
