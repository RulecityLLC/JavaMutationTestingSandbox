# JavaMutationTestingSandbox
Sample repo showing how to enforce mutation testing threshold gates on pull request

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

## Self-guided tutorial to observe how the mutation testing gate works

Let's say we want to enhance our project by adding the ability to find a user by their name.  While it would be more efficient to add functionality like this to our repository class, for educational purposes, we will add it to our service class instead.

Inside UserService, add a new method:

```
    /**
     * Get a user by their name
     * @param name The user's name
     * @return The user object
     * @throws NotFoundException If one matching user cannot be found
     */
    User getByName(String name) throws NotFoundException;
```

Inside UserServiceImpl, implement this method:

```
    @Override
    public User getByName(String name) throws NotFoundException
    {
        List<User> all = repo.getAll();
        List<User> result = all.stream().filter(m -> m.name().equals(name)).toList();
        if (result.isEmpty())
        {
            throw new NotFoundException(null);
        }
        if (result.size() != 1)
        {
            throw new RuntimeException("More than one user has that name!");
        }
        return result.getFirst();
    }
```

Inside UserServiceImplTest, add a new test method that does not properly assert on the result:

```
    @Test
    public void getByNameTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User user1 = new User(id, name, email);

        List<User> expected = new ArrayList<>();
        expected.add(user1);

        when(mockRepo.getAll()).thenReturn(expected);

        // ACT

        User actual = instance.getByName(name);

        // ASSERT
    }
```

Create a pull request.  The mutation testing gate should fail with an error about the mutation score being below the threshold.

Download and view the mutation report from the pull request.  It will be in the artifacts section of the pull request run.
Take note of where mutations (defects) are being added and how the test we added isn't catching this defect.

Improve this test by adding an assert at the end of the test so it now looks like this:

```
    @Test
    public void getByNameTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User user1 = new User(id, name, email);

        List<User> expected = new ArrayList<>();
        expected.add(user1);

        when(mockRepo.getAll()).thenReturn(expected);

        // ACT

        User actual = instance.getByName(name);

        // ASSERT
        assertEquals(user1, actual);
    }
```

Download and view the new mutation report and note how our improved test is now catching at least one more of the defects that the mutation testing framework is injecting.
