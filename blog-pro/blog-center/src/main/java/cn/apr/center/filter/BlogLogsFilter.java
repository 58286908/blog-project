package cn.apr.center.filter;

import cn.apr.center.annotation.BlogLogs;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class BlogLogsFilter implements ConstraintValidator<BlogLogs,Object> {

    private boolean output;

    @Override
    public void initialize(BlogLogs constraintAnnotation) {
        output = constraintAnnotation.output();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        log.info(object.toString());
        return true;
    }


}
