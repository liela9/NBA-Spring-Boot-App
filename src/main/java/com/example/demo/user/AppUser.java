package com.example.demo.user;

import com.example.demo.input.MyInput;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue
    @SerializedName("user_id")
    @Column(updatable = false)
    private Long userId;

    @Email
    @NotEmpty
    private String email = "";

    @SerializedName("full_name")
    @Column(name = "full_name")
    @NotEmpty
    private String fullName = "";

    @Column(length = 100)
    private String password = "";

    @OneToMany
    @SerializedName("my_inputs")
    @Column(name = "my_inputs")
    private List<MyInput> myInputs;

    public AppUser(String email,
                   String fullName,
                   String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }
}
