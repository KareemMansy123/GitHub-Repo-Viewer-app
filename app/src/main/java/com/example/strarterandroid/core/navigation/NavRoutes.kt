package com.example.strarterandroid.core.navigation

import android.net.Uri

class OuterClass {
    sealed class NavCommand(open val route: String) {
        object Main : NavCommand("main")
        object Details : NavCommand("details")
        companion object {
            // Static route pattern for Detail
            const val Details_ROUTE_PATTERN = "detail/{name}/{owner}"
            const val Issues_ROUTE_PATTERN = "issues/{name}/{owner}"
        }

        data class Detail(val name: String, val owner: String) : NavCommand("detail/{name}/{owner}}") {
            fun createRoute() = "detail/${Uri.encode(name)}/${Uri.encode(owner)}"
        }
        data class Issues(val owner: String, val name: String) : NavCommand("issues/{owner}/{name}") {
            fun createRoute() = "issues/$owner/$name"
        }
    }
}
