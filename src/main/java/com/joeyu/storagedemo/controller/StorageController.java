package com.joeyu.storagedemo.controller;

import com.joeyu.storagedemo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/file/upload/{filename}")
    public ResponseEntity<String> create(@RequestParam("file") MultipartFile file, @PathVariable String filename) throws IOException {
        String url = storageService.putObject(file.getInputStream(), file.getSize(), file.getContentType(), filename);

        return ResponseEntity.ok(url);
    }
}
