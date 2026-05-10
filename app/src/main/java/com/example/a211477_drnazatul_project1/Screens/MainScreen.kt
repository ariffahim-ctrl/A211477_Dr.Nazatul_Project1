package com.example.a211477_drnazatul_project1.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211477_drnazatul_project1.data.UserUiState

@Composable
fun MainScreen(
    uiState: UserUiState,
    onNavigateToGoal: () -> Unit,
    onEnrollClick: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { SimpleBottomNavigation(onNavigateToProfile = onNavigateToProfile) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileHeader(userName = uiState.userName)
            MainHeading()

            // Daily Goal Card (Navigates to GoalScreen when clicked)
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onNavigateToGoal() },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Daily Learning Goal", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text("Target: ${uiState.dailyGoal} minutes per day", fontSize = 14.sp)
                    LinearProgressIndicator(
                        progress = 0.3f,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            SimpleSearchBar(onClick = onNavigateToSearch)

            Text("Top courses in AI", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            CourseRow(items = listOf("Machine Learning", "Neural Networks", "AI Ethics"), onEnrollClick)

            Spacer(modifier = Modifier.height(24.dp))
            Text("Web Development", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            CourseRow(items = listOf("HTML & CSS", "React JS", "Node.js"), onEnrollClick)
        }
    }
}

@Composable
fun CourseRow(items: List<String>, onEnrollClick: (String) -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp).horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items.forEach { title ->
            SimpleCard(title, onEnrollClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleCard(title: String, onEnrollClick: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

    Card(
        modifier = Modifier.width(220.dp).padding(vertical = 8.dp).animateContentSize(),
        onClick = { isExpanded = !isExpanded }
    ) {
        Column {
            Surface(modifier = Modifier.fillMaxWidth().height(110.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.PlayArrow, null, modifier = Modifier.size(40.dp))
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.weight(1f), maxLines = 1)
                    Icon(Icons.Default.KeyboardArrowDown, null, modifier = Modifier.rotate(rotationState))
                }
                Text("RM 49.00", color = MaterialTheme.colorScheme.primary)

                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("⭐ 4.8 • 1.2k students", fontSize = 11.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { onEnrollClick(title) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Enroll Now", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

// Helper Components
@Composable
fun ProfileHeader(userName: String) { // Added this parameter
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(45.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(contentAlignment = Alignment.Center) {
                // Bonus: Use the first letter of the name for the avatar
                Text(userName.take(1).uppercase())
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        // This is the key change: use the variable, not a fixed string!
        Text("Welcome, $userName", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun MainHeading() {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        Text("Learning that fits", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Text("Skills for your future", color = Color.Gray)
    }
}

@Composable
fun SimpleSearchBar(onClick: () -> Unit) { // Add parameter
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(bottom = 8.dp)
            .clickable { onClick() }, // This makes the whole bar a button
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Search courses...", fontSize = 14.sp)
        }
    }
}


@Composable
fun SimpleBottomNavigation(onNavigateToProfile: () -> Unit) { // Added parameter
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.PlayArrow, null) },
            label = { Text("Courses") }
        )
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToProfile, // No longer red!
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Account") }
        )
    }
}