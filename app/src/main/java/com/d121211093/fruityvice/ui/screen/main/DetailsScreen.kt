package com.d121211093.fruityvice.ui.screen.main


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.d121211093.fruityvice.FruitViceDetailsState
import com.d121211093.fruityvice.FruitViceMainState
import com.d121211093.fruityvice.FruitViceUiState
import com.d121211093.fruityvice.data.model.FruitsItem

// Add this composable function to your existing code
@Composable
fun FruitDetailScreen(uiState: FruitViceUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {when(uiState.detailScreenState){
        is FruitViceDetailsState.Success->
            DetailScreenInfo(fruit = uiState.detailScreenState.fruits)
        is FruitViceDetailsState.Loading->
            Text(text = "Loading")
        is FruitViceDetailsState.Failure->
            Text(text = "Failure")
    }
    }
}
@Composable
fun DetailScreenInfo(fruit:FruitsItem){
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
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Nutritional Information:",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "Calories: ${fruit.nutritions.calories} kcal",
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = "Carbohydrates: ${fruit.nutritions.carbohydrates} g",
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = "Fat: ${fruit.nutritions.fat} g",
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = "Sugar: ${fruit.nutritions.sugar} g",
        color = MaterialTheme.colorScheme.onSurface
    )
}