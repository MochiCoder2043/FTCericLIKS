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
    private  DcMotor frontRightMotor;

    private DcMotor claw;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontright");

        claw = hardwareMap.get(DcMotor.class, "claw");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    //test
    @Override
    public void loop() {
        double wheelPower = -gamepad1.left_stick_y;
        double slidePower = -gamepad1.left_stick_x;

        if (gamepad1.a){
            claw.setPower(9.0);
        } else if (gamepad1.y) {
            claw.setPower(-9.0);
        }

        if (slidePower == 0){
            leftMotor.setPower(wheelPower);
            rightMotor.setPower(wheelPower);
        } else if (slidePower != 0) {
            leftMotor.setPower(slidePower);
            rightMotor.setPower(slidePower);
            frontRightMotor.setPower(-slidePower);
            frontLeftMotor.setPower(-slidePower);
        }

    }
}
