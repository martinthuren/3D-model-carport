import org.abstractica.javacsg.Angle;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Beam
{
    private final static double TOLERANCE = 0.15;  // 15%
    private double length;
    private double width;
    private double height;
    private double SCALE;

    public Beam(double length, double width, double height, double SCALE)
    {
        this.length = length;
        this.width = width;
        this.height = height;
        this.SCALE = SCALE;
    }

    public Geometry3D getBeam()
    {
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D beam = csg.box3D(length, width, height, false);
        Geometry3D hole = csg.cylinder3D(3.4/2, width, 32, true );

        Geometry3D beam0 = csg.translate3D(length / 2, 0,0).transform(beam);
        Geometry3D cylRotated = csg.rotate3DX(csg.degrees(90.0)).transform(hole);
        Geometry3D cyl1 =  csg.translate3D(100 * 10 * SCALE, 0.0, height/2.0 ).transform(cylRotated);
        Geometry3D cyl2 =  csg.translate3D(410 * 10 * SCALE, 0.0,height/2.0).transform(cylRotated);
        Geometry3D cyl3 =  csg.translate3D(750 * 10 * SCALE, 0.0,height/2.0).transform(cylRotated);

        return csg.difference3D(beam0, cyl1, cyl2, cyl3);
    }

}
