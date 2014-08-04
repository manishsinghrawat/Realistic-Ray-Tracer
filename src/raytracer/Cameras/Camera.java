/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raytracer.Cameras;
import javax.swing.JFrame;
import raytracer.world.*;
/**
 *
 * @author Manish
 */
abstract public class Camera {
	protected ViewPlane vp;
	protected World w;
	abstract public void render_scene(JFrame frame);
	protected JFrame frame;

}
