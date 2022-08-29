package org.sofka.demo.repository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.sofka.demo.domain.User;
import org.sofka.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * [
 *  UserRepositoryIpm
 *  se implementa la interface  UserRepository, se usan sus metodos
 *  se crean los querys necesarios
 *  esta se  cominica directamente con la BD  (@Repository @Transactional)
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Repository
@Transactional
public class UserRepositoryIpm implements UserRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    @Transactional
    public List<User> getUser() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User register(User user) {
        entityManager.merge(user);
        return user;
    }


    @Override
    public User idCredential(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> list = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (list.isEmpty()) {
            return null;
        }

        String passwordHashed = list.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, user.getPassword())) {
            return list.get(0);
        }
        return null;
    }

    //validar token
    public boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }


}
