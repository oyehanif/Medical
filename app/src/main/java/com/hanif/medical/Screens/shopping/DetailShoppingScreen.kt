package com.hanif.medical.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanif.medical.R
import com.hanif.medical.ui.theme.DMSans

@Preview(showBackground = true)
@Composable
fun DetailShoppingScreen() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.img_6),
                contentDescription = "",
                contentScale = ContentScale.Crop, modifier =
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )


            Text(
                text = "Benylin Dry and Ticky cough syrup 100 ML",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = DMSans, fontStyle = FontStyle.Italic, maxLines = 3, fontSize = 20.sp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {

                Text(
                    text = "Rs. 1000",
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Normal,
                    maxLines = 3,
                    fontSize = 16.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "4.8")
                    Image(Icons.Default.Star, contentDescription = "Star")
                    Text(text = "(4 Reviews)")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Qty : ")
                Spacer(modifier = Modifier.width(10.dp))

                Image(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Star")
                Text(text = "1", Modifier.padding(horizontal = 10.dp))
                Image(
                    painterResource(id = R.drawable.baseline_horizontal_rule_24),
                    contentDescription = "Star"
                )
            }

            Text(
                text = "Benylin Dry and Ticky cough syrup is a highly effective medication for treating both dry and tickly coughs. With its powerful formula, this cough syrup helps to soothe the throat and relieve coughing in no time. The 100 ML bottle is the perfect size for home use, allowing you to keep it on hand whenever you need it. The syrup is easy to take, with a pleasant taste that makes it easy to swallow. It is also suitable for adults and children over the age of six, making it a versatile option for families. If you're looking for a reliable and effective way to treat your cough, look no further than Benylin Dry and Ticky cough syrup.",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.Normal,
                fontFamily = DMSans, fontStyle = FontStyle.Normal, maxLines = 5, overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Reviews ",
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSans,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    fontSize = 20.sp
                )


                Text(
                    text = "Read All",
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Normal,
                    fontFamily = DMSans, fontStyle = FontStyle.Normal
                )
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CommonButton(
                text = "Buy Now", modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(5.dp)
            ) {

            }
            CommonButton(
                text = "Add To card", modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

            }
        }
    }
}