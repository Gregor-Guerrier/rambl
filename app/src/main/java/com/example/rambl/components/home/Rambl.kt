package com.example.rambl.components.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.rambl.R
import com.example.rambl.ui.theme.Background
import com.example.rambl.ui.theme.NavIsland

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RamblMain(onClick : () -> Unit, onLongClick : (Int) -> Unit, selected : Boolean, index : Int, content : String?, author : String?){
    Column(modifier = Modifier
        .zIndex(-1f)
        .fillMaxWidth()
        .combinedClickable(
            onClick = onClick,
            onLongClick = { onLongClick(index) },
        )
    ){
        Column(
            modifier = Modifier
                .background(
                    if (selected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                )
                .fillMaxSize()
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Box{
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .size(48.dp)
                    )
                }
                Text(
                    text = author ?: "Gregor Guerrier",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp, end = 4.dp),
                    color = if(selected) Background else MaterialTheme.colorScheme.secondary
                )
                Image(
                    painter = painterResource(R.drawable.verified),
                    contentDescription = "Verified",
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.CenterVertically),
                    colorFilter = ColorFilter.tint(if(selected) Background else MaterialTheme.colorScheme.secondary)
                )
                Text(
                    text = "@gregorguerrier",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 4.dp),
                    color = if(selected) Background else NavIsland
                )
            }
            if(content.isNullOrEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(start = 16.dp, bottom = 24.dp, end = 16.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable {  }
                ){

                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(start = 16.dp, bottom = 24.dp, end = 16.dp)
                ){
                    Text(
                        text = content
                    )
                }
            }

        }
        Divider(
            color = NavIsland,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}