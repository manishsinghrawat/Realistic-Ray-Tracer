/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Cameras;

import java.util.Random;
import javax.swing.JFrame;
import raytracer.utilities.*;
import raytracer.world.ViewPlane;
import raytracer.world.World;

/**
 *
 * @author Manish
 */
public class Depthcam extends Camera{
	private double radius;
	private double vpd;
	private double fpd;
	private double zoom;

	private Point3D eye;
	private Vector3D lookat;
	private Vector3D up;
	private RGBColor arr[];
	private RGBColor arr1[];
	private Vector3D x;
	private     Vector3D y;

	private    int n ;
	private double ratio;
	public Depthcam(World w,Point3D eye,Vector3D lookat,Vector3D up,double zoom,double radius, double vpd, double fpd) {
		this.w=w;
		this.vp=new ViewPlane(w.vp);
		this.eye=eye;
		this.lookat=lookat.hat();
		this.up=up.hat();
		arr=new RGBColor[vp.hres*vp.vres];

		arr1=new RGBColor[vp.hres*vp.vres];
		for(int i=0;i<vp.hres*vp.vres;i++){
			arr[i]=new RGBColor(0,0,0);
			arr1[i]=new RGBColor(0,0,0);
		}
		x=lookat.cross(up).hat();
		y=up;
		vp.s /= zoom;

		n = (int)Math.sqrt((float)vp.samples);
		this.radius = radius;
		this.vpd = vpd;
		this.fpd = fpd;
		this.zoom = zoom;
		this.ratio=fpd/vpd;
	}

	public void render_scene(JFrame frame) {
		Ray		ray=new Ray();
		Vector3D	pp;


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
					double rad=radius*Math.sqrt(rand.nextDouble());
					double ang=2*Math.PI*rand.nextDouble();
					Vector3D ptlens=x.multiply(rad*Math.cos(ang)).add(y.multiply(rad*Math.sin(ang)));
					ray.o = new Point3D(eye.add(ptlens));

					pp = x.multiply(vp.s * (c - 0.5 * vp.hres + (q + 0.5) / n)).add(y.multiply(vp.s * (r- 0.5 * vp.vres + (p + 0.5) / n)));
					pp.plusEqual(lookat.multiply(vpd));
					pp=pp.multiply(ratio);
					ray.d=pp.subtract(ptlens).hat();
					//  if(i==0) arr1[r*vp.vres+c].plusEqual(w.tracer.trace_ray(ray));
					// arr1[r*vp.vres+c]=w.tracer.trace_ray(ray,1);
					arr[r*vp.hres+c].plusEqual(w.tracer.trace_ray(ray,1,true));
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
