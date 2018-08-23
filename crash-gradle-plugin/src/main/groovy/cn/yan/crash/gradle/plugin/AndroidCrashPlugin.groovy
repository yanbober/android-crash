package cn.yan.crash.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidCrashPlugin implements Plugin<Project> {
        void apply(Project project) {
            project.task('testTask') << {
                println "Hello gradle plugin"
            }
        }
}