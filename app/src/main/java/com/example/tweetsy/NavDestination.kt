package com.example.tweetsy

sealed class NavDestination(val route: String) {

    object CategoryScreen : NavDestination(route = "category")

    object DetailScreen : NavDestination(route = "detail")

    /**
     * Use this function to pass arguments to navigation destination
     */
    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
