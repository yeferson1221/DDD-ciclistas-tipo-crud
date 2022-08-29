package org.sofka.demo.api.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.sofka.demo.domain.User;
import org.sofka.demo.repository.UserRepository;
import org.sofka.demo.repository.UserRepositoryIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [
 *  UserController
 *  contiene metodos crud y dos inyecciones  (UserRepository ,   UserRepositoryIpm)
 *  todas las apis estan protegidas con el token (@RequestHeader(value="Authorization")
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryIpm userRepositoryIpm;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value="Authorization") String token) {
        if (!userRepositoryIpm.validateToken(token)) { return null; }

        return (List<User>) userRepository.getUser();
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userRepository.register(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value="Authorization") String token,
                         @PathVariable Integer id) {
        if (!userRepositoryIpm.validateToken(token)) { return; }
       userRepository.delete(id);
    }
}
