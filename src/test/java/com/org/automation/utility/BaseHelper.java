package com.org.automation.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class BaseHelper {

    @BeforeSuite(alwaysRun = true)
    public void getBeforeSuiteHelper() throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/log4j.properties");
        properties.load(fileInputStream);
        PropertyConfigurator.configure(properties);
        BasicConfigurator.configure();
    }

}
