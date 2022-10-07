package models

object ActionsUtils {
    fun getListOfActions(): List<String> = listOf(Actions.PAY.actionName, Actions.FINISH.actionName)
}