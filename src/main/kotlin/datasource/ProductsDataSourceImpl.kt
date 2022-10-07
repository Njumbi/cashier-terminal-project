package datasource

import models.ProductsModel

// It implements the ProductsDataSource interface and returns a list of ProductsModel objects
class ProductsDataSourceImpl : ProductsDataSource {

    // Creating a list of ProductsModel objects.
    private val productsList = listOf(
        ProductsModel("milk", 55.0),
        ProductsModel("coffee", 120.0),
        ProductsModel("bread", 80.0),
        ProductsModel("sugar", 220.0)
    )

    /**
     * Return the first product in the list whose name matches the name passed in as a parameter.
     * @param name The name of the product to be searched for.
     */
    override fun getProductByName(name: String): ProductsModel? = productsList.find { it.name == name }

    /**
     * It returns a list of ProductsModel objects
     */
    override fun getAllProducts(): List<ProductsModel> = productsList
}