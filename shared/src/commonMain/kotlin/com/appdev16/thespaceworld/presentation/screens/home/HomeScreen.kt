package com.appdev16.thespaceworld.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdev16.thespaceworld.presentation.theme.AccentGradient
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.*

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
    onNavigateToLaunches: () -> Unit,
    onNavigateToEvents: () -> Unit,
    onNavigateToAgencies: () -> Unit,
    onNavigateToNews: () -> Unit
) {
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
                title = Res.string.home_agencies_title,
                subtitle = Res.string.home_agencies_subtitle,
                icon = Icons.Default.Business,
                onClick = onNavigateToAgencies
            ),
            HomeMenuItem(
                title = Res.string.home_news_title,
                subtitle = Res.string.home_news_subtitle,
                icon = Icons.Default.Newspaper,
                onClick = onNavigateToNews,
                enabled = false
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(SpaceGradient))
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { 
                        Text(
                            stringResource(Res.string.home_title),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 4.sp
                        ) 
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = stringResource(Res.string.home_description),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                items(menuItems) { item ->
                    HomeCard(
                        title = stringResource(item.title),
                        subtitle = stringResource(item.subtitle),
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
fun HomeCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(enabled = enabled, onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.15f)
        ),
        border = if (enabled) {
            androidx.compose.foundation.BorderStroke(1.dp, Brush.linearGradient(AccentGradient))
        } else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (enabled) Brush.linearGradient(AccentGradient) 
                        else Brush.linearGradient(listOf(Color.Gray.copy(0.3f), Color.Gray.copy(0.1f)))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = if (enabled) MaterialTheme.colorScheme.onPrimary else Color.White.copy(0.3f)
                )
            }
            
            Spacer(modifier = Modifier.width(20.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (enabled) MaterialTheme.colorScheme.onSurface else Color.White.copy(0.4f),
                    letterSpacing = 1.sp
                )
                Text(
                    text = if (enabled) subtitle else stringResource(Res.string.coming_soon),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (enabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) else Color.White.copy(0.2f)
                )
            }

            if (enabled) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
