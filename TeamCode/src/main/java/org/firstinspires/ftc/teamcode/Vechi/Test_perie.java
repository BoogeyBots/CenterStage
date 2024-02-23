package org.firstinspires.ftc.teamcode.Vechi;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test_perie", group = "Test")
public class Test_perie extends LinearOpMode{

        public CRServo servo_nfs;
        public DcMotor motor_lant;
        boolean da=true;

        @Override
        public void runOpMode() throws InterruptedException {
            servo_nfs=hardwareMap.get(CRServo.class ,"servo_nfs");
            motor_lant=hardwareMap.get(DcMotor.class ,"motor_lant");

            waitForStart();

            while(opModeIsActive()){

                while(da==true)
                {
                    servo_nfs.setPower(1);
                    motor_lant.setPower(1);
                }



                telemetry.update();
            }
        }


}
