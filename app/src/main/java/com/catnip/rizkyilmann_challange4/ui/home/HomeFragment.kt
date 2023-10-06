package com.catnip.rizkyilmann_challange4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.rizkyilmann_challange4.data.CategoryDataSource
import com.catnip.rizkyilmann_challange4.data.CategoryDataSourceImpl
import com.catnip.rizkyilmann_challange4.data.MenuDataSource
import com.catnip.rizkyilmann_challange4.data.MenuDataSourceImpl
import com.catnip.rizkyilmann_challange4.databinding.FragmentHomeBinding
import com.catnip.rizkyilmann_challange4.model.Category
import com.catnip.rizkyilmann_challange4.model.DetailMenu
import com.catnip.rizkyilmann_challange4.ui.home.adapter.category.CategoryAdapter
import com.catnip.rizkyilmann_challange4.ui.home.adapter.product.AdapterLayoutMode
import com.catnip.rizkyilmann_challange4.ui.home.adapter.product.ProductAdapter

class HomeFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    private val dataSource: CategoryDataSource by lazy {
        CategoryDataSourceImpl()
    }

    private val datasource: MenuDataSource by lazy {
        MenuDataSourceImpl()
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi CategoryAdapter
        categoryAdapter = CategoryAdapter { category ->
            // Tindakan yang akan diambil ketika salah satu item diklik
            // Misalnya, Anda dapat menavigasi ke fragmen lain atau menampilkan detail kategori.
        }
        val layoutManagerCategory = GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
        binding.rvCategory.layoutManager = layoutManagerCategory
        binding.rvCategory.adapter = categoryAdapter

        // Dapatkan data dari CategoryDataSource
        val categoryList: List<Category> = dataSource.getProductCategory()
        categoryAdapter.submitData(categoryList)

        // Inisialisasi ProductAdapter
        productAdapter = ProductAdapter(AdapterLayoutMode.GRID) { detailMenu: DetailMenu ->
            navigateToDetail(detailMenu)
        }
        val layoutManagerProduct = GridLayoutManager(requireContext(), 2)
        binding.rvMenu.layoutManager = layoutManagerProduct
        binding.rvMenu.adapter = productAdapter

        // Dapatkan data dari MenuDataSource
        val menuList: List<DetailMenu> = datasource.getDetailMenu()
        productAdapter.submitData(menuList)
    }

    private fun navigateToDetail(detailMenu: DetailMenu) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
