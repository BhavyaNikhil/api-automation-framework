package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxRetry = 2;
    @Override
    public boolean retry(ITestResult result) {
        if(count < maxRetry){
            count++;
            return true;
        }
        return false;
    }
}

/* In real systems some APIs fail due to:
network issues
temporary server delay
token refresh
rate limits

Your framework already has RetryUtil for API calls.
Implemented TestNG RetryAnalyzer to rerun failed tests automatically,
reducing flaky failures in CI pipelines.
 */