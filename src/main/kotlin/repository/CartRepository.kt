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
        // This is checking if the product is already in the cart. If it is, it checks if the quantity is less than the
        //quantity in the datasource. If it is, it increments the quantity by 1. If it is not, it throws an exception. If
        // the product is not in the cart, it adds it to the cart with a quantity of 1.
        if (existingProduct != null) {
            val productFromDatasource = getProductById(existingProduct.id)
            if (existingProduct.quantity < productFromDatasource.quantity) {
                existingProduct.quantity = existingProduct.quantity + 1
            } else {
                throw Exception("${productsModel.name} is out of stock")
            }

        } else {
            //This is creating a copy of the product with a quantity of 1 and adding it to the cart.
            val prd = productsModel.copy( quantity = 1 )
            cart.add(prd)
        }
    }


    /**
     * If the cart is empty, throw an exception, otherwise return the total amount of the cart
     * @return The total amount of the cart
     */
    fun totalAmount(): Double {
        if (cart.isEmpty()) throw Exception("cart is empty")
        return cart.sumOf { it.amount!! * it.quantity }
    }

    /**
     * Return the cart.
     */
    fun getAllCartItems() = cart

    fun clear() {
        cart.clear()
    }
}