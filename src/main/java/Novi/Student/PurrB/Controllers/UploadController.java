package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.FileDto;
import Novi.Student.PurrB.Models.FileDocument;
import Novi.Student.PurrB.Services.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Random;

@CrossOrigin
@RestController
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public FileDto singleFileUpload(@RequestParam( value = "file") MultipartFile file, @RequestHeader("Authorization") String authHeader ) throws IOException {
        String fileName = System.currentTimeMillis() + "-" + new Random().nextInt(1000) + "-" + file.getOriginalFilename();
        FileDocument fileDocument = uploadService.uploadFile(fileName, file, authHeader);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
        String contentType = file.getContentType();
        return new FileDto(fileDocument.getFileName(), contentType, url);
    }

    @GetMapping("/download/{file_id}")
    ResponseEntity<byte[]> singleFileDownload(@PathVariable Long file_id, HttpServletRequest request, @RequestHeader("Authorization") String authHeader ) {
        return uploadService.downloadFile(file_id, request, authHeader);
    }

}