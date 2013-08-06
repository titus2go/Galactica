package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import controller.KeyControl;
import model.Bullet;
import model.BulletType;
import model.EnemyPlane;
import controller.GameBoardController;
import model.Point;

public class GamePanel extends JPanel implements Observer
{
	private static int WIDTH = 600;
	private static int HEIGHT = 600;
	private static int STATUS_WIDTH = 200;
	private static final int EDGE = 10;
	private static final int PLANE_SIZE = 50;
	private int p_x = WIDTH / 3;
	private int p_y = HEIGHT - 100;
	private int e_x = WIDTH / 3;
	private int e_y = 100;
	private BufferedImage enemy, player;
	private Bullet my_bullet;
	private KeyControl my_key;
	private Timer my_time;
	private String my_level;
	private GameBoardController my_board;
	private BufferedImage bullet;
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));


//		my_board = new GameBoardController(WIDTH, HEIGHT, PLANE_SIZE);

	//	my_bullet = new Bullet(new Point(p_x, p_y), 10, BulletType.PLAYER_BULLET);	
		my_key = new KeyControl(my_board, this);
		addKeyListener(my_key);
		setFocusable(true);
		loadImages();  		

	}
	private final void loadImages() 
	{
		try {
			enemy = ImageIO.read(new File("src/GUI/e_plane.png"));
			player = ImageIO.read(new File("src/GUI/c_plane.png"));
			bullet = ImageIO.read(new File ("src/GUI/bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics the_graphics)
	{
		super.paintComponent(the_graphics);
		Graphics2D g2d = (Graphics2D)the_graphics;
		setBackground(Color.BLACK);
		p_x = my_board.playerLocation().getX();
		p_y = my_board.playerLocation().getY();
		EnemyPlane[] enemies = my_board.enemyPlane();

		//draw bullets
		for(int i = 0; i < my_board.firedBullets().size(); i++)
		{

			g2d.drawImage(bullet, my_board.firedBullets().get(i).getCoordinate().getX(),
					my_board.firedBullets().get(i).getCoordinate().getY(),
				10, 20, this);
		}
		//draw game status
		//draw player planes
		g2d.drawImage(player, p_x, p_y, PLANE_SIZE, PLANE_SIZE, this);

		//draw enemy planes
//		for(int i = 0; i < enemies.length; i++)
//		{
//			g2d.drawImage(enemy, my_board.enemyLocation()[i].getX(), 
//					my_board.enemyLocation()[i].getY(), PLANE_SIZE, PLANE_SIZE, this);
//		}

		g2d.drawImage(enemy, my_board.getEnemy().getCurrentLocation().getX(), 
				my_board.getEnemy().getCurrentLocation().getY(), PLANE_SIZE, PLANE_SIZE, this);



		Gamestatus(g2d);
	}
	public void set_plane_x(int x)
	{
		p_x += x;
	} 
	private Graphics2D Gamestatus(Graphics2D the_graphics)
	{
		the_graphics.setColor(Color.BLUE);
		the_graphics.drawRect(WIDTH - STATUS_WIDTH - EDGE, EDGE, STATUS_WIDTH, HEIGHT - 2*EDGE);
		the_graphics.setColor(Color.CYAN);
		the_graphics.setFont(new Font("Times New Roman", Font.BOLD, 20));
		the_graphics.drawString("Game Status", WIDTH - STATUS_WIDTH + EDGE, HEIGHT / 5);
		the_graphics.drawString("==========", WIDTH - STATUS_WIDTH + EDGE, HEIGHT / 5 + 2 * EDGE);

		the_graphics.drawString("Level: " + my_level, WIDTH - STATUS_WIDTH + EDGE, HEIGHT / 5 + 2 * EDGE);

		return the_graphics;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();

	}
	public int plane_x() {

		// TODO Auto-generated method stub
		return p_x + 2 * EDGE;
	}
	public int plane_y() {
		// TODO Auto-generated method stub
		return p_y;
	}



}