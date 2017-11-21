package com.dubatovka.app.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class provides form input parameters sent with requests and other values validation service.
 *
 * @author Sasnouskikh Aliaksandr
 */
public class FormValidator {

    /**
     * Validation constants.
     */
    private static final int    MAX_EMAIL_LENGTH        = 320;
    private static final int    MAX_EMAIL_NAME_LENGTH   = 64;
    private static final int    MAX_EMAIL_DOMAIN_LENGTH = 255;
    private static final String EMAIL_SPLITERATOR       = "@";
    private static final int    EMAIL_PAIR_LENGTH       = 2;
    private static final int    MAX_QUESTION_LENGTH     = 64;
    private static final int    MAX_ANSWER_LENGTH       = 32;
    private static final int    MAX_SUPPORT_LENGTH      = 700;
    private static final int    MAX_NEWS_HEADER_LENGTH  = 45;
    private static final int    MAX_NEWS_TEXT_LENGTH    = 700;
    /**
     * Validation regular expressions.
     */
    private static final String EMAIL_REGEX             = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
                                                          "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String PASSWORD_REGEX          = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[\\w_-]{8,}$";
    private static final String NAME_REGEX              = "[A-Za-z ]{1,70}";
    private static final String PASSPORT_REGEX          = "\\w{1,30}";
    private static final String AMOUNT_REGEX            = "^[0-9]{1,7}\\.?[0-9]{0,2}$";
    private static final String DATE_MONTH_REGEX        = "^[12][0-9]{3}\\-((0[1-9])|(1[0-2]))$";
    private static final String NUMBER_RANGE_REGEX      = "^\\d+\\D+\\d+$";

    /**
     * Private constructor to forbid create {@link FormValidator} instances.
     */
    private FormValidator() {
    }

    /**
     * Validates user e-mail.
     *
     * @param email user e-mail
     * @return true if e-mail valid
     */
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()
            || email.length() > MAX_EMAIL_LENGTH
            || !matchPattern(email, EMAIL_REGEX)) {
            return false;
        }
        String[] emailPair = email.split(EMAIL_SPLITERATOR);
        if (emailPair.length != EMAIL_PAIR_LENGTH) {
            return false;
        }
        String name   = emailPair[0];
        String domain = emailPair[1];
        return name.length() <= MAX_EMAIL_NAME_LENGTH
               && domain.length() <= MAX_EMAIL_DOMAIN_LENGTH;
    }

    /**
     * Validates user password.
     *
     * @param password user password
     * @return true if password valid
     */
    public static boolean validatePassword(String password) {
        return password != null && !password.trim().isEmpty() && matchPattern(password, PASSWORD_REGEX);
    }

    /**
     * Validates user password and compares it with password entered again.
     *
     * @param password      user password
     * @param passwordAgain user password entered again
     * @return true if password valid and matches to entered again
     */
    public static boolean validatePassword(String password, String passwordAgain) {
        return !(password == null || password.trim().isEmpty() || !password.equals(passwordAgain))
               && validatePassword(password);
    }

    /**
     * Validates user name.
     *
     * @param name user name
     * @return true if name is valid
     */
    public static boolean validateName(String name) {
        return name == null || name.trim().isEmpty() || matchPattern(name, NAME_REGEX);
    }

    /**
     * Validates user secret question.
     *
     * @param question user secret question
     * @return true if question is valid
     */
    public static boolean validateQuestion(String question) {
        return question == null || question.trim().isEmpty() || question.trim().length() <= MAX_QUESTION_LENGTH;
    }

    /**
     * Validates user answer to secret question.
     *
     * @param answer user answer to secret question
     * @return true if answer is valid
     */
    public static boolean validateAnswer(String answer) {
        return answer == null || answer.trim().isEmpty() || answer.trim().length() <= MAX_ANSWER_LENGTH;
    }

    /**
     * Validates user birthdate.
     *
     * @param birthdate user birthdate
     * @return true if birthdate is valid
     */
    public static boolean validateBirthdate(String birthdate) {
        if (birthdate == null || birthdate.trim().isEmpty()) {
            return false;
        }

        LocalDate date;
        try {
            date = LocalDate.parse(birthdate);
        } catch (DateTimeParseException e) {
            return false;
        }
        LocalDate now = LocalDate.now();
        return date.plusYears(18).isBefore(now) || date.plusYears(18).isEqual(now);

    }

    /**
     * Validates user passport number.
     *
     * @param passport user passport number
     * @return true if passport number is valid
     */
    public static boolean validatePassport(String passport) {
        return passport != null && matchPattern(passport, PASSPORT_REGEX);
    }

    
    private static boolean matchPattern(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}