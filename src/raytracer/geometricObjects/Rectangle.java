/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.geometricObjects;

import java.util.Random;
import raytracer.utilities.*;
/**
 *
 * @author Manish
 */


public class Rectangle extends GeometricObject{
    private static final double kEpsilon=0.001;

    private Point3D p;
    private Vector3D a;
    private Vector3D b;
    private double asqr;	// square of the length of side a
    private double bsqr;	// square of the length of side b
    private Normal normal;	
    private double area;			// for rectangular lights
    private double inv_area;		// for rectangular

    private double samx;
    private double samy;
    private double distx;
    private double disty;
    public Rectangle(){
        super();
        p=new Point3D(-1,0,-1);
        a=new Vector3D(0,0,2);
        b=new Vector3D(2,0,0);
        asqr=4.0;
        bsqr=4.0;
        normal=new Normal(0,1,0);
        area=4.0;
        inv_area=0.25;
        samx=0;
        samy=0;
        distx=0.1;
        disty=0.1;
    }
    public Rectangle(Rectangle r){
        super(r);
        p=new Point3D(r.p);
        a=new Vector3D(r.a);
        b=new Vector3D(r.b);
        asqr=r.asqr;
        bsqr=r.bsqr;
        normal=new Normal(r.normal);
        area=r.area;
        inv_area=r.inv_area;
        samx=0;
        samy=0;
        distx=0.1;
        disty=0.1;
    }
    public Rectangle(Point3D p,Vector3D a,Vector3D b){
        super();
        this.p=new Point3D(p);
        this.a=new Vector3D(a);
        this.b=new Vector3D(b);
        asqr=a.len_squared();
        bsqr=b.len_squared();
        normal=new Normal(a.cross(b));
        normal.normalize();
        area=a.cross(b).length();
        inv_area=1/area;
        samx=0;
        samy=0;
        distx=0.1;
        disty=0.1;
    }
    public GeometricObject clone() {
        return new Rectangle(this);
    }


    public double hit(Ray ray, ShadeRec sr) {

	double t = p.subtract(ray.o).dot(normal) / ray.d.dot(normal);

	if (t <= kEpsilon)
		return -1;

	Point3D p1 = ray.o.add(ray.d.multiply(t));
	Vector3D d = p1.subtract(p);

	double ddota = d.dot(a);

	if (ddota < 0.0 || ddota > asqr)
		return -1;

	double ddotb = d.dot(b);

	if (ddotb < 0.0 || ddotb > bsqr)
		return -1;

	sr.normal 			= normal;
	sr.local_hit_point 	= p1;
        sr.material=material;
	return t;


    }


    public double shadow_hit(Ray ray) {double t = p.subtract(ray.o).dot(normal) / ray.d.dot(normal);

	if (t <= kEpsilon)
		return -1;

	Point3D p1 = ray.o.add(ray.d.multiply(t));
	Vector3D d = p1.subtract(p);

	double ddota = d.dot(a);

	if (ddota < 0.0 || ddota > asqr)
		return -1;

	double ddotb = d.dot(b);

	if (ddotb < 0.0 || ddotb > bsqr)
		return -1;

	return t;
    }
    public void startsampling(){
        samx=0;
        samy=0;
    }
    public Point3D sample(){
        if (samy>1.0)
            return null;
        if (samx>1.0){
            samy+=disty;
            samx=0;
        }else
            samx+=distx;
        return p.add(a.multiply(samx).add(b.multiply(samy)));
    }
    public Point3D randsam(){
        Random rand=new Random();
        return p.add(a.multiply(rand.nextDouble()).add(b.multiply(rand.nextDouble())));
    }
    public boolean hasmoresamples(){
        if (samy>1.0) return false; else return true;
    }

    public Normal getnormal() {
        return normal;
    }

    @Override
    public double pdf() {
        return distx*disty;
    }
}
