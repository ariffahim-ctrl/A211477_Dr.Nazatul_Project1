package com.example.a211477_drnazatul_project1.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(courseName: String, onBack: () -> Unit) {
    Scaffold(
        bottomBar = { BuyBottomBar() } // Floating buy button at bottom
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // 1. Header: Video Preview Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Preview",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    "Preview this course",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
                    fontSize = 14.sp
                )

                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            // 2. Course Info Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = courseName,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 32.sp
                )

                Text(
                    text = "Master $courseName, Plugins and Skills to Build AI Applications.",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 15.sp
                )

                // Rating and Students
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("4.6", fontWeight = FontWeight.Bold, color = Color(0xFFE59819))
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFE59819), modifier = Modifier.size(16.dp))
                    Text(" (911 ratings) 10k students", fontSize = 12.sp, color = Color.Gray)
                }

                Text("Created by Prof. Dr. Nazatul", fontSize = 13.sp, color = MaterialTheme.colorScheme.primary)
                Text("Last updated 04/2026 • English", fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                // 3. Pricing Section
                Row(verticalAlignment = Alignment.Bottom) {
                    Text("RM 49.90", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    //Text(
                    //    "RM 49.90",
                    //   fontSize = 16.sp,
                    //  textDecoration = TextDecoration.LineThrough,
                    //   color = Color.Gray
                    // )
                }
                //Text("33% off - 1 day left at this price!", color = Color(0xFFB3261E), fontSize = 12.sp)

                Spacer(modifier = Modifier.height(24.dp))

                // 4. "What you'll learn" Section
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("What you'll learn", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        LearningItem("Build and deploy full-stack $courseName applications.")
                        LearningItem("Understand the concept of 'Agent Skills' and 'Plugins'.")
                        LearningItem("Automate business tasks with specialized AI tools.")
                    }
                }
            }
        }
    }
}

@Composable
fun LearningItem(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 14.sp)
    }
}

@Composable
fun BuyBottomBar() {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { /* Add to Wishlist */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Add to wishlist")
            }
            Button(
                onClick = { /* Buy Logic */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0)) // Purple like the screenshot
            ) {
                Text("Buy now", color = Color.White)
            }
        }
    }
}