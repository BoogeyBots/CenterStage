package org.firstinspires.ftc.teamcode.Teste;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Identificare pozitie", group = "Test")
public class PozitiiServo_test extends LinearOpMode {
    public Servo servoC_DR, servoC_ST, servo_rotire, servo_extindere, servoDR, servoST;
    @Override
    public void runOpMode() throws InterruptedException {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");
        waitForStart();


        while (opModeIsActive()) {
            telemetry.addData("servo_DR: ", servoDR.getPosition());
            telemetry.addData("servo_ST: ", servoST.getPosition());
            telemetry.update();
        }
    }
}





