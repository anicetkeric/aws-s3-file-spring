package com.filestorage.awss3filespring.controller;

import com.filestorage.awss3filespring.common.annotation.FileMaxSize;
import com.filestorage.awss3filespring.common.annotation.FileRequired;
import com.filestorage.awss3filespring.common.annotation.ValidFileType;
import com.filestorage.awss3filespring.controller.responses.BaseResponse;
import com.filestorage.awss3filespring.controller.responses.FileResponse;
import com.filestorage.awss3filespring.service.AWSS3FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing File manager.
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Validated
public class FileResource {

    private final AWSS3FileStorageService awss3FileStorageService;

    public FileResource(AWSS3FileStorageService awss3FileStorageService) {
        this.awss3FileStorageService = awss3FileStorageService;
    }


    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<BaseResponse> uploadFile(@RequestParam("file")
                                               @FileRequired
                                               @FileMaxSize
                                               @ValidFileType MultipartFile file) {

        awss3FileStorageService.uploadFile(file);
        String fileDownloadUri = null;

        return new ResponseEntity<>(new BaseResponse(new FileResponse(file.getOriginalFilename(), fileDownloadUri, file.getContentType(), file.getSize()), "File uploaded with success!", false), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteFile(@RequestParam("file_name") String fileName)
    {
        awss3FileStorageService.deleteFile(fileName);

        return new ResponseEntity<>(new BaseResponse(null,"file [" + fileName + "] removing request submitted successfully.", false), HttpStatus.OK);
    }


    @PostMapping("/upload-files")
    @ResponseBody
    public List<ResponseEntity<BaseResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }
/*
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, @RequestParam("fileType") String fileType, @RequestParam("tenant") String tenant) {

        Resource resource = fileSytemStorage.loadFile(filename,fileType, tenant);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/

}
