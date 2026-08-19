package com.appdev16.thespaceworld.presentation.screens.agencies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import com.appdev16.thespaceworld.presentation.theme.TheSpaceWorldTheme
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.*

@Composable
fun AgenciesListScreen(
    viewModel: AgenciesViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    AgenciesListContent(
        state = state,
        onNavigateBack = onNavigateBack,
        onLoadNextPage = { viewModel.onAction(AgenciesAction.LoadNextPage) },
        onNavigateToDetail = onNavigateToDetail
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgenciesListContent(
    state: AgenciesUiState,
    onNavigateBack: () -> Unit,
    onLoadNextPage: () -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    val scrollState = rememberLazyListState()

    val shouldLoadNextPage = remember(scrollState, state.agencies.size, state.isLoading, state.isPaginationLoading, state.endReached) {
        derivedStateOf {
            val layoutInfo = scrollState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            
            lastVisibleItemIndex >= totalItemsNumber - 5 && 
                    !state.isLoading && 
                    !state.isPaginationLoading && 
                    !state.endReached &&
                    state.agencies.isNotEmpty()
        }
    }

    LaunchedEffect(shouldLoadNextPage.value) {
        if (shouldLoadNextPage.value) {
            onLoadNextPage()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(SpaceGradient))
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("SPACE AGENCIES", fontWeight = FontWeight.Black, letterSpacing = 2.sp) },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (state.isLoading && state.agencies.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else if (state.error != null && state.agencies.isEmpty()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center).padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(Res.string.error_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = state.error.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                } else {
                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(state.agencies) { _, agency ->
                            AgencyItem(
                                agency = agency,
                                onClick = { onNavigateToDetail(agency.id) }
                            )
                        }

                        if (state.isPaginationLoading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(32.dp),
                                        strokeWidth = 2.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AgenciesListPreview() {
    TheSpaceWorldTheme {
        AgenciesListContent(
            state = AgenciesUiState(
                agencies = com.appdev16.thespaceworld.presentation.util.MockData.agencies
            ),
            onNavigateBack = {},
            onLoadNextPage = {},
            onNavigateToDetail = {}
        )
    }
}

@Composable
fun AgencyItem(
    agency: Agency,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.15f)
        ),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, Color.White.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = agency.logo?.imageUrl ?: agency.image?.imageUrl ?: "",
                contentDescription = agency.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.1f)),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = agency.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                
                Text(
                    text = agency.abbrev ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                
                if (!agency.countryCode.isNullOrEmpty()) {
                    Text(
                        text = agency.countryCode,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
            
            if (agency.featured) {
                Surface(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "FEATURED",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Black,
                        fontSize = 8.sp
                    )
                }
            }
        }
    }
}
