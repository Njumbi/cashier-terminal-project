package presentation

import session.Session
import kotlin.system.exitProcess

fun main() {
    println("--------------")
    println("Welcome to Juja's Wholesale Shop, Please enter yor name to continue")

    var session: Session? = null
    var cart = mutableListOf<String>()

    while (true) {
        if (session == null) {
            val name = readLine()
            if (name?.isNotEmpty() == true) {
                session = Session.Builder().setUser(name = name).build()
            }else{
                println("Please enter your name to continue")
            }
        }else{
            if (cart.isEmpty()){
                println("--------------")
                println("Please enter cart items")
            }
            val input = readLine()
            if (input =="finish"){
                println("--------------")
                println("Here is your shopping list")
                cart.forEach {item->
                println("${cart.indexOf(item)} -- $item")
                }
                println("--------------")
                println("Enter P to finish")
            }
            if (input == "P"){
                println("--------------")
                println("Thank you for shopping with us")
                exitProcess(0)
            }
            if (input?.isNotEmpty() == true) cart.add(input)
        }
    }

}