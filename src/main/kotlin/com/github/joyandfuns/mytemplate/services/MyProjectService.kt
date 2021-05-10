package com.github.joyandfuns.mytemplate.services

import com.github.joyandfuns.mytemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
