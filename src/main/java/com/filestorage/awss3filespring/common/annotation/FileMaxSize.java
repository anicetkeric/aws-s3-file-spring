package com.filestorage.awss3filespring.common.annotation;


import com.filestorage.awss3filespring.common.validator.FileMaxSizeValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileMaxSizeValidator.class)
public @interface FileMaxSize {
    String message() default "File size exceeds limit!";
    long value() default (1024 * 1024);

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)

    @Documented
    @interface List {
        FileMaxSize[] value();
    }
}