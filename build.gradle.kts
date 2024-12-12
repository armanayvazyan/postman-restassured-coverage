plugins {
    kotlin("jvm") version "1.8.0"
    `maven-publish`
    java
    signing
}

group = "io.github.armanayvazyan"
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

//sourceSets {
//    getByName("main") {
//        kotlin {
//            // Set the Kotlin source directory
//            srcDir( "src/main/kotlin")
//        }
//    }
//}
//tasks.jar {
//    from(sourceSets["main"].allSource)
//}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "io.github.armanayvazyan"
            artifactId = "postman-restassured-coverage"
            version = "1.0.0"
            from(components["java"])

            pom {
                name.set("Restassured Postman Coverage")
                description.set("Open-source Library which calculates Coverage of RestAssured Endpoints compare with Postman Collection")
                url.set("https://github.com/armanayvazyan/postman-restassured-coverage")
                inceptionYear.set("2023")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("armanayvazyan")
                        name.set("Arman Ayvazyan")
                        email.set("ayvazyan.a.arman@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git:github.com/armanayvazyan/postman-restassured-coverage.git")
                    developerConnection.set("scm:git:ssh://github.com/armanayvazyan/postman-restassured-coverage.git")
                    url.set("https://github.com/armanayvazyan/postman-restassured-coverage")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.properties["ossrhUsername"] as String
                password = project.properties["ossrhPassword"] as String
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

