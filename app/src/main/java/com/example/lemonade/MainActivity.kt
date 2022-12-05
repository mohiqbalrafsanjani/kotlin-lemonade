package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp(){
    LemonadeAppWithImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeAppWithImage(
    modifier: Modifier = Modifier
){
    var result by remember {
        mutableStateOf(1)
    }
    if (result > 4){
        result = 1
    }else if (result == 3){
        var squeeze = (1..6).random()
        if (squeeze % result == 0){
            result = 3
        }else{
            result = 2
        }
    }
    val textResource = when(result){
        1 -> R.string.step_1
        2 -> R.string.step_2
        3 -> R.string.step_3
        else -> R.string.step_4
    }
    val imageResource = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val imageDescription = when(result){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon_fruit
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = textResource))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = imageDescription.toString(),
            modifier = Modifier.clickable { result += 1 }
        )
    }

}