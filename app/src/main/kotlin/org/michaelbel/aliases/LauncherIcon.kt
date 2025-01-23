package org.michaelbel.aliases

/**
 * Представляет возможные иконки приложения.
 */
enum class LauncherIcon(
    val backgroundRes: Int,
    val foregroundRes: Int
) {
    DEFAULT(
        backgroundRes = R.drawable.ic_launcher_background,
        foregroundRes = R.drawable.ic_launcher_foreground
    ),
    PREMIUM(
        backgroundRes = R.drawable.ic_launcher_background_premium,
        foregroundRes = R.drawable.ic_launcher_foreground_premium
    ),
    XMAS(
        backgroundRes = R.drawable.ic_launcher_background_xmas,
        foregroundRes = R.drawable.ic_launcher_foreground_xmas
    )
}