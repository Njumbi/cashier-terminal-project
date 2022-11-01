package presentation

import models.Actions
import models.ActionsUtils
import repository.CartRepository
import session.Session
import kotlin.system.exitProcess

fun main() {
    println("-----------------------------")
    println("Welcome to Bobo's Shop, enter your name to continue")

    //Declaring a variable session and assigning it to null
    var session: Session? = null
    // Declaring variable cart which is a mutable list of strings
    val cart = CartRepository()

    // An infinite loop that doesn't exit the app.
    while (true) {
        // check session
        if (session == null) {
            val name = readLine()
            if (name?.trim()?.isNotEmpty() == true) {
                // set up session
                // Creating a new session and assigning it to the session variable.
                session = Session.Builder().setUser(name = name).build()
            } else {
                println("Please enter your name")
            }
        } else {
            // continue with shopping
            if (cart.getAllCartItems().isEmpty()) {
                println("-----------------------------")
                println("Cart is empty.Enter item:")
            }

            val input = readLine()

            if (input?.trim() == Actions.FINISH.actionName && cart.getAllCartItems().isNotEmpty()) {
                println("-----------------------------")
                println("Here is your list:")
                val cartItems = cart.getAllCartItems()
                cartItems.forEach { item ->
                    println("${item.quantity} - ${item.name} ........ KES ${item.amount!! * item.quantity}")
                }
                println("--------------")
                println("Total: KES ${cart.totalAmount()}")
                println("-----------------------------")
                println("Enter Pay to pay for items")
            }

            if (input == Actions.PAY.actionName && cart.getAllCartItems().isNotEmpty()) {
                println(" --- Thank you fot shopping with us ---")
                exitProcess(0)
            }

            if (input?.isNotEmpty() == true && !ActionsUtils.getListOfActions().contains(input)) {
                val searchResults = cart.getProductDetailsByName(input)
                searchResults?.let {
                    try {
                        cart.addProductToCart(it)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                } ?: run {
                    println("Item not found!")
                }
            }
        }
    }
}





