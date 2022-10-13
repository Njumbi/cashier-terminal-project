package datasource

import models.ProductsModel

// It implements the ProductsDataSource interface and returns a list of ProductsModel objects
class ProductsDataSourceImpl : ProductsDataSource {

    // Creating a list of ProductsModel objects.
    private val productsList = listOf(
        ProductsModel(1, "milk", 55.0, 5),
        ProductsModel(2, "coffee", 120.0, 5),
        ProductsModel(3, "bread", 80.0, 5),
        ProductsModel(4, "sugar", 220.0, 5)
    )

    /**
     * Return the first product in the list whose name matches the name passed in as a parameter.
     * @param name The name of the product to be searched for.
     */
    override fun getProductByName(name: String): ProductsModel? = productsList.find { it.name == name }
    override fun getProductById(id: Int): ProductsModel  = productsList.find { it.id == id }!!

    /**
     * It returns a list of ProductsModel objects
     */
    override fun getAllProducts(): List<ProductsModel> = productsList
}