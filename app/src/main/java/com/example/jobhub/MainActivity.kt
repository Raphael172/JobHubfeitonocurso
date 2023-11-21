package com.example.jobhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobhub.ui.theme.JobHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobHubTheme {
                JobHub()
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 55)
@Composable
fun JobHub() {
    var name by remember { mutableStateOf("") }
    var UserOrEmail by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    var ConfirmPassword by remember { mutableStateOf("") }
    var Register by remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = R.drawable.azul),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)
    ) {
        Text(
            text = stringResource(id = R.string.Main),
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(top = 350.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh
                )
            )
    ) {
        if (Register){

            CamposDeTexto(
                value = name,
                onValueChange = {name=it},
                idTexto = R.string.name
            )
        }
        CamposDeTexto(
            value = Password,
            onValueChange = { Password = it },
            idTexto = R.string.Login,
        )

        CamposDeTexto(
            value = UserOrEmail,
            onValueChange = { UserOrEmail = it },
            idTexto = R.string.Pass
        )
        if (Register){

            CamposDeTexto(value = ConfirmPassword,
                onValueChange = {ConfirmPassword=it},
                idTexto = R.string.Confirm)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Sing in"
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(25.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                FormasDeLogin(id = R.drawable.facebook)
                FormasDeLogin(id = R.drawable.google)
                FormasDeLogin(id = R.drawable.linkedin)
            }
            Spacer(modifier = Modifier.size(10.dp))
            if (!Register) {
                Text(
                    text = if(Register){
                    "Sing in"
                    }else
                        "Register now ",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .clickable {
                            Register = true
                        }
                )
            }
        }
    }
}

@Composable
fun FormasDeLogin(id: Int) {
    val OnClickImage = {}

    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = OnClickImage)
            .size(35.dp)
            .clip(CircleShape)
            .border(1.5.dp, Color(2, 16, 237), CircleShape)

    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CamposDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    idTexto: Int

) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(id = idTexto)
            )
        }
    )
}

