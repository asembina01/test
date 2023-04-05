package com.example.eotinish.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Ghost {
    @Id
    private Long chatId;

    private String firstName;
    private String lastName;
    private String userName;

}
