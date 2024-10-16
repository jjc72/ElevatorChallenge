This project contains a class that simulates an elevator.

The class allows the user to define the number of floors it services and the amount of weight it can carry on instantiation.
The elevator can be "locked", simulating extending the brakes to keep the elevator on a specific floor for use in cargo loading
or use by firefighters. The class also allows simulation of loading and unloading the elevator and the elevator will
"lock" on the current floor if over-loaded as a safety measure.

The elevator class is designed to be generic with the idea that it can be inherited from / extended if additional
functionality is needed.

Since the use case for the elevator was not defined, this class assumes the most basic use case such as a feight or passanger
elevator which traverses only a few of floors. This assumption resulted in the movement capabilities of the elevator
being limited to one floor (up or down) at a time. Should a high rise elevator need to be simulated, more complex movement
algorthims would probably need to be added either to the class itself or as part of an inherited class.

No user interactable console application was created because the class was developed using test driven development techniques
and the code provided includes a complete suite of unit tests for the class.

