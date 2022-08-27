package org.sofka.demo.repository;

import org.sofka.demo.domain.User;

import java.util.List;


public interface UserRepository  {
    User idCredential(User user);
    List<User> getUser();

    void delete(Integer id);

    User register(User user);


}
