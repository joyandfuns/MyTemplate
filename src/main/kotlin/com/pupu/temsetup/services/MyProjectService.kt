package com.pupu.temsetup.services

import com.intellij.openapi.project.Project
import com.pupu.temsetup.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
