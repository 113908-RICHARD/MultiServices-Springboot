package com.example.multirubroproyectproducts.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = CategoryEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class CategoryEntity extends BaseEntity {

    static final String TABLE_NAME  = "CATEGORIES";



    @Column(name = "DESCRIPTION")
    String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

}
