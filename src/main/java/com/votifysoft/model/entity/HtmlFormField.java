package com.votifysoft.model.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlFormField {

    String labelFor() default "";

    String label() default "";

    String id() default "";

    String name() default "";

    HtmlFormFieldType type() default HtmlFormFieldType.TEXT;

    boolean required() default false;


}
