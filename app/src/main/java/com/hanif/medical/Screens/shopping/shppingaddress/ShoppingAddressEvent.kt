package com.hanif.medical.Screens.shopping.shppingaddress

sealed class ShoppingAddressEvent {

    data class FullName(val fullName: String) : ShoppingAddressEvent()
    data class Mobile(val Mobile: String) : ShoppingAddressEvent()
    data class FlatHouse(val FlatHouse: String) : ShoppingAddressEvent()
    data class AreaStreet(val AreaStreet: String) : ShoppingAddressEvent()
    data class Landmark(val Landmark: String) : ShoppingAddressEvent()
    data class PinCode(val PinCode: String) : ShoppingAddressEvent()
    data class TownCity(val TownCity: String) : ShoppingAddressEvent()
    data class State(val State: String) : ShoppingAddressEvent()
    data class Country(val country: String) : ShoppingAddressEvent()
    data class PaymentOption(val PaymentOption: String) : ShoppingAddressEvent()
    object OnSubmitClick : ShoppingAddressEvent()
    object OnPopBackstack : ShoppingAddressEvent()
}
