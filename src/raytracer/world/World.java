package raytracer.world;
import raytracer.utilities.*;
import raytracer.geometricObjects.*;
import raytracer.tracers.*;
import raytracer.scenes.*;
import raytracer.Lights.*;
import raytracer.Cameras.*;
import java.util.Vector;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class World {
	public ViewPlane vp;
	public RGBColor background_color;
	public Tracer tracer;

	public Camera camera;
	public Vector<GeometricObject>	objects;		
	public Vector<Light> ls;
	private Graphics graphics;   

	private static final int ROWS = 300, COLS = 300;

	public World(){
		vp = new ViewPlane();
		background_color = RGBColor.black;
		tracer= null;
		objects = new Vector<GeometricObject>();
		ls=new Vector<Light>();
	}

	public void setGraphics(Graphics gr) {
		graphics = gr;
	}

	public void add_object(GeometricObject object) {  
		objects.add(object);	
	}
	public void add_light(Light l) {
		ls.add(l);
	}

	public int[] build() {
		vp.set_hres(COLS);
		vp.set_vres(ROWS);
		vp.set_pixel_size(1.f);
		scene.build(this);


		int[] resolution = new int[2];
		resolution[0] = vp.hres;
		resolution[1] = vp.vres;

		return resolution;
	}


	public RGBColor max_to_one(RGBColor c) {
		float max_value = Math.max(c.r, Math.max(c.g, c.b));

		if (max_value > 1.0)
			return new RGBColor(c.divide(max_value));
		else
			return new RGBColor(c);
	}

	public RGBColor clamp_to_color(RGBColor raw_color) {
		RGBColor c = new RGBColor(raw_color);

		if (raw_color.r > 1.0 || raw_color.g > 1.0 || raw_color.b > 1.0) {
			c.r = 1.0f; c.g = 0.0f; c.b = 0.0f;
		}

		return (c);
	}

	public void display_pixel(int row, int column, RGBColor raw_color) {
		RGBColor mapped_color;

		if (vp.show_out_of_gamut)
			mapped_color = clamp_to_color(raw_color);
		else
			mapped_color = max_to_one(raw_color);


		if (vp.gamma != 1.0)
			mapped_color = mapped_color.powc(vp.inv_gamma);

		int x = column;
		int y = vp.vres - row - 1;

		Color color = new Color((int)(mapped_color.r * 255),
				(int)(mapped_color.g * 255),
				(int)(mapped_color.b * 255));

		graphics.setColor(color);

		graphics.fillRect(x, y, 1, 1);

	}


	public ShadeRec hit_bare_bones_objects(Ray ray) {
		ShadeRec	sr = new ShadeRec(this);
		ShadeRec        fin=sr;
		double		t; 			
		double		tmin 			= Constants.kHugeValue;
		int 		num_objects 	= objects.size();

		for (int j = 0; j < num_objects; j++) {
			t = objects.get(j).hit(ray,sr);
			if ((0 < t) && (t < tmin)) {

				sr.hit_an_object	= true;
				sr.r=ray;
				tmin 				= t;
				fin=new ShadeRec(sr);
			}
		}

		return (fin);
	}

	public void delete_objects() {
		objects.clear();
	}

}

