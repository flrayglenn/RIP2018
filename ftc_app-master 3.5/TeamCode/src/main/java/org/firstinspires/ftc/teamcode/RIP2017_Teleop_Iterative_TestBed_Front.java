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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

/**
 * This file provides basic Telop driving for a 2017-2018 Robots In Paradise robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common HardwareRIP class to define the devices on the robot.
 * All device access is managed through the HardwareRIP class.
 *
 * This particular OpMode executes a basic Mecanum Wheel Drive Teleop for our robot.
 * It drives forward and reverse using the Gamepad1 Y sticks vertical axis respectively.
 * gamepad1.left_stick_y gamepad.right_stick_y
 * It drives left and right using the Gamepad1 X sticks horizontal axis respectively.
 * gamepad1.left_stick_x gamepad1.right_stick_x
 * It raises and lowers the claw using the Gamepad2 Y and A button respectively.
 * gamepad2.y gamepad2.a
 * It opens and closes the claws slowly using the Gamepad2 dpad_up and dpad_down repectively.
 * gamepad2.dpad_up gamepad2.dpad_down
 *
 */

@TeleOp(name="RIP2017bot: TestBed", group="RIP2017bot")
//@Disabled
public class RIP2017_Teleop_Iterative_TestBed_Front extends OpMode {

    /* Declare OpMode members. */
    HardwareRIP robot       = new HardwareRIP_TestBed(); // used to define a robot's hardware
                                                         // could also use HardwareRIPMatrix class.

    /*double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;*/                 // sets rate to move servo

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double leftNS;
        double rightNS;
        double leftEW;
        double rightEW;

        // Use gamepad 1 joysticks vertical axis to drive forward or reverse
        // Run wheels in forward mode (note: The joystick goes negative when pushed forwards, so negate it)
        leftNS = -gamepad1.left_stick_y;
        rightNS = -gamepad1.right_stick_y;

        robot.leftFrontDrive.setPower(leftNS);
        robot.rightFrontDrive.setPower(rightNS);
        robot.leftRearDrive.setPower(leftNS);
        robot.rightRearDrive.setPower(rightNS);

        // Use gamepad 1 yoysticks horizontal axis to drive left or right
        // Run wheels in forward mode (note: The joystick goes negative when pushed forwards, so negate it)
        leftEW = -gamepad1.left_stick_x;
        rightEW = -gamepad1.right_stick_x;

        robot.leftFrontDrive.setPower(leftEW);
        robot.rightFrontDrive.setPower(-rightEW);
        robot.leftRearDrive.setPower(-leftEW);
        robot.rightRearDrive.setPower(rightEW);
        /*
        // Use gamepad2 dpadto open and close the claw
        if (gamepad2.dpad_up)
            clawOffset += CLAW_SPEED;
        else if (gamepad2.dpad_down)
            clawOffset -= CLAW_SPEED;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
        robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

        // Use gamepad buttons to move the arm up (Y) and down (A)
        if (gamepad2.y)
            robot.clawPivot.setPower(robot.LIFT_UP_POWER);
        else if (gamepad2.a)
            robot.clawPivot.setPower(robot.LIFT_DOWN_POWER);
        else
            robot.clawPivot.setPower(0.0);

        // Use front bumpers to control arm pivot extend
        // left_bumper conrols extension of armbottom pivot
        // right_bumper controls extension armMiddle pivot
        if (gamepad2.left_bumper)
            robot.armBottom.setPower(robot.EXTEND_POWER);
        else
            robot.armBottom.setPower(0.0);
        if (gamepad2.right_bumper)
            robot.armMiddle.setPower(robot.EXTEND_POWER);
        else
            robot.armMiddle.setPower(0.0);
        */
        // Send telemetry message to signify robot running;
        /*
        telemetry.addData("claw",  "Offset = %.2f", clawOffset);
         */
        telemetry.addData("leftNS",  "%.2f", leftNS);
        telemetry.addData("rightNS", "%.2f", rightNS);
        telemetry.addData("leftNS",  "%.2f", leftEW);
        telemetry.addData("rightNS", "%.2f", rightEW);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
