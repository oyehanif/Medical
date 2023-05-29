package com.hanif.medical.Screens.validation

import com.hanif.medical.Screens.doctor.ValidationResult

class ValidateContact {

    fun execute(contact: String): ValidationResult {
        if (contact.isBlank()) {
            return ValidationResult(
                successful = false, errorMessage = "Please Insert Phone Number"
            )
        }
        if (contact.length != 10) {
            return ValidationResult(
                successful = false, errorMessage = "Please 10 digit phone number"
            )
        }
        return ValidationResult(successful = true)
    }
}