package com.hanif.medical.Screens

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanif.medical.repository.ReportModel
import com.hanif.medical.ui.theme.DMSans
import com.hanif.medical.viewmodel.HomeViewModel
import com.matrixhive.subsalert.component.notification.EmptyScreen


@Composable
fun ReportScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.reportState
    if (state.companies.isEmpty()) {
        EmptyScreen("No Report Found!", "")
    } else {
        LazyColumn() {
            items(state.companies) {
                ReportItemLayout(it)
            }
        }
    }
}


@Composable
fun ReportItemLayout(s: ReportModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .defaultMinSize(minHeight = 50.dp)
    ) {
        Text(
            text = s.message,
            fontFamily = DMSans,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )

        Text(
            text = s.Time,
            fontFamily = DMSans,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, end = 10.dp),
            textAlign = TextAlign.End
        )
    }
}