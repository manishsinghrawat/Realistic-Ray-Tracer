package RayTracerGUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import raytracer.world.World;

public class RayTracer extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Image screen;
	private static JFrame frame;
	private int width, height;
	private boolean finished = false;
        protected static JPanel panel;
	private JTextArea log;
	private JFileChooser fc;
	private JMenu menu;
	private Graphics g;
	
	private World world;
	private Thread raytracer;

	public RayTracer () {
		world = new World();
		int[] dimensions = world.build();
		width = dimensions[0];
		height = dimensions[1];

		frame = new JFrame("Ray Tracer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Container pane = frame.getContentPane();
		pane.add(BorderLayout.CENTER, this);
		frame.setSize(width,height);

	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (dim.width-frame.getSize().width)/2,
	    	y = (dim.height-frame.getSize().height)/2;
	   frame.setLocation(x, y);

		screen = frame.createImage(width, height);
		frame.setVisible(true);  
		raytracer = new Thread(this);
		raytracer.start();
	}

	public void paintComponent(Graphics g) {
		if (finished)
			g.drawImage(screen, 0, 0, this);
	}

	public void run() {
		g = getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		world.setGraphics(g);

		long time = System.currentTimeMillis();
		world.camera.render_scene(frame);
		g.drawImage(screen, 0, 0, this);
                
                //menu.setEnabled(true);
		time = System.currentTimeMillis() - time;
		frame.setTitle("Rendered in "+(time/60000)+":"+((time%60000)*0.001));
                System.out.println("Rendered in "+(time/60000)+":"+((time%60000)*0.001));
		finished = true;
	}


	public static void main (String[] args)
	{
		panel=new RayTracer();
	}

}

