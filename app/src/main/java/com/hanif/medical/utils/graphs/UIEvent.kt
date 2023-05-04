package com.hanif.medical.utils.graphs

sealed class UIEvent {

    object PopBackStack : UIEvent() //for back stack

    data class Navigate(val route: String) : UIEvent() //for navigate the screens

    data class ShowSnackbar(
        val message: String, val action: String? = null,
    ) : UIEvent() //for showing snackbar also some action are perform like: undo

    data class Preference(val user: String) : UIEvent()
}