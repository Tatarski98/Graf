package render;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable {

    private Thread thread;
    private JFrame frame;
    private static String title = "Projekt Grafika";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static boolean running = false;


    public Display() {
        this.frame = new JFrame();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);
    }


    public static void main(String[] args) {

        Display display = new Display();
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setResizable(false);
        display.frame.setVisible(true);
        display.start();
    }


    public synchronized void start() {

        running = true;
        this.thread = new Thread(this, "render.Display");
        this.thread.start();

    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //start our thread from start()
    @Override
    public void run() {
        long lastTime = System.nanoTime(); //what time it was during the previous iteration of while loop
        long timer = System.currentTimeMillis(); //
        final double ns = 100000000.0/60; // number of nanoseconds between each update if we updating at 60 updates per second
        double delta = 0; //that s represent our percent  like progres of getting towards to the next update
        int frames = 0;


        while (running) {
           long now = System.nanoTime(); //what s the current time now
            delta += (now - lastTime)/ns;
            lastTime = now;
            while(delta >=1 ){
                update();
                delta--;
            }
            render();
            frames++;
          //  update();
            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
                this.frame.setTitle(title+" | "+ frames +"fps");
                frames=0;
            }
        }
        stop();
    }


    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.red);
        g.fillRect(50,50,200,100);

        g.dispose();
        bs.show();
    }

    private void update() {
    }

}


