package com.hanif.medical.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanif.medical.ui.theme.RomanSilver
import com.simon.xmaterialccp.component.MaterialCountryCodePicker
import com.simon.xmaterialccp.data.ccpDefaultColors
import com.simon.xmaterialccp.data.utils.getDefaultLangCode
import com.simon.xmaterialccp.data.utils.getDefaultPhoneCode
import com.simon.xmaterialccp.data.utils.getLibCountries

//Country Code Picker
@Composable
fun selectCountryWithCountryCode(
    text: String = "",
    onValueChange: (String) -> Unit = {},
    isValidPhone :Boolean,
): PhoneValuesForValidation {
    val context = LocalContext.current
    var phoneCode by remember { mutableStateOf(getDefaultPhoneCode(context)) }
    var defaultLang by rememberSaveable { mutableStateOf(getDefaultLangCode(context)) }
    //val isValidPhone by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        MaterialCountryCodePicker(
            pickedCountry = {
                phoneCode = it.countryPhoneCode
                defaultLang = it.countryCode
            },
            defaultCountry = getLibCountries().single { it.countryCode == defaultLang },
            error = !isValidPhone,
            text = text,
            onValueChange = onValueChange,
            searchFieldPlaceHolderTextStyle = TextStyle(color = MaterialTheme.colors.secondary),
            searchFieldTextStyle = TextStyle(color = MaterialTheme.colors.onSecondary),
            countrytextstyle = TextStyle(color = MaterialTheme.colors.onSecondary),
            countrycodetextstyle = TextStyle(color = MaterialTheme.colors.onSecondary),
            phonehintnumbertextstyle = TextStyle(color = RomanSilver),
            showErrorText = true,
            showCountryCodeInDIalog = true,
            dialogcountrycodetextstyle = TextStyle(color = MaterialTheme.colors.onSecondary),
            showDropDownAfterFlag = true,
            textFieldShapeCornerRadiusInPercentage = 40,
            searchFieldShapeCornerRadiusInPercentage = 40,
            appbartitleStyle = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp
            ),
            countryItemBgShape = RoundedCornerShape(5.dp),
            showCountryFlag = true,
            showCountryCode = false,
            isEnabled = true,
            colors = ccpDefaultColors(
                primaryColor = MaterialTheme.colors.secondary,
                errorColor = MaterialTheme.colors.error,
                surfaceColor = MaterialTheme.colors.secondaryVariant,
                disabledOutlineColor = MaterialTheme.colors.secondaryVariant,
                unfocusedOutlineColor = MaterialTheme.colors.secondaryVariant,
                textColor = MaterialTheme.colors.secondary,
                cursorColor = RomanSilver,
                topAppBarColor = MaterialTheme.colors.background,
                countryItemBgColor = MaterialTheme.colors.secondaryVariant,
                dialogNavIconColor = MaterialTheme.colors.primary,
            )
        )
    }
    return PhoneValuesForValidation(phoneCode, defaultLang)
}

data class PhoneValuesForValidation(
    val phoneCode :String,
    val defaultLang :String,
)