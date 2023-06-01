package com.hanif.medical.Screens.validation


import android.util.Patterns
import com.hanif.medical.Screens.doctor.ValidationResult

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false, errorMessage = "Please Enter Email"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false, errorMessage = "Please Insert Valid Email"
            )
        }
        return ValidationResult(successful = true)
    }
}