### First Section:

* Constructor: 
> Constructor injection instruments the constructor of a class,
which used by the DI framework to inject the dependencies.
The benefits of this injection type are:
    you have to read exactly one method, the constructor, to figure out what are the dependencies of this class.
    you create an immutable class which makes caching and so easier
The drawback here is, again, you can’t distinguish between optional and required dependencies.
The constructor enforces that all fields are set.

* Setter: 
> Setter injection instruments setter methods, one per dependency,
that are used by the DI framework to inject the dependencies.During testing it has the benefit that you mustn’t use
the DI framework or reflection mechanisms but can directly set the dependencies.
The drawback of this approach is: You can construct a class that is in a state where it can’t work.
This is because you can’t distinguish from the outside if a dependency is required or optional.


* Field:
 > This type of injection instruments some kind of reflection mechanism for injecting the required dependencies into the class.
A drawback is, that the chance for creating a class that has multiple responsibilities is higher than
compared to the situation when using Setter or 
>Constructor Injection.
Your classes have tight coupling with your DI container and cannot be used outside of it
You cannot create immutable objects, as you can with constructor injection

### Second Section:
 > It is better to test this class using the mockisty methods. For example
>there is a method in PetTimedCache called "get" which uses one of the internal
>methods of PetRepository (called findById). This method should be tested with a
>mock since we are dealing with functionality of another class here.
> By taking a glimpse into the PetTimedCache we can say that almost all the variables and fields are
  somehow local and cannot be injected into the class remotely. Besides, the behavior of this class
  doesn't have anything specific or worth to be verified behaviorally. The only thing that has to be done
  about this class is to verify states, and their returned values. The only thing that has to be
  injected into the PetTimedCache is the repository which would be easy to mock it. All facts said,
  adopting the Mockist approach would be more compelling.


