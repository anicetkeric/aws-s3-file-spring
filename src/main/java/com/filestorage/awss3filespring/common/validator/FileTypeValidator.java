package com.filestorage.awss3filespring.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.filestorage.awss3filespring.common.FileUtils;
import com.filestorage.awss3filespring.common.annotation.ValidFileType;
import org.springframework.web.multipart.MultipartFile;

public class FileTypeValidator implements ConstraintValidator<ValidFileType, MultipartFile> {

    private String message;

    @Override
    public void initialize(ValidFileType constraint) {
        this.message = constraint.message();

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        if (!FileUtils.isValid(multipartFile)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    message)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}