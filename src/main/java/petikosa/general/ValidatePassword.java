package petikosa.general;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePassword {
    String message() default "Password should have at least 8 characters.\n" +
            "A digit must occur at least once\n" +
            "Alower case letter must occur at least once\n" +
            "An upper case letter must occur at least once\n" +
            "A special character !~?@#$%^&+= must occur at least once\n" +
            "No whitespace allowed in the entire string";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
