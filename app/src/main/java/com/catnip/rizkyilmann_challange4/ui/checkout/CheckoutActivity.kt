package com.catnip.rizkyilmann_challange4.ui.checkout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.rizkyilmann_challange4.R
import com.catnip.rizkyilmann_challange4.data.database.AppDatabase
import com.catnip.rizkyilmann_challange4.data.database.datasource.CartDataSource
import com.catnip.rizkyilmann_challange4.data.database.datasource.CartDatabaseDataSource
import com.catnip.rizkyilmann_challange4.data.repository.CartRepository
import com.catnip.rizkyilmann_challange4.data.repository.CartRepositoryImpl
import com.catnip.rizkyilmann_challange4.databinding.ActivityCheckoutBinding
import com.catnip.rizkyilmann_challange4.network.api.datasource.AppApiDataSource
import com.catnip.rizkyilmann_challange4.network.api.service.AppApiService
import com.catnip.rizkyilmann_challange4.ui.cart.adapter.CartAdapter
import com.catnip.rizkyilmann_challange4.utils.GenericViewModelFactory
import com.catnip.rizkyilmann_challange4.utils.proceedWhen
import com.catnip.rizkyilmann_challange4.utils.toCurrencyFormat
import com.chuckerteam.chucker.api.ChuckerInterceptor

class CheckoutActivity : AppCompatActivity() {

    private val viewModel: CheckoutViewModel by viewModels {
        val database = AppDatabase.getInstance(this)
        val cartDao = database.cartDao()
        val cartDataSource: CartDataSource = CartDatabaseDataSource(cartDao)
        val chuckerInterceptor = ChuckerInterceptor(applicationContext)
        val service = AppApiService.invoke(chuckerInterceptor)
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
        setContentView(binding.root)
        setupList()
        observeData()
        setClickListeners()
        supportActionBar?.hide()
    }

    private fun setClickListeners() {
        binding.btnCheckout.setOnClickListener {
            viewModel.order()
        }
    }

    private fun observeData() {
        observeCartData()
        observeCheckoutResult()
    }

    private fun observeCheckoutResult() {
        viewModel.checkoutResult.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    showDialogCheckoutSuccess()
                },
                doOnError = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    Toast.makeText(this, "Checkout Success", Toast.LENGTH_SHORT).show()
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                }
            )
        }
    }

    private fun showDialogCheckoutSuccess() {
        AlertDialog.Builder(this)
            .setMessage("Checkout Success")
            .setPositiveButton(getString(R.string.text_okay)) { _, _ ->
                viewModel.clearCart()
                finish()
            }.create().show()
    }

    private fun observeCartData() {
        viewModel.cartList.observe(this) {
            it.proceedWhen(doOnSuccess = { result ->
                binding.layoutState.root.isVisible = false
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = false
                binding.rvCheckout.isVisible = true
                binding.cvSectionOrder.isVisible = true
                result.payload?.let { (carts, totalPrice) ->
                    adapter.submitData(carts)
                    binding.tvTotalPrice.text = totalPrice.toCurrencyFormat()
                }
            }, doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.rvCheckout.isVisible = true
                    binding.cvSectionOrder.isVisible = false
                }, doOnError = { err ->
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = err.exception?.message.orEmpty()
                    binding.rvCheckout.isVisible = true
                    binding.cvSectionOrder.isVisible = false
                }, doOnEmpty = { data ->
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = getString(R.string.text_cart_is_empty)
                    data.payload?.let { (_, totalPrice) ->
                        binding.tvTotalPrice.text = totalPrice.toCurrencyFormat()
                    }
                    binding.rvCheckout.isVisible = true
                    binding.cvSectionOrder.isVisible = false
                })
        }
    }
}
