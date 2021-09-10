package com.filestorage.awss3filespring.configuration;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AwsS3Config {

    private final AwsProperties awsProperties;

    @Bean
    public AmazonS3 getAmazonS3Client() {
        AWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());

        // Get AmazonS3 client and return the s3Client object.
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(awsProperties.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
