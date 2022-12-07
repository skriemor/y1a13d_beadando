package com.epam.training.ticketservice.shell;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import com.epam.training.ticketservice.movie.repository.MovieEntityRepository;
import com.epam.training.ticketservice.user.login.LoginStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@ShellComponent
public class MovieCommandLineParser {
    Logger logger = Logger.getLogger("SHELL LOG");
    @Autowired
    MovieEntityRepository movieRepository;
    @Autowired
    LoginStateService service;

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create movie",value = "Add movie to list")
    public String addMovie(String title, String category, Integer length) {
        logger.warning(title + category + String.valueOf(length));
        if (!movieRepository.existsById(title)) {
            movieRepository.save(new MovieEntity(title,category,length));
            return "" + title + " is added to movies!";
        }
        return "Movie by title" + title + "already exists.";
    }

    public Availability isAdmin() {
        return service.isAdmin() ? Availability.available() : Availability.unavailable("");
    }

    @ShellMethod(key = "list movies",value = "List movies")
    public String listMovies() {
        if (movieRepository.count() == 0) {
            return "There are no movies at the moment";
        }
        StringBuffer stringBuffer = new StringBuffer();
        movieRepository.findAll().stream().forEach(
            m -> stringBuffer.append(
            m.getTitle()
            +
            " ("
            +
            m.getCategory()
            + ", "
            + m.getLength()
            + " minutes)\n"
            )
        );

        return stringBuffer.substring(0,stringBuffer.length() - 1);
    }

    @Transactional
    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "update movie", value = "Update a movie")
    public String updateMovie(String movieName, String newGenre, Integer newLength) {
        if (movieRepository.existsById(movieName)) {
            movieRepository.deleteById(movieName);
            MovieEntity movie = new MovieEntity(movieName, newGenre, newLength);
            movieRepository.save(movie);
            return "Updated movie " + movieName;
        }
        return "Did not update movie";
    }

    @Transactional
    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete movie", value = "Delete a movie")
    public String deleteMovie(String movieName) {
        if (movieRepository.existsById(movieName)) {
            movieRepository.deleteById(movieName);
            return "Successfully deleted movie";
        }
        return "Movie "
                + movieName
                + " does not exist";
    }

}
