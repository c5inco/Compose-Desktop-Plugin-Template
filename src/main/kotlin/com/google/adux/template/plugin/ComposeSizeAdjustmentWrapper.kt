package com.google.adux.template.plugin

import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.runtime.Composable
import com.intellij.openapi.ui.DialogWrapper
import java.awt.Dimension

@Composable
fun ComposeSizeAdjustmentWrapper(
    window: DialogWrapper,
    panel: ComposePanel,
    preferredSize: IntSize,
    content: @Composable () -> Unit
) {
    var packed = false
    Box {
        content()
        Layout(
            content = {},
            modifier = Modifier.onGloballyPositioned { childCoordinates ->
                // adjust size of the dialog
                if (!packed) {
                    val contentSize = childCoordinates.parentCoordinates!!.size
                    panel.preferredSize = Dimension(
                        if (contentSize.width < preferredSize.width) preferredSize.width else contentSize.width,
                        if (contentSize.height < preferredSize.height) preferredSize.height else contentSize.height,
                    )
                    window.pack()
                    packed = true
                }
            },
            measurePolicy = { _, _ ->
                layout(0, 0) {}
            }
        )
    }
}