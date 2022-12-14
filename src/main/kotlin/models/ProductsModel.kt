package models

/**
 * @property {String?} name - The name of the product.
 * @property {Double?} amount - The amount of the product.
 */
data class ProductsModel(
    val id: Int,
    val name: String?,
    val amount: Double?,
    var quantity: Int
)
