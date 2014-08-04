/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Lights;

import raytracer.utilities.*;
/**
 *
 * @author Manish
 */
abstract public class Light {
	protected double k;
	protected RGBColor color;
	abstract public Vector3D direction(ShadeRec rec);
	abstract public RGBColor radiance(ShadeRec rec);
	abstract public boolean shadowed(Ray r,ShadeRec rec);
}
