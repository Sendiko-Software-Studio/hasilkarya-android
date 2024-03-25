package com.system.hasilkarya.dashboard.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.system.hasilkarya.core.ui.theme.poppinsFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCard(
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit,
    text: String,
    icon: Painter,
) {
    Card(
        modifier = modifier,
        onClick = { onClickAction() },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Icon(
                    painter = icon,
                    contentDescription = text,
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = poppinsFont,
                    modifier = Modifier.width(128.dp)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCardExpendable(
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit,
    text: String,
    icon: Painter,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier,
        onClick = { isExpanded = !isExpanded },
        content = {
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp),
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = text,
                        modifier = Modifier.size(128.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = poppinsFont,
                        modifier = Modifier.width(128.dp)
                    )
                }
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally(),
                    content = {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            TextButton(
                                onClick = { onClickAction() },
                                shape = RectangleShape,
                                content = {
                                    Text(
                                        text = "BBM Truk ",
                                        fontFamily = poppinsFont,
                                        fontSize = 16.sp,
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            )
                            Divider()
                            TextButton(
                                onClick = { onClickAction() },
                                shape = RectangleShape,
                                content = {
                                    Text(
                                        text = "BBM Alat Berat",
                                        fontFamily = poppinsFont,
                                        fontSize = 16.sp,
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 8.dp)
                                    )
                                },
                                enabled = false
                            )
                            Divider()
                        }
                    }
                )
            }
        }
    )
}