package presentation

import models.Actions
import models.ActionsUtils
import session.Session
import kotlin.system.exitProcess

fun main() {
    println("-----------------------")
    println("Welcome to Momo's shopping app,enter your name to continue")

    var session: Session? = null
    val cart = mutableListOf<String>()

    while (true) {
        if (session == null){
            val name = readLine()
            if (name?.trim()?.isNotEmpty()==true){
                session = Session.Builder().setUser(name = name).build()
            }else{
                println("Please enter your name")
            }
        }else{
            if (cart.isEmpty()){
                println("-----------------------")
                println("Please enter your cart items (enter finish to finish shopping)")
            }
            val cartItemsInput = readLine()

            if (cartItemsInput?.trim()== Actions.FINISH.actionName&& cart.isNotEmpty()){
                println("-----------------------")
                println("Here is your shopping list")
                cart.forEach {cartItem->
                    println("${cart.indexOf(cartItem)} -- $cartItem")
                }
                println("---------------")
                println("Enter pay to pay for items")
            }
            if (cartItemsInput?.trim() == Actions.PAY.actionName && cart.isNotEmpty()){
                println("--------------")
                println("Thank you for shopping with us")
                exitProcess(0)
            }

          if (cartItemsInput?.isNotEmpty() == true && !ActionsUtils.getListOfActions().contains(cartItemsInput))
              cart.add(cartItemsInput)

        }

    }
}