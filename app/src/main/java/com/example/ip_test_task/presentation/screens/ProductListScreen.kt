package com.example.ip_test_task.presentation.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ip_test_task.common.Resource
import com.example.ip_test_task.domain.model.ProductInfo
import com.example.ip_test_task.presentation.components.CardItemProduct
import com.example.ip_test_task.presentation.components.SearchView
import com.example.ip_test_task.presentation.components.TopAppBarProductList


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel()
) {

    val productInfo by viewModel.productInfo.collectAsState(initial = Resource.Loading)
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    val filteredProducts = remember { mutableStateListOf<ProductInfo>() }
    var allProducts by remember { mutableStateOf(emptyList<ProductInfo>()) }

    LaunchedEffect(textState.value) {
        filterProducts(
            textState = textState,
            allProducts = allProducts,
            filteredProducts = filteredProducts
        )
    }
    Scaffold(
        topBar = { TopAppBarProductList() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (productInfo) {
                is Resource.Loading -> {
                    LoadingIndicator()
                }
                is Resource.Success -> {
                    Log.d("dataDB", (productInfo as Resource.Success<List<ProductInfo>>).data.toString())
                    allProducts = (productInfo as Resource.Success<List<ProductInfo>>).data
                    filteredProducts.clear()
                    filteredProducts.addAll(allProducts)
                    LazyColumn(
                        modifier = Modifier
                            .padding(8.dp),

                        ) {
                        item{
                            SearchView(
                                state = textState
                            )
                        }
                        items(filteredProducts.size) {
                            CardItemProduct(
                                viewModel = viewModel,
                                productItem = filteredProducts[it]
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    Text(text = "Error")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadingIndicator() {
    CircularProgressIndicator()
}

@Preview
@Composable
fun ErrorScreen() {
    Text(text = "Произошла ошибка")
}

// Фильтрация списка по тексту в поле поиска
fun filterProducts(
    textState: MutableState<TextFieldValue>,
    allProducts: List<ProductInfo>,
    filteredProducts: MutableList<ProductInfo>
) {
    val query = textState.value.text.lowercase()
    filteredProducts.clear()
    filteredProducts.addAll(allProducts.filter {
        it.name.lowercase().contains(query)
    })
}