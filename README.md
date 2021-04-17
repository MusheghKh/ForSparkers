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

#### Health check endpoint(Get method)
> localhost:8080/actuator/health

response example
```json
{
    "status": "UP"
}
```

#### Get first 10 partners endpoint(Get method)
> localhost:8080/api/partner

response example
```json
[
    {
        "id"                : 1,
        "name"              : "Bells & Whistles",
        "reference"         : "xxxxxx",
        "locale"            : "en_ES",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 2,
        "name"              : "Emanuel Greer",
        "reference"         : "xxxxxx2",
        "locale"            : "en_GB",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 3,
        "name"              : "Toby Blair",
        "reference"         : "xxxxxx3",
        "locale"            : "fr_BE",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 4,
        "name"              : "Sara Vasquez",
        "reference"         : "xxxxxx4",
        "locale"            : "fr_FR",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 5,
        "name"              : "Calvin Clark",
        "reference"         : "xxxxxx5",
        "locale"            : "de_DE",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    }
]
```

#### You can also use query parameters like this, for desired partners count and page(Get method)
> localhost:8080/api/partner?from=1&size=7

response example
```json
[
    {
        "id"                : 1,
        "name"              : "Bells & Whistles",
        "reference"         : "xxxxxx",
        "locale"            : "en_ES",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 2,
        "name"              : "Emanuel Greer",
        "reference"         : "xxxxxx2",
        "locale"            : "en_GB",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 3,
        "name"              : "Toby Blair",
        "reference"         : "xxxxxx3",
        "locale"            : "fr_BE",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 4,
        "name"              : "Sara Vasquez",
        "reference"         : "xxxxxx4",
        "locale"            : "fr_FR",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    },
    {
        "id"                : 5,
        "name"              : "Calvin Clark",
        "reference"         : "xxxxxx5",
        "locale"            : "de_DE",
        "expirationTime"    : "2017-10-03T12:18:46+00:00"
    }
]
```

#### Get partner by id endpoint(Get method)
> localhost:8080/api/partner/{id}

response example
```json
{
  "id"                : 5,
  "name"              : "Calvin Clark",
  "reference"         : "xxxxxx5",
  "locale"            : "de_DE",
  "expirationTime"    : "2017-10-03T12:18:46+00:00"
}
```

#### Add new partner endpoint(Post method)
> localhost:8080/api/partner

With Request body like this
```json
{
    "name"              : "Bells & Whistles",
    "reference"         : "xxxxxx",
    "locale"            : "en_ES",
    "expirationTime"    : "2017-10-03T12:18:46+00:00"
}
```

response example
```json
{
  "id"                : 5,
  "name"              : "Calvin Clark",
  "reference"         : "xxxxxx5",
  "locale"            : "de_DE",
  "expirationTime"    : "2017-10-03T12:18:46+00:00"
}
```

#### Put partner endpoint(Put method)
> localhost:8080/api/partner/{id}

With Request body like this
```json
{
    "name"              : "Bells & Whistles",
    "reference"         : "xxxxxx",
    "locale"            : "en_ES",
    "expirationTime"    : "2017-10-03T12:18:46+00:00"
}
```

response example
```json
{
  "id"                : 5,
  "name"              : "Calvin Clark",
  "reference"         : "xxxxxx5",
  "locale"            : "de_DE",
  "expirationTime"    : "2017-10-03T12:18:46+00:00"
}
```

#### Delete Partner endpoint(Delete method)
> localhost:8080/api/partner/{id}

response does not have body

#### Error body example
```json
{
    "code": 500,
    "message": "Something went wrong"
}
```