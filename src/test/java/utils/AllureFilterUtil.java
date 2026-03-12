package utils;

import io.qameta.allure.Attachment;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureFilterUtil implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        attachRequest(requestSpec.getBody());
        Response response = ctx.next(requestSpec, responseSpec);
        attachResponse(response.asString());
        return response;
    }

    @Attachment(value = "Request", type = "application/json")
    public String attachRequest(Object request) {
        return request == null ? "No Request Body" : request.toString();
    }

    @Attachment(value = "Response", type = "application/json")
    public String attachResponse(String response) {
        return response;
    }
}