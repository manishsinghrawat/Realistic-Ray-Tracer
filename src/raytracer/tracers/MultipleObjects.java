package raytracer.tracers;

import raytracer.utilities.*;
import raytracer.world.World;


public class MultipleObjects extends Tracer {

	// -------------------------------------------------------------------- default constructor

	public MultipleObjects() {
		super();
	}

	// -------------------------------------------------------------------- constructor

	public MultipleObjects(World world) {
		super(world);
	}



	// -------------------------------------------------------------------- trace_ray

	public RGBColor trace_ray(Ray ray) {
		ShadeRec sr = world_ptr.hit_bare_bones_objects(ray);

		if (sr.hit_an_object){
			//System.out.println(sr.local_hit_point);
			//return RGBColor.red;
			return (sr.material.Shade(sr));
		} else
			return (world_ptr.background_color);
	}

	// -------------------------------------------------------------------- trace_ray

	public RGBColor trace_ray(Ray ray, int depth,boolean drawlights) {
		return trace_ray(ray);
	}

}
