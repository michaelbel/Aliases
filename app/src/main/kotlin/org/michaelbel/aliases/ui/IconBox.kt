package org.michaelbel.aliases.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import org.michaelbel.aliases.LauncherIcon

@Composable
fun IconBox(
    icon: LauncherIcon,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .border(
                width = 1.dp,
                color = if (isEnabled) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent,
                shape = MaterialTheme.shapes.large
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon.backgroundRes),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape),
            tint = Color.Unspecified
        )

        Icon(
            painter = painterResource(icon.foregroundRes),
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            tint = Color.Unspecified
        )
    }
}

class IconAliasProvider: PreviewParameterProvider<LauncherIcon> {
    override val values = sequenceOf(
        LauncherIcon.DEFAULT,
        LauncherIcon.PREMIUM,
        LauncherIcon.XMAS
    )
}

@Preview
@Composable
private fun IconBoxPreview(
    @PreviewParameter(IconAliasProvider::class) icon: LauncherIcon
) {
    AppTheme {
        IconBox(
            icon = icon,
            isEnabled = false,
            onClick = {}
        )
    }
}