package com.springboot.authservice.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = AuthUser.TABLE_NAME)
public class AuthUser {

    static  final String TABLE_NAME = "auth_user";
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",   strategy = "uuid2")
    private UUID id;


    private String username;
    private String password;
}
