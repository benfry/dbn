import java.awt.*;
import java.applet.Applet;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;


public class DbnApplication extends DbnApplet
{
    // when using dbn as an application instead of an applet
    // (this will always be done with jdk 1.1 or higher)
    static public void main(String args[]) {
	//new MemoryReporter();

	Frame frame = new Frame("DBN");
	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		//e.getWindow().dispose();
		System.exit(0);
	    }
	});
	Dimension screen = 
	    Toolkit.getDefaultToolkit().getScreenSize();
	frame.reshape(screen.width+10, 10, 100, 200);
	frame.show();
	DbnApplet app = new DbnApplet();
	app.applet = app;
	app.properties = new Properties();
	try {
	    app.properties.load(new FileInputStream("dbn.properties"));
	} catch (Exception e1) {
	    try {
		app.properties.load(new FileInputStream("lib/dbn.properties"));
	    } catch (Exception e) {
		System.err.println("Error reading dbn.properties");
		e.printStackTrace();
		System.exit(1);
	    }
	}
	int width = Integer.parseInt(app.properties.getProperty("width"));
	int height = Integer.parseInt(app.properties.getProperty("height"));
	frame.add(app);
	app.init();
	Insets insets = frame.getInsets();
	frame.reshape(50, 50, width + insets.left + insets.right, 
		      height + insets.top + insets.bottom);
	frame.pack();
	//frame.doLayout();
    }
}


/*
class MemoryReporter implements Runnable {
    Thread thread;

    public MemoryReporter() {
	thread = new Thread(this);
	thread.start();
    }

    public void run() {
	while (Thread.currentThread() == thread) {
	    try {
		thread.sleep(1000);
	    } catch (InterruptedException e) {
	    }
	    Runtime r = Runtime.getRuntime();
	    r.gc();
	    long mem = r.freeMemory();
	    long tmem = r.totalMemory();
	    //mem /= 1024;
	    System.err.println(mem + " " + tmem);
	}
    }
}
*/
