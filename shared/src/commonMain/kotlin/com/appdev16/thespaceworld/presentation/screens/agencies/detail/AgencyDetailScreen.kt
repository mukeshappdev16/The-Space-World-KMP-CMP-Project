package com.appdev16.thespaceworld.presentation.screens.agencies.detail

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
import com.appdev16.thespaceworld.presentation.screens.launches.detail.DetailInfoRow
import com.appdev16.thespaceworld.presentation.screens.launches.detail.SectionTitle
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme
import com.appdev16.thespaceworld.presentation.util.MockData
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.*

@Composable
fun AgencyDetailScreen(
    viewModel: AgencyDetailViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    AgencyDetailContent(
        state = state,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyDetailContent(
    state: AgencyDetailUiState,
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
                    title = { Text(state.agency?.abbrev ?: "AGENCY", fontWeight = FontWeight.Black, letterSpacing = 1.sp) },
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
                } else if (state.agency != null) {
                    val agency = state.agency
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .background(Color.White.copy(alpha = 0.05f)),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = agency.image?.imageUrl ?: "",
                                contentDescription = agency.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            
                            // Logo overlay
                            if (agency.logo != null) {
                                Surface(
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(16.dp)
                                        .size(80.dp),
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp),
                                    tonalElevation = 8.dp
                                ) {
                                    AsyncImage(
                                        model = agency.image?.imageUrl,
                                        contentDescription = "Logo",
                                        modifier = Modifier.padding(8.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }
                        }
                        
                        Column(
                            modifier = Modifier
                                .offset(y = (-20).dp)
                                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                                .background(MaterialTheme.colorScheme.background)
                                .padding(24.dp)
                        ) {
                            Text(
                                text = agency.name,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            
                            if (!agency.abbrev.isNullOrEmpty()) {
                                Text(
                                    text = agency.abbrev,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(24.dp))

                            SectionTitle("DESCRIPTION")
                            Text(
                                text = agency.description ?: "No description available.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle("STATISTICS")
                            AgencyStatGrid(agency)

                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle("OVERVIEW")
                            DetailInfoRow("Type", agency.type?.name ?: "N/A")
                            DetailInfoRow("Country", agency.countryCode ?: "N/A")
                            DetailInfoRow("Administrator", agency.administrator ?: "N/A")
                            DetailInfoRow("Founding Year", agency.foundingYear ?: "N/A")

                            if (!agency.wikiUrl.isNullOrEmpty() || !agency.infoUrl.isNullOrEmpty()) {
                                Spacer(modifier = Modifier.height(32.dp))
                                SectionTitle("RESOURCES")
                                if (!agency.infoUrl.isNullOrEmpty()) {
                                    DetailInfoRow("Official Website", agency.infoUrl)
                                }
                                if (!agency.wikiUrl.isNullOrEmpty()) {
                                    DetailInfoRow("Wikipedia", agency.wikiUrl)
                                }
                            }

                            Spacer(modifier = Modifier.height(48.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AgencyDetailPreview() {
    TheSpaceWorldTheme {
        AgencyDetailContent(
            state = AgencyDetailUiState(
                isLoading = false,
                agency = MockData.agency
            ),
            onNavigateBack = {}
        )
    }
}

@Composable
fun AgencyStatGrid(agency: com.appdev16.thespaceworld.domain.modal.agencies.Agency) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            com.appdev16.thespaceworld.presentation.screens.launches.detail.StatCard(
                modifier = Modifier.weight(1f),
                label = "Total Launches",
                value = agency.totalLaunchCount.toString(),
                subValue = "All Time",
                icon = Icons.Default.RocketLaunch
            )
            com.appdev16.thespaceworld.presentation.screens.launches.detail.StatCard(
                modifier = Modifier.weight(1f),
                label = "Successful",
                value = agency.successfulLaunches.toString(),
                subValue = "Missions",
                icon = Icons.Default.CheckCircle
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            com.appdev16.thespaceworld.presentation.screens.launches.detail.StatCard(
                modifier = Modifier.weight(1f),
                label = "Failed",
                value = agency.failedLaunches.toString(),
                subValue = "Missions",
                icon = Icons.Default.Error
            )
            com.appdev16.thespaceworld.presentation.screens.launches.detail.StatCard(
                modifier = Modifier.weight(1f),
                label = "Pending",
                value = agency.pendingLaunches.toString(),
                subValue = "Scheduled",
                icon = Icons.Default.Upcoming
            )
        }
    }
}
