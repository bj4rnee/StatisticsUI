plugins {
    java
    id("com.gradleup.shadow") version "9.6.1"
}

group = "dev.bjarne"
version = "2.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    // Spigot API. Compiling against the Bukkit/Spigot surface keeps the plugin loadable on
    // both raw Spigot and Paper; Paper-only calls simply will not compile.
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") { name = "spigot" }
    maven("https://oss.sonatype.org/content/repositories/snapshots/") { name = "sonatype" }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT")
    // Gson ships with the server on both Spigot and Paper, so it stays off the plugin jar.
    compileOnly("com.google.code.gson:gson:2.11.0")
    // bStats must be shaded and relocated into the jar (see shadowJar below).
    implementation("org.bstats:bstats-bukkit:3.2.1")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}

tasks.processResources {
    filteringCharset = "UTF-8"
    val props = mapOf("version" to project.version.toString())
    inputs.properties(props)
    filesMatching("plugin.yml") { expand(props) }
}

tasks.shadowJar {
    archiveClassifier.set("")
    relocate("org.bstats", "dev.bjarne.statisticsui.bstats")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
