package org.firstinspires.ftc.teamcode.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Motor test", group = "Test")
public class Motor_test extends LinearOpMode {
    public DcMotorEx motor = null;
    double modifier = 0.00001;
    double power = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotorEx.class, "motor_intake");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.dpad_up) {
                power+=modifier;
            }
            if(gamepad1.dpad_down) {
                power-=modifier;
            }
            if(gamepad1.x) {
                motor.setPower(power);
            }
            else {
                motor.setPower(0);
            }
            if(gamepad1.a) {
                motor.setDirection(DcMotorSimple.Direction.REVERSE);
            }
            if(gamepad1.b) {
                motor.setDirection(DcMotorSimple.Direction.FORWARD);
            }
            telemetry.addData("Power: ", power);
            telemetry.addData("Direction", motor.getDirection());
            telemetry.update();
        }
    }
}
