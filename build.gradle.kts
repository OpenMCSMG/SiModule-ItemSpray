// 27
plugins {
    kotlin("jvm") version "1.9.20"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

version = "1.0.1"

repositories {
    maven("https://nexus.cyanbukkit.cn/repository/maven-public")
    maven("https://repo.loohpjames.com/repository")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
}


bukkit {
    main = "cn.cyanbukkit.shit.ItemSpray"
    name = "ItemSpray"
    version = project.version.toString()
    description = ""
    website = "https://cyanbukkit.net"
    //depend = listOf("CYANKOTLINLOADER", "PlayerPoints")
}

kotlin {
    jvmToolchain(8)
}


tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveFileName.set("ItemSpray-1.0.1.jar")
    }

}
