package com.google.adux.template.plugin

import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import javax.swing.JComponent

class MyToolWindowFactory : ToolWindowFactory {
    /**
     * Create the tool window content.
     *
     * @param project    current project
     * @param toolWindow current tool window
     */
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(createCenterPanel(), "", false)
        toolWindow.contentManager.addContent(content)
    }

    fun createCenterPanel(): JComponent {
        return ComposePanel().apply {
            setContent {
                Thread.currentThread().contextClassLoader = PluginAction::class.java.classLoader
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        Modifier.padding(8.dp)
                    ) {
                        Text("This is a tool window.")
                    }
                }
            }
        }
    }
}