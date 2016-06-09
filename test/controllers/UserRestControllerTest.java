package controllers;

import controllers.routes;
import models.Role;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.db.Database;
import play.db.Databases;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.route;


/**
 * JUnit tests for REST Controller
 */
public class UserRestControllerTest extends WithApplication {


    private Database database;

    @Before
    public void setUp() {
        database = Databases.createFrom(
                "org.h2.Driver",
                "jdbc:h2:mem:play"
        );
        User user = new User();
        user.setName("New Name");
        user.setLogin("New Login");
        user.setPassword("New Password");

        Role role = new Role();
        role.setName("ROLE_NAME");

        Set<Role> roles = new HashSet<Role>() {{
            add(role);
        }};

        user.setRoles(roles);

        user.save();
    }

    @After
    public void shutDown() {
        database.shutdown();
    }


    @Test
    public void get() {
        Result result = route(routes.UserRestController.get(1));
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
    }
}
