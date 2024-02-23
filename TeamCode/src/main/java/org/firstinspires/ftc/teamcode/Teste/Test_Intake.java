package org.firstinspires.ftc.teamcode.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "INTAKE BUCLUCAS DACA BUBUIE MA OMOR")
public class Test_Intake extends LinearOpMode {
    public DcMotorEx motor;
    public CRServo servo;
    public double modifier = 0.000001, power = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        servo = hardwareMap.get(CRServo.class, "servo");

        servo.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                motor.setPower(power);
                servo.setPower(1);
            }

            else {
                motor.setPower(0);
                servo.setPower(0);
            }

            if (gamepad1.dpad_up) {
                power+=modifier;
            }

            else if (gamepad1.dpad_down) {
                power-=modifier;
            }

            if (gamepad1.right_bumper) {
                motor.setDirection(DcMotorSimple.Direction.FORWARD);
                servo.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            else if (gamepad1.left_bumper) {
                motor.setDirection(DcMotorSimple.Direction.REVERSE);
                servo.setDirection(DcMotorSimple.Direction.FORWARD);
            }

            telemetry.addData("Directie: ", motor.getDirection());
            telemetry.addData("Power: ", power);
            telemetry.update();

        }

    }
}
