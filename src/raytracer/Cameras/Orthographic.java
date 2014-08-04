/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Cameras;
import javax.swing.JFrame;
import raytracer.utilities.*;
import raytracer.world.*;

/**
 *
 * @author Manish
 */
public class Orthographic extends Camera{
    public Orthographic(World w){
        this.w=w;
        this.vp=w.vp;
    }
    public void render_scene(JFrame frame)  {

		RGBColor	col;
		Ray			ray 	= new Ray();
		double		zw		= 100.0;			
		double		x, y;

		ray.d = new Vector3D(0, 0, -1);

		for (int r = 0; r < vp.vres; r++)			// up
			for (int c = 0; c <= vp.hres; c++) {	// across
				x = vp.s * (c - 0.5 * (vp.hres - 1.0));
				y = vp.s * (r - 0.5 * (vp.hres - 1.0));
				ray.o = new Point3D(x, y, zw);
				col = w.tracer.trace_ray(ray);
				w.display_pixel(r, c, col);
			}
	}


}
