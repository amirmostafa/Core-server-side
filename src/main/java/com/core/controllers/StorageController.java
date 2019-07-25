package com.core.controllers;


import com.core.models.BasicModel;
import com.core.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/storage")
public class StorageController extends AbstractController {


    @Autowired
    StorageService storageService;


    @PostMapping("/uploadImage")
    @ResponseBody
    public ResponseEntity<BasicModel> handleFileUpload(@RequestParam("file") MultipartFile file) {
        return handle( storageService.store(file));
    }

}
