package test.java;

import main.java.interview.Application;
import main.java.interview.User;
import main.java.interview.UserController;
import main.java.interview.UserRepository;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.GregorianCalendar;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootJPAIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void init(){
        userRepository.save(new User("Luisa", "Lane",
                new GregorianCalendar(1995, 12, 31).getTime()));
    }

    @AfterAll
    public void teardown(){
        userRepository.deleteAll();
    }

    @Test
    public void testAddUser() {
        User user = userRepository.save(new User("Clark", "Kent",
                new GregorianCalendar(1990, 12, 31).getTime()));
        Optional<User> foundUser = userRepository.findById(Integer.valueOf(user.getId()));
        assertNotNull(foundUser);
        assertEquals(user.getFirstName(), foundUser.get().getFirstName());
    }

    @Test
    public void testUpdateUser() {
        Optional<User> foundUser = userRepository.findById(Integer.valueOf(1));
        foundUser.get().setFirstName("Mary");
        userRepository.save(foundUser.get());
        assertEquals("Mary", userRepository.findById(Integer.valueOf(1)).get().getFirstName());
    }

    @Test
    public void testDeleteUser() {
        Optional<User> foundUser = userRepository.findById(Integer.valueOf(1));
        assertEquals("ACTIVE",foundUser.get().getStatus());
        userController.delete(1);
        foundUser = userRepository.findById(Integer.valueOf(1));
        assertEquals("INACTIVE",foundUser.get().getStatus());
    }

    @Test
    public void testListAll() {
        userRepository.save(new User("Lex", "Luthor",
                new GregorianCalendar(1980, 12, 31).getTime()));

        //There is one user and  Lex just added. We have two users
        assertEquals(2,userRepository.findAll().size());

        //Lets disable Lex (status="INACTIVE")
        userController.delete(2);

        //We still have 2 users (1 active, 1 inactive)
        assertEquals(2,userRepository.findAll().size());

        //UserController lists only active users so we have only one active user.
        assertEquals(1,userController.list().size());

    }
}
