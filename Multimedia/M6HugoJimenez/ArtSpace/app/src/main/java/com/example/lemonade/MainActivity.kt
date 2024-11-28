package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.lemonade.ui.ArtSpaceViewModel
import com.example.lemonade.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val viewModel = ArtSpaceViewModel()
                Greeting(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(viewModel: ArtSpaceViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Art Space",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = dimensionResource(R.dimen.padding_vertical)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when (uiState.currentStep) {
                        1 -> {
                            LemonTextAndImage(
                                textLabelResourceId = R.string.CamaDes,
                                drawableResourceId = R.drawable.imag1,
                                contentDescriptionResourceId = R.string.cama
                            )
                        }
                        2 -> {
                            LemonTextAndImage(
                                textLabelResourceId = R.string.pijamaDes,
                                drawableResourceId = R.drawable.img2,
                                contentDescriptionResourceId = R.string.pijama
                            )
                        }
                        3 -> {
                            LemonTextAndImage(
                                textLabelResourceId = R.string.LucesDes,
                                drawableResourceId = R.drawable.img3,
                                contentDescriptionResourceId = R.string.luces
                            )
                        }
                        4 -> {
                            LemonTextAndImage(
                                textLabelResourceId = R.string.OvejasDes,
                                drawableResourceId = R.drawable.img4,
                                contentDescriptionResourceId = R.string.oveja
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_horizontal))
                ) {
                    Button(
                        onClick = {
                            viewModel.previousStep()
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Anterior")
                    }

                    Button(
                        onClick = {
                            viewModel.nextStep()
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Siguiente")
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .width(dimensionResource(R.dimen.button_image_width))
                .height(dimensionResource(R.dimen.button_image_height))
                .padding(dimensionResource(R.dimen.button_interior_padding))
        )

        Text(
            text = stringResource(textLabelResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
