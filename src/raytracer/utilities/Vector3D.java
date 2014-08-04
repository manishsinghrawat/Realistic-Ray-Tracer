package raytracer.utilities;

public class Vector3D {
	public double	x, y, z;
	public Vector3D() {
		x = y = z = 0.0;
	}
	public Vector3D(double a) {
		x = y = z = a;
	}
	public Vector3D(double a, double b, double c)	 
	{
		x = a;
		y = b;
		z = c;
	}
	public Vector3D(Vector3D vector) {
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}

	public Vector3D(Normal n)	
	{
		x = n.x;
		y = n.y;
		z = n.z;
	}
	public Vector3D(Point3D p)	 
	{
		x = p.x;
		y = p.y;
		z = p.z;
	}

    public Vector3D(Point3D a, Point3D b) {
                x=b.x-a.x;
                y=b.y-a.y;
                z=b.z-a.z;
    }
	public void set (Vector3D rhs) {
		if (this != rhs) {
			x = rhs.x; y = rhs.y; z = rhs.z;
		}
	}
	public void set (Normal rhs) {
		x = rhs.x; y = rhs.y; z = rhs.z;
	}
	public void set (Point3D rhs) {
		x = rhs.x; y = rhs.y; z = rhs.z;
	}

	public Vector3D minus() {
		return new Vector3D(-x, -y, -z);    
	}

	public double length() {
		return (Math.sqrt(x * x + y * y + z * z));
	}

	public double len_squared() {	
		return (x * x + y * y + z * z);
	}

	public Vector3D multiply (double a) {	
		return new Vector3D(x * a, y * a, z * a);	
	}

	public Vector3D divide (double a) {	
		return new Vector3D(x / a, y / a, z / a);	
	}

	public Vector3D add (Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}
	public Vector3D subtract (Vector3D v) {
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}
        public double dot (Vector3D v) {
		return (x * v.x + y * v.y + z * v.z);
	} 
        public double dot (Normal v) {
		return (x * v.x + y * v.y + z * v.z);
	} 
        public Vector3D cross (Vector3D v)  {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
        public Vector3D cross (Normal v)  {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	public void plusEqual(Vector3D v) {
		x += v.x; y += v.y; z += v.z;
	}
	public void normalize() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		x /= length; y /= length; z /= length;
	}
	public Vector3D hat() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		x /= length; y /= length; z /= length;
		return this;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Vector3D other = (Vector3D)obj;
			return (this.x==other.x && this.y==other.y && this.z==other.z);
		}
	}
	
	public String toString() {
		return "Vector3D: (" + x + "," + y + "," + z + ")";
	}

	public Vector3D multiply (double a, Vector3D v) {
		return new Vector3D(a * v.x, a * v.y, a * v.z);	
	}
	public static Vector3D multiply (Matrix mat, Vector3D v) {
		return new Vector3D(mat.m[0][0] * v.x + mat.m[0][1] * v.y + mat.m[0][2] * v.z,
						mat.m[1][0] * v.x + mat.m[1][1] * v.y + mat.m[1][2] * v.z,
						mat.m[2][0] * v.x + mat.m[2][1] * v.y + mat.m[2][2] * v.z);
	}

	
	
}
