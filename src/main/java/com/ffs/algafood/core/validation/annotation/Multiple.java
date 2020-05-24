package com.ffs.algafood.core.validation.annotation;

import com.ffs.algafood.core.validation.annotation.validator.MultiploValidator;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author francisco
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class})
public @interface Multiple {

    String message() default "{api.validation.constraints.Multuple.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public int number();
}
