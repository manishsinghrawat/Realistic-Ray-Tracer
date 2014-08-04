package raytracer.utilities;
import raytracer.world.World;
import raytracer.Materials.Material;

public class ShadeRec {
	public boolean 			hit_an_object;		
        public Material                 material;
	public Point3D			local_hit_point;	
	public Normal			normal;			
	public World			w;			
        public Ray                      r;
        public boolean                  drawlights;
        // ------------------------------------------------------------------ constructor

	public ShadeRec(World wr) { 
		hit_an_object = false;
		local_hit_point = new Point3D();
		normal = new Normal();
		
		w = wr;
                material=null;
                r=new Ray();
                drawlights=false;
	}


	// ------------------------------------------------------------------ copy constructor

	public ShadeRec(ShadeRec sr) {
		hit_an_object = sr.hit_an_object;
		local_hit_point = new Point3D(sr.local_hit_point);
		normal = new Normal(sr.normal);
		w = sr.w;
                material=sr.material;
                r=sr.r;
                drawlights=sr.drawlights;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			ShadeRec other = (ShadeRec)obj;
			return (this.hit_an_object==other.hit_an_object && 
					this.equals(other.local_hit_point) && 
					this.normal.equals(other.normal) &&
					this.w == other.w);
		}
	}
	
	public String toString() {
		if (hit_an_object)	
			return "ShadeRec: hit " + local_hit_point + " at " + normal;
		else
			return "ShadeRec: nothing hit";
	}
}
