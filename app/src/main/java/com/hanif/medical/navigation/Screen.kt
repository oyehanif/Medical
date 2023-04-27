package com.hanif.medical.navigation

import com.hanif.medical.utils.FORGET_PASSWORD
import com.hanif.medical.utils.HOME
import com.hanif.medical.utils.LOGIN
import com.hanif.medical.utils.REGISTER
import com.hanif.medical.utils.WELCOME

sealed class Screen(val route :String){
    object Welcome :Screen( route = WELCOME)
    object Home :Screen( route = HOME   )
    object Login :Screen( route = LOGIN   )
    object Register :Screen( route = REGISTER   )
    object ForgetPassword :Screen( route = FORGET_PASSWORD   )
}
