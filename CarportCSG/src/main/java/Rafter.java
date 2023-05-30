import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Rafter
{
    private final static double TOLERANCE = 0.15;  // 15%
    private double length;
    private double width;
    private double height;
    private double SCALE;

    public Rafter(double length, double width, double height, double SCALE) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.SCALE = SCALE;
    }

    public Geometry3D getRafter()
    {
        JavaCSG csg = JavaCSGFactory.createDefault();

        Geometry3D beam = csg.box3D(length, width, height, false);
        Geometry3D cutoutBox = csg.box3D(height, height, width * 1.15, false);

        Geometry3D beam0 = csg.translate3D(length / 2, 0,0).transform(beam);
        Geometry3D cutoutBoxRotated = csg.rotate3DY(csg.degrees(90.0)).transform(cutoutBox);
        Geometry3D cutoutBoxRotatedTranslated1 = csg.translate3D(30.0 * 10 * SCALE, 0 , 0).transform(cutoutBoxRotated);
        Geometry3D cutoutBoxRotatedTranslated2 = csg.translate3D(570.0 * 10 * SCALE, 0 , 0).transform(cutoutBoxRotated);

        return csg.difference3D(beam0, cutoutBoxRotatedTranslated1, cutoutBoxRotatedTranslated2);
        //return csg.union3D(beam0, cutoutBoxRotatedTranslated1, cutoutBoxRotatedTranslated2);
    }

}
