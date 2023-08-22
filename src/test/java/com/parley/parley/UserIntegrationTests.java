package com.parley.parley;


import com.parley.parley.models.User;
import com.parley.parley.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParleyApplication.class)
@AutoConfigureMockMvc
public class UserIntegrationTests {
    private User testUser;

    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void addDeleteUser() throws Exception{
        testUser = userDao.findByUsername("testUser");

        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(true);
            testUser = userDao.save(newUser);
        }

        assertNotNull(testUser);

        userDao.delete(testUser);

        testUser = userDao.findByUsername("testUser");
        assertNull(testUser);
    }

    @Test
    public void updateUser(){
        testUser = userDao.findByUsername("testUser");

        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(true);
            testUser = userDao.save(newUser);
        }
        assertNotNull(testUser);
        assertNull(testUser.getBio());
        assertNull(testUser.getBanner_img());
        assertNull(testUser.getProfile_pic());

        testUser.setBio("New bio.");
        testUser.setBanner_img("Banner");
        testUser.setProfile_pic("Pic");
        assertNotNull(testUser.getBio());
        assertNotNull(testUser.getBanner_img());
        assertNotNull(testUser.getProfile_pic());
        assertEquals(testUser.getBio(), "New bio.");
        assertEquals(testUser.getBanner_img(), "Banner");
        assertEquals(testUser.getProfile_pic(), "Pic");

        assertTrue(testUser.is_admin());
        testUser.set_admin(false);
        assertFalse(testUser.is_admin());
    }

    @Test
    public void testUserGetByID(){
        testUser = userDao.findByUsername("testUser");

        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(true);
            testUser = userDao.save(newUser);
        }

        long id = testUser.getId();

        User checkUser = userDao.findUserById(id);

        boolean isEqual = testUser.getUsername().equals(checkUser.getUsername());
        assertTrue(isEqual);

    }

    @Test
    public void testGetUserByEmail(){
        testUser = userDao.findByUsername("testUser");

        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(true);
            testUser = userDao.save(newUser);
        }

        String email = testUser.getEmail();

        User checkUser = userDao.findByEmail(email);

        boolean isEqual = testUser.getUsername().equals(checkUser.getUsername());
        assertTrue(isEqual);
    }

    @Test
    public void testGetAllContainingUsername(){
        List<User> testUserList = userDao.findAllByUsernameContainingIgnoreCase("a");

        if(testUserList.size() == 0){
            User newUser = new User();
            newUser.setUsername("ad1");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(false);
            userDao.save(newUser);
            newUser.setUsername("ad2");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(false);
            userDao.save(newUser);
            testUserList = userDao.findAllByUsernameContainingIgnoreCase("a");
        }

        boolean usersFound = testUserList.size() > 0;

        for (User u :
                testUserList) {
            System.out.println("u = " + u.getUsername());
        }

        assertTrue(usersFound);
    }

    @Test
    public void testGetAllByEmailContainingIgnoreCase(){
        List<User> testUserList = userDao.findAllByEmailContainingIgnoreCase("codeup.com");

        if(testUserList.size() == 0){
            User newUser = new User();
            newUser.setUsername("ad1");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(false);
            userDao.save(newUser);
            newUser.setUsername("ad2");
            newUser.setPassword(passwordEncoder.encode("Password1!"));
            newUser.setEmail("testUser@codeup.com");
            newUser.set_admin(false);
            userDao.save(newUser);
            testUserList = userDao.findAllByEmailContainingIgnoreCase("codeup.com");
        }

        boolean usersFound = testUserList.size() > 0;

        for (User u :
                testUserList) {
            System.out.println("u = " + u.getUsername());
        }

        assertTrue(usersFound);
    }

}
