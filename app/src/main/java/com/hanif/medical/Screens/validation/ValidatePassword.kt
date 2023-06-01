package com.hanif.medical.Screens.validation

import com.hanif.medical.Screens.doctor.ValidationResult
import java.util.regex.Pattern

class ValidatePassword {

    fun execute(password: String, isRegister: Boolean): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false, errorMessage = "Please enter password"
            )
        }
        if (password.length < 8 || password.length > 16) {
            return ValidationResult(
                successful = false, errorMessage = "Please enter password between 8 to 16 character"
            )
        }
        if (isRegister) {
            val containsLetterAndDigits = RegexUtils.isValidPassword(password)
            if (!containsLetterAndDigits) {
                return ValidationResult(
                    successful = false, errorMessage = "password should be 1 uppercase, 1 lowercase, 1 special character and at-least minimum 8 characters"
                )
            }
        }
        return ValidationResult(successful = true)
    }

}


object RegexUtils {
    private val NAME_PATTERN = Pattern.compile("^[\\p{L} .'-]+$", Pattern.CASE_INSENSITIVE)

    private val PASSWORD_PATTERN =
        Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,16}" +             //at least 8 characters
                    "$"
        )

    private val PACKAGE_NAME =
        Pattern.compile("^(?=.*[a-zA-Z])[A-Za-z0-9]", Pattern.CASE_INSENSITIVE)

    fun isValidName(name: String): Boolean {
        return NAME_PATTERN.matcher(name).find()
    }

    fun isValidPassword(password: String): Boolean {
        return PASSWORD_PATTERN.matcher(password).matches()
    }

    fun isValidPackageName(name: String): Boolean {
        return PACKAGE_NAME.matcher(name).find()
    }
}
