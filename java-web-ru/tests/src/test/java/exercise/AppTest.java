package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void createValidUser() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Tom")
                .field("lastName", "Hanks")
                .field("email", "tom@yahoo.com")
                .field("password", "12345")
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);

        User targetUser = new QUser()
                .lastName.equalTo("Hanks")
                .findOne();

        assertThat(targetUser).isNotNull();
        assertThat(targetUser.getFirstName()).isEqualTo("Tom");
        assertThat(targetUser.getLastName()).isEqualTo("Hanks");
        assertThat(targetUser.getEmail()).isEqualTo("tom@yahoo.com");
        assertThat(targetUser.getPassword()).isEqualTo("12345");
    }

    void testCreateInvalidUser() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "")
                .field("lastName", "Hanks")
                .field("email", "tomyahoo.com")
                .field("password", "12")
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User targetUser = new QUser()
                .lastName.equalTo("Hanks")
                .findOne();

        assertThat(targetUser).isNull();

        String content = response.getBody();

        assertThat(content).contains("Имя не должно быть пустым");
        assertThat(content).contains("Должно быть валидным email");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}
