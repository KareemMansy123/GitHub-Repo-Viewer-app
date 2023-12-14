package com.example.strarterandroid.pricentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.strarterandroid.ui.theme.StrarterAndroidTheme

class MainActivity : ComponentActivity() {
    // that's for non composable projects
//    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrarterAndroidTheme {
               // val mainViewModel = getViewModel<MainViewModel>()
               Content()
            }
        }
    }
    @Composable
    private fun Content() {

    }

    @Composable
    fun DynamicSpinner(
        label: String,
        options: List<String>,
        selectedOption: String,
        onOptionSelected: (String) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = selectedOption,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }) {
                        Text(text = option)
                    }
                }
            }
        }
    }

    @Composable
    fun TransferForm(options: List<String>) {
        var from by remember { mutableStateOf(options.first()) }
        var to by remember { mutableStateOf(options.first()) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DynamicSpinner(
                label = "From",
                options = options,
                selectedOption = from,
                onOptionSelected = { from = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DynamicSpinner(
                label = "To",
                options = options,
                selectedOption = to,
                onOptionSelected = { to = it }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* TODO: Handle details click */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Details")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewTransferForm() {
        MaterialTheme {
            TransferForm(options = listOf("Option 1", "Option 2", "Option 3"))
        }
    }

}
