package com.appdev16.thespaceworld.presentation.screens.events.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.appdev16.thespaceworld.domain.modal.events.Event
import com.appdev16.thespaceworld.presentation.screens.launches.detail.DetailInfoRow
import com.appdev16.thespaceworld.presentation.screens.launches.detail.SectionTitle
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme
import com.appdev16.thespaceworld.presentation.util.MockData
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.*

@Composable
fun EventDetailScreen(
    viewModel: EventDetailViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    EventDetailContent(
        state = state,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailContent(
    state: EventDetailUiState,
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(SpaceGradient))
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text(state.event?.name ?: "", fontWeight = FontWeight.Black, letterSpacing = 1.sp) },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.back),
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White
                    )
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else if (state.event != null) {
                    val event = state.event
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = event.image?.imageUrl ?: "",
                            contentDescription = event.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp),
                            contentScale = ContentScale.Crop
                        )
                        
                        Column(
                            modifier = Modifier
                                .offset(y = (-30).dp)
                                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                                .background(MaterialTheme.colorScheme.background)
                                .padding(24.dp)
                        ) {
                            Text(
                                text = event.date.split("T").firstOrNull() ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                fontWeight = FontWeight.Bold
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
                                text = event.name,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            
                            Text(
                                text = event.type?.name ?: "",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            
                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle("DESCRIPTION")
                            Text(
                                text = event.description.ifEmpty { "No description available." },
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )

                            if (event.location.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(32.dp))
                                SectionTitle("LOCATION")
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Place,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = event.location,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle("DETAILS")
                            DetailInfoRow("Precision", event.datePrecision?.name ?: "N/A")
                            DetailInfoRow("Type", event.type?.name ?: "N/A")
                            DetailInfoRow("Webcast Live", if (event.webcastLive) "Yes" else "No")
                            if (event.url.isNotEmpty()) {
                                DetailInfoRow("More Info", event.url)
                            }

                            Spacer(modifier = Modifier.height(48.dp))
                            
                            Text(
                                text = "Last Updated: ${event.lastUpdated}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EventDetailPreview() {
    TheSpaceWorldTheme {
        EventDetailContent(
            state = EventDetailUiState(
                isLoading = false,
                event = MockData.event
            ),
            onNavigateBack = {}
        )
    }
}
