/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.utilities;

/**
 *
 * @author Manish
 */
public class MatProp {
    private RGBColor k; // Material property kan be represented tuple of three numbers from 0 to 1.0

    public void setk(RGBColor k){
        this.k=k;
    }
    public RGBColor get(){
        return k;
    }
}
