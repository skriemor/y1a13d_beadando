package com.epam.training.ticketservice.shell;

import com.epam.training.ticketservice.user.entity.UserEntity;
import com.epam.training.ticketservice.user.login.LoginStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UserCommandLineParser {
    @Autowired
    LoginStateService service;

    @ShellMethod(key = "sign in privileged",value = "Sign in")
    public String signIn(String userName, String userPassword) {
        UserEntity user = new UserEntity(userName,userPassword);
        if (service.loginAdmin(user)) {
            return "Signed in with privileged account 'admin'";
        }
        return "Login failed due to incorrect credentials";
    }

    @ShellMethod(key = "sign out",value = "Sign out")
    public String signIn() {
        if (service.isSignedIn()) {
            service.signOutUser();
            return "Successfully signed out";
        }
        return "You are not signed in";
    }

    @ShellMethod(key = "describe account",value = "Describe current account")
    public String describeAcc() {
        return service.describeAcc();
    }
}
