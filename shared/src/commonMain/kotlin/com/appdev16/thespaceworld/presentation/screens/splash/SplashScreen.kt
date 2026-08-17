package com.appdev16.thespaceworld.presentation.screens.splash

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdev16.thespaceworld.presentation.theme.SpaceGradient
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import thespaceworld.shared.generated.resources.Res
import thespaceworld.shared.generated.resources.app_name
import thespaceworld.shared.generated.resources.explore_universe

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1.1f else 0.8f,
        animationSpec = tween(2000, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(3000)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(SpaceGradient)),
        contentAlignment = Alignment.Center
    ) {
        // Subtle Star Field Effect
        StarField()

        AnimatedVisibility(
            visible = startAnimation,
            enter = fadeIn(animationSpec = tween(1500)) + scaleIn(initialScale = 0.8f, animationSpec = tween(1500)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.scale(scale)
            ) {
                Text(
                    text = stringResource(Res.string.app_name).uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 8.sp
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = stringResource(Res.string.explore_universe).uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    letterSpacing = 4.sp
                )
            }
        }
    }
}

@Composable
fun StarField() {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val starCount = 100
        val random = kotlin.random.Random(42)
        repeat(starCount) {
            drawCircle(
                color = Color.White.copy(alpha = alpha * random.nextFloat()),
                radius = random.nextFloat() * 3f,
                center = androidx.compose.ui.geometry.Offset(
                    random.nextFloat() * size.width,
                    random.nextFloat() * size.height
                )
            )
        }
    }
}
