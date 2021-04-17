# ForSparkers

ForSparkers is small Rest Api developed with [Spring Boot](http://projects.spring.io/spring-boot/).

## Run the app

You must hove preinstalled [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) in your computer.

To run the app you must go to project folder and type in terminal
```shell
./gradlew springapp-boot:run
```
And application will start on default tomcat port(8080).

To run app tests type in terminal
```shell
./gradlew clean test -i
```

## Endpoints

### Health check endpoint(Get method)
> localhost:8080/actuator/health

### Get first 10 partners endpoint(Get method)
> localhost:8080/api/partner

### You can also use query parameters like this, for desired partners count and page(Get method)
> localhost:8080/api/partner?from=1&size=7

### Get partner by id endpoint(Get method)
> localhost:8080/api/partner/{id}

### Add new partner endpoint(Post method)
> localhost:8080/api/partner

With Request body like this
>{
>    "name"              : "Bells & Whistles",
>    "reference"         : "xxxxxx",
>    "locale"            : "en_ES",
>    "expirationTime"    : "2017-10-03T12:18:46+00:00"
>}

### Put partner endpoint(Put method)
> localhost:8080/api/partner/{id}

With Request body like this
>{
>    "name"              : "Bells & Whistles",
>    "reference"         : "xxxxxx",
>    "locale"            : "en_ES",
>    "expirationTime"    : "2017-10-03T12:18:46+00:00"
>}

### Delete Partner endpoint(Delete method)
> localhost:8080/api/partner/{id}