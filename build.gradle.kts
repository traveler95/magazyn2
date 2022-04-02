
    val ktor_version: String by project
    val kotlin_version: String by project
    val logback_version: String by project


    plugins {
        application
        kotlin("jvm") version "1.5.30"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
        id("com.github.johnrengelman.shadow") version "6.1.0"

    }
// Required by the 'shadowJar' task
    project.setProperty("mainClassName", "io.ktor.server.netty.EngineMain")
    group = "com.plcoding"
    version = "0.0.1"
    application {
        mainClass.set("io.ktor.server.netty.EngineMain")

    }

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation("io.ktor:ktor-server-core:$ktor_version")
        implementation("io.ktor:ktor-serialization:$ktor_version")
        implementation("io.ktor:ktor-server-host-common:$ktor_version")
        implementation("io.ktor:ktor-server-netty:$ktor_version")
        implementation("ch.qos.logback:logback-classic:$logback_version")
        implementation("io.ktor:ktor-gson:$ktor_version")
        testImplementation("io.ktor:ktor-server-tests:$ktor_version")
        testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")

        //database
        implementation("mysql:mysql-connector-java:8.0.11")
        implementation("org.ktorm:ktorm-core:3.2.0")
        implementation("org.ktorm:ktorm-support-mysql:3.2.0")
    }

    tasks{
        shadowJar {
            manifest {
                attributes(Pair("Main-Class", "com.example.ApplicationKt"))
            }
        }
    }