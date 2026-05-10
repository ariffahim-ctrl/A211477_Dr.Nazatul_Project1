package com.example.a211477_drnazatul_project1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211477_drnazatul_project1.data.UserUiState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun ProfileScreen(
    uiState: UserUiState,
    onSaveProfile: (String, String) -> Unit, // Updated to take two strings
    onBack: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var tempName by remember { mutableStateOf(uiState.userName) }
    var tempEmail by remember { mutableStateOf(uiState.userEmail) }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Added scroll in case screen is small
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Account", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(30.dp))

            // Profile Avatar
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(uiState.userName.take(1).uppercase(), fontSize = 36.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!isEditing) {
                // --- VIEW MODE ---
                Text(uiState.userName, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(uiState.userEmail, color = MaterialTheme.colorScheme.outline)

                Button(
                    onClick = { isEditing = true },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer)
                ) {
                    Text("Edit Profile")
                }

                Spacer(modifier = Modifier.height(32.dp))

                // dummy item
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Video preferences", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    AccountOptionItem("Download options")
                    AccountOptionItem("Video playback options")

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Account settings", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    AccountOptionItem("Occupation and interests")
                    AccountOptionItem("Push notifications")
                }

            } else {
                // --- EDIT MODE ---
                Text("Edit Information", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = tempName,
                    onValueChange = { tempName = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tempEmail,
                    onValueChange = { tempEmail = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onSaveProfile(tempName, tempEmail)
                        isEditing = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm Changes")
                }
            }

            TextButton(onClick = onBack, modifier = Modifier.padding(top = 16.dp)) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun AccountOptionItem(title: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontSize = 16.sp)
            Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.outline)
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}