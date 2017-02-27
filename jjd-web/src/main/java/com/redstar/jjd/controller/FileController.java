package com.redstar.jjd.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Pengfei on 2015/12/22.
 */
@Controller
@RequestMapping("api/file")
public class FileController {
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> download(HttpSession session, @RequestBody String filePath) throws Exception {
        File file = new File(session.getServletContext().getRealPath("") + filePath);
        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            Path path = Paths.get(filePath);

            headers.setContentType(MediaType.valueOf(Files.probeContentType(path)));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        }
        return null;
    }
}
