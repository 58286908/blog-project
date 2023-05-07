package com.blog.annotation;

import com.blog.filter.BlogLogsFilter;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BlogLogsFilter.class)
public @interface BlogLogs {
    boolean output() default true;

    String message () default "成功";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
