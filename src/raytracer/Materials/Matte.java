/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Materials;
import java.util.Random;
import raytracer.Lights.*;
import raytracer.utilities.*;

/**
 *
 * @author Manish
 */
public class Matte extends Material{
	private MatProp ambient;
	private MatProp diffuse;
	Random random;
	public Matte(){
		ambient=new MatProp();
		diffuse=new MatProp();
		random=new Random();
	}
	public void setka(RGBColor ka){
		ambient.setk(ka);
	}
	public void setkd(RGBColor kd){
		diffuse.setk(kd);
	}

	public RGBColor Shade(ShadeRec rec){
		int lightnum=rec.w.ls.size();
		RGBColor L=new RGBColor(0.0,0.0,0.0);
		Light cur;
		for(int i=0;i<lightnum;i++){
			cur=rec.w.ls.get(i);
			if(cur instanceof Ambient){
				L.plusEqual( ambient.get().multiply(cur.radiance(rec)) );
			}else if (cur instanceof PointLight){
				Vector3D w=cur.direction(rec);
				Normal n=rec.normal;

				double dot=w.dot(n);
				if(dot>0.0){
					boolean shadowed=false;
					PointLight temp=(PointLight)cur;
					Ray shadowray=new Ray(rec.local_hit_point,w);
					shadowed=temp.shadowed(shadowray, rec);
					if(!shadowed)
						L.plusEqual( diffuse.get().multiply(cur.radiance(rec).multiply(dot)));
				}
			}else if(cur instanceof AreaLight){
				AreaLight arealight=(AreaLight)cur;
				boolean shadowed=false;
				arealight.init_sampling();
				while(arealight.hasmoresamples()){
					arealight.nextsample();
					Vector3D w=cur.direction(rec);
					Ray shadowray=new Ray(rec.local_hit_point,w);
					Normal n=rec.normal;

					double dot=w.dot(n);

					shadowed=arealight.shadowed(shadowray, rec);
					if(!shadowed)
						L.plusEqual( diffuse.get().multiply(cur.radiance(rec).multiply(dot*arealight.pdf())) );
				}

			}
		}

		return L;
	}

	public RGBColor pathShades(ShadeRec rec, int depth) {
		int lightnum=rec.w.ls.size();
		RGBColor L=new RGBColor(0.0,0.0,0.0);
		Light cur;
		for(int i=0;i<lightnum;i++){
			cur=rec.w.ls.get(i);
			if(cur instanceof Ambient){
				L.plusEqual( ambient.get().multiply(cur.radiance(rec)) );
			}else if (cur instanceof PointLight){
				Vector3D w=cur.direction(rec);
				Normal n=rec.normal;

				double dot=w.dot(n);
				if(dot>0.0){
					boolean shadowed=false;
					PointLight temp=(PointLight)cur;
					Ray shadowray=new Ray(rec.local_hit_point,w);
					shadowed=temp.shadowed(shadowray, rec);
					if(!shadowed)
						L.plusEqual( diffuse.get().multiply(cur.radiance(rec).multiply(dot)));
				}
			}else if(cur instanceof AreaLight){
				AreaLight arealight=(AreaLight)cur;
				boolean shadowed=false;
				arealight.init_sampling();
				while(arealight.hasmoresamples()){
					arealight.nextsample();
					Vector3D w=cur.direction(rec);
					Ray shadowray=new Ray(rec.local_hit_point,w);
					Normal n=rec.normal;

					double dot=w.dot(n);

					shadowed=arealight.shadowed(shadowray, rec);
					if(!shadowed)
						L.plusEqual( diffuse.get().multiply(cur.radiance(rec).multiply(dot*arealight.pdf())) );
				}

			}
		}

		return L;

	}

	public Vector3D sample(ShadeRec rec,Vector3D wo){


		double x=random.nextDouble();
		double y=random.nextDouble();

		//double exp=1.0;
		double cos_phi = Math.cos(2.0 * Math.PI * x);
		double sin_phi = Math.sin(2.0 * Math.PI * x);
		double cos_theta = (1.0 - y);
		double sin_theta =  Math.sqrt (1.0 - cos_theta * cos_theta);
		double pu = sin_theta * cos_phi;
		double pv = sin_theta * sin_phi;
		double pw = cos_theta;

		//double ndotwo = wo.dot(rec.normal);
		//Vector3D r = wo.minus().add(new Vector3D(rec.normal.multiply(2.0* ndotwo)));

		Vector3D w = new Vector3D(rec.normal);
		Vector3D u = new Vector3D(0.00424, 1, 0.00764).cross(w);
		u.normalize();
		Vector3D v = u.cross(w);

		Vector3D i =  u.multiply(pu).add(v.multiply(pv).add(w.multiply(pw)));

		//if (rec.normal.dot(i) < 0.0)
			//	i = i.minus();

		return i;
	}

	@Override
	public RGBColor path(ShadeRec sr, int depth) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public RGBColor pathShade(ShadeRec rec, int depth) {Light cur;
	RGBColor L=new RGBColor(RGBColor.black);
	int lightnum=rec.w.ls.size();
	for(int i=0;i<lightnum;i++){
		cur=rec.w.ls.get(i);
		if(cur instanceof AreaLight){
			AreaLight arealight=(AreaLight)cur;
			boolean shadowed=false;
			arealight.randsam();
			Vector3D w=arealight.direction(rec);

			Ray shadowray=new Ray(rec.local_hit_point,w);
			Normal n=rec.normal;
			double dot=w.dot(n);

			shadowed=arealight.shadowed(shadowray, rec);

			if(!shadowed){
				if(dot>0.0)
					L.plusEqual( diffuse.get().multiply(cur.radiance(rec).multiply(dot*arealight.G(rec))));
			}
		}

	}

	double prob =random.nextDouble();
	double faktor=1.0;
	if(depth>1)
		if(prob<0.5)
			return L;
		else{
			faktor=0.5;
		}

	Vector3D n=sample(rec,rec.r.d.minus());
	Vector3D wo=rec.r.d;
	double ndotwo = rec.normal.dot(wo.minus());
	Vector3D r = wo.add(new Vector3D(rec.normal.multiply(2.0* ndotwo)));

	double pdfd = (rec.normal.dot(n));
	if(pdfd>0.001) { // for rays going bak into objekt{9
		L.plusEqual(diffuse.get().multiply(rec.w.tracer.trace_ray(new Ray(rec.local_hit_point,n.hat()), depth+1,false)).multiply(pdfd/(faktor)) );
	}

	return L;

	}



}

