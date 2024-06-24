package com.springboot.userservice.Repositories;

import com.springboot.userservice.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findById (UUID id);
    Optional<UserEntity> findByEmail (String email);

}
