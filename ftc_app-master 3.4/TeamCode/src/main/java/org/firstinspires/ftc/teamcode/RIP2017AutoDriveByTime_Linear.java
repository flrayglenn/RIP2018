/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**

 */
/**This is our 2017-2018 code
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
 **/


@Autonomous(name="RIP2017bot_AutoTime: Auto Drive By Time", group="RIP2017bot_AutoTime")
//@Disabled
public class RIP2017AutoDriveByTime_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRIP         robot   = new HardwareRIP();
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     flPower = 0.25;
    static final double     frPower = 0.25;
    static final double     blPower = 0.25;
    static final double     brPower = 0.25;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        robot.leftFrontDrive.setPower(flPower);
        robot.rightFrontDrive.setPower(frPower);
        robot.leftRearDrive.setPower(blPower);
        robot.rightRearDrive.setPower(brPower);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  Spin right for 1.3 seconds
        robot.leftFrontDrive.setPower(flPower);
        robot.rightFrontDrive.setPower(-frPower);
        robot.leftRearDrive.setPower(blPower);
        robot.rightRearDrive.setPower(-brPower);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 3:  Drive Backwards for 1 Second
        robot.leftFrontDrive.setPower(-flPower);
        robot.rightFrontDrive.setPower(-frPower);
        robot.leftRearDrive.setPower(-blPower);
        robot.rightRearDrive.setPower(-brPower);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4:  Stop and close the claw.
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftRearDrive.setPower(0);
        robot.rightRearDrive.setPower(0);
        robot.leftClaw.setPosition(1.0);
        robot.rightClaw.setPosition(0.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
