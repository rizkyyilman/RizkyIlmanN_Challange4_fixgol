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
        val repo: CartRepository = CartRepositoryImpl(cartDataSource)
        GenericViewModelFactory.create(CheckoutViewModel(repo))
    }
    private val binding : ActivityCheckoutBinding by lazy {
    ActivityCheckoutBinding.inflate(layoutInflater)
    }

    private val adapter: CartAdapter by lazy {
        CartAdapter()
    }


    private fun setupList() {
        binding.rvCart.adapter = adapter
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setupList()
        observeData()
        supportActionBar?.hide()
    }

    private fun observeData() {
        viewModel.cartList.observe(this) { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCart.isVisible = true

                    // Ambil data dari payload
                    val (carts, totalPrice) = result.payload!!

                    // Set data ke adapter
                    adapter.submitData(carts)

                    // Set total harga ke TextView
                    binding.tvTotalPrice.text = totalPrice.toCurrencyFormat()
                }

                is ResultWrapper.Loading -> {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCart.isVisible = false
                }

                is ResultWrapper.Error -> {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = result.message.orEmpty()
                    binding.rvCart.isVisible = false
                }

                is ResultWrapper.Empty -> {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = getString(R.string.text_empty_cart)
                    binding.rvCart.isVisible = false

                    // Ambil total harga dari payload jika tersedia
                    result.payload?.let { (_, totalPrice) ->
                        binding.tvTotalPrice.text = totalPrice.toCurrencyFormat()
                    }
                }
            }
        }
    }
}
