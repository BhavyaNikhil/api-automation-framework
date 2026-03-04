package utils;

import io.restassured.response.Response;

import java.util.function.Supplier;

public class RetryUtil {
    public static Response executeWithRetry(Supplier<Response> request, int retries){
        Response response=null;
        for(int i=0;i<retries;i++){
            response=request.get();
            if(response.getStatusCode()==200){
                break;
            }
        }
        return response;
    }
}
//APIs fail intermittently in real world. This makes resilience.