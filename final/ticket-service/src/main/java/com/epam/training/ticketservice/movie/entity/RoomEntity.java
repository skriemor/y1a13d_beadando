package com.epam.training.ticketservice.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
}