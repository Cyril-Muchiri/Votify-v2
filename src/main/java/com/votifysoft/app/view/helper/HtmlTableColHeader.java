package com.votifysoft.app.view.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlTableColHeader {

    String header();

    String dateFormat() default "";

    String numberFormat() default "";

}
