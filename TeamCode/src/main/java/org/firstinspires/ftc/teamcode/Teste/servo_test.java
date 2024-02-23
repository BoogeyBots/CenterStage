package org.firstinspires.ftc.teamcode.Teste;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo test", group = "Test")
public class servo_test extends LinearOpMode {
    Servo servoST, servoDR, rotire, bratDR, bratST;
    CRServo servoNFS;
    DcMotorEx motorlant;
    double modifier = 0.0001;

    double change_modifier = 0.00000005;

    @Override
    public void runOpMode() throws InterruptedException {
        servoDR = hardwareMap.get(Servo.class, "clapitaDR");
        servoST = hardwareMap.get(Servo.class, "clapitaST");
        bratDR = hardwareMap.get(Servo.class, "bratDR");
        bratST = hardwareMap.get(Servo.class, "bratST");
        rotire = hardwareMap.get(Servo.class, "rotire");

        servoDR.setPosition(0.6183);
        servoST.setPosition(0.4155);

        waitForStart();


        while (opModeIsActive()) {

            /*if(gamepad1.a){
                servoST.setPosition(0.508333);
                servoDR.setPosition(0.682777);
            }
            else if(gamepad1.b){
                servoDR.setPosition(0);
                servoST.setPosition(1);
            }

            if(gamepad1.x) {
                motorlant.setPower(0.87);
                servoNFS.setPower(1);
            }
            else {
                motorlant.setPower(0);
                servoNFS.setPower(0);
            }

             */




            /*
            if(gamepad1.x){
                servoST.setPosition(servoST.getPosition() - modifier);
            }
            else if(gamepad1.y){
                servoST.setPosition(servoST.getPosition() + modifier);
            }

             */

            if (gamepad1.a) {
                rotire.setPosition(rotire.getPosition() - modifier);
            }
            else if (gamepad1.b) {
                rotire.setPosition(rotire.getPosition() + modifier);
            }

            /*
            if(gamepad1.a){
                servoDR.setPosition(servoDR.getPosition() - modifier);
            }
            else if(gamepad1.b){
                servoDR.setPosition(servoDR.getPosition() + modifier);
            }

            */

            if(gamepad1.dpad_up){
                modifier += change_modifier;
            }
            else if(gamepad1.dpad_down){
                modifier -= change_modifier;
            }




            telemetry.addData("rotire: ", rotire.getPosition());
            telemetry.addData("MODIFIER", modifier);
            telemetry.addData("CHANGE MODIFIER", change_modifier);

            telemetry.update();
        }
    }
}





