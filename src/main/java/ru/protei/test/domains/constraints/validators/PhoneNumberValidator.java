package ru.protei.test.domains.constraints.validators;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ru.protei.test.domains.constraints.PhoneNumber;

/**
 * Validates {@link java.lang.String} elements annotated with {@link
 * ru.protei.test.domains.constraints.PhoneNumber} annotations.
 *
 * @see ru.protei.test.domains.constraints.PhoneNumber
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

  private String defaultRegion;

  @Override
  public void initialize(final PhoneNumber constraintAnnotation) {
    this.defaultRegion = constraintAnnotation.defaultRegion();
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    final var phoneNumberUtil = PhoneNumberUtil.getInstance();
    try {
      final var phoneNumber = phoneNumberUtil.parse(value, defaultRegion);
      return phoneNumberUtil.isValidNumber(phoneNumber);
    } catch (NumberParseException e) {
      return false;
    }
  }
}
