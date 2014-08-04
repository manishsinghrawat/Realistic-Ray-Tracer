package raytracer.utilities;

public class Ray {
	public Point3D		o;  	// origin 
	public Vector3D		d; 		// direction 
	
	public Ray () {
		o = new Point3D(0.0);
		d = new Vector3D(0.0, 0.0, 1.0);
	}
        public Ray (Point3D origin, Vector3D dir) {
			o = new Point3D(origin);
			d = new Vector3D(dir).hat();
	}
        public Ray (Point3D a,Point3D b){
            o=new Point3D(a);
            d=b.subtract(a).hat();
        }
        public Ray (Ray ray) {
		o = new Point3D(ray.o);
		d = new Vector3D(ray.d);
	}


        public void set (Ray rhs) {
		
		if (this != rhs) {			
			o = new Point3D(rhs.o); 
			d = new Vector3D(rhs.d);
		}
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Ray other = (Ray)obj;
			return (o.equals(other.o) && d.equals(other.d));
		}
	}
	
	public String toString() {
		return "Ray: origin (" + o + ")\tdir (" + d + ")";
	}
}
