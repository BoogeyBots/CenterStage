package org.firstinspires.ftc.teamcode.Detection.test;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

//@Autonomous(name = "Test camera", group = "Test")
public class Webcam_setup {
    OpenCvWebcam webcam = null;


    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
        int webcamID = hardwareMap.appContext.getResources().getIdentifier("webcamID", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, webcamID);

        //webcam.setPipeline(new TestCamera.Pipeline());

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public void loop() {

    }

    public void stopStreaming(){
        webcam.stopStreaming();

    }

}
