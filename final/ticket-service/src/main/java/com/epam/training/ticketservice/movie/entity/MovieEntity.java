package com.epam.training.ticketservice.movie.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieEntity that = (MovieEntity) o;
        return Objects.equals(title, that.title)
                && Objects.equals(category, that.category)
                && Objects.equals(length, that.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category, length);
    }
}