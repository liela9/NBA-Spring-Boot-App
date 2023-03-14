package com.example.demo.input;

import com.example.demo.game.Game;
import com.example.demo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyInputRepository extends JpaRepository<MyInput, Long> {
    @Query("SELECT i FROM MyInput i WHERE i.inputId = ?1")
    MyInput findInputById(Long id);

    @Query("SELECT i FROM MyInput i WHERE i.email = ?1")
    List<MyInput> findInputsByEmail(String email);

    @Query("SELECT i FROM MyInput i WHERE i.date = ?1")
    List<MyInput> findInputByDate(String date);

    @Query("SELECT i FROM MyInput i WHERE i.homeTeam = ?1")
    List<MyInput> findInputByHomeTeam(String homeTeam);

    @Query("SELECT i FROM MyInput i WHERE i.visitorTeam = ?1")
    List<MyInput> findInputByVisitorTeam(String visitorTeam);

    @Query("SELECT i FROM MyInput i WHERE i.fee = ?1")
    List<MyInput> findInputByFee(double fee);

    @Query("select i from MyInput i " +
            "where i.email = :email " +
            "and( " +
                "lower(i.homeTeam) like lower(concat('%', :searchTerm, '%')) " +
                "or lower(i.visitorTeam) like lower(concat('%', :searchTerm, '%'))" +
                "or lower(i.date) like lower(concat('%', :searchTerm, '%'))" +
            ")")
    List<MyInput> search(@Param("email") String email, @Param("searchTerm") String searchTerm);
}
