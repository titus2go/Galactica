package Control;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import GUI.GamePanel;
import Model.Bullet;
import Model.GameBoardController;


public class KeyControl extends KeyAdapter implements ActionListener
{

	private int my_x;
	private int my_y;
	private Timer my_timer;
	private GamePanel my_panel;
	private GameBoardController my_board;
	private Bullet my_bullet;

	public KeyControl(GameBoardController the_board, GamePanel panel)
	{
		my_board = the_board;
		//my_bullet = new Bullet(coordinate, damage, type)
		my_timer = new Timer(10, this);
		my_panel = panel;
		my_timer.start();
	}
	@Override
	  public void keyPressed(final KeyEvent the_event)
	{
		System.out.println("pressed");
		
		if(the_event.getKeyCode() == KeyEvent.VK_SPACE)
		{
						
			my_board.shoot();
		}
		else if(the_event.getKeyCode() == KeyEvent.VK_LEFT)
		{
			my_board.playerMoveLeft();
			System.out.println("left");
		}
		else if(the_event.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			my_board.playerMoveRight();
			
		}
		 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		my_board.moveBullet();
		my_board.moveEnemyPlanes();
		//my_board.enemyLocation();
		my_panel.repaint();
		
	}

	
}
