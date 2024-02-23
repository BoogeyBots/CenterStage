package org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Module_FocsaniTechChallenge.GlisieraModule;
import org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Module_FocsaniTechChallenge.IntakeModule;
import org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Module_FocsaniTechChallenge.BratModule;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Disabled
@TeleOp (name = "TeleOP_Focsani")
public class TeleOP_FocsaniTechChallenge extends LinearOpMode {

    public double mod;
    public Servo servoC_DR, servoC_ST, servo_rotire, servo_extindere, servo_DR, servo_ST, servo_avion;
    ElapsedTime coborare = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servo_DR = hardwareMap.get(Servo.class, "servo_DR");
        servo_ST = hardwareMap.get(Servo.class, "servo_ST");
        servoC_DR = hardwareMap.get(Servo.class, "servoC_DR");
        servoC_ST = hardwareMap.get(Servo.class, "servoC_ST");
        servo_extindere = hardwareMap.get(Servo.class, "servo_extindere");
        servo_rotire = hardwareMap.get(Servo.class, "servo_rotire");
        servo_avion = hardwareMap.get(Servo.class, "servo_avion");


        GlisieraModule glisieraModule = new GlisieraModule(hardwareMap);
        BratModule bratModule = new BratModule(hardwareMap);
        IntakeModule intakeModule = new IntakeModule(hardwareMap);

        //glisieraModule.init();
        //bratModule.init();
        //intakeModule.init();

        //init
        servoC_DR.setPosition(0.79);
        servoC_ST.setPosition(0.217);
        servo_DR.setPosition(0.097);
        servo_ST.setPosition(0.097);
        servo_rotire.setPosition(0.35777);
        servo_extindere.setPosition(0.6127);
        servo_avion.setPosition(0.04833);


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

            if (gamepad2.x) {
                //init position
                servo_DR.setPosition(0.12611);
                servo_ST.setPosition(0.12611);
                servo_rotire.setPosition(0.43777);
                servoC_DR.setPosition(0.79);
                servoC_ST.setPosition(0.217);
                servo_extindere.setPosition(0.6127);
            }

            /*if (gamepad2.a) {
                //autonom position
                servo_DR.setPosition(0.6477);
                servo_ST.setPosition(0.6477);
                servo_extindere.setPosition(0.89166);
                servo_rotire.setPosition(0.83055);
            }



            if (gamepad2.b) {
                servo_DR.setPosition(0.121166);
                servo_ST.setPosition(0.121166);
                servo_extindere.setPosition(0.3316);
                servo_rotire.setPosition(0.43777);
            }

             */

            if (gamepad2.a) {
                //intake position
                servo_DR.setPosition(0.2);
                servo_ST.setPosition(0.2);
                servo_rotire.setPosition(0.42444);
                servo_extindere.setPosition(0.37555);
            }
            if (gamepad2.b) {
                servo_DR.setPosition(0.08);
                servo_ST.setPosition(0.08);
            }

            if (gamepad2.right_bumper) {
                    //up position
                servo_DR.setPosition(0.6);
                servo_ST.setPosition(0.6);
                servo_rotire.setPosition(0.84611);
                servo_extindere.setPosition(0.021666);
            }
            if (gamepad2.left_trigger > 0.1) {
                servoC_DR.setPosition(0.03722);
                servoC_ST.setPosition(1);
            }
            if (gamepad2.right_trigger > 0.1) {
                servoC_DR.setPosition(0.2611);
                servoC_ST.setPosition(0.8577);
            }

            if (gamepad2.dpad_up) {
                servo_avion.setPosition(0.12);
            }

            if (gamepad2.y) {
                servo_DR.setPosition(0.1);
                servo_ST.setPosition(0.1);
            }
        }

    }
}


