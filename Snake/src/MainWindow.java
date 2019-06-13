//MainWindow of the Game

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*; 


public class MainWindow extends JFrame{ 
/** 
* 
*/ 
private static final long serialVersionUID = 1L; 
private static MainWindow mainWindow; 
//private String BUTTON_NAME = "New Game"; 
JPanel panel = new JPanel(); 
//GameField gameField; 

	public MainWindow() { 
		setTitle("Snake"); 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		/*setSize(400,400); 
		setResizable(false); 
		setLocation(400,400);*/
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		/*JButton newGameButton = new JButton("New Game"); 
		JButton exitButton = new JButton("Exit"); 
		panel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
		panel.add(newGameButton); 
		panel.add(exitButton); 
		panel.setVisible(true); 
		add(panel);*/
		setCursor(getToolkit().createCustomCursor(
	            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            "null"));
		add(new GameField()); 
		setVisible(true); 
	} 

	public static void main(String[] args) { 
		mainWindow = new MainWindow(); 
	} 
}
