package com.example.multirubroproyectproducts.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = ProductEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductEntity extends BaseEntity{

    static final String TABLE_NAME = "PRODUCTS";


    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "PRICE")
    Double price;




    @Column(name = "STOCK")
    Integer stock;
}
