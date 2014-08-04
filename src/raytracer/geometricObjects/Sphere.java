package raytracer.geometricObjects;
import raytracer.utilities.*;

public class Sphere extends GeometricObject {

	private Point3D 	center;   			
	private double 		radius;				
	private static final double kEpsilon = 0.001;   
			
	public Sphere() {
		super();
		center = new Point3D(0.0);
		radius = 1.0;
	}
	
	
	public Sphere(Point3D c, double r) {
		super();
		center = new Point3D(c);
		radius = r;
	}


	public Sphere (Sphere sphere) {
		super(sphere);
		center = new Point3D(sphere.center);
		radius = sphere.radius;
	}

	public Sphere clone() {
		return (new Sphere(this));
	}
	


	public void set (Sphere rhs) {
		if (this != rhs) {
			this.center.set(rhs.center);
			this.radius	= rhs.radius;	
		}
	}
	

	public void set_center(Point3D c) {
		center.set(c);
	}
			
	public void set_center(double x, double y, double z) {
		center.x = x;
		center.y = y;
		center.z = z;
	}
			
	public void set_radius(double r) {
		radius = r;
	}
	


	// hit returns a -1 if no hit
	// hit returns a number > 0 if hit
	
	public double hit(Ray ray, ShadeRec sr) {
		double 		t;
		Vector3D	temp 	= ray.o.subtract(center);
		double 		a 		= ray.d.dot(ray.d);
		double 		b 		= 2.0 * temp.dot(ray.d);
		double 		c 		= temp.dot(temp) - (radius * radius);
		double 		disc	= b * b - 4.0 * a * c;
		
		if (disc < 0.0)
			return(-1);  
		else {	
			double e = Math.sqrt(disc);
			double denom = 2.0 * a;
			t = (-b - e) / denom;    
		
			if (t > kEpsilon) {
				sr.normal 	 = new Normal(temp.add(ray.d.multiply(t)).divide(radius));
				sr.local_hit_point = ray.o.add(ray.d.multiply(t));
                                sr.material=this.material;
				return (t);
			} 
		
			t = (-b + e) / denom;    
		
			if (t > kEpsilon) {
				sr.normal 	 = new Normal(temp.add(ray.d.multiply(t)).divide(radius));
				sr.local_hit_point = ray.o.add(ray.d.multiply(t));
                                sr.material=this.material;
				return (t);
			} 
		}
		
		return (-1); // no hit
	}
	public double shadow_hit(Ray ray) {
		double 		t;
		Vector3D	temp 	= ray.o.subtract(center);
		double 		a 		= ray.d.dot(ray.d);
		double 		b 		= 2.0 * temp.dot(ray.d);
		double 		c 		= temp.dot(temp) - (radius * radius);
		double 		disc	= b * b - 4.0 * a * c;

		if (disc < 0.0)
			return(-1); 
		else {
			double e = Math.sqrt(disc);
			double denom = 2.0 * a;
			t = (-b - e) / denom;    

			if (t > kEpsilon) 
				return (t);
			

			t = (-b + e) / denom;

			if (t > kEpsilon) 
				return (t);
			
		}

		return (-1); 
	}
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Sphere other = (Sphere)obj;
			return (super.equals(obj) 
					&& center.equals(other.center)
					&& radius == other.radius);
		}
	}
	
	public String toString() {
		return "sphere at " + center + " with radius " + radius;
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
