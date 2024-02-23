package org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Module_FocsaniTechChallenge;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeModule {
    HardwareMap hardwareMap;

    public IntakeModule(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public Servo servo_rotire, servoDR_clapita, servoST_clapita;
    public Servo servoC_DR, servoC_ST;

    public void init() {
        servo_rotire = hardwareMap.get(Servo.class, "servo_rotire");
        servoDR_clapita = hardwareMap.get(Servo.class, "servoDR_clapita");
        servoST_clapita = hardwareMap.get(Servo.class, "servoST_clapita");
        servoC_DR = hardwareMap.get(Servo.class, "servoC_DR");
        servoC_ST = hardwareMap.get(Servo.class, "servoC_ST");
        servo_rotire.setPosition(0.415);
        servoC_DR.setPosition(0.79);
        servoC_ST.setPosition(0.217);

    }

    public void goUp() {
        servo_rotire.setPosition(1);
    }

    public void goDown() {
        servo_rotire.setPosition(0.415);
    }

    public void open() {
        servoC_DR.setPosition(1);
        servoC_ST.setPosition(0);
    }

    public void close() {
        servoC_DR.setPosition(0.79);
        servoC_ST.setPosition(0.217);
    }

}
