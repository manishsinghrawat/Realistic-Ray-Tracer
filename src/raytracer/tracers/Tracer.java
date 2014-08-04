package raytracer.tracers;
import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;
import raytracer.world.World;

//This is the declaration of the base class Tracer
//The tracer classes have no copy constructor or assignment operator because 
//of the world pointer, which should not be assigned or copy constructed
//See comments in World.java

public abstract class Tracer {
	protected World world_ptr;
	public Tracer() {
		world_ptr = null;
	}
        public Tracer(World worldPtr){
		world_ptr = worldPtr;
	}
        public abstract RGBColor trace_ray(Ray ray);
        public abstract RGBColor trace_ray(Ray ray, int depth,boolean drawlights);

	
}
