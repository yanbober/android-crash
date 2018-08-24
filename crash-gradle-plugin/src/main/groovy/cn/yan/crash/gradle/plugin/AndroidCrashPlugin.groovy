package cn.yan.crash.gradle.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.tasks.ExternalNativeBuildTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidCrashPlugin implements Plugin<Project> {
    void apply(Project project) {
        def isApp = project.plugins.hasPlugin(AppPlugin)
        def isAppLib = project.plugins.hasPlugin(LibraryPlugin)
        if (!isApp && !isAppLib) {
            throw new GradleException("Android Crash Plugin only can be used to android!")
        }

        project.afterEvaluate {
            BaseExtension android = project.extensions.getByType(AppExtension)
            androidVariantRun(project, android)
        }
    }

    private void androidVariantRun(Project project, BaseExtension android) {
        android.applicationVariants.all { ApplicationVariant variant->
            if (variant.ndkCompile) {
                ndkObjDir = variant.ndkCompile.objFolder
            }

            if (variant.externalNativeBuildTasks) {
                variant.externalNativeBuildTasks.each { ExternalNativeBuildTask externalNativeBuildTask->
                    externalNativeBuildTask.doLast {

                        def dirPath = project.buildDir.absolutePath + java.io.File.separator + "outputs"+ java.io.File.separator + "soSymble" + java.io.File.separator + externalNativeBuildTask.variantName
                        def dir = new File(dirPath)
                        if (!dir.exists()) {
                            dir.mkdirs()
                        }

                        project.copy {
                            from externalNativeBuildTask.objFolder.absolutePath
                            into dir.absolutePath
                        }

                        project.exec {
                            executable 'adb'
                            args 'devices'
                        }
                    }
                }
            }
        }
    }
}