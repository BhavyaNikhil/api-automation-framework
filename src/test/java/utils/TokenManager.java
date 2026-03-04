package utils;

import api.GetAccessTokenAPI;
import io.restassured.response.Response;

public class TokenManager {

    private static ThreadLocal<String> accessToken = new ThreadLocal<>(); //for making parallel execution safer
    private static ThreadLocal<Long> expiryTime = new ThreadLocal<>();
    public static synchronized String getToken() {

        if (accessToken == null || System.currentTimeMillis() >= (expiryTime.get() != null ? expiryTime.get() : 0)) {
            Response response = GetAccessTokenAPI.getAccessToken();
            accessToken.set(response.jsonPath().getString("access_token"));
            Integer expiresIn = response.jsonPath().getInt("expires_in");//handles token expiry
            if(expiresIn==null){
                throw new RuntimeException("expires_in not found in token response");
            }
            expiryTime.set(System.currentTimeMillis() + (expiresIn * 1000));
        }
        return accessToken.get();
    }
}
/*Reusable Token Storage
Initially I implemented synchronized token refresh to prevent race conditions.
For parallel execution scalability, I upgraded it using ThreadLocal to isolate token per thread.
 */