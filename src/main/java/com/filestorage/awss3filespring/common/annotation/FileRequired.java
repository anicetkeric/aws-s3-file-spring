package com.filestorage.awss3filespring.common.annotation;

import com.filestorage.awss3filespring.common.validator.FileRequiredValidator;

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
@Constraint(validatedBy = FileRequiredValidator.class)
public @interface FileRequired {

    String message() default "Cannot upload empty file";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)

    @Documented
    @interface List {
        FileRequired[] value();
    }
}
