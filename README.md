# coffee-shop
A coffee shop spring boot project.

## Development

To run in development environment, do:

```
mvn clean install
```

and run CoffeeShopApplication.

## Deploying

To deploy application in production mode, do:

```
mvn package
```

This will build the jar. The build docker image with the following command:

```
docker build -t assessment/coffeeshop .
```

This will build the docker image. It can be deployed to a server.

We can run the docker image locally with:

```
docker run -d -p 8080:8080 assessment/coffeeshop
```

and it will be available on port 8080 of our machine.
