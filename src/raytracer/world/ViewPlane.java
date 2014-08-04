package raytracer.world;

public class ViewPlane {
	public int hres;
	public int vres;
	public float s;					

	public float gamma;
	public float inv_gamma;

	public int samples;
	public int iterations;
	public boolean show_out_of_gamut;			


	public ViewPlane() {							
		hres = 400;
		vres = 400;
		s = 1f;
		show_out_of_gamut = false;
		iterations=100000;
		samples=1;

		gamma = 1.5f;
		inv_gamma =1.0f/gamma;
	}


	public ViewPlane(ViewPlane vp){
		hres = vp.hres;
		vres = vp.vres;
		s = vp.s;
		show_out_of_gamut = vp.show_out_of_gamut;
		this.samples=vp.samples;
		this.iterations=vp.iterations;
		this.gamma=vp.gamma;
		this.inv_gamma=vp.inv_gamma;
	}


	public void set (ViewPlane rhs) {
		if (this != rhs) {			
			hres = rhs.hres;
			vres = rhs.vres;
			s = rhs.s;
			show_out_of_gamut = rhs.show_out_of_gamut;
			samples=rhs.samples;
			iterations=rhs.iterations;

			this.gamma=rhs.gamma;
			this.inv_gamma=rhs.inv_gamma;
		}
	}


	public void set_hres(int h_res) {
		hres = h_res;
	}

	public void set_vres(int v_res) {
		vres = v_res;
	}


	public void set_pixel_size(float size) {
		s = size;
	}



	public void set_gamut_display(boolean show) {
		show_out_of_gamut = show;
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			ViewPlane vp = (ViewPlane)obj;
			return (hres == vp.hres && 
					vres == vp.vres &&
					s == vp.s &&
					show_out_of_gamut == vp.show_out_of_gamut);
		}
	}

	public String toString() {
		return "View Plane: " + hres + " x " + vres + ", pixel size" + s + ")";
	}
}
