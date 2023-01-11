package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.DB;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

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
        String body = ctx.body();

//        BodyValidator<User> firstNameValidator = ctx.bodyValidator(User.class)
//                .check(fn -> !fn.getFirstName().isEmpty(),
//                        "First name couldn't be empty");
//
//        BodyValidator<User> lastNameValidator = ctx.bodyValidator(User.class)
//                .check(ln -> !ln.getLastName().isEmpty(),
//                        "Last name couldn't be empty");
//
//        BodyValidator<User> emailValidator = ctx.bodyValidator(User.class)
//                .check(em -> EmailValidator.getInstance().isValid(em.getEmail()),
//                        "Email is not valid");
//
//        BodyValidator<User> passwordValidator = ctx.bodyValidator(User.class)
//                .check(pwd -> pwd.getPassword().length() >= 4,
//                        "Password length should be not less than 4 symbols");

        
        User user = DB.json().toBean(User.class, body);
        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User updatedUser = DB.json().toBean(User.class, body);


        new QUser()
                .id.equalTo(Integer.parseInt(id))
                .asUpdate()
                .set("firstName", updatedUser.getFirstName())
                .set("lastName", updatedUser.getLastName())
                .set("email", updatedUser.getEmail())
                .set("password", updatedUser.getPassword())
                .update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.equalTo(Integer.parseInt(id))
                .delete();
        // END
    };
}
