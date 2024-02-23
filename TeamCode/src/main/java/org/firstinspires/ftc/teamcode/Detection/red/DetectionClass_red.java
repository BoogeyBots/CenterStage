package org.firstinspires.ftc.teamcode.Detection.red;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class DetectionClass_red extends TeamProp_red {
    HardwareMap hardwareMap;
    OpenCvWebcam webcam;
    TeamProp_red detectionRed = new TeamProp_red();

    public DetectionClass_red(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }


    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
        int webcamID = hardwareMap.appContext.getResources().getIdentifier("webcamID", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, webcamID);

        //aici scriem Pipeline-ul
        detectionRed = new TeamProp_red();
        webcam.setPipeline(detectionRed);

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

    public void stopStreaming(){
        webcam.stopStreaming();
    }
    public Location getLocation() {
        return detectionRed.getLocation();
    }


}


