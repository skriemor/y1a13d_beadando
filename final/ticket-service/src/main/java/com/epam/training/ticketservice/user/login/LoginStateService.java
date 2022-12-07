package com.epam.training.ticketservice.user.login;

import com.epam.training.ticketservice.user.entity.LoggedInUser;
import com.epam.training.ticketservice.user.entity.UserEntity;
import com.epam.training.ticketservice.user.repository.LoggedInUserRepository;
import com.epam.training.ticketservice.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class LoginStateService {
    private final UserEntityRepository userRepo;
    private final LoggedInUserRepository loggedInRepo;

    @PostConstruct
    public Boolean createAdmin() {
        if (!userRepo.existsById("admin")) {
            userRepo.save(new UserEntity(
                    "admin","admin"
            ));
            return true;
        }
        return false;
    }

    public String getLoggedInUserType() {
        return isSignedIn() ? loggedInRepo.findAll().get(0).getUserType() : "";
    }



    public Boolean isUserAdminLoginable(UserEntity user) {
        return loggedInRepo.findAll().size() == 0
                &&
                userRepo.existsById(user.getUserName())
                &&
                userRepo.findById(user.getUserName())
                        .get().getPassword().equals(user.getPassword());
    }

    @Transactional
    public Boolean loginAdmin(UserEntity user) {
        if (isUserAdminLoginable(user)) {
            loggedInRepo.save(new LoggedInUser(
                    user.getUserName(), "privileged")
            );
            return true;
        }
        return false;
    }

    public Boolean isSignedIn() {
        return loggedInRepo.findAll().size() == 1;
    }

    public Boolean signOutUser() {
        if (loggedInRepo.findAll().size() > 0) {
            loggedInRepo.deleteAll();
            return true;
        }
        return false;
    }

    public String getSignedInUserName() {
        return isSignedIn() ? loggedInRepo.findAll()
                .get(0).getName() : "";
    }

    public Boolean isAdmin() {
        return loggedInRepo.count() == 1
                ? loggedInRepo.findAll().get(0)
                        .getUserType().equals("privileged")
                : false;
    }

    public String describeAcc() {
        return isSignedIn() ? "Signed in with "
                +
                getLoggedInUserType()
                +
                " account \'"
                +
                getSignedInUserName()
                +
                "\'"
                : "You are not signed in";
    }

}
