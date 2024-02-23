package org.firstinspires.ftc.teamcode.Detection.test;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Pipeline {
    Mat YCbCr = new Mat();
    Mat leftCrop;
    Mat centerCrop;
    Mat rightCrop;
    double leftavgfin;
    double centeravgfin;
    double rightavgfin;
    Mat outPut = new Mat();
    Scalar rectColor = new Scalar(255, 0, 0);

    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
        telemetry.addLine("pipeline running");

        Rect leftRect = new Rect(1, 1, 425, 719);
        Rect centerRect = new Rect(426, 1, 425, 719);
        Rect rightRect = new Rect(852, 1, 425, 719);

        input.copyTo(outPut);
        Imgproc.rectangle(outPut, leftRect, rectColor, 2);
        Imgproc.rectangle(outPut, centerRect, rectColor, 2);
        Imgproc.rectangle(outPut, rightRect, rectColor, 2);

        leftCrop = YCbCr.submat(leftRect);
        centerCrop = YCbCr.submat(centerRect);
        rightCrop = YCbCr.submat(rightRect);

        Core.extractChannel(leftCrop, leftCrop, 2);
        Core.extractChannel(centerCrop, centerCrop, 2);
        Core.extractChannel(rightCrop, rightCrop, 2);


        Scalar leftavg = Core.mean(leftCrop);
        Scalar centeravg = Core.mean(centerCrop);
        Scalar rightavg = Core.mean(rightCrop);

        leftavgfin = leftavg.val[0];
        centeravgfin = centeravg.val[0];
        rightavgfin = rightavg.val[0];

        if (leftavgfin > rightavgfin && leftavgfin > centeravgfin) {
            telemetry.addLine("Left");
        }
        else if (rightavgfin > leftavgfin && rightavgfin > centeravgfin) {
            telemetry.addLine("Right");
        }
        else {
            telemetry.addLine("Center");
        }

        return (outPut);

    }

}

