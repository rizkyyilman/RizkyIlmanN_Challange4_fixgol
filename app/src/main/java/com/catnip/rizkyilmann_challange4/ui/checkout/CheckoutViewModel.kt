package com.catnip.rizkyilmann_challange4.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.rizkyilmann_challange4.data.repository.CartRepository
import kotlinx.coroutines.Dispatchers

class CheckoutViewModel(private val cartRepository: CartRepository) : ViewModel() {
    val cartList = cartRepository.getUserCardData().asLiveData(Dispatchers.IO)
}
