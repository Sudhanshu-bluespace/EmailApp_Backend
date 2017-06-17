package com.bluespacetech;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * The Class EmailAppApplication.
 * 
 * @author Sudhanshu Tripathy
 */
@SpringBootApplication
public class EmailAppApplication extends SpringBootServletInitializer
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailAppApplication.class);

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args)
    {
        LOGGER.info("Starting application");
        SpringApplication.run(EmailAppApplication.class, args);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.
     * SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(EmailAppApplication.class);
    }

}
