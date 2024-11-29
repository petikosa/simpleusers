package petikosa.general;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<ValidatePassword, String> {

    @Override
    public void initialize(ValidatePassword password) {
    }

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext cxt) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!~?@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
