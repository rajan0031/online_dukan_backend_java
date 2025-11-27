package com.example.helloServer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.helloServer.Model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom queries later, e.g. findByEmail, findByUserName
    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    // boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);
}
