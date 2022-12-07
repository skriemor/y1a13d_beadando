package com.epam.training.ticketservice.user.login;

import com.epam.training.ticketservice.user.entity.LoggedInUser;
import com.epam.training.ticketservice.user.entity.UserEntity;
import com.epam.training.ticketservice.user.repository.LoggedInUserRepository;
import com.epam.training.ticketservice.user.repository.UserEntityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * LoginStateService Tester.
 *
 * @author Szilard
 * @version 1.0
 * @since <pre>Dec 5, 2022</pre>
 */

@ExtendWith(MockitoExtension.class)
public class LoginStateServiceTest {

    @Mock
    private UserEntityRepository userRepo;
    @Mock
    private LoggedInUserRepository loginRepo;
    @InjectMocks
    private LoginStateService service;

    @Before
    public void before() throws Exception {


    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testDescribeAcc() throws Exception {
        //when signed in
        String expected = "Signed in with privileged account 'admin'";
        LoggedInUser usr = new LoggedInUser("admin", "privileged");
        Mockito.when(
            this.loginRepo.findAll()
        ).thenReturn(List.of(usr));
        var got = service.describeAcc();

        Assertions.assertEquals(expected, got);

        //when not signed in
        expected = "You are not signed in";
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of());
        got = service.describeAcc();
        Assertions.assertEquals(expected, got);
    }

    @Test
    public void testSignOutShouldSuccess() throws Exception {
        LoggedInUser usr = new LoggedInUser("admin", "privileged");
        Mockito.when(
                this.loginRepo.findAll()
        ).thenReturn(List.of(usr));

        var got = this.service.signOutUser();

        Assertions.assertEquals(true, got);
    }

    @Test
    public void testSignOutUser() throws Exception {
        LoggedInUser usr = new LoggedInUser("admin", "privileged");

        //when signed in as admin
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of(usr));
        var got = this.service.signOutUser();
        Assertions.assertTrue(got);

        //when not signed in
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of());
        got = this.service.signOutUser();
        Assertions.assertFalse(got);
    }

    @Test
    void getLoggedInUserType() {
        //when logged in as admin
        LoggedInUser usr = new LoggedInUser("admin", "privileged");
        usr.setUserType("privileged");
        usr.setName("admin");
        var expUserType = usr.getUserType();
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of(usr));
        var got = service.getLoggedInUserType();
        Assertions.assertEquals(expUserType, got);

        //when logged out
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of());
        got = service.getLoggedInUserType();
        var exp = "";
        Assertions.assertEquals(exp, got);
    }

    @Test
    void loginAdmin() {
        //when admin login
        var usr = new UserEntity("admin", "admin");
        usr.setUserName("admin");
        usr.setPassword("admin");

        var usr2 = new UserEntity("admin","admin");
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of());
        Mockito.when(this.userRepo.findById(any())).thenReturn(Optional.of(usr));
        //Mockito.when(this.service.isUserAdminLoginable(usr)).thenReturn(true);
        Mockito.when(this.userRepo.existsById(any())).thenReturn(true);


        var got = service.loginAdmin(usr2);

        Assertions.assertTrue(got);


        //when admin but logged in already
        var loggedInAdminUser = new LoggedInUser("admin","privileged");
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of(loggedInAdminUser));

        got = service.loginAdmin(usr);
        Assertions.assertFalse(got);


        //when incorrect creds
        var newUser = new UserEntity("admin","notadmin");
        loggedInAdminUser.setUserType("slave");
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of());
        got = service.loginAdmin(newUser);
        Assertions.assertFalse(got);
    }

    @Test
    void isSignedInShouldReturnFalse() {
        Mockito.when(
                this.loginRepo.findAll()
        ).thenReturn(List.of());

        var got = this.service.isSignedIn();

        Assertions.assertFalse(got);
    }

    @Test
    void isSignedInShouldReturnTrue() {
        var loggedInAdminUser = new LoggedInUser("admin","privileged");

        Mockito.when(
                this.loginRepo.findAll()
        ).thenReturn(List.of(loggedInAdminUser));

        var got = this.service.isSignedIn();

        Assertions.assertTrue(got);
    }


    @Test
    void getSignedInUserName() {
        LoggedInUser usr = new LoggedInUser("admin", "privileged");
        usr.setUserType("privileged");
        usr.setName("admin");
        //when logged in
        var expUserName = usr.getName();
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of(usr));
        var got = service.getSignedInUserName();
        Assertions.assertEquals(expUserName, got);

        //When logged out
        Mockito.when(this.loginRepo.findAll()).thenReturn(List.of());

        got = service.getSignedInUserName();
        Assertions.assertEquals("",got);
    }

    @Test
    void isAdmin() {
        Mockito.when(loginRepo.count()).thenReturn(1L);
        LoggedInUser usr = new LoggedInUser("admin", "privileged");
        Mockito.when(loginRepo.findAll()).thenReturn(List.of(usr));
        //when admin admin
        var got = this.service.isAdmin();
        Assertions.assertTrue(got);

        //when not admin but logged in
        usr = new LoggedInUser("notAdmin", "slave");
        Mockito.when(loginRepo.findAll()).thenReturn(List.of(usr));
        got = this.service.isAdmin();
        Assertions.assertFalse(got);

        //when logged out
        Mockito.when(loginRepo.count()).thenReturn(0L);
        got = this.service.isAdmin();
        Assertions.assertFalse(got);


    }

    @Test
    void CreateAdmin() {
        //when admin not yet created
        var usr = new UserEntity("admin", "admin");
        var got = this.service.createAdmin();
        Assertions.assertTrue(got);

        //when admin already created
        Mockito.when(userRepo.existsById(any())).thenReturn(true);
        got = this.service.createAdmin();
        Assertions.assertFalse(got);

    }

}
