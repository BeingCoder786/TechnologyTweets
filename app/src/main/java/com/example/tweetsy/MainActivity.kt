package com.example.tweetsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsy.api.TweetsyAPI
import com.example.tweetsy.screens.CategoryScreen
import com.example.tweetsy.screens.DetailScreen
import com.example.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsyAPI: TweetsyAPI

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// for testing
        /*       GlobalScope.launch {
                    var response = tweetsyAPI.getCategories()
                    Log.d("response", response.body().toString())
                    response.body().distinct()
                }*/
        setContent {
            TweetsyTheme {
                // to define controller, to maange all screen to that created composable
                // App()  //this is for example of navgraph
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Tweetsy")
                        }, Modifier.background(Color.Black))
                    },
                ) {
                    Box(modifier = Modifier.padding(it))
                    AppTweetsy()
                }
            }
        }
    }
}

@Composable
fun AppTweetsy() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen(onClick = {
                navController.navigate("detail/$it")
            })
        }

        composable(
            route = "detail/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                },
            ),
        ) {
            // val category = it.arguments?.getString("category")
            DetailScreen(navController)
        }
    }
}

/*Example of nav graph component*/
@Composable
fun App() {
    val navController = rememberNavController()

    // now define nahost and navgraph
    NavHost(navController = navController, startDestination = "registration" /*default*/) {
        // here define graph to that we use composable function with route
        composable(route = "registration") {
            RegistationScreen {
                navController.navigate("main/$it") // using callback
            }
            /* RegistationScreen(navController)*/ // we can do that as well
        }
        composable(route = "login") {
            login()
        }
        composable(
            route = "main/{email}", // get email to main route
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },
            ),
        ) {
            val getData = it.arguments?.getString("email")
            if (getData != null) {
                mainscreen(getData)
            }
        }

        // route can be declare in sealed class for better implemantion
    }
}

@Composable
fun RegistationScreen(/*navController: NavController*/ onClick: (String) -> Unit) {
    Text(
        text = "Registation",
        /*    modifier = Modifier.clickable {
                navController.navigate("main") // this function match with rout in graph.
            },*/
        modifier = Modifier.clickable { onClick("abhishek.keer@gmail.com") },

    )
}

@Composable
fun login() {
    Text(text = "login")
}

@Composable
fun mainscreen(email: String) {
    Text(text = "main")
    Spacer(modifier = Modifier.padding(top = 50.dp))
    Text(text = email, modifier = Modifier.padding(top = 50.dp))
}

/*actually ho kaya eha
        ek screen se dusri screen data bhejna ho to dusri screen ko bataa pdega
        ki kistype ka data expect kr rhe he to email type ka variable define ki ya or type define kiya
        jo bhi is route ko call krega,use email bhejna pdega,
        to humne first screen pe email bheji with navcontroller

        or fir durste screen se argument se data nikal liya

        jab bhi hum navigate krte argument ek or jagah store hote he savestatehandle me. to khi se bhi fetch kr skte he


        */
