package com.appdev16.thespaceworld.presentation.screens.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.presentation.theme.AccentGradient
import com.appdev16.thespaceworld.presentation.theme.SpaceCyan
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import com.appdev16.thespaceworld.presentation.theme.SpacePurple
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.*
import kotlin.random.Random

data class HomeMenuItem(
    val title: StringResource,
    val subtitle: StringResource,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val enabled: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToLaunches: () -> Unit,
    onNavigateToLaunchDetail: (String) -> Unit,
    onNavigateToEvents: () -> Unit,
    onNavigateToAgencies: () -> Unit,
    onNavigateToAstronauts: () -> Unit,
    onNavigateToSpaceStations: () -> Unit,
    onNavigateToSpacecrafts: () -> Unit,
    onNavigateToLocations: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    val menuItems = remember {
        listOf(
            HomeMenuItem(
                title = Res.string.home_launches_title,
                subtitle = Res.string.home_launches_subtitle,
                icon = Icons.Default.RocketLaunch,
                onClick = onNavigateToLaunches
            ),
            HomeMenuItem(
                title = Res.string.home_events_title,
                subtitle = Res.string.home_events_subtitle,
                icon = Icons.Default.Event,
                onClick = onNavigateToEvents
            ),
            HomeMenuItem(
                title = Res.string.home_stations_title,
                subtitle = Res.string.home_stations_subtitle,
                icon = Icons.Default.HomeWork,
                onClick = onNavigateToSpaceStations
            ),
            HomeMenuItem(
                title = Res.string.home_spacecrafts_title,
                subtitle = Res.string.home_spacecrafts_subtitle,
                icon = Icons.Default.Rocket,
                onClick = onNavigateToSpacecrafts
            ),
            HomeMenuItem(
                title = Res.string.home_locations_title,
                subtitle = Res.string.home_locations_subtitle,
                icon = Icons.Default.Place,
                onClick = onNavigateToLocations
            ),
            HomeMenuItem(
                title = Res.string.home_agencies_title,
                subtitle = Res.string.home_agencies_subtitle,
                icon = Icons.Default.Business,
                onClick = onNavigateToAgencies
            ),
            HomeMenuItem(
                title = Res.string.home_astronauts_title,
                subtitle = Res.string.home_astronauts_subtitle,
                icon = Icons.Default.Person,
                onClick = onNavigateToAstronauts
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF06070E)) // Deep Space Base
    ) {
        // Innovative Background: Animated Nebula & Stars
        AnimatedSpaceBackground()

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            stringResource(Res.string.home_title),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 4.sp,
                            color = SpaceCyan
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Futuristic Hero Section
                item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                    FuturisticHero(
                        launch = state.nextLaunch,
                        isLoading = state.isLoading,
                        onClick = {
                            state.nextLaunch?.id?.let(onNavigateToLaunchDetail) ?: onNavigateToLaunches()
                        }
                    )
                }

                item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                    Row(
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp, 2.dp)
                                .background(SpaceCyan, CircleShape)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "MISSION CONTROL",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = SpaceCyan.copy(alpha = 0.8f),
                            letterSpacing = 2.sp
                        )
                    }
                }

                items(menuItems) { item ->
                    InnovativeGlassCard(
                        title = stringResource(item.title),
                        icon = item.icon,
                        onClick = item.onClick,
                        enabled = item.enabled
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedSpaceBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    
    // Nebula movement
    val nebulaOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(40000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Layer 1: Stars
        Canvas(modifier = Modifier.fillMaxSize()) {
            val random = Random(42)
            repeat(100) {
                val x = random.nextFloat() * size.width
                val y = random.nextFloat() * size.height
                val radius = random.nextFloat() * 2f
                val alpha = random.nextFloat() * 0.7f + 0.3f
                drawCircle(
                    color = Color.White,
                    radius = radius,
                    center = Offset(x, y),
                    alpha = alpha
                )
            }
        }

        // Layer 2: Animated Nebula Glows
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(80.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(SpacePurple.copy(alpha = 0.15f), Color.Transparent),
                        center = Offset(size.width * 0.2f, size.height * 0.3f + (nebulaOffset % 200f)),
                        radius = size.width * 0.6f
                    ),
                    radius = size.width * 0.6f,
                    center = Offset(size.width * 0.2f, size.height * 0.3f)
                )
                
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(SpaceCyan.copy(alpha = 0.1f), Color.Transparent),
                        center = Offset(size.width * 0.8f, size.height * 0.7f - (nebulaOffset % 150f)),
                        radius = size.width * 0.5f
                    ),
                    radius = size.width * 0.5f,
                    center = Offset(size.width * 0.8f, size.height * 0.7f)
                )
            }
        }
    }
}

@Composable
fun FuturisticHero(
    launch: Launch?,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .scale(glowScale)
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White.copy(alpha = 0.03f))
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(SpaceCyan.copy(0.3f), SpacePurple.copy(0.3f), Color.Transparent)
                ),
                shape = RoundedCornerShape(32.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color.White.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SpaceCyan.copy(0.3f))
                ) {
                    Text(
                        text = "LIVE TELEMETRY",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = SpaceCyan,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                }
                
                Icon(
                    imageVector = Icons.Default.SignalCellularAlt,
                    contentDescription = null,
                    tint = SpaceCyan,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column {
                if (isLoading && launch == null) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().height(2.dp).clip(CircleShape),
                        color = SpaceCyan,
                        trackColor = Color.White.copy(0.1f)
                    )
                } else {
                    Text(
                        text = launch?.name?.uppercase() ?: "SCANNING FOR LAUNCHES...",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        maxLines = 1
                    )
                    Text(
                        text = launch?.launchServiceProvider?.name ?: "UNKNOWN AGENCY",
                        style = MaterialTheme.typography.titleSmall,
                        color = SpaceCyan.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "TARGET WINDOW",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.4f)
                    )
                    Text(
                        text = launch?.net?.split("T")?.firstOrNull() ?: "-- -- --",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Surface(
                    color = SpaceCyan,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.clickable { onClick() }
                ) {
                    Text(
                        text = "DETAILS",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun InnovativeGlassCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.9f)
            .clip(RoundedCornerShape(32.dp))
            .clickable(enabled = enabled) { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.02f))
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        listOf(Color.White.copy(0.15f), Color.Transparent, Color.White.copy(0.05f))
                    ),
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(
                            if (enabled) SpaceCyan.copy(alpha = 0.08f)
                            else Color.White.copy(alpha = 0.03f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = if (enabled) SpaceCyan else Color.White.copy(0.2f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    color = if (enabled) Color.White else Color.White.copy(0.3f),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}
