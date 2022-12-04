package com.epam.training.ticketservice.user.repository;

import com.epam.training.ticketservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

}