package raytracer.utilities;


/** 
 * this file contains the declaration of the class Matrix
 * Matrix is a 4 x 4 square matrix that is used to represent affine transformations
 * we don't need a general m x n matrix
 *
 */
public class Matrix {

	public double[][] m = new double[4][4];								// elements
	
	// ----------------------------------------------------------------------- default constructor
	// a default matrix is an identity matrix

	public Matrix() {	
		
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++) {
				if (x == y)
					m[x][y] = 1.0;
				else
					m[x][y] = 0.0;
			}
	}


	// ----------------------------------------------------------------------- copy constructor

	public Matrix (Matrix mat) {
		for (int x = 0; x < 4; x++)				
			for (int y = 0; y < 4; y++)			
				m[x][y] = mat.m[x][y];	
	}

	// ----------------------------------------------------------------------- assignment operator

	public void set (Matrix rhs) {
		if (this != rhs) {
			for (int x = 0; x < 4; x++)				
				for (int y = 0; y < 4; y++)			
					m[x][y] = rhs.m[x][y];	
		}
	}


	// ----------------------------------------------------------------------- operator*
	// multiplication of two matrices

	public Matrix multiply (Matrix mat) {
		Matrix 	product = new Matrix();
		
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 4; x++) {
				double sum = 0.0;

				for (int j = 0; j < 4; j++)
					sum += m[x][j] * mat.m[j][y];
	 
				product.m[x][y] = sum;			
			}
		
		return (product);
	}


	// ----------------------------------------------------------------------- operator/
	// division by a scalar

	public void divide (double d) {
		for (int x = 0; x < 4; x++)				
			for (int y = 0; y < 4; y++)			
				m[x][y] = m[x][y] / d;	
	}



	// ----------------------------------------------------------------------- set_identity
	// set matrix to the identity matrix

	public void set_identity() {
	    for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++) {
				if (x == y)
					m[x][y] = 1.0;
				else
					m[x][y] = 0.0;
			}
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Matrix other = (Matrix)obj;
			for (int x = 0; x < 4; x++)
				for (int y = 0; y < 4; y++) {
					if (this.m[x][y] != other.m[x][y])
						return false;
				}
			return true;
		}
	}
	
	public String toString() {
		return 
			"[" + m[0][0] + "\t" + m[0][1] + "\t" + m[0][2] + "\t" + m[0][3] + "]\n" +  
			"[" + m[1][0] + "\t" + m[1][1] + "\t" + m[1][2] + "\t" + m[1][3] + "]\n" +  
			"[" + m[2][0] + "\t" + m[2][1] + "\t" + m[2][2] + "\t" + m[2][3] + "]\n" +  
			"[" + m[3][0] + "\t" + m[3][1] + "\t" + m[3][2] + "\t" + m[3][3] + "]";  
	}

	
}
