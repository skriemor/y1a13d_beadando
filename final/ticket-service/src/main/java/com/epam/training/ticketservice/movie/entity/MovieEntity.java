package com.epam.training.ticketservice.movie.entity;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie_entity")
public class MovieEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "title")
    private String title;

    @Column(name = "length")
    Integer length;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationEntity> reservationList;

    public MovieEntity(String title, String category, Integer length) {
        this.id = 0L;
        this.title = title;
        this.category = category;
        this.length = length;
    }

    public MovieEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<ReservationEntity> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationEntity> reservationList) {
        this.reservationList = reservationList;
    }



}