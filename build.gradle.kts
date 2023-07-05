plugins {
    kotlin("jvm") version "1.8.0"
    `maven-publish`
    java
}

group = "com.armanayvazyan"
version = "1.0.0"

repositories {
    mavenCentral()
}

val gsonVersion = "2.10.1"
val restAssuredVersion = "5.3.1"

dependencies {
    implementation("io.rest-assured:rest-assured:${restAssuredVersion}")
    implementation("com.google.code.gson:gson:${gsonVersion}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

sourceSets {
    getByName("main") {
        kotlin {
            // Set the Kotlin source directory
            srcDir( "src/main/kotlin")
        }
    }
}
tasks.jar {
    from(sourceSets["main"].allSource)
}

publishing {
    repositories {
        maven {
            val ghb_username: String by project
            val ghb_password: String by project
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/armanayvazyan/postman-restassured-coverage")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: ghb_username
                password = project.findProperty("gpr.key") as String? ?: ghb_password
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}