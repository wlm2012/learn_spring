package com.test.study.util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    public static void main(String[] args) {
        Logger logger=LogManager.getLogger();
        logger.trace("trace level");  
        logger.debug("debug level");  
        logger.info("info level");  
        logger.warn("warn level");  
        logger.error("error level");  
        logger.fatal("fatal level");

        org.slf4j.Logger log= LoggerFactory.getLogger(Log.class);
        log.trace("trace level");
        log.debug("debug level");
        log.info("info level");
        log.warn("warn level");
        log.error("error level");

    }

}