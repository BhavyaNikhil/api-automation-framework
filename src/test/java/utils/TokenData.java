package utils;

public class TokenData {
    private String token;
    private long expiryTime;
    public TokenData(String token, long expiryTime) {
        this.token = token;
        this.expiryTime = expiryTime;
    }
    public String getToken() {
        return token;
    }
    public long getExpiryTime() {
        return expiryTime;
    }
    public boolean isExpired() {
        return System.currentTimeMillis() >= expiryTime;
    }
}