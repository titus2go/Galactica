package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GameWindow extends JFrame
{
	public GameWindow()
	{
		super();
		GamePanel panel = new GamePanel();
		this.add(panel);
		setJMenuBar(createMenu());
	}
	public static void main(String ...strings)
	{
		GameWindow window = new GameWindow();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
	private JMenuBar createMenu()
	{
		JMenuBar main_menu = new JMenuBar();
		JMenu menu = new JMenu("File");
		main_menu.add(menu);
		return main_menu;
	}

}