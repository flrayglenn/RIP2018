# RIP2018
Robots In Paradise 2017-2018 Robot Code
This is the base code for the 2017-2018 FTC year
We are using Mecanum wheels for this years robot
To drive forward/backward, you just power all the wheels forward/backward.
To turn right, you power the right wheels backward and the left wheels forward, just like normal wheels.
(Turning left is the same idea.)
To move sideways right, move the front-left and back-right wheels forward, and the others backward.
Now we have this table:
            FORWARD(+x) BACKWARD(-x)    SIDEWAYS RIGHT(+y)  SIDEWAY LEFT(-y)    TURN RIGHT(+r)  TURN LEFT(-r)
front left      +           -                 +                   -                 +               -
front right     +           -                 -                   +                 -               +
back left       +           -                 -                   +                 +               -
back right      +           -                 +                   -                 -               +
And we can convert this table to an algorithm:
inputs: x, y, and r
flPower = + x + y + r
frPower = + x - y - r
blPower = + x - y + r
brPower = + x + y - r
Teleop, AutoDriveByEncoder_Linear, AutoDriveByTime_Linear, and OpMode_Iterative
