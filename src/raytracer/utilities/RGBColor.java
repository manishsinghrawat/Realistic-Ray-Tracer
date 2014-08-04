package raytracer.utilities;


public class RGBColor {
	public float	r, g, b;	
	public static final RGBColor black = new RGBColor(0f);
	public static final RGBColor white = new RGBColor(1f);
	public static final RGBColor red = new RGBColor(1.0f, 0.0f, 0.0f);
	public static final RGBColor yellow = new RGBColor(1f, 1f, 0f);										// yellow
	public static final RGBColor brown = new RGBColor(0.71f, 0.40f, 0.16f);								// brown
	public static final RGBColor dark_green = new RGBColor(0.0f, 0.41f, 0.41f);							// dark_green
	public static final RGBColor orange = new RGBColor(1f, 0.75f, 0f);									// orange
	public static final RGBColor green = new RGBColor(0f, 0.6f, 0.3f);									// green
	public static final RGBColor light_green = new RGBColor(0.65f, 1f, 0.30f);							// light green
	public static final RGBColor dark_yellow = new RGBColor(0.61f, 0.61f, 0f);							// dark yellow
	public static final RGBColor light_purple = new RGBColor(0.65f, 0.3f, 1f);							// light purple
	public static final RGBColor dark_purple = new RGBColor(0.5f, 0f, 1f);								// dark purple
	public static final RGBColor cyan = new RGBColor(0f, 0.5f, 0.75f);									// cyan
	public static final RGBColor blue = new RGBColor(0f, 0f, 1f);										// blue
	
	public RGBColor() {
		r = g = b = 0f;
	}
	public RGBColor(float c) {
		r = g = b = c;
	}
	public RGBColor(float newR, float newG, float newB)	{
		r = newR;
		g = newG;
		b = newB;
	}
	public RGBColor(double newR, double newG, double newB)	{
		r = (float)newR;
		g = (float)newG;
		b = (float)newB;
	}
	public RGBColor(RGBColor c){
		r = c.r;
		g = c.g;
		b = c.b;
	}
	public void set (RGBColor rhs) {
		if (this != rhs) {
			r = rhs.r; g = rhs.g; b = rhs.b;
		}
	}
	public RGBColor add (RGBColor c) {
		return new RGBColor(r + c.r, g + c.g, b + c.b);
	}

	public void plusEqual (RGBColor c) {
		r += c.r; g += c.g; b += c.b;
	}
	public RGBColor multiply (float a) {
		return new RGBColor (r * a, g * a, b * a);	
	}
	public RGBColor multiply (double a) {
		return new RGBColor (r * a, g * a, b * a);	
	}
	public void timesEqual (float a) {
		r *= a; g *= a; b *= a;
	}
	public RGBColor divide (float a) {
		return new RGBColor (r / a, g / a, b / a);
	}
	public void divideEqual (float a) {	
		r /= a; g /= a; b /= a;
	}
	public RGBColor multiply (RGBColor c)  {
		return new RGBColor (r * c.r, g * c.g, b * c.b);
	} 
	public boolean equal (Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			RGBColor c = (RGBColor)obj;
			return (r == c.r && g == c.g && b == c.b);
		}	
	}
	public float average() {
		return (0.333333333333f * (r + g + b));
	}
	public RGBColor powc(float p)  {
		return new RGBColor(Math.pow(r, p), Math.pow(g, p), Math.pow(b, p));
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			RGBColor other = (RGBColor)obj;
			return (r==other.r && g==other.g && b==other.b);
		}
	}
	
	public String toString() {
		return "RGBColor: (" + r + "," + g + "," + b + ")";
	}
	public static RGBColor multiply (float a, RGBColor c) {
		return new RGBColor (a * c.r, a * c.g, a * c.b);	
	}

    public float max() {
        if(r>g && r>b)
            return r;
        else if(g>b)
            return g;
        else
            return b;
    }


}
