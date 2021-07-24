package com.app.scrapapp.test.entity

import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {




        /*ResourceBundle.getBundle()
        var result : Result = InProgress()
*/

    }
}


sealed class Result
class Success : Result() {
    val responsebody : String? = null
    val message : String? = null
}
class Fail : Result(){
    val message : String? = null
}
class InProgress : Result()