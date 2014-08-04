package raytracer.scenes;


/**
 * This class contains the code to creating scenes from chapter 3.
 */

import raytracer.world.World;
import raytracer.geometricObjects.*;
import raytracer.tracers.*;
import raytracer.utilities.*;
import raytracer.Materials.*;
import raytracer.Lights.*;
import raytracer.Cameras.*;


public class scene {
	public static void build(World w){
		w.tracer=new PathTracer(w);
		w.background_color = RGBColor.black;
		w.vp.set_hres(800);
		w.vp.set_vres(450);
		Specular matt=new Specular();
		// Matte matt=new Matte();
		matt.setka(new RGBColor(0.0,0.0,0.0));
		matt.setkd(new RGBColor(0.5,0.5,0.5));
		matt.setks(new RGBColor(0.5,0.5,0.5));
		//matt.setshin(50);


		Emissive mat1=new Emissive();
		mat1.set_radiance(1.0);
		mat1.set_color(new RGBColor(1.0,1.0,1.0));
		Rectangle rekt = new Rectangle(new Point3D(-150.0,-100.0,50.0), new Vector3D(0, 50, 0),new Vector3D(100,000,0));
		w.add_object(rekt);
		rekt.set_material(mat1);

		w.add_light(new AreaLight(rekt,new RGBColor(RGBColor.white),1));

		//Orthographic camera=new Orthographic(w);
		// Perspective camera=new Perspective(w,new Point3D(-10,-100.0,-70.0),new Vector3D(0.0,1.0,-0.0),new Vector3D(0.0,0.0,1.0),1.5);
		Depthcam camera=new Depthcam(w,new Point3D(-10,-100.0,-70.0),new Vector3D(0.0,1.0,-0.0),new Vector3D(0.0,0.0,1.0),2.0,2.0,150.0,25.0);
		w.camera=camera;

		Sphere sphere = new Sphere();

		//Matte mat=new Matte();
		Specular mat=new Specular();
		mat.setka(new RGBColor(0.0,0.0,0.0));
		mat.setkd(new RGBColor(0.0,0.75,0.0));
		mat.setks(new RGBColor(0.5,0.5,0.5));
		mat.setshin(50);

		sphere.set_center(0, -55, -70);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(7);
		sphere.set_material(mat);
		w.add_object(sphere);
		//Matte mat2=new Matte();
		Specular mat2=new Specular();
		mat2.setka(new RGBColor(0.0,0.0,0.0));
		mat2.setkd(new RGBColor(0.0,0.0,0.75));
		mat2.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(8, -70, -70);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(10);
		sphere.set_material(mat2);
		w.add_object(sphere);

		Specular mat3=new Specular();
		mat3.setka(new RGBColor(0.0,0.0,0.0));
		mat3.setkd(new RGBColor(0.75,0.75,0.75));
		mat3.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(-10, -75, -75);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(5);
		sphere.set_material(mat3);
		w.add_object(sphere);

		Specular mat4=new Specular();
		mat4.setka(new RGBColor(0.0,0.0,0.0));
		mat4.setkd(new RGBColor(0.0,0.75,0.0));
		mat4.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(-22, -72, -76);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(4);
		sphere.set_material(mat4);
		w.add_object(sphere);

		Specular mat5=new Specular();
		mat5.setka(new RGBColor(0.0,0.0,0.0));
		mat5.setkd(new RGBColor(0.0,0.75,0.0));
		mat5.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(-3, -80, -77);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(3);
		sphere.set_material(mat5);
		w.add_object(sphere);

		Specular mat6=new Specular();
		mat6.setka(new RGBColor(0.0,0.0,0.0));
		mat6.setkd(new RGBColor(0.0,0.0,0.75));
		mat6.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(-25, -78, -74);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(6);
		sphere.set_material(mat6);
		w.add_object(sphere);

		Specular mat7=new Specular();
		mat7.setka(new RGBColor(0.0,0.0,0.0));
		mat7.setkd(new RGBColor(0.75,0.0,0.0));
		mat7.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(-14, -70, -77);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(4);
		sphere.set_material(mat7);
		w.add_object(sphere);

		Specular mat8=new Specular();
		mat8.setka(new RGBColor(0.0,0.0,0.0));
		mat8.setkd(new RGBColor(0.75,0.0,0.0));
		mat8.setks(new RGBColor(0.5,0.5,0.5));
		//mat.setshin(50);

		sphere = new Sphere();

		sphere.set_center(-28, -60, -71);
		//sphere.set_center(0, -25, 0);
		sphere.set_radius(9);
		sphere.set_material(mat8);
		w.add_object(sphere);
		//              Plane plane_ptr =new Plane(new Point3D(0,0,-80.0),new Normal(0.0,0.0,1.0));
		Tplane plane_ptr =new Tplane(new Point3D(0,0,-80.0),new Normal(0.0,0.0,1.0),new Vector3D(-1.0,0.0,0.0),7.0,0.1);
		plane_ptr.set_material(matt);
		w.add_object(plane_ptr);


	}




}
