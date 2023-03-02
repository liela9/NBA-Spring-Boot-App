package com.example.demo.user;

import com.example.demo.input.MyInput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Year;
import java.util.List;


@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class AppUser {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(updatable = false)
    private Long userId;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(length = 100)
    private String password;

    @Column(name = "birth_year")
    private Year birthYear;

    @OneToMany(mappedBy = "user")
    private List<MyInput> myInputs;

//    @Enumerated(EnumType.STRING)
//    private AppUserRole role;
    //TODO: needed?

    public AppUser(String email,
                   String fullName,
                   String password,
                   Year birthYear) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.birthYear = birthYear;
    }

    public String getUsername() {
        return email;
    }
}
