package raytracer.geometricObjects;
import raytracer.utilities.Normal;
import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;
import raytracer.utilities.ShadeRec;
import raytracer.Materials.Material;
import raytracer.utilities.Point3D;

public abstract class GeometricObject {

	protected Material material;


	public GeometricObject() {}
	public GeometricObject (GeometricObject object) {}



	// ---------------------------------------------------------------------- assignment operator

	public void set (GeometricObject rhs) {}


	public abstract GeometricObject clone();

	public abstract double hit(Ray ray, ShadeRec s);
	public abstract double shadow_hit(Ray ray);
	public abstract boolean hasmoresamples();
	public abstract void startsampling();
	public abstract Point3D sample();


	public void set_material(Material mat){
		material=mat;
	}


	public abstract Normal getnormal();

	public abstract double pdf();

	public abstract Point3D randsam() ;

}
