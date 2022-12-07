package com.epam.training.ticketservice.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoggedInUser that = (LoggedInUser) o;
        return Objects.equals(name, that.name)
                && Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userType);
    }
}