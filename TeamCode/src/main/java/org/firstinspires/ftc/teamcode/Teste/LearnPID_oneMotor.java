package org.firstinspires.ftc.teamcode.Teste;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "PID motor")
public class LearnPID_oneMotor extends LinearOpMode {
    FtcDashboard dashboard;
    public DcMotorEx motor = null;
    public int modifier = 5;
    @Override
    public void runOpMode() throws InterruptedException {
        dashboard = FtcDashboard.getInstance();
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        PIDController controller = new PIDController(0.1, 0, 0);

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
                    controller.setSetPoint(controller.getSetPoint() + modifier);
            }
            else if(gamepad1.b){
                controller.setSetPoint(controller.getSetPoint() - modifier);

            }
            else {
                controller.setSetPoint(controller.getSetPoint());
            }

            controller.setP(0.1);
            controller.setI(0);
            controller.setD(0);

            dashboard.updateConfig();
            telemetry.addData("A AJUNS?", controller.atSetPoint());
            telemetry.addData("Pozitie: ", motor.getCurrentPosition());
            telemetry.addData("Pozitia care trebuie atinsa: ", controller.getSetPoint());
            telemetry.addData("Eroare: ", controller.getPositionError());

            telemetry.update();
        }

    }
}
