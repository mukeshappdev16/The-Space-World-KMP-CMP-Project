package com.appdev16.thespaceworld.presentation.screens.launches.detail

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.appdev16.thespaceworld.presentation.screens.launches.StatusBadge
import com.appdev16.thespaceworld.presentation.theme.AccentGradient
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme
import com.appdev16.thespaceworld.presentation.util.MockData
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.*

@Composable
fun LaunchDetailScreen(
    viewModel: LaunchDetailViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    LaunchDetailContent(
        state = state,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailContent(
    state: LaunchDetailUiState,
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
                    title = { Text(state.launch?.name ?: "", fontWeight = FontWeight.Black, letterSpacing = 1.sp) },
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
                } else if (state.launch != null) {
                    val launch = state.launch
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = launch.image?.imageUrl ?: "",
                            contentDescription = launch.name,
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                StatusBadge(status = launch.status?.name ?: "")
                                Text(
                                    text = launch.net.split("T").firstOrNull() ?: "",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(20.dp))
                            
                            Text(
                                text = launch.name,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            
                            Text(
                                text = launch.launchServiceProvider?.name ?: "",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            
                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle(stringResource(Res.string.mission_title))
                            Text(
                                text = launch.mission?.description ?: stringResource(Res.string.no_description),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )

                            if (!launch.failReason.isNullOrBlank()) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF44336).copy(alpha = 0.1f)),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF44336).copy(alpha = 0.5f)),
                                    modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = stringResource(Res.string.fail_reason_label).uppercase(),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color(0xFFE57373),
                                            fontWeight = FontWeight.Black
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = launch.failReason,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.White.copy(alpha = 0.9f)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle(stringResource(Res.string.statistics_title))
                            StatGrid(launch)

                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle(stringResource(Res.string.technical_specs_title))
                            DetailInfoRow(stringResource(Res.string.rocket_label), launch.rocket?.configuration?.fullName ?: "N/A")
                            DetailInfoRow(stringResource(Res.string.variant_label), launch.rocket?.configuration?.variant ?: "N/A")
                            DetailInfoRow(stringResource(Res.string.designator_label), launch.launchDesignator ?: "N/A")

                            Spacer(modifier = Modifier.height(32.dp))

                            SectionTitle(stringResource(Res.string.window_title))
                            DetailInfoRow(stringResource(Res.string.window_start_label), launch.windowStart)
                            DetailInfoRow(stringResource(Res.string.window_end_label), launch.windowEnd)

                            Spacer(modifier = Modifier.height(48.dp))
                            
                            Text(
                                text = "${stringResource(Res.string.last_updated_label)}: ${launch.lastUpdated ?: "N/A"}",
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

@Composable
fun SectionTitle(title: String) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 2.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.linearGradient(
                        listOf(MaterialTheme.colorScheme.primary, Color.Transparent)
                    )
                )
        )
    }
}

@Composable
fun StatGrid(launch: com.appdev16.thespaceworld.domain.modal.launches.Launch) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(
                modifier = Modifier.weight(1f),
                label = stringResource(Res.string.agency_attempts_label),
                value = launch.agencyLaunchAttemptCount.toString(),
                subValue = "Year: ${launch.agencyLaunchAttemptCountYear}",
                icon = Icons.Default.Business
            )
            StatCard(
                modifier = Modifier.weight(1f),
                label = stringResource(Res.string.location_attempts_label),
                value = launch.locationLaunchAttemptCount.toString(),
                subValue = "Year: ${launch.locationLaunchAttemptCountYear}",
                icon = Icons.Default.Place
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(
                modifier = Modifier.weight(1f),
                label = stringResource(Res.string.orbital_attempts_label),
                value = launch.orbitalLaunchAttemptCount.toString(),
                subValue = "Year: ${launch.orbitalLaunchAttemptCountYear}",
                icon = Icons.Default.Public
            )
            StatCard(
                modifier = Modifier.weight(1f),
                label = "Pad Attempts",
                value = launch.padLaunchAttemptCount.toString(),
                subValue = "Year: ${launch.padLaunchAttemptCountYear}",
                icon = Icons.Default.Dataset
            )
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    subValue: String,
    icon: ImageVector
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = value, 
                style = MaterialTheme.typography.headlineSmall, 
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Text(
                text = label, 
                style = MaterialTheme.typography.labelSmall, 
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subValue, 
                style = MaterialTheme.typography.labelSmall, 
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun DetailInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun LaunchDetailPreview() {
    TheSpaceWorldTheme {
        LaunchDetailContent(
            state = LaunchDetailUiState(
                isLoading = false,
                launch = MockData.launch
            ),
            onNavigateBack = {}
        )
    }
}
