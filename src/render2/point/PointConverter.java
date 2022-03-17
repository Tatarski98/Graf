package render2.point;

import render.Display;

import java.awt.*;

public class PointConverter {

    //odpowiada za przekonwertowanie punktu 3D na punkt 2D - zeby się wyświetlił na ekranie
    public static Point convertPoint(MyPoint point3D) {

        int x2d = (int) (Display.WIDTH / 2 + point3D.y);
        int y2d = (int) (Display.HEIGHT / 2 + point3D.z);

        Point point2D = new Point(x2d,y2d);

        return point2D;
    }

}
