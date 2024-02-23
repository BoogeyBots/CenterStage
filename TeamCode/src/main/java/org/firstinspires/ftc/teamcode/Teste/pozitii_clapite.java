package org.firstinspires.ftc.teamcode.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "Test Clapite", group = "Test")
public class pozitii_clapite extends LinearOpMode {
    Servo clapitaST, clapitaDR;

    double modifier = 0.0001;

    double change_modifier = 0.00000005;

    @Override
    public void runOpMode() throws InterruptedException {
        clapitaDR = hardwareMap.get(Servo.class, "clapitaDR");
        clapitaST = hardwareMap.get(Servo.class, "clapitaST");

        clapitaDR.setPosition(1);
        clapitaST.setPosition(0);
        waitForStart();


        while (opModeIsActive()) {
            if(gamepad1.x){
                clapitaST.setPosition(clapitaST.getPosition() - modifier);
            }
            else if(gamepad1.y){
                clapitaST.setPosition(clapitaST.getPosition() + modifier);
            }

            if(gamepad1.a){
                clapitaDR.setPosition(clapitaDR.getPosition() - modifier);
            }
            else if(gamepad1.b){
                clapitaDR.setPosition(clapitaDR.getPosition() + modifier);
            }

            if(gamepad1.dpad_up){
                modifier += change_modifier;
            }
            else if(gamepad1.dpad_down){
                modifier -= change_modifier;
            }
            telemetry.addData("clapitaDR: ", clapitaDR.getPosition());
            telemetry.addData("clapitaST: ", clapitaST.getPosition());
            telemetry.addData("MODIFIER", modifier);
            telemetry.addData("CHANGE MODIFIER", change_modifier);

            telemetry.update();
        }

    }
}