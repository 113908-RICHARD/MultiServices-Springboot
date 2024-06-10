package com.springboot.bikeservice.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = BikeEntity.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor

public class BikeEntity {

    static final String TABLE_NAME = "bikes";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
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
