package com.example.multirubroproyectproducts.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.naming.Name;
import java.util.UUID;

@Entity
@Table(name = ProviderEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProviderEntity extends BaseEntity{
    static final String TABLE_NAME = "PROVIDERS";



    @Column(name = "NAME")
    String name;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "CELLPHONE")
    String cellphone;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;
}
