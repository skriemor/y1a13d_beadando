package com.epam.training.ticketservice.shell;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import com.epam.training.ticketservice.movie.entity.ReservationEntity;
import com.epam.training.ticketservice.movie.entity.RoomEntity;
import com.epam.training.ticketservice.movie.repository.MovieEntityRepository;
import com.epam.training.ticketservice.movie.repository.ScreeningRepository;
import com.epam.training.ticketservice.movie.repository.RoomEntityRepository;
import com.epam.training.ticketservice.user.login.LoginStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class ScreeningCommandLineParser {
    @Autowired
    RoomEntityRepository roomRepo;
    @Autowired
    LoginStateService service;
    @Autowired
    MovieEntityRepository mRepo;
    @Autowired
    ScreeningRepository resRepo;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create screening",value = "Create a screening")
    public String addScreening(String movieTitle, String roomName, String startDate) {
        if (!mRepo.existsById(movieTitle)) return "Movie not found";
        if (!roomRepo.existsById(roomName)) return "Room not found";
        LocalDateTime time = LocalDateTime.parse(startDate,formatter);
        if (time == null) return "Time parse exception";
        if (resRepo.existsByMovieAndRoomAndDate(movieTitle,roomName,time)) {
            return "Screening already exists";
        }
        ReservationEntity res = new ReservationEntity(movieTitle, roomName, time);
        res.setDate(time);
        res.setRoom(roomName);
        res.setMovie(movieTitle);
        String canAdd = canAddScreening(res);
        if (canAdd.equals("can")){
            resRepo.save(res);
        }else return canAdd;

        return "Saved screening ";

    }

    @ShellMethodAvailability("isAdmin")
    @Transactional
    @ShellMethod(key = "delete screening", value = "Delete a screening")
    public String deleteScreening(String title, String name, String startDate) {
        LocalDateTime time = LocalDateTime.parse(startDate,formatter);
        if (resRepo.existsByMovieAndRoomAndDate(title,name,time)){
            resRepo.deleteByMovieAndRoomAndDate(title,name,time);
            return "Successfully deleted screening";
        }
        return "Screening with movie name "+ name +" does not exist";
    }


    public Availability isAdmin() {
        return service.isAdmin()?Availability.available():Availability.unavailable("");
    }



    @ShellMethod(key = "list screenings",value = "List screenings")
    public String listScreenings() {

        if(resRepo.count()==0) {
            return"There are no screenings";
        }
        StringBuffer stringBuffer = new StringBuffer();
        resRepo.findAll().stream().forEach(
                m -> {
                    MovieEntity movie = mRepo.findById(m.getMovie()).get();
                    RoomEntity room = roomRepo.findById(m.getRoom()).get();

                    stringBuffer.append(
                        movie.getTitle()+" (" +
                                movie.getCategory() +
                                ", " +
                                movie.getLength() +
                                " minutes), screened in room " +
                                room.getName() +
                                ", at " +
                                m.getDate().format(formatter) +
                                "\n"
                );}
        );

        return stringBuffer.substring(0,stringBuffer.length() - 1);
    }


    private String canAddScreening(ReservationEntity reservation) {
        boolean duringbreak = false;
        boolean duringscreening = false;
        List<ReservationEntity> resevations = new ArrayList<>(resRepo.findAll());
        for(var s: resevations) {
            if (s.getRoom().equals(reservation.getRoom())) {
                LocalDateTime date = s.getDate();
                Integer sMovieLength = mRepo.findById(s.getMovie()).get().getLength();
                Integer rMovieLength = mRepo.findById(reservation.getMovie()).get().getLength();
                if (reservation.getDate().isAfter(s.getDate()) &&
                        reservation.getDate().isBefore(s.getDate().plusMinutes(sMovieLength + 10))
                ) {
                    if (reservation.getDate().isBefore(s.getDate().plusMinutes(sMovieLength ))) {
                        duringscreening = true;

                    }
                    duringbreak = true;

                }
            }
        }
        if(duringbreak) {
            if (duringscreening) {
                return "There is an overlapping screening";
            }
            return "This would start in the break period after another screening in this room";
        }
        return "can";
    }
}
