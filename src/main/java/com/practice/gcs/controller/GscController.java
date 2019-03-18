package com.practice.gcs.controller;

import com.practice.gcs.service.DocumentUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/gcs-service")
public class GscController {

    private DocumentUploadService documentUploadService;

    public GscController(DocumentUploadService documentUploadService) {
        this.documentUploadService = documentUploadService;
    }

    @PostMapping("/upload-file")
    public String updateKycDetails(@RequestBody MultipartFile multipartFile) {
        try {
            return documentUploadService.uploadFile(multipartFile);
        } catch (Exception e) {
            return "failure";
        }
    }


}
