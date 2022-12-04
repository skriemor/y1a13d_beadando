package com.epam.training.ticketservice.user.repository;

import com.epam.training.ticketservice.user.entity.LoggedInUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedInUserRepository extends JpaRepository<LoggedInUser, String> {
}