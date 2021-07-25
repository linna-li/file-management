package com.linna.assignment.file.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linna.assignment.file.model.FileData;
import com.linna.assignment.file.repository.FileRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
/*
* 1. Upload a file
ex. POST /files/<name>, Content-Type: multipart/form-data
2. Delete a file
ex. DELETE /files/<name>
3. List uploaded files (if a file is uploaded then deleted it should not be listed)
ex. GET /files may return a list of files: [file1.txt, file2.txt, ..]
* */
public class FileController {
    @Autowired
    FileRepository fileRepository;

    @GetMapping("/files")
    public ResponseEntity<List<FileData>> getAllFiles() {
        try {
            final List<FileData> fileData = fileRepository.findAll();
            return new ResponseEntity<>(fileData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/file", headers = "content-type=multipart/*")
    public ResponseEntity<FileData> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileData fileData = new FileData(fileName, file.getBytes());
            return new ResponseEntity<>(fileRepository.save(fileData), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/file/{id}")
    public ResponseEntity<Long> deleteFile(@PathVariable Long id) {
        try {
            fileRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<FileData> getFileMetaDataById(@PathVariable long id) {
        final Optional<FileData> fileMetaData = fileRepository.findById(id);
        return fileMetaData.map(metaData -> new ResponseEntity<>(metaData, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
