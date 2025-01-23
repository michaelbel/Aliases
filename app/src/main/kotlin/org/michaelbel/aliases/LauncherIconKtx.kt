package org.michaelbel.aliases

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

/**
 * Проверяет, активна ли указанная иконка.
 * @return true, если иконка включена.
 */
fun Context.isEnabled(icon: LauncherIcon): Boolean {
    val componentName = ComponentName(/* pkg */ packageName, /* cls */ "org.michaelbel.aliases.${icon.name}")
    val componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName)
    return componentEnabledSetting == PackageManager.COMPONENT_ENABLED_STATE_ENABLED || componentEnabledSetting == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT && icon == LauncherIcon.DEFAULT
}

/**
 * Включает указанную иконку, отключая остальные.
 */
fun Context.setEnabled(enabledIcon: LauncherIcon) {
    LauncherIcon.entries.forEach { icon ->
        val componentName = ComponentName(/* pkg */ packageName, /* cls */ "org.michaelbel.aliases.${icon.name}")
        packageManager.setComponentEnabledSetting(
            /* componentName */ componentName,
            /* newState */ if (icon == enabledIcon) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            /* flags */ PackageManager.DONT_KILL_APP
        )
    }
}

/**
 * Устанавливает иконку по умолчанию, если ни одна не активна.
 */
fun Context.installLauncherIcon() {
    LauncherIcon.entries.forEach { icon ->
        if (isEnabled(icon)) return
    }
    setEnabled(LauncherIcon.DEFAULT)
}

/**
 * Возвращает текущую активную иконку приложения.
 * @throws Exception если иконка не найдена.
 */
val Context.enabledIcon: LauncherIcon
    get() = LauncherIcon.entries.find { isEnabled(it) } ?: LauncherIcon.DEFAULT