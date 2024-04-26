import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rambl.R
import com.example.rambl.ui.theme.Background
import com.example.rambl.viewmodels.NavbarViewModel

@Composable
fun NavigationIsland(
    navbarViewModel: NavbarViewModel,
    navHostController : NavHostController
){
    val navbarState = navbarViewModel.state.collectAsState()
    Column(
        modifier = Modifier.animateContentSize().focusTarget()
    ) {
        AnimatedContent(targetState = navbarState.value, label = "") { state ->
            Row{
                when(state){
                    is NavbarViewModel.State.Standard -> {
                        LeftNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.search,
                            onClick = {navbarViewModel.onSearch("Crypto")},
                            navbarViewModel = navbarViewModel
                        )
                        CenterNavButtons(
                            listOf(R.drawable.create_message, R.drawable.notification_on, R.drawable.news),
                            navbarViewModel = navbarViewModel,
                            onClicks = listOf({ navbarViewModel.onRambling() }, {}, {navHostController.navigate("news")}),
                            contentColor = listOf(MaterialTheme.colorScheme.secondary,MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.secondary))
                        RightNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.account,
                            onClick = {navHostController.navigate("profile")},
                            navbarViewModel = navbarViewModel
                        )
                    }
                    is NavbarViewModel.State.Searching -> {
                        var text by remember { mutableStateOf("")}
                        LeftNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.search,
                            onClick = {navbarViewModel.onSearch(text)},
                            navbarViewModel = navbarViewModel
                        )
                        CenterNavSearch(width = 208.dp, temporaryText = "Hola", navbarViewModel = navbarViewModel,
                            textH = { str -> text = str})
                        RightNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.cancel,
                            onClick = {navbarViewModel.onReturn()},
                            contentColor = Color.Red,
                            navbarViewModel = navbarViewModel
                        )
                    }
                    is NavbarViewModel.State.RamblOptions -> {
                        LeftNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.like,
                            onClick = {},
                            navbarViewModel = navbarViewModel
                        )
//                        CenterNavButtons(
//                            listOf(R.drawable.dislike, R.drawable.report, R.drawable.block, R.drawable.cancel),
//                            dividerColor = Background,
//                            navbarViewModel = navbarViewModel
//                        )
                    }
                    is NavbarViewModel.State.ProfileScreen -> {
                        if(state.personalAccount){
                        } else {
                            LeftNavContent(
                                width = 64.dp,
                                vectorId = R.drawable.subscribe,
                                onClick = {},
                                navbarViewModel = navbarViewModel
                            )
                            CenterNavButtons(
                                listOf(R.drawable.report, R.drawable.block),
                                dividerColor = Background,
                                navbarViewModel = navbarViewModel,
                                onClicks = listOf({}, {}, {}),
                                contentColor = listOf(Color.Red, Color.Red)
                            )
                            RightNavContent(
                                width = 64.dp,
                                vectorId = R.drawable.cancel,
                                onClick = { /*TODO*/ },
                                navbarViewModel = navbarViewModel
                            )
                        }
                    }
                    is NavbarViewModel.State.Rambling -> {
                        LeftNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.send_alt_1_svgrepo_com,
                            onClick = {},
                            navbarViewModel = navbarViewModel
                        )
                        CenterNavButtons(
                            vectorIds = listOf(R.drawable.image_square_svgrepo_com, R.drawable.microphone, R.drawable.location_pin_svgrepo_com),
                            dividerColor = Background,
                            contentColor = listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.secondary),
                            onClicks = listOf({}, {}, {}),
                            navbarViewModel = navbarViewModel
                        )
                        RightNavContent(
                            width = 64.dp,
                            vectorId = R.drawable.cancel,
                            onClick = { navbarViewModel.onReturn() },
                            contentColor = Color.Red,
                            navbarViewModel = navbarViewModel
                        )
                    }
                }
            }
        }
    }


}

@Composable
private fun LeftNavContent(width: Dp, vectorId : Int, onClick: () -> Unit, contentColor : Color = MaterialTheme.colorScheme.secondary, navbarViewModel: NavbarViewModel){
    Button(onClick = onClick,
        modifier = Modifier
            .width(width)
            .height(64.dp)
            .background(Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = contentColor
        ),
        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
    ){
        Image(painter = painterResource(id = vectorId), contentDescription = null,
            contentScale = ContentScale.Fit, modifier = Modifier.size(64.dp), colorFilter = ColorFilter.tint(contentColor))
    }
    VerticalDivider(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
        color = Background
    )
}

@Composable
private fun RightNavContent(width: Dp, vectorId : Int, onClick: () -> Unit, contentColor : Color = MaterialTheme.colorScheme.secondary, navbarViewModel: NavbarViewModel){
    Button(onClick = onClick,
        modifier = Modifier
            .width(width)
            .height(64.dp)
            .background(Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = contentColor
        ),
        shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
    ){
        Image(painter = painterResource(id = vectorId), contentDescription = null,
            contentScale = ContentScale.Fit, modifier = Modifier.size(64.dp), colorFilter = ColorFilter.tint(contentColor))
    }
}

@Composable
private fun CenterNavSearch(width : Dp, temporaryText : String, contentColor : Color = MaterialTheme.colorScheme.secondary, navbarViewModel : NavbarViewModel, textH : (String) -> Unit){
    var text by remember { mutableStateOf("")}
    TextField(
        value = text,
        onValueChange = {text = it},
        singleLine = true,
        placeholder = { Text(text = "Search...") },
        modifier = Modifier
            .height(64.dp)
            .width(208.dp),
        textStyle = TextStyle(
            color = contentColor,
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        )
    )
    VerticalDivider(modifier = Modifier
        .fillMaxHeight()
        .width(1.dp),
        color = Background
    )
}

@Composable
private fun CenterNavButtons(vectorIds : List<Int>, onClicks : List<() -> Unit>, dividerColor : Color = Background, contentColor : List<Color> = listOf(MaterialTheme.colorScheme.secondary), navbarViewModel : NavbarViewModel){
    vectorIds.forEachIndexed { id,  vector ->
        Button(onClick = onClicks[id],
            modifier = Modifier
                .size(64.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = contentColor[id],
                disabledContainerColor = Color.Transparent,
                disabledContentColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(0.dp)
        ){
            Image(painter = painterResource(id = vector), contentDescription = null,
                contentScale = ContentScale.Fit, modifier = Modifier.size(64.dp), colorFilter = ColorFilter.tint(contentColor[id]))
        }
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = dividerColor
        )
    }
}