package com.epam.training.ticketservice.shell;

import com.epam.training.ticketservice.movie.repository.MovieEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.logging.Logger;

@ShellComponent
public class CommandLineParser {
    Logger logger = Logger.getLogger("SHELL LOG");
    @Autowired
    MovieEntityRepository mRepo;

    @ShellMethod(key = "add movie",value = "Add movie to list")
    public String addMovie(String title, String category, Integer length) {
        if(!mRepo.existsByTitle(title)) {
            return "" + title + " is added to movies!";
        }
        return "Movie by title" + title + "already exists.";
    }
}
