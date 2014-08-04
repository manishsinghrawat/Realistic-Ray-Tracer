/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Lights;

import raytracer.utilities.*;
import raytracer.geometricObjects.GeometricObject;
import raytracer.Materials.Material;

public class AreaLight extends Light{
    private GeometricObject object;
    private Material material;
    private Point3D cur_sample;
    private Normal cur_norm;
    
    public AreaLight(GeometricObject obj,RGBColor color,double k){
        this.object=obj;
        this.color=color;
        this.k=k;

    }
    public void init_sampling(){
        object.startsampling();
    }
    public boolean hasmoresamples(){
        return object.hasmoresamples();
    }
    public void nextsample(){
        cur_sample=object.sample();
        cur_norm=object.getnormal();
    }
    public double pdf(){
        return object.pdf();
    }
    public Vector3D direction(ShadeRec rec) {
        return cur_sample.subtract(rec.local_hit_point).hat();
    }
    public void randsam(){
        cur_sample=object.randsam();

        cur_norm=object.getnormal();
    }

    public RGBColor radiance(ShadeRec rec) {
        return color.multiply(k);
    }

    public boolean shadowed(Ray r,ShadeRec rec){
        double t;
        int num=rec.w.objects.size();
        double d=cur_sample.distance(r.o);

        for(int i=0;i<num;i++){
            t=rec.w.objects.get(i).shadow_hit(r);
            if(t>0 && t<d-Constants.kEpsilon)
                return true;
        }
        return false;
    }
    public double G(ShadeRec rec){
        Vector3D wi=new Vector3D(cur_sample,rec.local_hit_point);
        double len=wi.length();
        double dot=cur_norm.dot(wi)/len;
        if(dot>0.0)
            return dot;
        else
            return 0.0;

    }
}
