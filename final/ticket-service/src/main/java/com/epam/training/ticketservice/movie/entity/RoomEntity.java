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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_entity")
public class RoomEntity {
    @Id
    @Column(name = "room_name")
    private String name;

    @Column(name = "row_count")
    private Integer rowCount;
    @Column(name = "column_count")
    private Integer colCount;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationEntity> reservationList;

    public RoomEntity(String name, Integer rows, Integer cols) {
        this.colCount = cols;
        this.rowCount = rows;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomEntity room = (RoomEntity) o;
        return Objects.equals(name, room.name)
                && Objects.equals(rowCount, room.rowCount)
                && Objects.equals(colCount, room.colCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rowCount, colCount);
    }
}