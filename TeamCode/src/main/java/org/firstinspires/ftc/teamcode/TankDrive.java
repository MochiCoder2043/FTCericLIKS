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
    //private DcMotor frontLeftMotor;
    //private  DcMotor frontRightMotor;

    private DcMotor claw;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        //frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        //frontRightMotor = hardwareMap.get(DcMotor.class, "frontright");

        claw = hardwareMap.get(DcMotor.class, "claw");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    //test
    @Override
    public void loop() {
        // declarando la variable para la velocidad del movimiento hacia enfrente
        double wheelPower = -gamepad1.left_stick_y;

        // declarando la variable para la velocidad del movimiento cangrejo
        //double slidePower = -gamepad1.left_stick_x;

        // checando si esta precionado el boton de la garra
        if (gamepad1.a){
            // checkando si es para arriba el movimient (boton a)
            claw.setPower(8.0);
        } else if (gamepad1.y) {
            // checkando si es para abajo el movimient (boton b)
            claw.setPower(-8.0);
        }

        leftMotor.setPower(wheelPower);
        rightMotor.setPower(wheelPower);

        /* checkando si el robot se mueve cangrejo o no
        if (slidePower == 0){
            // ir hacia enfrente
            leftMotor.setPower(wheelPower);
            rightMotor.setPower(wheelPower);
        } else if (slidePower != 0) {
            // CANGREJO
            leftMotor.setPower(slidePower);
            rightMotor.setPower(slidePower);
            frontRightMotor.setPower(-slidePower);
            frontLeftMotor.setPower(-slidePower);
        }*/

        // trying smthn
    }
}
