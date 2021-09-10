package com.filestorage.awss3filespring.common.annotation;

import com.filestorage.awss3filespring.common.validator.FileTypeValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileTypeValidator.class})
public @interface ValidFileType {
    String message() default "File not valid !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}