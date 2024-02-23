package org.firstinspires.ftc.teamcode.Meeturi;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
@TeleOp (name = "TeleOP original 1 dimineata IASI MERGE 84% - 5 erori deja")
public class TeleOP extends LinearOpMode {
    public DcMotorEx motorDR, motorST, motor_intake;
    public Servo clapitaDR, clapitaST, bratDR, bratST, rotire, avion, servoDR, servoST;

    public CRServo CR7;
    double mod;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorDR = hardwareMap.get(DcMotorEx.class, "motorDR");
        motorST = hardwareMap.get(DcMotorEx.class, "motorST");
        motor_intake = hardwareMap.get(DcMotorEx.class, "motor_intake");

        motorDR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorST.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorST.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDR.setDirection(DcMotorSimple.Direction.FORWARD);

        clapitaDR = hardwareMap.get(Servo.class, "clapitaDR");
        clapitaST = hardwareMap.get(Servo.class, "clapitaST");
        bratDR = hardwareMap.get(Servo.class, "bratDR");
        bratST = hardwareMap.get(Servo.class, "bratST");
        rotire = hardwareMap.get(Servo.class, "rotire");
        avion = hardwareMap.get(Servo.class, "avion");
        servoDR = hardwareMap.get(Servo.class, "servoDR");
        servoST = hardwareMap.get(Servo.class, "servoST");

        CR7 = hardwareMap.get(CRServo.class, "CR7");

        //pozitie init
        clapitaDR.setPosition(0.8);
        clapitaST.setPosition(0.216);
        bratST.setPosition(0);
        bratST.setPosition(0);
        rotire.setPosition(0.77);
        avion.setPosition(0.04833);
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.right_trigger > 0.1) {
                mod = 1.0;
            } else if (gamepad1.left_trigger > 0.1) {
                mod = 0.25;
            } else {
                mod = 0.5;
            }

            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y * mod,
                            gamepad1.left_stick_x * mod,
                            gamepad1.right_stick_x * mod
                    )
            );

            drive.update();

            if (gamepad1.a) {
                clapitaDR.setPosition(0.89);
                clapitaST.setPosition(0);
            }
            else if (gamepad1.b) {
                clapitaDR.setPosition(0.6183);
                clapitaST.setPosition(0.4155);
            }

            if (gamepad1.right_bumper) {
                motorDR.setPower(0.77);
                motorST.setPower(0.77);
            }

            else if (gamepad1.left_bumper){
                motorDR.setPower(-1);
                motorST.setPower(-1);
            }

            else {
                motorDR.setPower(0);
                motorST.setPower(0);
            }

            if (gamepad1.dpad_left) {
                bratDR.setPosition(0);
                bratST.setPosition(0);
                rotire.setPosition(0.77);
                clapitaDR.setPosition(0.6183);
                clapitaST.setPosition(0.4155);
            }

            if (gamepad1.dpad_right) {
                bratDR.setPosition(0.2066);
                bratST.setPosition(0.2066);
                rotire.setPosition(0.63333);
            }

            if (gamepad1.dpad_up) {
                avion.setPosition(0.12);
            }

            if (gamepad1.x) {
                motor_intake.setPower(0.87);
                CR7.setPower(1);
                servoST.setPosition(0.508333);
                servoDR.setPosition(0.682777);
            }

            else {
                motor_intake.setPower(0);
                CR7.setPower(0);
            }

            telemetry.addData("Clapite: ", clapitaST.getPosition());

            telemetry.update();
        }
    }
}
