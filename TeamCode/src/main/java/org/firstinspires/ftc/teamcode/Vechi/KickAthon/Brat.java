package org.firstinspires.ftc.teamcode.Vechi.KickAthon;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Brat {
    HardwareMap hardwareMap;

    public Brat(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public DcMotorEx motor_brat = null;
    PIDController controller = new PIDController(4.0, 0.0, 0.1);


    public void init() {

        motor_brat = hardwareMap.get(DcMotorEx.class, "motor_brat");

        motor_brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor_brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor_brat.setDirection(DcMotorEx.Direction.REVERSE);

        controller.reset();

    }

    public void update(){
        if (!controller.atSetPoint()) {
            double output = controller.calculate(
                    motor_brat.getCurrentPosition()      // the measured value
            );
            motor_brat.setVelocity(output);

        }
    }

    public void goUp(){
        controller.setSetPoint(1600);
    }


    public void goDown(){
        controller.setSetPoint(0);
    }

    public void goMid(){
        controller.setSetPoint(250);
    }


}




