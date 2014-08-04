/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.tracers;

import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;
import raytracer.utilities.ShadeRec;
import raytracer.world.World;

/**
 *
 * @author Manish
 */
public class PathTracer extends Tracer{

	public PathTracer(World worldPtr) {
		super(worldPtr);
	}

	public PathTracer() {
		super();
	}


	public RGBColor trace_ray(Ray ray) {
		ShadeRec sr = world_ptr.hit_bare_bones_objects(ray);

		if (sr.hit_an_object){
			return (sr.material.Shade(sr));
		} else
			return (world_ptr.background_color);
	}


	public RGBColor trace_ray(Ray ray, int depth,boolean drawlights) {

		ShadeRec sr = world_ptr.hit_bare_bones_objects(ray);

		if (sr.hit_an_object){
			sr.drawlights=drawlights;
			return (sr.material.pathShade(sr,depth));
		} else
			return (world_ptr.background_color);

	}

}
