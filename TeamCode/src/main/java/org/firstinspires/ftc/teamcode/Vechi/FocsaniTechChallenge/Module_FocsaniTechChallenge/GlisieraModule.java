package org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Module_FocsaniTechChallenge;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class GlisieraModule {
    HardwareMap hardwareMap;
    public GlisieraModule(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public static double kp = 0.0, ki = 0.0, kd = 0.0;
    public DcMotorEx motorDR_ENC, motorST;

    PIDController controller = new PIDController(4.0, 0.0, 0.1);

    public void init() {
        motorDR_ENC = hardwareMap.get(DcMotorEx.class, "motorDR_ENC");
        motorST = hardwareMap.get(DcMotorEx.class, "motorST");

        motorDR_ENC.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorST.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        motorDR_ENC.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        motorST.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        motorDR_ENC.setDirection(DcMotorEx.Direction.REVERSE);

        controller.reset();
    }

    public void update() {
        if(!controller.atSetPoint()) {
            double output = controller.calculate(
                    motorST.getCurrentPosition()
            );

            motorST.setVelocity(output);
            motorDR_ENC.setVelocity(output);
        }
    }

    public void goUp() {
        controller.setSetPoint(0);
    }
    
}
