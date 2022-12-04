package com.epam.training.ticketservice.movie.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "movie_entity")
public class MovieEntity {
    @Id
    @Column(name = "movie_title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "length")
    Integer length;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationEntity> reservationList;

    public MovieEntity(String title, String category, Integer length) {
        this.title = title;
        this.category = category;
        this.length = length;
    }




}