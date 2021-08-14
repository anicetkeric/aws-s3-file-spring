package com.filestorage.awss3filespring.configuration;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.protocol.Protocol;
import com.amazonaws.services.apigateway.model.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AwsS3Config {

    private final AwsConfig awsConfig;


    @Bean
    public AmazonS3 getAmazonS3Client() {
        //final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        AWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());

        // Get AmazonS3 client and return the s3Client object.
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(awsConfig.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();


    }


}
