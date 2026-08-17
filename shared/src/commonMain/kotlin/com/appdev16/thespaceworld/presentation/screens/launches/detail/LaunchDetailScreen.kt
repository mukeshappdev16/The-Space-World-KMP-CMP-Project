package com.appdev16.thespaceworld.presentation.screens.launches.detail

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.appdev16.thespaceworld.presentation.screens.launches.StatusBadge
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.launch?.name ?: "", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
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
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                    
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StatusBadge(status = launch.status?.name ?: "")
                            Text(
                                text = launch.net.split("T").firstOrNull() ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = launch.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = launch.launchServiceProvider?.name ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))

                        // Mission Section
                        SectionTitle(stringResource(Res.string.mission_title))
                        Text(
                            text = launch.mission?.description ?: stringResource(Res.string.no_description),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        if (!launch.failReason.isNullOrBlank()) {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = stringResource(Res.string.fail_reason_label),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onErrorContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = launch.failReason,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Statistics Section
                        SectionTitle(stringResource(Res.string.statistics_title))
                        StatGrid(launch)

                        Spacer(modifier = Modifier.height(24.dp))

                        // Technical Specs Section
                        SectionTitle(stringResource(Res.string.technical_specs_title))
                        DetailInfoRow(stringResource(Res.string.rocket_label), launch.rocket?.configuration?.fullName ?: "N/A")
                        DetailInfoRow(stringResource(Res.string.variant_label), launch.rocket?.configuration?.variant ?: "N/A")
                        DetailInfoRow(stringResource(Res.string.designator_label), launch.launchDesignator ?: "N/A")
                        DetailInfoRow("Slug", launch.slug)

                        Spacer(modifier = Modifier.height(24.dp))

                        // Window Section
                        SectionTitle(stringResource(Res.string.window_title))
                        DetailInfoRow(stringResource(Res.string.window_start_label), launch.windowStart)
                        DetailInfoRow(stringResource(Res.string.window_end_label), launch.windowEnd)

                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Text(
                            text = "${stringResource(Res.string.last_updated_label)}: ${launch.lastUpdated ?: "N/A"}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    HorizontalDivider(modifier = Modifier.padding(bottom = 12.dp))
}

@Composable
fun StatGrid(launch: com.appdev16.thespaceworld.domain.modal.launches.Launch) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            Text(text = subValue, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
        }
    }
}

@Composable
fun DetailInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
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
