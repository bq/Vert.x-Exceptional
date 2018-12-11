# Vert.x Exceptional

Vertx Exceptional is a minimalist error handling library. It is intended 
for reporting and rendering errors using the features provided by Vertx.
It is designed to be customizable so you can adapt its behaviour to your
needs.

It supports the common errors of the HTTP protocol but allows its extension
in order to support any error desired by the user.

### Getting started

To pull in this library, simply copy this dependency into your `pom.xml`:

````
<dependency>
  <groupId>com.bq</groupId>
  <artifactId>errorhandler</artifactId>
  <version>1.0</version>
</dependency>
````

Vertx Exceptional gives you `ErrorHandler` interface which, out of the box,
reports the handled errors simply logging them into the console and renders
them as a JSON response with the correspondent status code and a message 
that indicates the cause.

You can implement this interface to alter the handling behaviour in the way 
that suits your needs.

### Sentry integration

Out of the box, Vertx Exceptional is prepared to work with Sentry as error 
tracking tool. To make it work, you only have to configure your Sentry DSN 
using one of the methods detailed below.

Via the properties file:

````
UNDER CONSTRUCTION
````

Via a System Environment Variable:

````
SENTRY_DSN=https://public:private@host:port/1
````

Using either one of the methods shown above, Vertx Exceptional will be prepared
to automatically send to Sentry the information of all the errors handled. 

To dig deeper in the Sentry SDK configuration, please go to its [official documentation](https://docs.sentry.io/clients/java/).

#### Other error tracking tools

If you don't want to use sentry as your error tracking tool, Vertx Exceptional is 
designed to fit other ones…
