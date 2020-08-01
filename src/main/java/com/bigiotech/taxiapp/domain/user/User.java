package com.bigiotech.taxiapp.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "User")
public class User {


    public User() {

    }

    public User(String id, String names, String surnames, String email, String username, String password, String profilePicture) {
        this.id = id;
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public User(String names, String surnames, String email, String username, String password, String profilePicture) {
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    @Id
    private String id;
    private String names;
    private String surnames;
    private String email;
    private String username;
    private String password;
    private String profilePicture;
}
