//package utils.Listeners;
//
//import org.testng.IRetryAnalyzer;
//import org.testng.ITestResult;
//
//public class Retry implements IRetryAnalyzer{
//
//    int minRetryCount = 0;
//    int maxRetryCount = 1;
//
//
//
//    @Override
//    public boolean retry(ITestResult result) {
//
//        if (!result.isSuccess()) {
//            if (minRetryCount <= maxRetryCount) {
//
//                System.out.println("Following test failed: " + result.getName());
//                minRetryCount++;
//                result.setStatus(ITestResult.FAILURE);
//                return true;
//            } else {
//
//                result.setStatus(ITestResult.FAILURE);
//            }
//
//        } else {
//
//            result.setStatus(ITestResult.SUCCESS);
//        }
//
//        return false;
//
//    }
//}
