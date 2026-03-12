package utils;

import api.GetJHSTenantAdminAccessTokenAPI;
import api.GetSuperUserAccessTokenAPI;
import io.restassured.response.Response;

import java.util.EnumMap;
import java.util.Map;

public class TokenManager {
    private static final Map<TokenType, TokenData> tokenStore = new EnumMap<>(TokenType.class);
    public static synchronized String getToken(TokenType tokenType) {
        TokenData tokenData = tokenStore.get(tokenType);
        if (tokenData == null || tokenData.isExpired()) {
            Response response = generateToken(tokenType);
            String accessToken =response.jsonPath().getString("access_token");
            Integer expiresIn = response.jsonPath().getInt("expires_in");//handles token expiry
            if (accessToken == null || expiresIn == null) {
                throw new RuntimeException("Invalid token response");
            }
            long expiryTime = System.currentTimeMillis() + (expiresIn*1000L);
            tokenData = new TokenData(accessToken, expiryTime);
            tokenStore.put(tokenType, tokenData);
        }
        return tokenData.getToken();
    }

    private static Response generateToken(TokenType tokenType){
        switch (tokenType){
            case SUPERUSER:
                return GetSuperUserAccessTokenAPI.getAccessTokenSuper();
            case JHSTENANTADMIN:
                return GetJHSTenantAdminAccessTokenAPI.getAccessToken();
            default:
                throw new RuntimeException("Unsupported Token Type" + tokenType);
        }
    }
}


/*Reusable Token Storage
Initially I implemented synchronized token refresh to prevent race conditions.
For parallel execution scalability, I upgraded it using ThreadLocal to isolate token per thread.
 */