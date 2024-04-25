import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rambl.R
import com.example.rambl.components.home.RamblMain
import com.example.rambl.ui.theme.Background
import com.example.rambl.ui.theme.CardBackground
import com.example.rambl.ui.theme.NavIsland

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(){
    Column(modifier = Modifier.fillMaxWidth()){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)){
           Box(modifier = Modifier
               .background(Color.Gray)
               .fillMaxWidth()
               .height(128.dp)){
               var badgeRowState by remember { mutableStateOf(false)}
               Box(modifier = Modifier
                   .height(32.dp)
                   .align(Alignment.TopEnd)
                   .offset(x = (-8).dp, y = 8.dp)
                   .padding(start = 16.dp)
                   .background(NavIsland, RoundedCornerShape(16.dp))
                   .combinedClickable(
                       onClick = { badgeRowState = !badgeRowState },
                       onLongClick = {}
                   )){
                   Row(modifier = Modifier
                       .animateContentSize()
                       .height(24.dp)
                       .padding(horizontal = 8.dp)
                       .align(Alignment.Center)
                       .then(if (badgeRowState) Modifier.fillMaxWidth() else Modifier)){
                       Image(painter = painterResource(R.drawable.verified), contentDescription = null)
                       Spacer(modifier = Modifier.width(4.dp))
                       Image(painter = painterResource(R.drawable.premium), contentDescription = null)
                   }
               }
           }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ){
                Text(
                    text = "Gregor Guerrier",
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.CenterHorizontally)
                ){
                    Box(modifier = Modifier
                        .weight(1f)
                        .background(NavIsland)
                        .shadow(16.dp)
                        .height(32.dp)
                        .align(Alignment.CenterVertically)){
                        val annotatedString = buildAnnotatedString {
                            // Append the first part of text with red color

                            // Append the second part of text with green color and underline
                            pushStyle(
                                style = SpanStyle(color = CardBackground)
                            )
                            append("following")
                            pushStyle(
                                style = SpanStyle(color = MaterialTheme.colorScheme.secondary)
                            )
                            append(" 1.2k")
                        }
                        Text(
                            text = annotatedString,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                        .size(80.dp)
                        .border(
                            BorderStroke(4.dp, NavIsland), RoundedCornerShape(16.dp)
                        ))
                    Box(modifier = Modifier
                        .weight(1f)
                        .background(NavIsland)
                        .shadow(16.dp)
                        .height(32.dp)
                        .align(Alignment.CenterVertically)){
                        val annotatedString = buildAnnotatedString {
                            // Append the first part of text with red color
                            pushStyle(
                                style = SpanStyle(color = MaterialTheme.colorScheme.secondary)
                            )
                            append("1.2m ")
                            // Append the second part of text with green color and underline
                            pushStyle(
                                style = SpanStyle(color = CardBackground)
                            )
                            append("followers")
                        }
                        Text(
                            text = annotatedString,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
        Text(
            text = "@gregorguerrier",
            style = TextStyle(fontSize = 16.sp, color = NavIsland),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "Trying to find my way in life. Over-ambitious guy with not enough hours in the day...",
            style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 16.dp)
        )
        var selected by remember { mutableIntStateOf(-1) }
        LazyColumn {

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
}