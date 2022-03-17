package render3.shapes;

import render2.point.MyPoint;
import render2.point.PointConverter;

import java.awt.*;

public class MyPolygon {

    private MyPoint[] points;

    //kopiujemy wszystko do MyPolygon
    public MyPolygon(MyPoint...points){

        this.points = new MyPoint[points.length];
        //dla kazdego punktu dla którego tworzymy kopie punktu (tablica)

        for(int i=0; i<points.length;i++) {
        MyPoint p = points[i];
        this.points[i]= new MyPoint(p.x,p.y,p.z); // kopiuje to wszystko do MyPolygon
        }

    }

    public void render(Graphics g){
        // konwertowanie każdego pojedynczego punktu w tym 3 wymiarowym ksztalcie, wykona kilka punktów 2d, wklepie do 2 wymiarowego polygonu
        //i narysuje to na ekranie
        Polygon poly = new Polygon();
        for(int i=0;i<points.length;i++) {
                Point p = PointConverter.convertPoint(points[i]);
                poly.addPoint(p.x,p.y);
        }
        g.setColor(Color.RED);
        g.fillPolygon(poly);
    }

}
