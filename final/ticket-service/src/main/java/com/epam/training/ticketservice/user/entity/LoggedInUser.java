package com.epam.training.ticketservice.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logged_in_user")
public class LoggedInUser {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    private String userType;

}