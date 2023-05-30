import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

public class Runner
{
	private static final double LENGTH = 780.0 * 10.0;
	private static final double WIDTH = 600.0 * 10.0;
	private static final double SCALE = 20.0 / 780.0;

	public static void main(String[] args)
	{
		JavaCSG csg = JavaCSGFactory.createDefault();
		Beam beam = new Beam(LENGTH * SCALE, 4.5 * 10 * SCALE, 19.5 * 10 * SCALE, SCALE);
		Pole pole = new Pole(210.0 * 10 * SCALE, 10.0 * 10 * SCALE, 4.5 * 10 * SCALE, 19.5 * 10 * SCALE, SCALE );
		Rafter rafter = new Rafter(WIDTH * SCALE, 4.5 * 10 * SCALE, 19.5 * 10 * SCALE, SCALE);

		csg.view(beam.getBeam(), 1);
		csg.view(pole.getPole(), 2);
		csg.view(rafter.getRafter(), 3);
	}
}
