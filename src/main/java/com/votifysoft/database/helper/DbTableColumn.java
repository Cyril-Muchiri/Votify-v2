package com.votifysoft.database.helper;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTableColumn {

    String name();

    String definition() default "varchar(255)";

}
