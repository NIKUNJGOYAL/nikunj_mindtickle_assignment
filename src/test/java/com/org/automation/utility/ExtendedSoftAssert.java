package com.org.automation.utility;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class ExtendedSoftAssert extends SoftAssert {
    private LogController logger = new LogController(ExtendedSoftAssert.class);

    @Override
    public synchronized void onAssertSuccess(IAssert<?> assertCommand) {
        if (assertCommand.getMessage() == null) {
            String suffix =
                    String.format("Actual Result : %s and Expected Result : %s",
                            assertCommand.getActual() == null ? "null" : assertCommand.getActual().toString(),
                            assertCommand.getExpected() == null ? "null" : assertCommand.getExpected().toString());
            logger.passed("Assertion [PASSED] : " + suffix);
        } else {
            String suffix =
                    String.format("Actual Result : %s and Expected Result : %s",
                            assertCommand.getActual() == null ? "null" : assertCommand.getActual().toString(),
                            assertCommand.getExpected() == null ? "null" : assertCommand.getExpected().toString());
            logger.passed("Assertion [PASSED] : " + suffix + " Message : " + assertCommand.getMessage());
        }
    }

    @Override
    public synchronized void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        if (assertCommand.getMessage() == null) {
            String suffix =
                    String.format(
                            "Expected [%s] but found [%s]",
                            assertCommand.getExpected() == null ? "null" : assertCommand.getExpected().toString(),
                            assertCommand.getActual() == null ? "null" : assertCommand.getActual().toString());
            logger.fail("Assertion [FAILED] : " + suffix);
        } else {
            String suffix =
                    String.format(
                            "Expected [%s] but found [%s]",
                            assertCommand.getExpected() == null ? "null" : assertCommand.getExpected().toString(),
                            assertCommand.getActual() == null ? "null" : assertCommand.getActual().toString());
            logger.fail("Assertion [FAILED] : " + suffix + " Message : " + assertCommand.getMessage());
        }
    }
}
