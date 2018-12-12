# Vert.x Exceptional

[![CircleCI](https://circleci.com/gh/bq/Vert.x-Exceptional/tree/master.svg?style=svg)](https://circleci.com/gh/bq/Vert.x-Exceptional/tree/master)

Vertx Exceptional is a minimalist error handling library. It is intended 
for reporting and rendering errors using the features provided by Vertx.
It is designed to be customizable so you can adapt its behaviour to your
needs.

It supports the common errors of the HTTP protocol but allows its extension
in order to support any error desired by the user.

## Getting started

To pull in this library, simply copy this dependency into your `pom.xml`:

````
<dependency>
  <groupId>com.bq</groupId>
  <artifactId>errorhandler</artifactId>
  <version>1.0</version>
</dependency>
````

Vertx Exceptional by default is able to report the errors into the terminal, but
it can be integrated with external tracking software such as Sentry. It is also
capable of rendering the different errors as Vertx JSON responses with their 
correspondent status codes and messages.

Below there is an example of how you can start using it within a Vertx handler:

````
...


ErrorReporter errorReporter = new RollbarReporter();
ErrorHandler errorHandler = new ErrorHandler(errorReporter);

Router.router(vertx);

router.get("/ping").handler(routingContext -> {
    try {
        unsafeMethod();
    } catch (Exception e) {
        errorHandler.handleError(routingContext, e);
    }
});

...
````

Assuming `unsafeMethod()` throws an exception which is considered to be an internal 
server error, the method `handleError()` will return the following JSON response:

````
{
  "status": 500,
  "errorCode": 1,
  "msg": "Something unpredictable blow up on your server!",
  "causes": {}
}
````

Every error handled by Vertx Exceptional will have this format, keeping it easy 
for future API clients to manage the errors encountered.

## Sentry integration

Out of the box, Vertx Exceptional is prepared to work with Sentry as error 
tracking tool. To make it work, you only have to configure your Sentry DSN 
using one of the methods detailed below.

Via the properties file `sentry.properties` found in your project's classpath:

````
dsn=https://public:private@host:port/1
````

Via a System Environment Variable:

````
SENTRY_DSN=https://public:private@host:port/1
````

Using either one of the methods shown above, Vertx Exceptional will be prepared
to automatically send to Sentry the information of all the errors handled.
**However** we recommend you to use the properties file method, because this 
allows you to apply all of the configuration options detailed in Sentry's 
[official documentation](https://docs.sentry.io/clients/java/).

### Other error tracking tools

If you don't want to use sentry as your error tracking tool, Vertx Exceptional is 
designed to fit other ones.

For example, if you prefer to use Rollbar, you would have to implement the interface
`ErrorReporter` with the necessary code to make it work and finally inject it into 
the `ErrorHandler` constructor.

````
package com.example.demo;

import com.bq.errorhandler.reporters.ErrorReporter;

public class RollbarReporter implements ErrorReporter {

    @Override
    public ErrorReporter withMessage(String message) {...}

    @Override
    public ErrorReporter withTag(String key, String value) {...}

    @Override
    public void capture(Throwable throwable) {...}
}
````

````
...

ErrorReporter errorReporter = new RollbarReporter();
ErrorHandler errorHandler = new ErrorHandler(errorReporter);

...
````

## Running Vert.x Exceptional as a verticle
_UNDER CONSTRUCTION_ 
