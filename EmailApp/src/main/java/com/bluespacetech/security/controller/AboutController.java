package com.bluespacetech.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class AboutController.
 * 
 * @author Sudhanshu Tripathy created date 28-May-2017
 */
@RestController
@CrossOrigin
@RequestMapping("/about")
@PropertySource("classpath:application.properties")
public class AboutController
{

    /** The app version. */
    @Value("${app.version}")
    private String appVersion;

    /** The release date. */
    @Value("${app.releaseDate}")
    private String releaseDate;

    /**
     * Gets the application version.
     *
     * @param response
     *            the response
     * @return the application version
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @GetMapping(value = "/getAppVersion")
    public void getApplicationVersion(HttpServletResponse response) throws IOException
    {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(
                "\"response\":{\"appVersion\":\"" + appVersion + "\",\"releaseDate\":\"" + releaseDate + "\"}");
    }
}
