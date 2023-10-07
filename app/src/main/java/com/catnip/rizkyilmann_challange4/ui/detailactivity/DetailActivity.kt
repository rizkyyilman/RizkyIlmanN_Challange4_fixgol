package com.catnip.rizkyilmann_challange4.ui.detailactivity;

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.catnip.rizkyilmann_challange4.databinding.ActivityDetailBinding
import com.catnip.rizkyilmann_challange4.model.DetailMenu

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan objek DetailMenu yang dikirimkan melalui intent
        val detailMenu = intent.getParcelableExtra<DetailMenu>(EXTRA_PRODUCT)

        // Inisialisasi UI elemen
        val ivBannerDetail: ImageView = binding.ivBannerDetail
        val tvHeadlineMenu: TextView = binding.tvHeadlineMenu
        val tvDescriptionMenu: TextView = binding.tvDescriptionMenu
        val tvPriceDetail: TextView = binding.tvPriceDetail

        // Tampilkan data DetailMenu di layout DetailActivity
        if (detailMenu != null) {
            ivBannerDetail.load(detailMenu.imgUrl) {
                crossfade(true)
            }
            tvHeadlineMenu.text = detailMenu.name
            tvDescriptionMenu.text = detailMenu.desc
            tvPriceDetail.text = detailMenu.price.toString()
        }

        // Handle tombol kembali
        val ivBackIcon: ImageView = binding.ivBackIcon
        ivBackIcon.setOnClickListener {
            onBackPressed()
        }

        // Handle tombol lokasi
        val tvLocationDetail: TextView = binding.tvLocationDetail
        tvLocationDetail.setOnClickListener {
            openGoogleMaps()
        }
    }

    private fun openGoogleMaps() {
        val gmmIntentUri = Uri.parse("https://maps.app.goo.gl/h4wQKqaBuXzftGK77")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        startActivity(mapIntent)
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"

        // Fungsi untuk memulai DetailActivity dari suatu Activity/Fragment
        fun startActivity(context: Context, product: DetailMenu) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            context.startActivity(intent)
        }
    }
}
