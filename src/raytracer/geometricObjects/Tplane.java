/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.geometricObjects;

import raytracer.utilities.Normal;
import raytracer.utilities.Point3D;
import raytracer.utilities.Ray;
import raytracer.utilities.ShadeRec;
import raytracer.utilities.Vector3D;

/**
 *
 * @author Manish
 */
public class Tplane extends GeometricObject{
	private Point3D 	a;
	private Normal 		n;
	private Vector3D        k;
	private double w;
	private double w1;
	private static final double kEpsilon = 0.001;

	public Tplane() {
		super();
		a = new Point3D(0.0);
		n = new Normal(0, 1, 0);
		k=new Vector3D(1,0,0);
		w=100;
		w1=5;
	}

	public Tplane(Point3D point, Normal normal,Vector3D k,double w,double w1) {
		super();
		a = new Point3D(point);
		n = new Normal(normal);
		n.normalize();
		this.k=new Vector3D(k);
		k.normalize();
		this.w=w;
		this.w1=w1;
	}

	public Tplane(Tplane plane) {
		super(plane);
		a = new Point3D(plane.a);
		n = new Normal(plane.n);
		this.k=new Vector3D(plane.k);
		this.w=plane.w;
		this.w1=plane.w1;
	}

	public GeometricObject clone() {
		return (new Tplane(this));
	}

	public void set (Tplane rhs)	{

		if (this != rhs) {
			super.set(rhs);
			a.set(rhs.a);
			n.set(rhs.n);
			k.set(rhs.k);
			w=rhs.w;
			w1=rhs.w1;
		}
	}

	// hit returns a -1 if no hit
	// hit returns a number > 0 if hit

	public double hit(Ray ray, ShadeRec sr) {
		double t = ((a.subtract(ray.o)).dot(n)) / ((ray.d.dot(n)));

		if (t > kEpsilon) {

			sr.local_hit_point = ray.o.add(ray.d.multiply(t));
			double tex=sr.local_hit_point.subtract(a).dot(k)/w;
			tex=tex-Math.floor(tex)-w1/2;
			tex=2*tex/w1;
			if(tex<1)
				sr.normal=n.multiply(1-Math.cos(tex*Math.PI)).add(new Normal(k.multiply(-2*Math.sin(tex*Math.PI))));
			else
				sr.normal = n;
			sr.material=this.material;
			return t;
		}

		return -1;
	}
	public double shadow_hit(Ray ray) {
		double t = ((a.subtract(ray.o)).dot(n)) / ((ray.d.dot(n)));

		if (t > kEpsilon)
			return t;
		return -1;
	}
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Tplane other = (Tplane)obj;
			return (super.equals(obj)
					&& a.equals(other.a)
					&& n.equals(other.n));
		}
	}

	public String toString() {
		return  "plane through " + a + " with " + n;
	}

	@Override
	public boolean hasmoresamples() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void startsampling() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Point3D sample() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Normal getnormal() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public double pdf() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Point3D randsam() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
