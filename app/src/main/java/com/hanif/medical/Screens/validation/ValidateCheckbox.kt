package com.hanif.medical.Screens.validation

import com.hanif.medical.Screens.doctor.ValidationResult


class ValidateCheckbox {

    fun execute(isClick: Boolean): ValidationResult {
        if (!isClick) {
            return ValidationResult(
                successful = false,
                errorMessage = "please check the checkbox."
            )
        }
        return ValidationResult(successful = true)
    }
}