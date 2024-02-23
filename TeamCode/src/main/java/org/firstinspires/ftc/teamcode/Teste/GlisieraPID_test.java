package org.firstinspires.ftc.teamcode.Teste;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@Config
@TeleOp (name = "PID glisiere")
public class GlisieraPID_test extends LinearOpMode {
    FtcDashboard dashboard;
    public static double kp = 1.0, ki = 0.0, kd = 0.1;
    public DcMotorEx motorDR_ENC, motorST;
    int modifier = 5;
    int poz_max = 2000;
    int poz_min = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motorDR_ENC = hardwareMap.get(DcMotorEx.class, "motorDR");
        motorST = hardwareMap.get(DcMotorEx.class, "motorST");

        motorDR_ENC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorST.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorDR_ENC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorST.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorDR_ENC.setDirection(DcMotorSimple.Direction.REVERSE);
        motorST.setDirection(DcMotorSimple.Direction.FORWARD);

        PIDController controller = new PIDController(kp, ki, kd);

        controller.reset();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.dpad_up){
                modifier++;
            }
            else if(gamepad1.dpad_down){
                modifier--;
            }

            if(gamepad1.a){
                if(controller.getSetPoint()  >= poz_min && controller.getSetPoint() + modifier <= poz_max){
                    controller.setSetPoint(Range.clip(controller.getSetPoint() + modifier, poz_min, poz_max ));
                }
            }
            else if(gamepad1.b){
                if(controller.getSetPoint() - modifier >= poz_min && controller.getSetPoint() <= poz_max){
                    controller.setSetPoint(Range.clip(controller.getSetPoint() - modifier, poz_min, poz_max ));
                }
            }
            else {
                controller.setSetPoint(controller.getSetPoint());
            }


            controller.setP(kp);
            controller.setI(ki);
            controller.setD(kd);

            if (Math.abs(motorST.getCurrentPosition() - controller.getSetPoint()) < 2.0) {
                double output = controller.calculate(
                        motorST.getCurrentPosition()      // the measured value
                );
                telemetry.addData("Output", output);
                motorST.setVelocity(output);
                motorDR_ENC.setVelocity(output);
            }

            dashboard.updateConfig();
            telemetry.addData("A AJUNS?", controller.atSetPoint());
            telemetry.addData("motorDR_ENC", motorDR_ENC.getCurrentPosition());
            telemetry.addData("motorST", motorST.getCurrentPosition());
            telemetry.addData("Pozitia care trebuie atinsa: ", controller.getSetPoint());
            telemetry.addData("Eroare: ", controller.getPositionError());
            telemetry.update();

        }
    }
}