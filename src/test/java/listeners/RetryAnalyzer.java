package listeners;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);
    private int count = 0;
    private static final int MAX_RETRY = 1;
    @Override
    public boolean retry(ITestResult result) {
        if(count<MAX_RETRY){
            count++;
            log.warn("Retrying" + result.getName()+" -attempt "+ count);
            return true;
        }
        return false;
    }
}
