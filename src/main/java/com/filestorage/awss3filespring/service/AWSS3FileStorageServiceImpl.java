package com.filestorage.awss3filespring.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.filestorage.awss3filespring.configuration.AwsProperties;
import com.filestorage.awss3filespring.exception.FileStorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


@RequiredArgsConstructor
@Slf4j
@Service
public class AWSS3FileStorageServiceImpl implements AWSS3FileStorageService {

    private final AmazonS3 amazonS3;

    private final AwsProperties awsProperties;

    /**
     * {@inheritDoc}
     */
    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();

        try {
            //creating the file in the server (temporarily)
            File file = new File(Objects.requireNonNull(fileName));
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsProperties.getS3BucketName(), fileName, file);

            PutObjectResult response = amazonS3.putObject(putObjectRequest);
            //removing the file created in the server
            file.delete();

        } catch (IOException | AmazonServiceException ex) {
            log.error("error [" + ex.getMessage() + "] occurred while uploading file.");
            throw new FileStorageException("Could not upload file");
        }

        return fileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFile(String fileName) {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsProperties.getS3BucketName(), fileName));
        } catch (AmazonServiceException ex) {
            log.error("error [" + ex.getMessage() + "] occurred while removing [" + fileName + "] ");
            throw new FileStorageException("Error: Could not delete file");
        }
    }
}
