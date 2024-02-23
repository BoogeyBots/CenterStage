package org.firstinspires.ftc.teamcode.Detection.test;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Detection extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    Mat blue_output = new Mat();
    Mat red_output = new Mat();
    //mat reprezinta matrixul, o variabila ce poate stoca imaginea
    public Detection(Telemetry t){
        telemetry = t;
    }



    public enum Location{
        LEFT,
        CENTER,
        RIGHT
    }
    private static Location location;

    //aceste linii de cod ne permiteau sa cautam doar intr-o anumita zona prestabilita
    //insa noi avem nevoie sa cautam pe ecran, in 3 zone, dar care sa acopere toata suprafata
    /*static final Rect LEFT = new Rect(
            new Point(60, 35),
            new Point(120, 75));

    static final Rect CENTER = new Rect(
            new Point(140, 35),
            new Point(200, 75));

    static final Rect RIGHT = new Rect(
            new Point(220, 35),
            new Point(280, 75));

     */

    @Override

    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        //intervalul din HSV pentru rosu
        Scalar lowHSV_red = new Scalar(0, 50, 70);
        Scalar highHSV_red = new Scalar(20, 255, 255);

        //intervalul din HSV pentru albastru
        Scalar lowHSV_blue = new Scalar(110, 50, 50);
        Scalar highHSV_blue = new Scalar(130, 255, 255);

        /*thresholing, adica segmentarea imaginii, mai exact separam obiectele ce se incadreaza
        in aceste intervale de culoare din spectrul HSV de restul imaginii
        in acest fel imaginea devine alba*/
        Core.inRange(mat, lowHSV_red, highHSV_red, red_output);
        Core.inRange(mat, lowHSV_blue, highHSV_blue, blue_output);

        double blue = Core.sumElems(blue_output).val[0] / 255;
        double red = Core.sumElems(red_output).val[0] / 255;


        if(blue > red) {
            mat = blue_output;
        }
        else {
            mat = red_output;
        }



        Rect leftRect = new Rect(1, 1, 425, 719);
        Rect centerRect = new Rect(426, 1, 425, 719);
        Rect rightRect = new Rect(852, 1, 425, 719);


        Mat left = mat.submat(leftRect);
        Mat center = mat.submat(centerRect);
        Mat right = mat.submat(rightRect);

        //calculeaza procentul de alb din fiecare zona
        double leftValue = Core.sumElems(left).val[0] / leftRect.area() / 255;
        double centerValue = Core.sumElems(center).val[0] / centerRect.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / rightRect.area() / 255;

        left.release();
        center.release();
        right.release();

        //pentru debugging, ne ofera valorile obtinute in fiecare zona
        telemetry.addData("Left value: ", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Center value: ", (int) Core.sumElems(center).val[0]);
        telemetry.addData("Right value: ", (int) Core.sumElems(right).val[0]);

        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Center percentage", Math.round(centerValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");


        if(leftValue > centerValue && leftValue > rightValue) {
            location = Location.LEFT;
            telemetry.addData("Team Prop location: ", "left");
        }
        else if(centerValue > leftValue && centerValue > rightValue) {
            location = Location.CENTER;
            telemetry.addData("Team Prop location: ", "center");
        }
        else {
            location = Location.RIGHT;
            telemetry.addData("Team Prop location: ", "right");

        }

        telemetry.update();

        //pentru a colora dreptunghiurile in care vom detecta TeamProp-ul
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        return mat;
    }

    public static Location getLocation(){
        return location;
    }

}
