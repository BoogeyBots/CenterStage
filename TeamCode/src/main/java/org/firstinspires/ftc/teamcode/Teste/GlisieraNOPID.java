package org.firstinspires.ftc.teamcode.Teste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "NONOPID_GLISIERE")
public class GlisieraNOPID extends LinearOpMode {
    public DcMotorEx motorDR, motorST;
    public int position = 0;
    public int modifier = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        motorST = hardwareMap.get(DcMotorEx.class, "motorST");

        motorDR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorST.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorDR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorST.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorDR.setTargetPosition(0);
        motorST.setTargetPosition(0);

        motorDR.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                position += modifier;
            }
            else if (gamepad1.b) {
                position-= modifier;
            }

            if (gamepad1.right_bumper) {
                motorDR.setTargetPosition(position);
                motorST.setTargetPosition(position);
            }

            if (gamepad1.left_bumper) {
                motorDR.setTargetPosition(0);
                motorST.setTargetPosition(0);
            }

            telemetry.addData("Target position: ", position);
            telemetry.addData("MotorDR: ", motorDR.getCurrentPosition());
            telemetry.addData("MotorST: ", motorST.getCurrentPosition());

            telemetry.update();
        }

    }
}
