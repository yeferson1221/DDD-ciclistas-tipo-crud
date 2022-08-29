package org.sofka.demo.repository;

import org.sofka.demo.domain.User;

import java.util.List;

/**
 * [
 *  UserRepository
 *  solo se deja como interface y se declaran los metodos a usar
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
public interface UserRepository  {
    User idCredential(User user);
    List<User> getUser();

    void delete(Integer id);

    User register(User user);
}
