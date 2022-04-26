package chess.controller;

import static org.hamcrest.Matchers.containsString;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ChessControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void showLobby() {
        RestAssured.given().log().all()
                .when().get("/")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.HTML);
    }

    @Test
    void testRedirectWhenCreateGame() {

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("name", "test")
                .formParam("password", "1234")
                .log().all()
                .when().post("/chess/new")
                .then().log().all()
                .statusCode(HttpStatus.FOUND.value())
                .headers("Location", containsString("chess/game"));
    }
}
