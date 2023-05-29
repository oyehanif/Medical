package com.hanif.medical.Screens.validation

import com.hanif.medical.Screens.doctor.ValidationResult

class Validations {

    fun heightAndWeightValidation(value: String, errorName: String): ValidationResult {
        return if (value.isEmpty()) {
            ValidationResult(false, "please enter $errorName")
        } else if (value.length > 3) {
            ValidationResult(false, "please Valid $errorName")
        } else {
            ValidationResult(true)
        }
    }


    fun onlyEmptyCheck(value: String, errorName: String): ValidationResult {
        return if (value.isEmpty()) {
            ValidationResult(false, "please Select $errorName")
        } else {
            ValidationResult(true)
        }
    }
}