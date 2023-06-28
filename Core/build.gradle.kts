import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import java.net.URI
import java.net.URL

fun property(name: String): String {
    return project.rootProject.property(name).toString()
}

plugins {
    kotlin("jvm") version "1.8.20"
    id("java-library")
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka") version "1.8.20"
}

val versionProject = property("version")
val group = property("version")
val jvmTargetCompatibility = property("jdkVersion").toInt()
val name = property("name")

val moduleName = "core"

val artifactName = name.toLowerCaseAsciiOnly() + "-$moduleName"

repositories {
    mavenCentral()
    maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots/") }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmTargetCompatibility))
    withSourcesJar()
    withJavadocJar()
}

kotlin {
    jvmToolchain(jvmTargetCompatibility)
}

tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.31")
    testImplementation(kotlin("test"))
    platform("org.jetbrains.kotlin:kotlin-bom")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<DokkaTask>().configureEach {
    moduleName                  .set(project.name)
    moduleVersion               .set(project.version.toString())
    outputDirectory             .set(file("build/dokka/Core/$name"))
    failOnWarning               .set(false)
    suppressObviousFunctions    .set(false)
    suppressInheritedMembers    .set(false)
    offlineMode                 .set(false)

    dokkaSourceSets.configureEach {
        displayName             .set(name)
        suppress                .set(displayName.get() != "main")
        reportUndocumented      .set(true)
        skipEmptyPackages       .set(true)
        skipDeprecated          .set(true)
        suppressGeneratedFiles  .set(true)
        jdkVersion              .set(11)
        languageVersion         .set("11")
        apiVersion              .set("11")
        noStdlibLink.           set(false)
        noJdkLink               .set(false)
        noAndroidSdkLink        .set(false)
        includes    .from(project.files(), "Module.md")
        sourceRoots .from(file("Color/src/main/kotlin"))
        samples     .from(project.files(), "src/samples/kotlin/Basic.kt")
        sourceLink {
            localDirectory      .set(file("Core/src/main/kotlin"))
            remoteUrl           .set(URL("https://github.com/KatieUmbra/LoggerCLib/Core/src/main/kotlin"))
            remoteLineSuffix    .set("#L")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = property("group")
            artifactId = property("name").toLowerCaseAsciiOnly()+"-$moduleName"
            version = versionProject

            from(components.findByName("java"))
            pom {
                name            .set("LoggerCLib-color")
                description     .set("LoggerCLib main artifact")
                url             .set("https://github.com/KatieUmbra/loggerclib")
                inceptionYear   .set("2023")

                licenses {
                    license {
                        name    .set("MIT License")
                        url     .set("https://github.com/KatieUmbra/loggerclib/blob/main/LICENSE")
                    }
                }

                developers {
                    developer {
                        id      .set("Kanwi")
                        name    .set("Katherine Chesterfield")
                        email   .set("katherine@kaytea.dev")
                    }
                }

                scm {
                    connection          .set("scm:git:git:github.com/KatieUmbra/loggerclib.git")
                    developerConnection .set("scm:git:ssh://github.com/KatieUmbra/loggerclib.git")
                    url                 .set("https://github.com/KatieUmbra/loggerclib")
                }
            }
            repositories {
                maven {
                    name = "OSSRH"
                    url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = project.rootProject.properties["ossrhUsername"].toString()
                        password = project.rootProject.properties["ossrhPassword"].toString()
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications.findByName("mavenJava"))
}