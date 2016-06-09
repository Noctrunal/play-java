package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;
import static validate.EntityValidator.validateUserNotNull;

/**
 * REST controller http://localhost:9000/users
 **/
public class UserRestController extends Controller {
    /**
     * save new user
     * HTTP POST - 201(created) or 400(bad request)
     **/
    @BodyParser.Of(BodyParser.Json.class)
    public Result save() {
        JsonNode json = request().body().asJson();

        User user = fromJson(json, User.class);

        if (validateUserNotNull(user)) {
            return badRequest("Missing parameter");
        }

        user.save();

        return created(toJson(user));
    }

    /**
     * get all users
     * HTTP GET - 200(ok) or 404(not found)
     **/
    public Result all() {
        List<User> users = User.find.all();

        if (users.isEmpty()) {
            return notFound("Users not found");
        }

        return ok(toJson(users));
    }

    /**
     * get user by id
     * HTTP GET - 200(ok) or 404(not found)
     **/
    public Result get(int id) {
        User user = User.find.byId(id);

        if (user == null) {
            return notFound("User not found");
        }

        return ok(toJson(user));
    }

    /**
     * update user by id
     * HTTP PUT - 200(ok) or 404(not found)
     **/
    @BodyParser.Of(BodyParser.Json.class)
    public Result update(int id) {
        User user = User.find.byId(id);

        if (user == null) {
            return notFound("User not found");
        }

        JsonNode json = request().body().asJson();

        user = fromJson(json, User.class);
        user.update();

        return ok();
    }

    /**
     * delete user by id
     * HTTP DELETE - 200(ok) or 404(not found)
     **/
    public Result delete(int id) {
        User user = User.find.byId(id);

        if (user == null) {
            return notFound("User not found");
        }

        user.delete();

        return ok();
    }
}
