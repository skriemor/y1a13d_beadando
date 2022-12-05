package com.epam.training.ticketservice.shell;

import com.epam.training.ticketservice.movie.entity.RoomEntity;
import com.epam.training.ticketservice.movie.repository.RoomEntityRepository;
import com.epam.training.ticketservice.user.login.LoginStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.transaction.annotation.Transactional;

@ShellComponent
public class RoomCommandLineParser {
    @Autowired
    RoomEntityRepository repo;
    @Autowired
    LoginStateService service;

    public Availability isAdmin() {
        return service.isAdmin() ? Availability.available() : Availability.unavailable("");
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create room",value = "Add room to list")
    public String addRoom(String name, Integer rows, Integer cols) {
        if (!repo.existsById(name)) {
            repo.save(new RoomEntity(name,rows,cols));
            return "" + name + " is added to rooms!";
        }
        return "Room by name" + name + "already exists.";
    }

    @ShellMethodAvailability("isAdmin")
    @Transactional
    @ShellMethod(key = "update room", value = "Update a room")
    public String updateRoom(String name, Integer rows, Integer cols) {
        if (repo.existsById(name)) {
            repo.deleteById(name);
            RoomEntity room = new RoomEntity(name, rows, cols);
            repo.save(room);
            return "Room "
                    +
                    name
                    +
                    " with "
                    +
                    (rows * cols)
                    +
                    " seats, "
                    +
                    rows
                    +
                    " rows and "
                    +
                    cols
                    +
                    " columns";
        }
        return "Did not update room";
    }

    @ShellMethodAvailability("isAdmin")
    @Transactional
    @ShellMethod(key = "delete room", value = "Delete a room")
    public String deleteRoom(String name) {
        if (repo.existsById(name)) {
            repo.deleteById(name);
            return "Successfully deleted room";
        }
        return "Room " + name + " does not exist";
    }

    @ShellMethod(key = "list rooms",value = "List rooms")
    public String listRooms() {
        if (repo.count() == 0) {
            return "There are no rooms at the moment";
        }
        StringBuffer stringBuffer = new StringBuffer();
        repo.findAll().stream().forEach(
            m -> stringBuffer.append(
                "Room "
                +
                m.getName()
                +
                " with "
                +
                (m.getRowCount() * m.getColCount())
                +
                " seats, "
                +
                m.getRowCount()
                +
                " rows and "
                +
                m.getColCount()
                +
                " columns"
                +
                "\n"
                )
        );

        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }
}
