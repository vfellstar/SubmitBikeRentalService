**CSC8014 Assessed Coursework**

**Bike Rental Application**

The deliverable for this coursework must be submitted via
[NESS](https://ness.ncl.ac.uk/) on or before:

-   **14.30 on Friday 19th Feb 2021**

This is a hard deadline. Work uploaded after this time (even by 1
second) will be flagged as late. Make sure you submit AT LEAST 15
minutes before this deadline.

This coursework contributes 50% of the overall module mark.

**1. Aim**

The aim of this coursework is for you to practice the design principles
covered in lectures. You will develop interfaces and classes to
demonstrate that you have learned and understood module material,
including:

-   appropriate overriding of Object class methods, including overriding `toString` and providing a static `valueOf` method when appropriate

-   design of interface-based hierarchies, programming through interfaces and late binding

-   the use of factories to control instantiation of objects, including
    guaranteeing the instantiation of unique instances

-   defensive programming including the use of immutability

-   the use of appropriate interfaces and classes from the Collections
    framework

-   appropriate use of Javadocs to document your interfaces and classes

The coursework is **not** algorithmically challenging. The focus is on good design and good practice.

The coursework is **not** about development of an end-user application.
You are developing interfaces and classes that could be used for the
development of an application. You should **not** develop a graphical
user interface or a command line interface. They are not necessary and
you will be given no credit for doing so.

**Note** the system specified below is a deliberate
simplification. It is not an accurate model of a real world system.
Your solution should correspond to the simplicity of the specification.
You risk losing marks if you attempt to provide a more realistic model or provide a solution that is more complicated than necessary.

**2. System overview**

A bicycle rental company needs a set of interfaces and classes to manage rentals.

The company rents bicycles from its stock of 50 road bikes and 10 electric bikes. The road bikes can be loaned to any customer with a valid customer number. The electric bikes can only be loaned to "Gold class" customers.

When all bikes of a particular type have been rented out, no more of that type can be issued by the company until one of the rented bikes is returned.

A bike can only be rented out to one person at a time. So, a bike that is out for rent cannot be rented out again until after it has been returned and the existing rental contract terminated. Once a bike has been
returned, it is available for rent again. This means that the status of any given bicycle is either rented to one
person or not rented.

A person can only rent out one bike at a time.

The bike rental company needs to maintain a record of who has rented a given
bike (associating a person\'s customer identifier with the bike they
have rented). The company also needs to be able to issue bikes for rent
and terminate rental contracts on return of the bike. They also require
information on bikes currently out to rent.

The following provides more
detail on the required functionality:

`availableBikes(typeOfBike)`

> This method returns the number of bikes of the specified type that are
> available to rent.

`getRentedBikes()`

> This method returns a collection of all the bikes currently rented out
> (if any)

`getBike(customerRecord)`

> Given a person\'s customer record, this method returns the bike they are currently renting (if any)

`issueBike(customerRecord, typeOfBike)`

> Given a person\'s customerRecord and type of
> bike required (road or electric), this method determines whether the
> person is eligible to rent a bike of the specified type and, if there
> is a bike available, issues a bike of the specified type.  The method
> associates the bike with the person\'s customer number (so that the
> company has a record of bikes out for rent and the people renting
> them). If a bike cannot be issued, the method returns an appropriate
> indication of the failure to issue a bike. Note, this does not have to
> indicate why a bike cannot be issued, it simply indicates that a bike
> cannot be issued. The rules for determining whether or not a bike can
> be issued are given below.

`terminateRental(customerRecord)`

> This method terminates the rental contract associated with the given
> customer record. In effect, the renter is returning the bike. The bike
> is then available for rent by someone else. The method removes the
> record of the rental from the company\'s records. This
> method changes the status of the returned bike to not
> rented. If the bike is an electric bike, then it is necessary to recharge the battery. There is no charge to the customer for this. Terminating a non-existent contract has no effect.

When issuing a bike, the following rules must be observed.

-   the person renting the bike must have a customer record

-   they cannot rent more than one bike at a time

-   to rent a road bike, they simply need to have a customer record.

-   to rent an electric bike, they must be at least 21 years old and be a "Gold Class" customer.

You do **not** need to model the payment of rental fees in this system; you can assume payment is made when the rental begins, but you should not model this process.

