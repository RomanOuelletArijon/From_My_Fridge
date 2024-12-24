package com.example.frommyfridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frommyfridge.ui.theme.FromMyFridgeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FromMyFridgeTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController() // Controller to manage navigation
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("From My Fridge") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home", // Default page
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { Greeting(name = "Android") }
                composable("addRecipe") {
                    AddRecipePage()
                }
                composable("recipes") {
                    RecipesPage()
                }
                composable("ingredients") {
                    IngredientsPage()
                }
                composable("addIngredient") {
                    AddIngredientPage()
                }
            }
        }
    }
}

@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.Gray)
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    scope.launch {
                        navController.navigate("home")
                        drawerState.close() // Close the drawer after navigation
                    }
                }
        )
        Text(
            text = "Add a Recipe",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    scope.launch {
                        navController.navigate("addRecipe")
                        drawerState.close() // Close the drawer after navigation
                    }
                }
        )
        Text(
            text = "Recipes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    scope.launch {
                        navController.navigate("recipes")
                        drawerState.close() // Close the drawer after navigation
                    }
                }
        )
        Text(
            text = "My Ingredients",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    scope.launch {
                        navController.navigate("ingredients")
                        drawerState.close() // Close the drawer after navigation
                    }
                }
        )
        Text(
            text = "Add Ingredient",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    scope.launch {
                        navController.navigate("addIngredient")
                        drawerState.close() // Close the drawer
                    }
                }
        )
    }
}


@Composable
fun AddRecipePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Add a Recipe",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
    }
}

@Composable
fun RecipesPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "All My Recipes",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
    }
}

@Composable
fun IngredientsPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "All My Ingredients",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
    }
}

@Composable
fun AddIngredientPage() {
    var ingredientName by remember { mutableStateOf("") }
    var ingredientQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Add an Ingredient",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input field for ingredient name
        OutlinedTextField(
            value = ingredientName,
            onValueChange = { ingredientName = it },
            label = { Text("Ingredient Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Input field for ingredient quantity
        OutlinedTextField(
            value = ingredientQuantity,
            onValueChange = { ingredientQuantity = it },
            label = { Text("Quantity") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Submit button
        Button(
            onClick = {
                // Handle the submission
                // For now, just print the values
                println("Ingredient Name: $ingredientName")
                println("Ingredient Quantity: $ingredientQuantity")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Ingredient")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FromMyFridgeTheme {
        MainScreen()
    }
}
