package com.springboot.userservice.Entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter

@Table(name = UserEntity.TABLE_NAME)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    static final String TABLE_NAME = "Users";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATED_AT")
    private Date creationDate;

    @Column(name = "UPDATED_AT")
    private Date updatedDate;

    @Column(name = "VALID")
    private boolean valid;



}
