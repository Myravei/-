package Model;
import javafx.scene.paint.Color;


import java.util.ArrayList;

public class Model {
    public  int pointCount;
public  ArrayList<Points>p;

    public Model() {

        this.pointCount = pointCount;
        this.p = new ArrayList<Points>();
    }
    public int getPointCount()
    {
        return p.size();
    }

    public void addPoint(Points point)
    {
    p.add(point);
    }
public void  remuvePoint(Points point)
{
   this.p.remove(point);
}
    public Points getPoint(int i)
    {
        return this.p.get(i);
    }
    public  void  deleteArray()
    {
    p.clear();
    }
    public int serchPoint(int x, int y)
    {
        int index = -1;
        for (int i = 0; i < this.p.size(); i++)
        {
            if (this.p.get(i).x == x && this.p.get(i).y == y) index = i;
        }
        return index;
    }

}