**3. Implementation**

To complete the bike rental system outlined in Section 2 you will need to
provide interfaces and classes for the functionality described in this
section. You should also test your solution.

**Bikes**

All bikes have the following **public** functionality:

-   a method to get the bikes\'s unique serial number

-   a method, for an electric bike, that indicates whether the electric bike\'s battery is full or not. You do not need to model the reduction of battery charge: a battery is either fully charged or not.

-   a method, for an electric bike, to recharge the bike battery by setting the charge to full.

-   a method that indicates whether the bike is rented or not

You must provide an appropriate hierarchy for bikes. Your bike rental
class issues a bike of the appropriate type when requested (and according
to the rules set out in Section 2).

**Bicycle serial number**

A bicycle serial number has two components - two letters followed
by a three digit number. For example:

-   ad345

You must provide access to each component and an appropriate string
representation of the serial number.

Serial numbers are unique. You must guarantee that no two bikes
have the same serial number.

**Customer record**

A customer record has the customer name (comprising a first and last
name), the date of birth of the customer, a unique customer number, the year in which the customer record was issued, and an indication of whether or not the customer is a "Gold Class" customer.

The customer number has three components. The first component is made up of the initial of the first name of the customer followed by the initial of the last name of the customer. The second component is the year
the record was issued. The third component is an arbitrary  serial
number. For example, the string representation of the customer number for
a record issued to Charles Dickens in 2018 would have the form:

-   CD-2018-03

where the 03 is a serial number that, with the initials and year,
guarantees the uniqueness of the customer number as a whole.

Your customer record class must provide methods to access the customer\'s full name, date of birth, the customer number, the date of issue of
the record and whether the customer is a Gold Class customer or not.

You should provide appropriate classes for a person\'s name and for a
customer number.

You must guarantee the uniqueness of customer numbers.

You should use the
[java.util.Date](https://docs.oracle.com/javase/9/docs/api/java/util/Date.html)
class to represent dates. Do not use deprecated methods
of the Date class. In your testing use
[java.util.Calendar](https://docs.oracle.com/javase/9/docs/api/java/util/Calendar.html)
to construct dates of birth and dates of issue of customer records. You can
assume default time zone and locale.

**4. Deliverable**

Your solution should include your interfaces and classes that comprise
the implementation of the system and types outlined in Sections 2 and 3.
You should annotate your code with appropriate Javadocs. In addition, provide any classes you have used to test your solution.

You must submit your solution through
[NESS](https://ness.ncl.ac.uk/) as a single  zip archive that contains your Java source code files.

**5. Assessment**

In this coursework you should demonstrate:

-   the sensible use of Java inheritance mechanisms,

-   an understanding of how to declare and use interfaces,

-   the ability to handle collections of objects,

-   the use of defensive programming, including use of immutability and appropriate error handling,

-   an understanding of when and how to override Object methods,

-   the implementation of object factories,

-   the ability to implement simple algorithms, and

-   the ability to write Javadoc comments

Marks will be allocated for

-   overall structure and implementation of the solution (e.g. interfaces, classes and their relationships)

-   correct implementation of rules specified in Sections 2 and 3

-   choice and use of maps and collections

-   following good practice guidance: maintenance of invariants and
    defensive programming, use of immutability, appropriate overriding
    of Object methods, some use of Javadoc comments, etc.

**6. Style guidelines**

Adopt a consistent style, do not violate naming conventions (e.g. when
to use upper/lower case letters in class, method and variable names) and
make appropriate use of whitespace (indentation and other spacing).

**7. Further notes**

Break the coursework down into separate tasks. Start with the simpler
classes first (e.g. to represent bike serial number, customer name, customer number and
customer record) but leave the imposition of uniqueness until we cover
object factories (week 2). You can implement the different types of bicycle before
implementing the rental manager. Test your classes as you progress
through the coursework.

For each class you implement you should consider:

-   whether to override Object methods (equals, toString etc.),

-   whether to use an interface-based hierarchy, and

-   whether the class should be immutable.

As there are several recorded lectures to watch, you may have to defer parts of the coursework (or the implementation of
certain aspects of a class) until you have covered material in lectures.
You can make a start with a simpler solution that can be
extended later.
