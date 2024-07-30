package com.example.ip_test_task.presentation.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ip_test_task.R

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {

    val textStartState = stringResource(id = R.string.product_search)

    val keyboard = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        placeholder = {
            Text(
                text = stringResource(id = R.string.product_search),
                color = Color.Gray
            )
        },
        value = state.value,
        label = {
            if (state.value != TextFieldValue(textStartState)) {
                Text(
                    text = stringResource(id = R.string.product_search),
                    color = Color.Gray
                )
            }
        },
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp),
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF0E0158),
            unfocusedBorderColor = Color(0xFFACA8A8)
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboard?.hide()
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textStartState = stringResource(id = R.string.product_search)
    val textState = remember { mutableStateOf(TextFieldValue(textStartState)) }
    SearchView(textState)
}

