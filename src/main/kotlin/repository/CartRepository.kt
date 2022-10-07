package repository

import datasource.ProductsDataSource
import datasource.ProductsDataSourceImpl
import models.ProductsModel

class CartRepository() : ProductsDataSource by ProductsDataSourceImpl() {

    private val cart = mutableListOf<ProductsModel>()

    fun getProductDetailsByName(name: String): ProductsModel? {
        return getProductByName(name.lowercase())
    }

    fun addProductToCart(productsModel: ProductsModel) {
        cart.add(productsModel)
    }


    fun totalAmount(): Double {
        if (cart.isEmpty()) throw Exception("cart is empty")
        return cart.sumOf { it.amount!! }
    }

    fun getAllCartItems() = cart

    fun clear() {
        cart.clear()
    }
}