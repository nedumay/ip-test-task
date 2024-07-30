package com.example.ip_test_task.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ip_test_task.R
import com.example.ip_test_task.domain.model.ProductInfo
import com.example.ip_test_task.presentation.screens.ProductListViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardItemProduct(
    viewModel: ProductListViewModel,
    productItem: ProductInfo
) {
    val openAlertDialogDelete = remember { mutableStateOf(false) }
    val openAlertDialogUpdate = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productItem.name,
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 8.dp,
                        )
                        .weight(2f),
                    fontSize = 18.sp
                )
                IconButton(
                    onClick = {
                        openAlertDialogUpdate.value = true
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = colorResource(R.color.purple)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                    if (openAlertDialogUpdate.value) {
                        CounterDialog(
                            onDismissRequest = {
                                openAlertDialogUpdate.value = false
                            },
                            onConfirmation = { count ->
                                productItem.amount = count
                                viewModel.updateProductItem(productItem = productItem)
                                openAlertDialogUpdate.value = false
                            },
                            initialCount = productItem.amount
                        )
                    }
                }
                IconButton(
                    onClick = {
                        openAlertDialogDelete.value = true
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                    if (openAlertDialogDelete.value) {

                        AlertDialogDelete(
                            onDismissRequest = {
                                openAlertDialogDelete.value = false
                            },
                            onConfirmation = {
                                viewModel.deleteProductItem(productItem = productItem)
                                openAlertDialogDelete.value = false
                            }
                        )
                    }
                }
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                var inputString = productItem.tags
                inputString = inputString.replace("[", "").replace("]", "")
                val items = inputString.split(",\\s*".toRegex()).map { it.trim('"') }

                for (item in items) {
                    if (item == "") continue
                    SuggestionChipExample(item)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .weight(3f),
                    text = stringResource(id = R.string.on_stock),
                    color = Color.Black,
                )
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = stringResource(id = R.string.date_of_addition),
                    color = Color.Black,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp
                    )
            ) {
                Text(
                    modifier = Modifier
                        .weight(3f),
                    text = productItem.amount.toString(),
                    color = Color.Gray,
                )
                val dateTime = productItem.time
                val date = Date(dateTime * 1000L)
                val formatter = SimpleDateFormat("dd.MM.yyyy", Locale("ru", "RU"))
                val formattedDate = formatter.format(date)
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = formattedDate,
                    color = Color.Gray,
                )
            }
        }
    }
}


@Composable
fun SuggestionChipExample(text: String) {
    SuggestionChip(
        onClick = { },
        label = {
            Text(
                text = text,
                color = Color.Black
            )
        },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = Color.White,
            labelColor = Color.Black
        ),
    )
}

@Composable
fun AlertDialogDelete(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Filled.Warning, contentDescription = "")
        },
        title = {
            Text(text = stringResource(id = R.string.delete_product))
        },
        text = {
            Text(text = stringResource(id = R.string.delete_product_info))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(id = R.string.delete_yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(id = R.string.delete_no))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (count: Int) -> Unit,
    initialCount: Int = 0
) {
    var count by remember { mutableStateOf(initialCount) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            TextButton(onClick = {
                onConfirmation(count)
            }
            ) {
                Text(text = stringResource(id = R.string.accept))
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = ""
            )
        },
        title = { Text(text = stringResource(id = R.string.count_product)) },
        text = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { if (count > 0) count-- }) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp),
                        painter = painterResource(id = R.drawable.remove_circle_outline),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = count.toString(), style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { count++ }) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp),
                        painter = painterResource(id = R.drawable.add_circle_outline),
                        contentDescription = ""
                    )
                }
            }
        }
    )
}
