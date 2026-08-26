package Students_doc;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;

public class AuthUtil {

    // Hold the token + expiry in memory
    private static String accessToken = null;
    private static long tokenExpiry = 0;

    // Call this before making any API call
    public static String getValidToken() {
        long currentTime = Instant.now().getEpochSecond();

        // If token is missing or expired --> generate a new one
        if (accessToken == null || currentTime >= tokenExpiry) {
           // System.out.println("Fetching new token...");

            String authBody =
                    "{\n" +
                    "  \"userCode\": \"\",\n" +
                    "  \"userName\": \"2004210\",\n" +
                    "  \"password\": \"29Jun1999\"\n" +
                    "}";

            Response res = RestAssured
                    .given()
                        .baseUri("https://docs2.ksp-recruitment.in/")
                        .contentType(ContentType.JSON)
                        .body(authBody)
                    .when()
                        .post("/api/Account/authenticate");

            String token = res.jsonPath().getString("data.jwToken");

            // Store token and expiry (valid for 1 hour)
            accessToken = token;
            tokenExpiry = currentTime + 3600;   // 3600 = 1 hour in seconds

           // System.out.println("New token stored = " + token);
        } else {
           // System.out.println("Using existing token = " + accessToken);
        }

        return accessToken;
    }
}
