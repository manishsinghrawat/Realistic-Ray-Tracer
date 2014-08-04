package raytracer.utilities;

public class Point3D {
	public double x, y, z;
	public Point3D(){
		x = y = z = 0;
	}

	public Point3D(double a){
		x = y = z = a;
	}
	
	public Point3D(double a, double b, double c){
		x = a;
		y = b;
		z = c;
	}
	public Point3D(Point3D p){
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}
        public Point3D(Vector3D p){
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}

	public void set (Point3D p) {
		if (this != p) {
			x = p.x; y = p.y; z = p.z;
		}
	}
	public double distance(Point3D p) {
		return (Math.sqrt(	(x - p.x) * (x - p.x) 
						+ 	(y - p.y) * (y - p.y)
						+	(z - p.z) * (z - p.z) ));
	}
	public Point3D minus() {
		return new Point3D(-x, -y, -z);
	}
	public Vector3D subtract(Point3D p){
		return new Vector3D(x - p.x,y - p.y,z - p.z);
	}
	public Point3D add(Vector3D v) {
		return new Point3D(x + v.x, y + v.y, z + v.z);
	}
	public Point3D subtract(Vector3D v) {
		return new Point3D(x - v.x, y - v.y, z - v.z);
	}
	public Point3D multiply(double a) {
		return new Point3D(x * a,y * a,z * a);
	}
	public double d_squared(Point3D p)  {
		return (	(x - p.x) * (x - p.x) 
				+ 	(y - p.y) * (y - p.y)
				+	(z - p.z) * (z - p.z) );
	}
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Point3D other = (Point3D)obj;
			return (this.x==other.x && this.y==other.y && this.z==other.z);
		}
	}
	
	public String toString() {
		return "Point3D: (" + x + "," + y + "," + z + ")";
	}

	// static methods
	// non-member function

	// -------------------------------------------------------------- operator*
	// multiplication by a double on the left

	public static Point3D multiply (double a, Point3D p) {
		return new Point3D(a * p.x, a * p.y, a * p.z);
	}
	

	public static Point3D multiply (Matrix mat, Point3D p) {
		return new Point3D(mat.m[0][0] * p.x + mat.m[0][1] * p.y + mat.m[0][2] * p.z + mat.m[0][3],
						mat.m[1][0] * p.x + mat.m[1][1] * p.y + mat.m[1][2] * p.z + mat.m[1][3],
						mat.m[2][0] * p.x + mat.m[2][1] * p.y + mat.m[2][2] * p.z + mat.m[2][3]);
	}

	public static Point3D add(Point3D p, Vector3D p2) {
		return new Point3D(p.x + p2.x,
				p.y + p2.y,
				p.z + p2.z);
	}
		public static Vector3D subtract(Point3D p1, Point3D p2) {
		return new Vector3D(p1.x - p2.x,
				p1.y - p2.y,
				p1.z - p2.z);
	}
	
	public static Point3D subtract(Point3D p, Vector3D v) {
		return new Point3D(p.x - v.x,
				p.y - v.y,
				p.z - v.z);
	}
	
		public static Point3D minus(Point3D p) {
		return new Point3D(-p.x, -p.y, -p.z);
	}
	
	public static double d_squared(Point3D p1, Point3D p2) {
		return (	(p1.x - p2.x) * (p1.x - p2.x) 
				+ 	(p1.y - p2.y) * (p1.y - p2.y)
				+	(p1.z - p2.z) * (p1.z - p2.z) );
	}
	
}
