import org.gradle.configurationcache.extensions.capitalized


plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.1.2"

}

repositories{
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
    maven("https://jitpack.io")
}

dependencies {

    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("team.unnamed:inject:1.0.1")
    compileOnly("org.spongepowered:configurate-hocon:4.0.0")
    compileOnly("me.fixeddev:commandflow-bukkit:0.6.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name.capitalized())
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
