package com.guitar.repository.jpa;

import com.guitar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

    @Transactional(readOnly = true)
    @Repository
    public interface UserRepository extends JpaRepository<User, String> {

        Optional<User> findOneByName(String name);

        Optional<User> findOneById(String id);

        Optional<User> findOneByNick(String nick);

        Optional<User> findOneByEmail(String email);

    }

