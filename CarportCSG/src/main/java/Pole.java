import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Pole
{
    private final static double TOLERANCE = 0.15;  // 15%
    private double length;
    private double width;
    private double beamWidth;
    private double beamHeight;
    private double SCALE;

    public Pole(double length, double width, double beamWidth, double beamHeight, double SCALE) {
        this.length = length;
        this.width = width;
        this.beamWidth = beamWidth;
        this.beamHeight = beamHeight;
        this.SCALE = SCALE;
    }

    public Geometry3D getPole()
    {
        JavaCSG csg = JavaCSGFactory.createDefault();
        Geometry3D pole = csg.box3D(length, width, width, true);
        Geometry3D cutoutBox = csg.box3D(beamHeight, beamHeight, width / 2.0, false);
        Geometry3D hole = csg.cylinder3D(3.4/2, width * 2, 32, false);

        Geometry3D pole0 = csg.translate3D(length / 2, 0,0).transform(pole);
        Geometry3D cylRotated = csg.rotate3DZ(csg.degrees(90.0)).transform(hole);
        Geometry3D cutoutBox1 = csg.translate3D(beamHeight / 2.0, 0 , 0).transform(cutoutBox);
        Geometry3D hole1 = csg.translate3D(width, 0 ,-width).transform(cylRotated);
        return csg.difference3D(pole0, hole1, cutoutBox1);

        //return csg.union3D(pole0, hole1, cutoutBox1);
    }
}
