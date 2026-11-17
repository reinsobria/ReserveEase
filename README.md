# ReserveEase: A Smart Online Reservation System
OOP 101 final project (Java console application)

## Run
```
cd src
javac *.java
java Main
```
Default admin: `admin` / `admin123`

## OOP concepts
- Abstraction: abstract `User`
- Inheritance: `Customer` and `Administrator` extend `User`
- Polymorphism: each overrides `displayMenu()`
- Encapsulation: private fields with getters
- Exception handling: `Main.readInt()` uses try-catch
- Collections: ArrayLists in `ReservationManager`

## Limitation
Data is kept in memory only and is lost when the program closes.
