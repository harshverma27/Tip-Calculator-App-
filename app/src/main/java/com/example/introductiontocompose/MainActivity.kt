package com.example.introductiontocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introductiontocompose.ui.theme.IntroductionToComposeTheme
import kotlinx.coroutines.flow.Flow
import java.nio.file.WatchEvent
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroductionToComposeTheme {
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {
    var perPerson by remember { mutableIntStateOf(0) }
    var total by remember { mutableStateOf("") }
    var noOfPerson by remember { mutableStateOf(1) }
    var tip: Float by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Tip Calculator", fontSize = 30.sp)
        }

        OutlinedTextField(
            value = total,
            onValueChange = { total = it },
            label = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Enter amount",
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier, shape = RoundedCornerShape(10.dp)) {
            Slider(
                value = tip,
                onValueChange = { tip = it },
                valueRange = 0f..100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Text(text = "Tip: ${String.format("%.1f", tip)}%", modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
        }
        Spacer(modifier = Modifier.height(20.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "No of person", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp), onClick = {
                        noOfPerson = noOfPerson - 1
                    }) {
                    Text("-", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "$noOfPerson", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp), onClick = {
                        noOfPerson = noOfPerson + 1
                    }) {
                    Text("+", fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                perPerson = (total.toInt() + total.toInt() * tip.toInt() / 100) / noOfPerson.toInt()
            }
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.LightGray)
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Total Per Person", fontSize = 20.sp)
                Text(text = "$perPerson", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}