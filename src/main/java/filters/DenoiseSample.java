package filters;

import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.medianBlur;


public class DenoiseSample {


    public static void main(String[] args) {
        smooth("src/main/resources/composed.jpg");
    }

    public static void smooth(String filename) {
        Mat image = imread(filename);
        if (image != null) {
//            GaussianBlur(image, image, new Size(3, 3), 0);
            medianBlur(image, image, 3);
            medianBlur(image, image, 3);
            medianBlur(image, image, 3);

            imwrite(filename + "_blured33.jpg", image);
        }
    }

}
