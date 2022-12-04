package com.epam.training.ticketservice.user.login;

import com.epam.training.ticketservice.user.entity.LoggedInUser;
import com.epam.training.ticketservice.user.entity.UserEntity;
import com.epam.training.ticketservice.user.repository.LoggedInUserRepository;
import com.epam.training.ticketservice.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class LoginStateService {
    private final UserEntityRepository userRepo;
    private final LoggedInUserRepository loggedInRepo;
    UserEntity loggedInUser;
    Boolean privileged = true;



    @PostConstruct
    private void createAdmin() {
        if (!userRepo.existsById("admin"))
            userRepo.save(new UserEntity(
                    "admin","admin"
            ));
    }

    public String getLoggedInUserType() {
        return isSignedIn()?loggedInRepo.findAll().get(0).getUserType():"";
    }

    @Transactional
    public Boolean loginAdmin(UserEntity user) {
        if (loggedInRepo.findAll().size()==0 &&
        userRepo.existsById(user.getUserName()) &&
        userRepo.findById(user.getUserName())
                .get().getPassword().equals(user.getPassword())) {
            loggedInRepo.save(new LoggedInUser(
                    user.getUserName(),"privileged")
            );
            return true;
        }
        return false;
    }
    public Boolean isSignedIn() {
        return loggedInRepo.findAll().size()==1;
    }
    public void signOutUser() {
        if (loggedInRepo.findAll().size()>0) {
            loggedInRepo.deleteAll();
        }
    }

    public String getSignedInUserName() {
        return isSignedIn()?loggedInRepo.findAll().get(0).getName():"";
    }

    public Boolean isAdmin() {
        return loggedInRepo.count()==1?
                loggedInRepo.findAll().get(0).getUserType().equals("privileged")
                :false;
    }
}
