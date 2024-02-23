package org.firstinspires.ftc.teamcode.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Test GLisiereeee")
public class Test_glisiera_bcl extends LinearOpMode {
    public DcMotorEx motorDR, motorST;

    double power = 0;
    double modifier = 0.0001;
    double change_modifier = 0.00000005;

    @Override
    public void runOpMode() throws InterruptedException {
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        motorST = hardwareMap.get(DcMotorEx.class, "motorST");

        motorDR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorST.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorST.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDR.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            if(gamepad1.a) {
                motorDR.setPower(power);
                motorST.setPower(power);
            }

            else if(gamepad1.b){
                motorDR.setPower(-power);
                motorST.setPower(-power);
            }
            else{
                motorDR.setPower(0);
                motorST.setPower(0);
            }

            if(gamepad1.dpad_up) {
                power+=0.00001;
            }
            else if(gamepad1.dpad_down) {
                power-=0.00001;
            }

            /*if(gamepad1.left_bumper){
                motorST.setDirection(DcMotorSimple.Direction.FORWARD);
                motorDR.setDirection(DcMotorSimple.Direction.REVERSE);
            }
            else if(gamepad1.right_bumper) {
                motorST.setDirection(DcMotorSimple.Direction.REVERSE);
                motorDR.setDirection(DcMotorSimple.Direction.FORWARD);
            }

             */


            if(gamepad1.dpad_right){
                modifier += change_modifier;
            }
            else if(gamepad1.dpad_left){
                modifier -= change_modifier;
            }




            telemetry.addData("POWER: ", power);
            telemetry.addData("motorDR: ", motorDR.getDirection());
            telemetry.addData("motorST: ", motorST.getDirection());
            telemetry.addData("MODIFIER", modifier);
            telemetry.addData("CHANGE MODIFIER", change_modifier);

            telemetry.update();

        }

    }
}
