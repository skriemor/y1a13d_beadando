package com.epam.training.ticketservice.movie.entity;



import com.epam.training.ticketservice.movie.id.ReservationID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "reservation_entity")
@IdClass(ReservationID.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationEntity {
    @Id
    @Column(name = "movie_title")
    private String movie;
    @Id
    @Column(name = "room_name")
    private String room;
    @Id
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "start_date")
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationEntity that = (ReservationEntity) o;
        return movie.equals(that.movie)
                && room.equals(that.room)
                && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, room, date);
    }
}