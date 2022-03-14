package com.wrondon.fromtemplates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wrondon.fromtemplates.ui.theme.FromTemplatesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FromTemplatesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column{
        Text(text = "Hello $name! (project: From TEMPLATES)")
        Button(onClick = {
            goToActivity(context, "com.wrondon.basicactivity123", "com.wrondon.basicactivity.MainActivity")
        }) {
            Text(text = "Basic Activity Module")
        }
        Button(onClick = {
            goToActivity(context, "com.wrondon.bottomnavigation", "com.wrondon.bottomnavigation.MainActivity")
        }) {
            Text(text = "Bottom Navigation Module")
        }
        Button(onClick = {
            goToActivity(context, "com.wrondon.emptycomposeactivity", "com.wrondon.emptycomposeactivity.MainActivity")
        }) {
            Text(text = "Empty Compose Activity Module")
        }
        Button(onClick = {
            goToActivity(context, "com.wrondon.emptytradactivity", "com.wrondon.emptytradactivity.MainActivity")
        }) {
            Text(text = "Empty Activity Module")
        }
    }

}


private fun goToActivity(context: Context, packageName: String, className : String) {
    /////val intent = context.packageManager.getLaunchIntentForPackage(packageName)
    val intent = Intent().setClassName(packageName, className )
    try {
        context.startActivity(intent)
    }
    catch (e: Exception){
        Log.d("Templates"," ERROR STARTING ACTIVITY $e")
    }

}

@Composable
fun ShowAlertDialog(openDialog: MutableState<Boolean>, texto: String) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = texto)
            },
            text = {
                Text("JetPack Compose Alert Dialog!!!")
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss...")
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FromTemplatesTheme {
        Greeting("Android")
    }
}