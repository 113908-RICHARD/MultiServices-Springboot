package com.example.multirubroproyectproducts.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = ProductEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class CategoryEntity extends BaseEntity {

    static final String TABLE_NAME  = "CATEGORIES";


    @Column(name = "DESCRIPTION")
    String description;



}
