/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.scenes;


import raytracer.world.World;
import raytracer.geometricObjects.*;
import raytracer.tracers.*;
import raytracer.utilities.*;
import raytracer.Materials.*;
import raytracer.Lights.*;
import raytracer.Cameras.*;

/**
 *
 * @author Manish
 */
public class cornell {
	public static void build(World w) {
		//w.tracer = new MultipleObjects(w);
		w.tracer=new PathTracer(w);
		w.background_color = RGBColor.black;
		//Matte mat=new Matte();
		Specular mat=new Specular();
		mat.setka(new RGBColor(0.0,0.0,0.0));
		mat.setkd(new RGBColor(0.3,0.3,0.3));
		mat.setks(new RGBColor(0.75,0.75,0.75));
		mat.setshin(50);



		//Specular matt=new Specular();
		Matte matt=new Matte();
		matt.setka(new RGBColor(0.0,0.0,0.0));
		matt.setkd(new RGBColor(0.75,0.75,0.75));
		//matt.setks(new RGBColor(0.0,0.0,0.0));
		//matt.setshin(50);


		// w.add_light(new Ambient(new RGBColor(RGBColor.white),0.5));
		//w.add_light(new PointLight(new Point3D(200,-150,500),new RGBColor(RGBColor.white),0.714));

		Sphere sphere = new Sphere();
		sphere.set_center(-40, 80, -40);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(40);
		sphere.set_material(mat);
		w.add_object(sphere);

		sphere = new Sphere();
		sphere.set_center(40, 40, -30);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(50);
		sphere.set_material(mat);
		w.add_object(sphere);
		// use constructor to set centre and radius

		sphere = new Sphere(new Point3D(0, 50, 0), 60);
		sphere.set_material(mat);
		//w.add_object(sphere);

		Emissive mat1=new Emissive();
		mat1.set_radiance(1.0);
		mat1.set_color(new RGBColor(1.0,1.0,1.0));
		Rectangle rekt = new Rectangle(new Point3D(-50.0,50.0,200.0), new Vector3D(0, 50, 0),new Vector3D(100,000,0));
		w.add_object(rekt);
		rekt.set_material(mat1);

		w.add_light(new AreaLight(rekt,new RGBColor(RGBColor.white),1));

		//Orthographic camera=new Orthographic(w);
		// Perspective camera=new Perspective(w,new Point3D(0,-100.0,-00.0),new Vector3D(0.0,1.0,0.0),new Vector3D(0.0,0.0,1.0),0.9);
		Depthcam camera=new Depthcam(w,new Point3D(0,-100.0,-00.0),new Vector3D(0.0,1.0,0.0),new Vector3D(0.0,0.0,1.0),0.9,3.0,150.0,170.0);
		w.camera=camera;


		Specular matt6=new Specular();
		//Matte matt=new Matte();
		matt6.setka(new RGBColor(0.0,0.0,0.0));
		matt6.setkd(new RGBColor(0.3,0.3,0.3));
		matt6.setks(new RGBColor(0.5,0.5,0.5));
		matt6.setshin(50);

		Plane plane_ptr =new Plane(new Point3D(0,0,-80.0),new Normal(0.0,0.0,1.0));
		plane_ptr.set_material(matt);
		w.add_object(plane_ptr);

		//Specular matt1=new Specular();
		Matte matt1=new Matte();
		matt1.setka(new RGBColor(0.0,0.0,0.0));
		matt1.setkd(new RGBColor(0.25,0.25,0.75));
		//matt1.setks(new RGBColor(0.0,0.0,0.0));
		//matt1.setshin(50);

		plane_ptr =new Plane(new Point3D(150.0,0,0.0),new Normal(-1.0,0.0,0.0));
		plane_ptr.set_material(matt1);
		w.add_object(plane_ptr);

		//Specular matt2=new Specular();
		Matte matt2=new Matte();
		matt2.setka(new RGBColor(0.0,0.0,0.0));
		matt2.setkd(new RGBColor(0.75,0.25,0.25));
		//matt2.setks(new RGBColor(0.0,0.0,0.0));
		//matt2.setshin(50);

		plane_ptr =new Plane(new Point3D(-150.0,0,0.0),new Normal(1.0,0.0,0.0));
		plane_ptr.set_material(matt2);
		w.add_object(plane_ptr);

		Emissive matt3=new Emissive();
		matt3.set_color(RGBColor.white);
		matt3.set_radiance(1);

		plane_ptr =new Plane(new Point3D(0.0,0,210.0),new Normal(0.0,0.0,-1.0));
		plane_ptr.set_material(matt);
		w.add_object(plane_ptr);

		//Specular matt4=new Specular();
		Matte matt4=new Matte();
		matt4.setka(new RGBColor(0.0,0.0,0.0));
		matt4.setkd(new RGBColor(0.75,0.75,0.75));
		//matt4.setks(new RGBColor(0.0,0.0,0.0));
		//matt4.setshin(50);

		plane_ptr =new Plane(new Point3D(0.0,150.0,00.0),new Normal(0.0,-1.0,0.0));
		plane_ptr.set_material(matt4);
		w.add_object(plane_ptr);

		plane_ptr =new Plane(new Point3D(0.0,-110.0,00.0),new Normal(0.0,1.0,0.0));
		plane_ptr.set_material(matt);
		w.add_object(plane_ptr);
	}

}
