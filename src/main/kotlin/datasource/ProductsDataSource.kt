package datasource

import models.ProductsModel

interface ProductsDataSource {
    fun getProductByName(name: String): ProductsModel?
    fun getAllProducts(): List<ProductsModel>
}