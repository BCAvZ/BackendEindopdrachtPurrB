package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.CatDto;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.FileDocument;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Repositories.DocFileRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import Novi.Student.PurrB.Helpers.JwtUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {
    private final DocFileRepository docFileRepository;

    @Autowired
    private ClientRepository clientRepos;

    @Autowired
    private JwtUtils jwtUtils;

    public UploadService(DocFileRepository docFileRepository) {
        this.docFileRepository = docFileRepository;
    }

    public FileDocument uploadFile(String fileName, MultipartFile file, @RequestHeader("Authorization") String authHeader) throws IOException {

        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(fileName);
        fileDocument.setDocFile(file.getBytes());
        fileDocument.setInvoiceClient(c);

        return docFileRepository.save(fileDocument);
    }

    public ResponseEntity<byte[]> downloadFile(Long file_id, HttpServletRequest request, @RequestHeader("Authorization") String authHeader ) {
        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        List<FileDocument> fileDocuments = c.getFileDocuments();

        int file = (int) (file_id -1);

        FileDocument f = fileDocuments.get(file);

        String mimeType = request.getServletContext().getMimeType(f.getFileName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + f.getFileName()).body(f.getDocFile());
    }
}
