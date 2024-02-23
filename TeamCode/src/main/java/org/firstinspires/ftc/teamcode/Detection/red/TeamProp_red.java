package org.firstinspires.ftc.teamcode.Detection.red;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TeamProp_red extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat blue_output = new Mat();
    Mat red_output = new Mat();
    //mat reprezinta matrixul, o variabila ce poate stoca imaginea



    public enum Location{
        LEFT,
        CENTER,
        RIGHT
    }
    private static Location position = Location.LEFT;

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
        Scalar lowHSV_red = new Scalar(0, 35, 50); //0, 50, 70
        Scalar highHSV_red = new Scalar(45, 255, 255); //30



        /*thresholing, adica segmentarea imaginii, mai exact separam obiectele ce se incadreaza
        in aceste intervale de culoare din spectrul HSV de restul imaginii
        in acest fel imaginea devine alba*/
        Core.inRange(mat, lowHSV_red, highHSV_red, mat);


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


        if(leftValue > centerValue && leftValue > rightValue) {
            position = Location.LEFT;
        }
        else if(centerValue > leftValue && centerValue > rightValue) {
            position = Location.CENTER;
        }
        else {
            position = Location.RIGHT;
        }

        //pentru a colora dreptunghiurile in care vom detecta TeamProp-ul
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        return mat;
    }

    public Location getLocation(){
        return position;
    }

}
