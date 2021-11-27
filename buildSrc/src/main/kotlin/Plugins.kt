import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.jvm: PluginDependencySpec get() = kotlin("jvm").version(Versions.Plugin.KOTLIN)
