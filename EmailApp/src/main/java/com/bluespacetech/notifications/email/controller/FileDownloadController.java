package com.bluespacetech.notifications.email.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.crypto.Decryptor;

/**
 * The Class FileDownloadController.
 */
@RestController
@RequestMapping(value = "/products")
public class FileDownloadController
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(FileDownloadController.class);

    /**
     * Download file.
     *
     * @param path the path
     * @param response the response
     * @return the response entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @GetMapping(value = "/downloadFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@RequestParam("path") String path, HttpServletResponse response)
            throws IOException
    {
        LOGGER.debug("Decrypted path : " + Decryptor.Decrypt(path));
        FileSystemResource resource = new FileSystemResource(Decryptor.Decrypt(path));
        LOGGER.debug("resource exists: " + resource.exists());

        File file = resource.getFile();
        String fileName = "";
        String[] splitName = file.getName().split("_");

        if (splitName.length == 3)
        {
            fileName = splitName[2];
        }
        else
        {
            fileName = file.getName();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        ByteArrayResource resourceBytes = new ByteArrayResource(Files.readAllBytes(file.toPath()));

        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/force-download")).body(resourceBytes);
    }
}
