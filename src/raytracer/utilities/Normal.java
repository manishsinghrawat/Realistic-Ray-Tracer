package raytracer.utilities;

public class Normal {
	public double	x, y, z;
	
	// ---------------------------------------------------------- default constructor

	public Normal()						
	{
		x = y = z = 0;
	}


	// ---------------------------------------------------------- constructor

	public Normal(double a)
	{
		x = y = z = 1.0;
	}


	// ---------------------------------------------------------- constructor

	public Normal(double a, double b, double c)	 
	{
		x = a;
		y = b;
		z = c;
	}


	// ---------------------------------------------------------- copy constructor

	public Normal(Normal n)
	{
		 x = n.x;
		 y = n.y; 
		 z = n.z;
	}


	// ---------------------------------------------------------- constructor
	// construct a normal from a vector

	public Normal(Vector3D v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}


	// ----------------------------------------------------------- operator=
	// assignment operator

	public void set (Normal rhs) {
		if (this != rhs) {
			x = rhs.x; y = rhs.y; z = rhs.z;
		}
	}

	// ------------------------------------------------------------ operator=
	// assignment of a vector to a normal

	public void set (Vector3D rhs) {
		x = rhs.x; y = rhs.y; z = rhs.z;
	}


	// ------------------------------------------------------------ operator=
	// assignment of a point to a normal

	public void set (Point3D rhs) {		
		x = rhs.x; y = rhs.y; z = rhs.z;
	}


	// ----------------------------------------------------------------------- operator-
	// unary minus

	public Normal minus () {
		return new Normal(-x, -y, -z);
	}


	// ----------------------------------------------------------------------- operator+
	// addition of two normals

	public Normal add (Normal n)  {
		return new Normal(x + n.x, y + n.y, z + n.z);
	}


	// ----------------------------------------------------------------------- addition
	// compound addition of two normals

	public void plusEqual (Normal n) {
		x += n.x; y += n.y; z += n.z;
	}


	// ----------------------------------------------------------------------- operator*
	// dot product of a normal on the left and a vector on the right

	public double dot(Vector3D v) {
		return (x * v.x + y * v.y + z * v.z);
	}


	// ----------------------------------------------------------------------- operator*
	// multiplication by a double on the right

	public Normal multiply (double a) {
		return new Normal(x * a, y * a, z * a);
	}


	// ------------------------------------------------------------ normalize

	public void normalize() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		x /= length; y /= length; z /= length;
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Normal other = (Normal)obj;
			return (this.x==other.x && this.y==other.y && this.z==other.z);
		}
	}
	
	public String toString() {
		return "Normal: (" + x + "," + y + "," + z + ")";
	}

	// static methods
	// non-member function definition
	

	// ----------------------------------------------------------------------- operator*
	// multiplication by a double on the left

	public static Normal multiply(double f, Normal n) {
		return new Normal(f * n.x, f * n.y,f * n.z);
	}


	// ----------------------------------------------------------------------- operator+
	// addition of a vector on the left to return a vector 

	public static Vector3D add (Vector3D v, Normal n) {	
		return new Vector3D(v.x + n.x, v.y + n.y, v.z + n.z);
	}	


	// ----------------------------------------------------------------------- operator-
	// subtraction of a normal from a vector to return a vector

	public static Vector3D subtract(Vector3D v, Normal n) {
		return new Vector3D(v.x - n.x, v.y - n.y, v.z - n.z);
	}


	// ----------------------------------------------------------------------- operator*
	// dot product of a vector on the left and a normal on the right

	public static double dot (Vector3D v, Normal n) {
		return (v.x * n.x + v.y * n.y + v.z * n.z);     
	}

	
	// ---------------------------------------------------------- operator*
	// multiplication by a matrix on the left

	// a normal is transformed by multiplying it on the left by the transpose of the upper left 3 x 3
	// partition of the inverse transformation matrix

	public static Normal multiply (Matrix mat, Normal n) {
		return new Normal(	mat.m[0][0] * n.x + mat.m[1][0] * n.y + mat.m[2][0] * n.z,
						mat.m[0][1] * n.x + mat.m[1][1] * n.y + mat.m[2][1] * n.z,
						mat.m[0][2] * n.x + mat.m[1][2] * n.y + mat.m[2][2] * n.z);
	}

}
