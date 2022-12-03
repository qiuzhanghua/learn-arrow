plugins {
	java
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.google.osdetector") version "1.7.1"
	application
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

// ${osdetector.classifier}
dependencies {
	implementation("org.apache.arrow:arrow-vector:10.0.1")
	implementation("org.apache.arrow:arrow-memory-netty:10.0.1")
	implementation("org.apache.arrow:flight-core:10.0.1")
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

application {
	mainClass.set("com.example.learnarrow.App")
}

tasks.withType<JavaExec> {
	jvmArgs("--add-opens=java.base/java.nio=ALL-UNNAMED")
}