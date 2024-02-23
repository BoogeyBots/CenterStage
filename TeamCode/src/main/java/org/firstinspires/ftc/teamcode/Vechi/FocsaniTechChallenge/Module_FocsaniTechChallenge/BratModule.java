package org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Module_FocsaniTechChallenge;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BratModule {
    HardwareMap hardwareMap;

    public BratModule (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }
    Servo servoDR, servoST, servo_extindere = null;
    double modifier = 0.0001;

    double change_modifier = 0.00000005;

    public void init() {
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");
        servo_extindere = hardwareMap.get(Servo.class, "servo_extindere");
        servoDR.setPosition(0.0822222);
        servoST.setPosition(0.0822222);
        servo_extindere.setPosition(0.37777);
    }

    //ridicarea bratului

    //lalallalaalala bubuie dacă nu e brațul extins
    public void goDown() {
        servoDR.setPosition(0.0822222);
        servoST.setPosition(0.0822222);
    }

    //sper să fie asta poziția pentru autonom (emoji lună)
    public void autonom() {
        servoDR.setPosition(0.6472);
        servoST.setPosition(0.6472);
    }


    //extinderea bratului

    /*public void retras() {
        servo_extindere.setPosition(0);
    }

     */
    //sper să nu se rotească în sensul greșit că nu mai avem braț (emoji lună)
    public void interior() {
        servo_extindere.setPosition(0.37777);
    }
    public void exterior() {
        servo_extindere.setPosition(0.635);
    }
}





