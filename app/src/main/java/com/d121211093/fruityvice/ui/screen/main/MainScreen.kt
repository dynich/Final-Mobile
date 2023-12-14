package com.d121211093.fruityvice.ui.screen.main

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d121211093.fruityvice.FruitViceMainState
import com.d121211093.fruityvice.FruitViceUiState
import com.d121211093.fruityvice.data.model.FruitsItem
import com.d121211093.fruityvice.ui.viewmodel.FruitViceViewModel

enum class MainScreen(val title: String){
    Main(title = "Main"),
    Detail(title = "Detail")
}
data class Nutritions(
    val calories: Int,
    val carbohydrates: Double,
    val fat: Double,
    val sugar: Double
)

data class Fruit(
    val family: String,
    val genus: String,
    val id: Int,
    val name: String,
    val nutritions: Nutritions,
    val order: String,
    val calories: Int,
    val carbohydrates: Double,
    val fat: Double,
    val sugar: Double
)

@Composable
fun FruitListScreen(uiState: FruitViceUiState, navController: NavController, viewModel: FruitViceViewModel) {
    when(uiState.mainScreenState){
        is FruitViceMainState.Success->
            LazyColumn {
                items(uiState.mainScreenState.fruits) { fruit ->
                    FruitListItem(fruit = fruit, navController = navController, viewModel= viewModel)
                }
            }
        is FruitViceMainState.Loading->
            Text(text = "Loading")
        is FruitViceMainState.Failure->
            Text(text = "Failure")
    }

}

@Composable
fun FruitListItem(fruit: FruitsItem, navController:NavController, viewModel: FruitViceViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate(MainScreen.Detail.name)
                viewModel.getFruitInfo(fruitsItem = fruit)
            }
    ) {
        Text(
            text = fruit.name,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Family: ${fruit.family}",
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Genus: ${fruit.genus}",
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Order: ${fruit.order}",
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
fun FruitApp(uiState:FruitViceUiState,navController: NavHostController,viewModel: FruitViceViewModel) {
    NavHost(navController = navController, startDestination = MainScreen.Main.name){
        composable(MainScreen.Main.name){
            FruitListScreen(uiState = uiState, navController=navController, viewModel= viewModel)
        }
        composable(MainScreen.Detail.name){
            FruitDetailScreen(uiState=uiState)
        }
    }
//    FruitListScreen(uiState=uiState)
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewFruitApp() {
//    FruitApp()
//}

