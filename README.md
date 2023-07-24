<div align="center">

<a href="https://starly.kr">
    <img src="https://media.discordapp.net/attachments/1038747909551816755/1133168588182790144/signiture.png">
</a>

## StarlyCore
StarlyCore is one of the essential cores responsible for managing the StarlyPlugin.

</div>

## Contact
Join us on DIscord Server:

[![Join us on Discord](https://discord.com/api/guilds/1038714452352192553/widget.png?style=banner2)](https://starly.kr/discord)

## Downloads
Downloads can be obtained from the [downloads page](https://store.starly.kr) or the [downloads API](https://api.starly.kr).

## API

### Dependency Information

Maven
```xml
<repository>
    <id>starly</id>
    <url>https://repo.starly.kr/repository/maven-public/</url>
</repository>
```
```xml
<dependency>
    <groupId>kr.starly.core</groupId>
    <artifactId>StarlyCore</artifactId>
    <version>{version}</version>
    <scope>provided</scope>
</dependency>
```

Gradle
```kotlin
repositories {
    maven {
        url = "https://repo.starly.kr/repository/maven-public/"
    }
}
```
```kotlin
dependencies {
    compileOnly "kr.starly.core:StarlyCore:{version}"
}
```