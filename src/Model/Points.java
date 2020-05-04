package Model;


import javafx.scene.paint.Color;

import java.awt.*;

public class Points extends Point {
    private Color color;
    private double Point1;
    private  double Point2;

    public  Points()
    {
        this.color= Color.RED;
        this.Point1=1;
        this.Point2=1;
    }
    public  Points(int x,int y)
    {
        super(x,y);
    }
    public  Points(int x,int y,Color color,double Point1,double Point2)
    {
        super(x,y);
        this.color=color;
        this.Point1=Point1;
        this.Point2=Point2;
    }
    public Color getColor()
    {
        return  color;
    }
    public void  setColor(Color color)
    {
        this.color=color;
    }


    public double getPoint2() {
        return Point2;
    }
    public void setPoint2(double wP) {
        this.Point2 = Point1;
    }

    public double getPoint1() {
        return Point2;
    }
    public void setPoint1(double Point1) {
        this.Point2 = Point2;
    }

    public  void  setSizePoint(double Point1,double Point2)
    {
        this.Point1=Point1;
        this.Point2=Point2;
    }

}
