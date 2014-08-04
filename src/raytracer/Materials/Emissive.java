/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Materials;
import raytracer.utilities.*;
/**
 *
 * @author Manish
 */
public class Emissive extends Material{
    private double ls;
    private RGBColor color;

    public Emissive(){
        color=new RGBColor();
    }
    public void set_radiance(double ls){
        this.ls=ls;
    }
    public void set_color(RGBColor color){
        this.color=color;
    }

    public RGBColor Shade(ShadeRec rec) {
        if(rec.normal.dot(rec.r.d)<0.0)
            return color.multiply(ls);
        else
            return RGBColor.black;
    }

    public RGBColor pathShade(ShadeRec rec,int depth) {
        if(rec.normal.dot(rec.r.d)<0.0)
            if (rec.drawlights) 
                return color.multiply(ls);
        return RGBColor.black;
    }

    @Override
    public RGBColor path(ShadeRec sr, int depth) {
        return pathShade(sr,depth);
    }

}
