package org.firstinspires.ftc.teamcode.Vechi;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Brat Test", group = "Test")
public class Brat_test extends LinearOpMode {
    Servo servoDR, servoST = null;
    Servo servo_extindere, servoC_DR, servoC_ST, servo_rotire;
    double modifier = 0.0001;

    double change_modifier = 0.00000005;

    @Override
    public void runOpMode() throws InterruptedException {
        servoDR = hardwareMap.get(Servo.class, "bratDR");
        servoST = hardwareMap.get(Servo.class, "bratST");

        servoDR.setPosition(0.0);
        servoST.setPosition(0.0);
        waitForStart();


        while (opModeIsActive()) {
            if(gamepad1.a) {
                servoDR.setPosition(servoDR.getPosition() + modifier);
                servoST.setPosition(servoST.getPosition() + modifier);
            }
            else if(gamepad1.b) {
                servoDR.setPosition(servoDR.getPosition() - modifier);
                servoST.setPosition(servoST.getPosition() - modifier);
            }

            if(gamepad1.dpad_up){
                modifier += change_modifier;
            }
            else if(gamepad1.dpad_down){
                modifier -= change_modifier;
            }



            telemetry.addData("SERVO DREAPTA: ", servoDR.getPosition());
            telemetry.addData("SERVO STANGA: ", servoST.getPosition());
            telemetry.addData("MODIFIER", modifier);
            telemetry.addData("CHANGE MODIFIER", change_modifier);

            telemetry.update();
        }
    }
}





