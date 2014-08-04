/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Cameras;
import java.util.Random;
import javax.swing.JFrame;
import raytracer.utilities.*;
import raytracer.world.*;
/**
 *
 * @author Manish
 */
public class Perspective extends Camera{

	private Point3D eye;
	private Vector3D lookat;
	private Vector3D up;
	private double zoom;
	private RGBColor arr[];
	private RGBColor arr1[];
	private Vector3D x;
	private Vector3D y;

	private double dist;
	private int n ;


	public Perspective(World w,Point3D eye,Vector3D lookat,Vector3D up,double zoom){
		this.w=w;
		this.vp=new ViewPlane(w.vp);
		this.eye=eye;
		this.lookat=lookat.hat();
		this.up=up.hat();
		this.zoom=zoom;
		arr=new RGBColor[vp.hres*vp.vres];

		arr1=new RGBColor[vp.hres*vp.vres];
		for(int i=0;i<vp.hres*vp.vres;i++){
			arr[i]=new RGBColor(0,0,0);
			arr1[i]=new RGBColor(0,0,0);
		}
		x=lookat.cross(up).hat();
		y=up;
		vp.s /= zoom;

		dist=150.0;
		n = (int)Math.sqrt((float)vp.samples);
	}

	public void render_scene(JFrame frame) {
		Ray ray=new Ray();
		Vector3D pp;


		ray.o = eye;
		Random rand=new Random();
		float max=1.0f;
		for (int i = 0; i < vp.iterations; i++) {
			frame.setTitle("Ray Tracer : I="+i);
			for (int r = 0; r < vp.vres; r++)
				for (int c = 0; c < vp.hres; c++) {

					int p=rand.nextInt(n);
					int q=rand.nextInt(n);
					//for (int p = 0; p < n; p++)
					//      for (int q = 0; q < n; q++) {
					pp = x.multiply(vp.s * (c - 0.5 * vp.hres + (q + 0.5) / n)).add(y.multiply(vp.s * (r- 0.5 * vp.vres + (p + 0.5) / n)));
					ray.d = pp.add(lookat.multiply(dist)).hat();
					//  if(i==0) arr1[r*vp.vres+c].plusEqual(w.tracer.trace_ray(ray));
					arr1[r*vp.hres+c]=w.tracer.trace_ray(ray,1,true);
					arr[r*vp.hres+c].plusEqual(arr1[r*vp.hres+c]);
					//if(arr[r*vp.vres+c].max()>max)
					//max=arr[r*vp.vres+c].max();
					//    }
					// L.divideEqual(vp.samples);
					//	L.multiply(exposuretime);
					//   w.display_pixel(r, c, arr[r*vp.vres+c].divide(i+1));
				}

			for (int r = 0; r < vp.vres; r++)
				for (int c = 0; c < vp.hres; c++)
					w.display_pixel(r, c, arr[r*vp.hres+c].divide(i+1));


		}

	}
}
