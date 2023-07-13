package com.org.automation.utility;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogController {
    private static Logger sLog4JLogger;
    private static org.testng.log4testng.Logger sTestNGLogger;

    private Level logLevel;

    public <T> LogController(Class<T> clazz) {

        sLog4JLogger = LogManager.getLogger(clazz);
        sTestNGLogger = org.testng.log4testng.Logger.getLogger(clazz);
        logLevel = Level.INFO;
    }

    public void info(String message){

        logLevel = Level.INFO;
        if ((logLevel.equals(Level.TRACE)) || (logLevel.equals(Level.DEBUG)) || (logLevel.equals(Level.INFO))) {
            sLog4JLogger.info(message);
            sTestNGLogger.info(message);
        }
    }

    public void fail(String message) {
        logLevel = sLog4JLogger.getLevel();
        sTestNGLogger.error(message);
    }

    public void passed(String message) {
        logLevel = sLog4JLogger.getLevel();
        sLog4JLogger.info(message);
    }

}
