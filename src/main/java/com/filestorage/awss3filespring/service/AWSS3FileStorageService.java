package com.filestorage.awss3filespring.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3FileStorageService {

    /**
     * @param file MultipartFile file
     */
    void uploadFile(MultipartFile file);


    /**
     * Delete file from S3Bucket
     * @param fileName file name
     */
    void deleteFile(String fileName);
}
