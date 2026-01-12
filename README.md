# JavaMutationTestingSandbox
Sample repo showing how to enforce mutation testing threshold gates on pull requests

## Building

```bash
mvn package
```

## Running unit tests

```bash
# just running the unit tests
mvn test

# running the tests with mutations
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```
## Running embedded web server

```bash
mvn spring-boot:run
```

## Testing the API

With web server running, navigate to
```
http://localhost:8080/swagger-ui/index.html
```
