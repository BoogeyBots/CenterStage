package org.firstinspires.ftc.teamcode.Vechi.FocsaniTechChallenge.Autonom;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Detection.red.DetectionClass_red;
import org.firstinspires.ftc.teamcode.Detection.red.TeamProp_red;
@Disabled
@Autonomous(name = "Rosu_departe")
public class FarRed_auto extends LinearOpMode {
    DcMotorEx rightFront, rightBack, leftFront, leftBack, motor_intake;
    public Servo servoC_DR, servoC_ST, servo_rotire, servo_extindere, servo_DR, servo_ST, servo_avion;


    static final double COUNTS_PER_MOTOR_REV = 537.7;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 3.78;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.4;
    static final double TURN_SPEED = 0.3;
    public Servo avion;
    ElapsedTime runtime = new ElapsedTime();
    ElapsedTime rotire = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        motor_intake = hardwareMap.get(DcMotorEx.class, "motor_intake");
        servo_DR = hardwareMap.get(Servo.class, "servo_DR");
        servo_ST = hardwareMap.get(Servo.class, "servo_ST");
        servoC_DR = hardwareMap.get(Servo.class, "servoC_DR");
        servoC_ST = hardwareMap.get(Servo.class, "servoC_ST");
        servo_extindere = hardwareMap.get(Servo.class, "servo_extindere");
        servo_rotire = hardwareMap.get(Servo.class, "servo_rotire");
        servo_avion = hardwareMap.get(Servo.class, "servo_avion");

        DetectionClass_red detectare = new DetectionClass_red(hardwareMap);
        TeamProp_red.Location TeamProp_location = TeamProp_red.Location.LEFT;

        //motoarele din stanga sunt in onglida, asa ca primesc REVERSE pentru a se roti in directia FORWARD
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_intake.setDirection(DcMotorSimple.Direction.FORWARD);


        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Starting at", "%7d :%7d",
                leftFront.getCurrentPosition(),
                rightFront.getCurrentPosition(),
                leftBack.getCurrentPosition(),
                rightBack.getCurrentPosition());
        telemetry.update();

        servo_DR.setPosition(0.12611);
        servo_ST.setPosition(0.12611);
        servo_rotire.setPosition(0.43777);
        servoC_DR.setPosition(0.79);
        servoC_ST.setPosition(0.217);
        servo_extindere.setPosition(0.6127);


        detectare.init();

        avion.setPosition(0.64444);

        while (!opModeIsActive()) {
            TeamProp_location = detectare.getLocation();
            telemetry.addData("Location", TeamProp_location);
            telemetry.update();
        }

        waitForStart();


        if (TeamProp_location == TeamProp_red.Location.LEFT) {
            encoderDrive(DRIVE_SPEED, 26, 26, 4);
            encoderDrive(DRIVE_SPEED, -23, 23, 4);
            encoderDrive(DRIVE_SPEED, -69, -69, 4);

        }

        else if (TeamProp_location == TeamProp_red.Location.RIGHT) {
            encoderDrive(DRIVE_SPEED, 26, 26, 4);
            encoderDrive(DRIVE_SPEED, -23, 23, 4);
            encoderDrive(DRIVE_SPEED, -69, -69, 4);
        }

        else {

            encoderDrive(DRIVE_SPEED, 26, 26, 4);
            encoderDrive(DRIVE_SPEED, -23, 23, 4);
            encoderDrive(DRIVE_SPEED, -69, -69, 4);
        }
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftBack.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightBack.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftFront.setTargetPosition(newLeftTarget);
            rightFront.setTargetPosition(newRightTarget);
            leftBack.setTargetPosition((int) (newLeftTarget + 2.244));
            rightBack.setTargetPosition((int) (newRightTarget + 2.244));

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFront.setPower(Math.abs(speed));
            rightFront.setPower(Math.abs(speed));
            leftBack.setPower(Math.abs(speed));
            rightBack.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && rightFront.isBusy()) &&
                    (leftBack.isBusy() && rightBack.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Running to", " %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Currently at", " at %7d :%7d",
                        leftFront.getCurrentPosition(), rightFront.getCurrentPosition(),
                        leftBack.getCurrentPosition(), rightBack.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addData("Count per Inch: ", COUNTS_PER_INCH);
            telemetry.update();


            sleep(250);   // optional pause after each move.
        }
    }
}
