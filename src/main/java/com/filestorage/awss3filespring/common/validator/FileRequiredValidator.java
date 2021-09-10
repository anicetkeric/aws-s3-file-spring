package com.filestorage.awss3filespring.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.filestorage.awss3filespring.common.annotation.FileRequired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class FileRequiredValidator implements ConstraintValidator<FileRequired, MultipartFile> {
    @Override
    public void initialize(FileRequired constraint) {}

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile != null
                && !Objects.requireNonNull(multipartFile.getOriginalFilename()).isEmpty();
    }
}