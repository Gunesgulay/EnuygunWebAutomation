package com.enuygun.log;

import org.apache.log4j.PropertyConfigurator;

public class Log {

    public Log() {
        PropertyConfigurator
                .configure(Log.class.getClassLoader().getResource("log4j.properties"));
    }
}