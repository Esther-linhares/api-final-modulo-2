package br.com.apiNotes.apinotes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "users")
public class User {

    @Id
    private String email;
    @Column(name = "password_user")
    private String password;
    @OneToMany(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "useremail"
    )
    private List<Task> tasks;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.tasks = new ArrayList();
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

}

