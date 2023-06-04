package com.hanif.medical.Screens.validation

import com.hanif.medical.Screens.PhoneValuesForValidation
import com.hanif.medical.Screens.doctor.ValidationResult
import com.simon.xmaterialccp.data.utils.checkPhoneNumber

class ValidateContact1 {

    fun execute(contact: String, fieldResult: PhoneValuesForValidation): ValidationResult {
        if (contact.isBlank()) {
            return ValidationResult(
                successful = false, errorMessage = "Please Insert Phone Number"
            )
        }
        if (contact.length != 10) {
            return ValidationResult(
                successful = false, errorMessage = "Please Enter 20 digit Number"
            )
        }

        val checkPhoneNumber = checkPhoneNumber(
            phone = contact,
            fullPhoneNumber = "${fieldResult.phoneCode}$contact",
            countryCode = fieldResult.defaultLang
        )

        if (!checkPhoneNumber){
            return ValidationResult(
                successful = false
            )
        }

        return ValidationResult(successful = true)
    }
}