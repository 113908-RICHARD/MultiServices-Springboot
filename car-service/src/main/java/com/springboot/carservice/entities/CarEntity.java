package com.springboot.carservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter

@Table(name = CarEntity.TABLE_NAME)
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class CarEntity {

    static final String TABLE_NAME = "cars";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2",strategy = "uuid2")
    private UUID id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name="MODEL")
    private String model;

    @Column(name = ("USER_ID"))
    private UUID userId;

    @Column(name = "CREATED_AT")
    private Date creationDate;

    @Column(name = "UPDATED_AT")
    private Date updatedDate;

    @Column(name = "VALID")
    private boolean valid;


}
