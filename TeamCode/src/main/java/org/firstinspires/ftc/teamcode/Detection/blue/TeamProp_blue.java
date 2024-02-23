package org.firstinspires.ftc.teamcode.Detection.blue;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TeamProp_blue extends OpenCvPipeline {
    Mat mat = new Mat();
    Mat blue_output = new Mat();
    Mat red_output = new Mat();
    //mat reprezinta matrixul, o variabila ce poate stoca imaginea



    public enum Location {
        LEFT,
        CENTER,
        RIGHT
    }

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
    private volatile Location position = Location.LEFT;

    @Override

    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);



        //intervalul din HSV pentru albastru
        Scalar lowHSV_blue = new Scalar(100, 25, 25); //110 50 50
        Scalar highHSV_blue = new Scalar(150, 255, 255); //130

        /*thresholding, adica segmentarea imaginii, mai exact separam obiectele ce se incadreaza
        in aceste intervale de culoare din spectrul  HSV de restul imaginii
        in acest fel imaginea devine alba*/
        Core.inRange(mat, lowHSV_blue, highHSV_blue, mat);

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

    public Location getLocation() {
        return position;
    }
}
