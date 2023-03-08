package com.rays.tank;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class SimpleGame extends Canvas implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Thread thread;
    private boolean running;
    private Player player;

    public SimpleGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame("Simple Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        player = new Player(WIDTH / 2, HEIGHT / 2);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        player.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.fillRect(0, 0, getWidth(), getHeight());
        Color c = g.getColor();
        g.setColor(Color.RED);
        player.draw(g);
        g.setColor(c);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        SimpleGame game = new SimpleGame();
        game.start();
    }
}

class Player {

    private int x;
    private int y;
    private int speed;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 5;
    }

    public void update() {
        x += speed;
        if (x > 1000) {
            x = 0;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(x - 10, y - 10, 20, 20);
    }
}
