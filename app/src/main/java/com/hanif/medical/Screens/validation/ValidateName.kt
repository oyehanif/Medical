package com.hanif.medical.Screens.validation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.hanif.medical.Screens.doctor.ValidationResult
import java.util.regex.Pattern

class ValidateName {

    fun execute(name: String, containNumber: Boolean = false, errorName: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false, errorMessage = "please enter ".plus(errorName)
            )
        }
        if (!containNumber) {
            if (name.checkStringHaveNumber()) {
                return ValidationResult(
                    successful = false, errorMessage = "the given name is not valid".plus(errorName)
                )
            }
        }
        if (name.length > 15) {
            return ValidationResult(
                successful = false, errorMessage = "insert name below 15 character"
            )
        }
        return ValidationResult(successful = true)
    }
}




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


fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


fun String.checkStringHaveNumber(): Boolean {
    return Pattern.matches("[0-9]+", this)
}
