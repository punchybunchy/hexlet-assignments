package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.DB;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String jsonUsers = DB.json().toJson(users);
        ctx.json(jsonUsers);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String jsonUser = DB.json().toJson(user);
        ctx.json(jsonUser);
        // END
    };

    public void create(Context ctx) {

        // BEGIN

        User user = ctx.bodyValidator(User.class)
                .check(usr -> usr.getFirstName().length() > 0, "First name can not be empty")
                .check(usr -> usr.getLastName().length() > 0, "Last name can not be empty")
                .check(usr -> EmailValidator.getInstance().isValid(usr.getEmail()), "Should be valid email")
                .check(usr -> StringUtils.isNumeric(usr.getPassword()), "Password must contains only digits")
                .check(usr -> usr.getPassword().length() >= 4, "Password must contain at least 4 characters")
                .get();

        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User updatedUser = DB.json().toBean(User.class, body);
        updatedUser.setId(id);
        updatedUser.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.equalTo(Long.parseLong(id))
                .delete();
        // END
    };
}
