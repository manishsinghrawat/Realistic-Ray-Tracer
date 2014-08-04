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
public class Ambient extends Light{
    

    public Ambient(RGBColor color,double k){
        this.color=color;
        this.k=k;
    }

    public Vector3D direction(ShadeRec rec){
        return new Vector3D();

   }

   public RGBColor radiance(ShadeRec rec){
        return color.multiply(k);
   }

    public boolean shadowed(Ray r,ShadeRec rec){
        return false;
    }
}
