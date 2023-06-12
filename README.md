# Topinambur Async

![Topinambur](./logo.svg)

Topinambur Async is an extension for Topinambur 
* Use the corouines a really lightweight http library
* Use an object-oriented http library


## How to add Topinambur Async to your project
[![](https://jitpack.io/v/daikonweb/topinambur-async.svg)](https://jitpack.io/#daikonweb/topinambur-async)

### Gradle
- Add JitPack in your root build.gradle at the end of repositories:
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```

- Add the dependency
```
implementation 'com.github.DaikonWeb:topinambur:1.2.4'
implementation 'com.github.DaikonWeb:topinambur-async:1.5.0'
```

### Maven
- Add the JitPack repository to your build file
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
- Add the dependency
```
<dependency>
    <groupId>com.github.DaikonWeb</groupId>
    <artifactId>topinambur</artifactId>
    <version>1.5.0</version>
</dependency>
<dependency>
    <groupId>com.github.DaikonWeb</groupId>
    <artifactId>topinambur-async</artifactId>
    <version>1.5.0</version>
</dependency>
```

## Getting Started

#### Async - Await way

```
runBlocking {
    val response = "https://github.com/DaikonWeb".httpAsync(this).get()

    println(response.await().statusCode)
}
```

#### Launch way

```
runBlocking {
    "https://github.com/DaikonWeb".httpAsync(this).get() { response ->
        println(response.statusCode)
    }
}
```

## Enable request logging as Curl
```
runBlocking { 
    HttpAsyncClient(this, "https://github.com/DaikonWeb", System.out).get().body
}
```
