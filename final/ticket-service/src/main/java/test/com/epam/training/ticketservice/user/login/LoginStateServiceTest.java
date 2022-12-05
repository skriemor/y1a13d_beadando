package test.com.epam.training.ticketservice.user.login; 

import com.epam.training.ticketservice.user.entity.UserEntity;
import com.epam.training.ticketservice.user.login.LoginStateService;
import com.epam.training.ticketservice.user.repository.LoggedInUserRepository;
import com.epam.training.ticketservice.user.repository.UserEntityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.junit.Assert.assertEquals;

/** 
* LoginStateService Tester. 
* 
* @author Szilard
* @since <pre>Dec 5, 2022</pre> 
* @version 1.0 
*/
public class LoginStateServiceTest {
    @Mock
    UserEntityRepository userRepo;
    @Mock
    LoggedInUserRepository loginRepo;
    @InjectMocks
    LoginStateService service;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testGetLoggedInUserType() throws Exception {
        String userName = "admin";
        String userPassword = "admin";
        String expected = "Signed in with privileged account 'admin'";

        this.service.signOutUser();
        this.service.loginAdmin(new UserEntity(userName, userPassword));

        String got = this.service.getLoggedInUserType();
        assertEquals(expected, got);
    }

    @Test
    public void testIsSignedIn() throws Exception {
        this.service.signOutUser();
        String username = "admin";
        String pw = "admin";

        this.service.loginAdmin(new UserEntity(username, pw));
        assertEquals(
                this.service.getSignedInUserName(),
                username
        );
    }

    @Test
    public void testSignOutUser() throws Exception {
        this.service.signOutUser();

        assertEquals(this.loginRepo.count(),0);
    }

    @Test
    public void testGetSignedInUserName() throws Exception {
        this.service.signOutUser();
        String username = "admin";
        String pw = "admin";

        this.service.loginAdmin(new UserEntity(username, pw));
        assertEquals(
                this.service.getSignedInUserName(),
                username
        );
    }

    @Test
    public void testIsAdmin() throws Exception {
        this.service.signOutUser();
        String username = "admin";
        String pw = "admin";

        this.service.loginAdmin(new UserEntity(username, pw));
        assertEquals(
                this.service.getLoggedInUserType(),
                "privileged"
        );
    }

    @Test
    public void testCreateAdmin() throws Exception {
        this.service.signOutUser();
        String username = "admin";
        String pw = "admin";

        Boolean success = this.service.loginAdmin(new UserEntity(username, pw));
        assertEquals(
                success,
                true
        );
    }

} 
