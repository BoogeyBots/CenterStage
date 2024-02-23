package org.firstinspires.ftc.teamcode.Vechi.KickAthon;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class TestPIDBrat extends OpMode {

    public DcMotorEx motor_brat = null;
    public ElapsedTime timeElapsed = new ElapsedTime() ;

    @Override
    public void init() {
        motor_brat = hardwareMap.get(DcMotorEx.class, "motor_brat");

        motor_brat.setTargetPosition(0);
        motor_brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_brat.setPower(0.4);
        motor_brat.setVelocityPIDFCoefficients(8.0, 3.0, 0.0, 0.0);

    }

    @Override
    public void loop() {
        if(timeElapsed.milliseconds() > 100.0) {
            if (gamepad1.a) {
                motor_brat.setTargetPosition(motor_brat.getTargetPosition() + 1);
            }
            if (gamepad1.b) {
                motor_brat.setTargetPosition(motor_brat.getTargetPosition() - 1);
            }
            timeElapsed.reset();
        }
        telemetry.addData("Pozitie", motor_brat.getCurrentPosition());
        telemetry.update();
    }
}
