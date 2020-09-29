package com.tul.instagram.controller;

import com.tul.instagram.service.IOService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;

@RestController
@RequestMapping("/api/IO")
public class IOController {

    IOService ioService;

    public IOController(IOService ioService) {
        this.ioService = ioService;
    }

    @GetMapping(
            value = "/download",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<BufferedOutputStream> downloadImg(@RequestBody String imgUrl) {
        ResponseEntity<BufferedOutputStream> responseEntity = ioService.downloadImg(imgUrl);
        return responseEntity;
    }
}
