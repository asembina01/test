package com.example.eotinish.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    private String middlename;

    private String fio;

    private Long chatId;

    private String email;

    public AppUser() {

    }

    public AppUser(String firstname, String lastname, String middlename, Long chatId, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.fio = lastname+" "+firstname+" "+middlename;
        this.fio = fio.toUpperCase();
        this.chatId = chatId;
        this.email = email;
    }

}
