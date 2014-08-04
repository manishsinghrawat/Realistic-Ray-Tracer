package raytracer.geometricObjects;

import raytracer.utilities.*;

public class Plane extends GeometricObject {
	private Point3D 	a;   				
	private Normal 		n;					
				
	private static final double kEpsilon = 0.001;  
	
	public Plane() {	
		super();
		a = new Point3D(0.0);
		n = new Normal(0, 1, 0);						
	}

	public Plane(Point3D point, Normal normal) {
		super();
		a = new Point3D(point);
		n = new Normal(normal);
		n.normalize();
	}

	public Plane(Plane plane) {
		super(plane);
		a = new Point3D(plane.a);
		n = new Normal(plane.n); 				
	}

	public GeometricObject clone() {
		return (new Plane(this));

	}

	public void set (Plane rhs)	{
		
		if (this != rhs) {
			super.set(rhs);
			a.set(rhs.a);
			n.set(rhs.n);
		}
	}

        // hit returns a -1 if no hit
	// hit returns a number > 0 if hit
	
	public double hit(Ray ray, ShadeRec sr) {
		double t = ((a.subtract(ray.o)).dot(n)) / ((ray.d.dot(n))); 
		
		if (t > kEpsilon) {
			sr.normal = n;
			sr.local_hit_point = ray.o.add(ray.d.multiply(t));
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
			Plane other = (Plane)obj;
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
