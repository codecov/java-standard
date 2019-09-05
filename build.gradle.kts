https://github.com/KengoTODA/gradle-boilerplate/blob/master/build.gradle

ext {
    junitVersion = '5.5.1'
    guavaVersion = '27.1-jre'
    immutablesVersion = '2.7.5'
    errorproneVersion = '2.3.3'
}

plugins {
    id 'java'
    id 'jacoco'
}

repositories {
    mavenCentral()
}


dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'com.google.code.findbugs:jsr305:3.0.2'
    compileOnly "org.immutables:value:${immutablesVersion}:annotations"
    annotationProcessor "org.immutables:value:${immutablesVersion}"
    testImplementation "com.google.guava:guava:${guavaVersion}"
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation "org.junit.jupiter:junit-jupiter-api:${junitVersion}"
    testRuntimeOnly "org.junit.jupiter:junit-jupiter-engine:${junitVersion}"
    errorprone "com.google.errorprone:error_prone_core:${errorproneVersion}"
}


jacoco {
        toolVersion = "0.8.4"
}

tasks.jacocoTestReport {
        reports {
                xml.isEnabled = true
                csv.isEnabled = true
                html.isEnabled = true
        }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform {
        includeEngines("junit-jupiter","spek2")
    }

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events("passed", "failed", "skipped")
    }
}

tasks.withType<KotlinCompile> {

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    kotlinOptions {
        jvmTarget = "1.8"
        apiVersion = "1.3"
        languageVersion = "1.3"
        allWarningsAsErrors = true
    }
}

tasks.wrapper {
    gradleVersion = "5.4.1"
}