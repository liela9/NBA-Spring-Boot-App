package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT a FROM AppUser a WHERE a.id = ?1")
    AppUser findUserById(Long id);

    @Query("SELECT a FROM AppUser a WHERE a.email = ?1")
    AppUser findUserByEmail(String email);

    @Query("SELECT a FROM AppUser a WHERE a.email = ?1")
    AppUser findUserByUserName(String userName);
}

