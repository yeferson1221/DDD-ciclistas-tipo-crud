package org.sofka.demo.api.controller;


import org.sofka.demo.domain.User;
import org.sofka.demo.repository.UserRepository;
import org.sofka.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * [
 *  AuthController
 *  contiene metodo login y dos inyecciones  (UserRepository,   JWTUtil)
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    /**
     * [
     *  AuthController permite autentificarse, donde si el correo y la contraseña es diferente de null
     *  como respuesta manda un token y si no retorna un FAIL
     * ]
     * @version [1,0.0]
     *
     * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
     * @since [1,0,0]
     *
     */
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
