plugins {
    id("java")
}

group = "software.joe.photoSync"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.photos.library:google-photos-library-client:1.7.3")
    implementation("commons-io:commons-io:2.19.0")
    implementation("org.xerial:sqlite-jdbc:3.50.2.0")
}

tasks.test {
    useJUnitPlatform()
}