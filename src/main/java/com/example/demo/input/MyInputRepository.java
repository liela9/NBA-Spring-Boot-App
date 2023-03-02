package com.example.demo.input;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyInputRepository extends JpaRepository<MyInput, Long> {
    @Query("SELECT i FROM MyInput i WHERE i.inputId = ?1")
    MyInput findInputById(Long id);

    @Query("SELECT i FROM MyInput i WHERE i.user = ?1")
    List<MyInput> findInputsByUser(String email);

    @Query("SELECT i FROM MyInput i WHERE i.date = ?1")
    List<MyInput> findInputByDate(String date);

    @Query("SELECT i FROM MyInput i WHERE i.homeTeam = ?1")
    List<MyInput> findInputByHomeTeam(String homeTeam);

    @Query("SELECT i FROM MyInput i WHERE i.visitorTeam = ?1")
    List<MyInput> findInputByVisitorTeam(String visitorTeam);

    @Query("SELECT i FROM MyInput i WHERE i.fee = ?1")
    List<MyInput> findInputByFee(double fee);
}
