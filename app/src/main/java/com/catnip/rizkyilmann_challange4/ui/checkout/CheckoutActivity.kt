package com.catnip.rizkyilmann_challange4.ui.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.catnip.rizkyilmann_challange4.R
import com.catnip.rizkyilmann_challange4.data.database.AppDatabase
import com.catnip.rizkyilmann_challange4.data.database.datasource.CartDataSource
import com.catnip.rizkyilmann_challange4.data.database.datasource.CartDatabaseDataSource
import com.catnip.rizkyilmann_challange4.data.repository.CartRepository
import com.catnip.rizkyilmann_challange4.data.repository.CartRepositoryImpl
import com.catnip.rizkyilmann_challange4.databinding.ActivityCheckoutBinding
import com.catnip.rizkyilmann_challange4.model.Cart
import com.catnip.rizkyilmann_challange4.model.CartProduct
import com.catnip.rizkyilmann_challange4.network.api.datasource.AppApiDataSource
import com.catnip.rizkyilmann_challange4.network.api.service.AppApiService
import com.catnip.rizkyilmann_challange4.ui.cart.adapter.CartAdapter
import com.catnip.rizkyilmann_challange4.utils.GenericViewModelFactory
import com.catnip.rizkyilmann_challange4.utils.ResultWrapper
import com.catnip.rizkyilmann_challange4.utils.proceedWhen
import com.catnip.rizkyilmann_challange4.utils.toCurrencyFormat

class CheckoutActivity : AppCompatActivity() {

    private val viewModel: CheckoutViewModel by viewModels {
        val database = AppDatabase.getInstance(this)
        val cartDao = database.cartDao()
        val cartDataSource: CartDataSource = CartDatabaseDataSource(cartDao)
        val service = AppApiService.invoke()
        val apiDataSource = AppApiDataSource(service)
        val repo: CartRepository = CartRepositoryImpl(cartDataSource, apiDataSource)
        GenericViewModelFactory.create(CheckoutViewModel(repo))
    }
    private val binding: ActivityCheckoutBinding by lazy {
        ActivityCheckoutBinding.inflate(layoutInflater)
    }

    private val adapter: CartAdapter by lazy {
        CartAdapter()
    }


    private fun setupList() {
        binding.rvCheckout.adapter = adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setupList()
        observeData()
        supportActionBar?.hide()
    }

    private fun observeData() {
        viewModel.cartList.observe(this) { resultWrapper ->
            when (resultWrapper) {
                is ResultWrapper.Success -> {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCheckout.isVisible = true

                    val carts: List<Cart> = resultWrapper.payload?.first ?: emptyList()
                    val totalPrice = resultWrapper.payload?.second ?: 0.0
                    adapter.submitData(carts)
                    binding.tvTotalPrice.text = totalPrice.toCurrencyFormat()
                }

                is ResultWrapper.Loading -> {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCheckout.isVisible = false
                }

                is ResultWrapper.Error -> {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = resultWrapper.message.orEmpty()
                    binding.rvCheckout.isVisible = false
                }

                is ResultWrapper.Empty -> {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = getString(R.string.text_empty_cart)
                    binding.rvCheckout.isVisible = false

                    // Ambil total harga dari payload jika tersedia
                    val totalPrice = resultWrapper.payload?.second ?: 0.0
                    binding.tvTotalPrice.text = totalPrice.toCurrencyFormat()
                }
            }
        }
    }

}