package com.example.rambl.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rambl.R
import com.example.rambl.components.home.RamblMain
import com.example.rambl.ui.theme.NavIsland
import com.example.rambl.viewmodels.NavbarViewModel

@Composable
fun HomeScreen(
    navbarViewModel: NavbarViewModel
){

    var selected by remember { mutableIntStateOf(-1) }
    val navbarState = navbarViewModel.state.collectAsState()
    LazyColumn {
        item{
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(32.dp)
                )
            }
            HorizontalDivider(
                color = NavIsland,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
        item{
            Box(modifier = Modifier.fillMaxWidth()
                .animateContentSize().then(
                    if(navbarState.value is NavbarViewModel.State.Rambling) Modifier else Modifier.height(0.dp))){
                var text by remember { mutableStateOf("") }
                TextField(
                    value = text,
                    onValueChange = {text = it},
                    maxLines = Int.MAX_VALUE,
                    placeholder = { Text(text = "Start Rambling...") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )
            }
            HorizontalDivider(
                color = NavIsland,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
        items(25){ index ->
            RamblMain(
                onClick = {

                },
                onLongClick = { currIndex ->
                    selected = if(selected == currIndex) -1 else currIndex
                },
                selected = (index == selected),
                index,
                null,
                null
            )
        }
        item{
            Spacer(modifier = Modifier
                .height(128.dp)
                .fillMaxWidth())
        }
    }


}