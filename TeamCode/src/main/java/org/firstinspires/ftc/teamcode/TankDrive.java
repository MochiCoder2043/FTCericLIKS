package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TankDrive extends OpMode {

    // Wheels
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;

    // Claw mechanism (optional)
    //private DcMotor claw;

    @Override
    public void init() {
        // Initialize motors from hardware map
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");

        // Initialize claw motor (optional)
        //claw = hardwareMap.get(DcMotor.class, "claw");

        // Reverse left motors to account for opposite side orientation
        //leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        // Get joystick inputs
        double forwardPower = -gamepad1.left_stick_y;  // Forward/backward movement
        double strafePower = gamepad1.left_stick_x;    // Strafing (left/right movement)
        double turnPower = gamepad1.right_stick_x;     // Turning (rotation in place)

        // Apply scaling for smoother control (cubic scaling to reduce sensitivity at lower speeds)
        forwardPower = scaleInput(forwardPower);
        strafePower = scaleInput(strafePower);
        turnPower = scaleInput(turnPower);

        // Calculate motor power for mecanum/tank drive
        double leftFrontPower = forwardPower - strafePower + turnPower;
        double rightFrontPower = forwardPower - strafePower - turnPower;
        double leftBackPower = forwardPower + strafePower + turnPower;
        double rightBackPower = forwardPower + strafePower - turnPower;



        // Normalize the values so no motor power exceeds 1.0
        double maxPower = Math.max(1.0, Math.abs(leftFrontPower));
        maxPower = Math.max(maxPower, Math.abs(rightFrontPower));
        maxPower = Math.max(maxPower, Math.abs(leftBackPower));
        maxPower = Math.max(maxPower, Math.abs(rightBackPower));

        leftFrontPower /= maxPower;
        rightFrontPower /= maxPower;
        leftBackPower /= maxPower;
        rightBackPower /= maxPower;

        // Set motor powers
        leftMotor.setPower(-leftBackPower);
        rightMotor.setPower(rightBackPower);
        frontLeftMotor.setPower(-leftFrontPower);
        frontRightMotor.setPower(rightFrontPower);

        /* Claw control (optional)
        if (claw != null) {
            if (gamepad1.a) {
                claw.setPower(0.5);  // Open claw
            } else if (gamepad1.y) {
                claw.setPower(-0.5); // Close claw
            } else {
                claw.setPower(0);    // Stop claw
            }
        } */

        // Send telemetry data to the driver station for debugging
        telemetry.addData("Left Front Power", leftFrontPower);
        telemetry.addData("Right Front Power", rightFrontPower);
        telemetry.addData("Left Back Power", leftBackPower);
        telemetry.addData("Right Back Power", rightBackPower);
        telemetry.update();
    }

    /**
     * Scales joystick input to make control more sensitive at low speeds.
     * Uses cubic scaling.
     *
     * @param input The raw joystick input (-1.0 to 1.0)
     * @return The scaled input value
     */
    private double scaleInput(double input) {
        return Math.pow(input, 3);
    }
}
