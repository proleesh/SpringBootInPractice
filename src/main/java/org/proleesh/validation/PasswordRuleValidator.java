package org.proleesh.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordRuleValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_COMPLEX_RULES = 2;
    private static final int MAX_REPETITIVE_CHAR = 3;
    private static final int MIN_SPECIAL_CASE_CHARS = 1;
    private static final int MIN_UPPER_CASE_CHARS = 1;
    private static final int MIN_LOWER_CASE_CHARS = 1;
    private static final int MIN_DIGIT_CASE_CHARS = 1;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(8, 30));
        CharacterCharacteristicsRule characteristicsRule =
                new CharacterCharacteristicsRule(MIN_COMPLEX_RULES,
                new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS));

        passwordRules.add(characteristicsRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHAR));
        PasswordValidator validator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(value);
        RuleResult ruleResult = validator.validate(passwordData);
        return ruleResult.isValid();
    }
}
