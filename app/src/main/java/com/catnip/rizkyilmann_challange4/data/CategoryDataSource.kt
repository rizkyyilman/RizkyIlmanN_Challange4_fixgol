package com.catnip.rizkyilmann_challange4.data

import com.catnip.rizkyilmann_challange4.model.Category

interface CategoryDataSource {
    fun getProductCategory(): List<Category>
}

class CategoryDataSourceImpl() : CategoryDataSource {
    override fun getProductCategory(): List<Category> =
        listOf(
            Category(
                name = "Groceries",
                categoryImgUrl = "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_groceries.png"
            ),
            Category(
                name = "Fruits",
                categoryImgUrl = "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_fruits.png"
            ),
            Category(
                name = "Spices",
                categoryImgUrl = "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_spices.png"
            ),
            Category(
                name = "Herbs",
                categoryImgUrl = "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_herbs.png"
            ),
        )
}