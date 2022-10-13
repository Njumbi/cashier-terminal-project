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
        val existingProduct = cart.find { it.id == productsModel.id }
        if (existingProduct != null) {
            val productFromDatasource = getProductById(existingProduct.id)
            if (existingProduct.quantity < productFromDatasource.quantity) {
                existingProduct.quantity = existingProduct.quantity + 1
            } else {
                throw Exception("${productsModel.name} is out of stock")
            }

        } else {
            val prd = productsModel.copy( quantity = 1 )
            cart.add(prd)
        }
    }


    fun totalAmount(): Double {
        if (cart.isEmpty()) throw Exception("cart is empty")
        return cart.sumOf { it.amount!! * it.quantity }
    }

    fun getAllCartItems() = cart

    fun clear() {
        cart.clear()
    }
}