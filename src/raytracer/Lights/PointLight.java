/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Lights;

import raytracer.utilities.*;
/**
 *
 * @author Manish
 */
public class PointLight extends Light{

	private Point3D position;

	public PointLight(Point3D position,RGBColor color,double k){
		this.position=position;
		this.color=color;
		this.k=k;

	}
	public Point3D getpos(){
		return position;
	}
	public Vector3D direction(ShadeRec rec){
		return position.subtract(rec.local_hit_point).hat();
	}
	public RGBColor radiance(ShadeRec rec){
		return color.multiply(k);
	}
	public boolean shadowed(Ray r,ShadeRec rec){
		double t;
		int num=rec.w.objects.size();
		double d=position.distance(r.o);

		for(int i=0;i<num;i++){
			t=rec.w.objects.get(i).shadow_hit(r);
			if(t>0 && t<d)
				return true;
		}
		return false;
	}

}
