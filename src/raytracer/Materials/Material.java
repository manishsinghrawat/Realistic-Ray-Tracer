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
abstract public class Material {

    abstract public RGBColor Shade(ShadeRec rec);

    abstract public RGBColor pathShade(ShadeRec rec,int depth);

    abstract public RGBColor path(ShadeRec sr, int depth);
}
