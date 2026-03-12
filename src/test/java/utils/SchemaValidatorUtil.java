package utils;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidatorUtil {
    public static void validateSchema(Response response, String schemaPath) {
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }
}
