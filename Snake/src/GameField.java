import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
import java.util.Random; 

public class GameField extends JPanel implements ActionListener{ 

	private static final long serialVersionUID = 1L; 
	
	Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize (); //получаем размер экрана
	int vert = sSize.height;
	int hor  = sSize.width;
	
	private final int SIZE = vert; //размер поля
	private final int DOT_SIZE = 16; //размер точки 16x16
	private final int ALL_DOTS = (vert*vert)/DOT_SIZE; //количество точек на поле
	private Image dot; 
	private Image apple;
	private Image border;
	private int appleX; 
	private int appleY; 
	private int timeCounter = 400;
	private int dotCounter = 3;
	private int[] x = new int[ALL_DOTS]; 
	private int[] y = new int[ALL_DOTS]; 
	private int dots; 
	private Timer timer; 
	private boolean left = false; 
	private boolean right = true; 
	private boolean up = false; 
	private boolean down = false; 
	private boolean inGame = true; 

	public GameField() { 
		setBackground(Color.black); 
		loadImages();
		initGame();
		addKeyListener(new FieldKeyListener()); 
		setFocusable(true); 
	} 

	public void initGame() { 
		dots = 3; 
		for(int i=0; i<dots; i++) { 
			x[i] = 48 - i*DOT_SIZE; 
			y[i] = 48; 
		} 
		timer = new Timer(timeCounter,this); 
		timer.start(); 
		createApple(); 
	} 

	public void createApple(){ 
		appleX = new Random().nextInt(48)*DOT_SIZE; 
		appleY = new Random().nextInt(48)*DOT_SIZE;
		if(appleX <= DOT_SIZE){
			appleX+=DOT_SIZE;
		}
		if(appleX == SIZE-DOT_SIZE){
			appleX-=DOT_SIZE;
		}
		if(appleY <= DOT_SIZE){
			appleY+=DOT_SIZE;
		}
		if(appleY == SIZE-DOT_SIZE){
			appleY-=DOT_SIZE;
		}
	} 

	public void loadImages() { 
		ImageIcon iia = new ImageIcon("D:/JavaDevelopment/workspace/Snake/src/img/apple.png"); 
		apple = iia.getImage(); 
		ImageIcon iid = new ImageIcon("D:/JavaDevelopment/workspace/Snake/src/img/dot.png"); 
		dot = iid.getImage(); 
		ImageIcon iic = new ImageIcon("D:/JavaDevelopment/workspace/Snake/src/img/border.png"); 
		border = iic.getImage();
	} 


	@Override 
	protected void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		if(inGame) { 
			for(int i = 0; i < SIZE; i+=DOT_SIZE) {
				g.drawImage(border,0,i,this);
			}
			for(int i = 0; i < SIZE; i+=DOT_SIZE) {
				g.drawImage(border,i,0,this);
			}
			for(int i = 0; i < SIZE; i+=DOT_SIZE) {
				g.drawImage(border,i,SIZE-DOT_SIZE,this);
			}
			for(int i = 0; i < SIZE; i+=DOT_SIZE) {
				g.drawImage(border,SIZE-DOT_SIZE,i,this);
			}
			
			g.drawImage(apple,appleX,appleY,this);
			for(int i = 0; i < dots; i++) { 
				g.drawImage(dot,x[i],y[i],this); 
			} 
		}else{ 
			String str = "Game Over"; 
			g.setColor(Color.white); 
			g.drawString(str,SIZE/2-50,SIZE/2-50); 
		} 
	} 

	public void move() { 
		for(int i=dots; i > 0; i--) { 
			x[i] = x[i-1]; 
			y[i] = y[i-1]; 
		} 
		if(left) { 
			x[0] -=DOT_SIZE; 
		} 
		if(right) { 
			x[0] +=DOT_SIZE; 
		} 
		if(up) { 
			y[0] -=DOT_SIZE; 
		} 
		if(down) { 
			y[0] +=DOT_SIZE; 
		} 
	} 

	public void checkApple() { 
		if(x[0] == appleX && y[0] == appleY) { 
			dots++;
			dotCounter++;
			createApple(); 
		}
		if(dotCounter == 5) {
			timeCounter -= 50;
			dotCounter = 0;
		}
	} 

	public void checkCollisions(){ 
		for (int i = dots; i >0 ; i--) { 
			if(i>4 && x[0] == x[i] && y[0] == y[i]){ 
				inGame = false; 
			} 
		} 

		if(x[0]>SIZE-DOT_SIZE*2){ 
			inGame = false; 
		} 
		if(x[0]<DOT_SIZE){ 
			inGame = false; 
		} 
		if(y[0]>SIZE-DOT_SIZE*2){ 
			inGame = false; 
		} 
		if(y[0]<DOT_SIZE){ 
			inGame = false; 
		} 
	} 

	@Override 
	public void actionPerformed(ActionEvent e) { 
		if (inGame) { 
			checkApple(); 
			checkCollisions(); 
			move(); 
		} 
		repaint(); 
	} 

	class FieldKeyListener extends KeyAdapter{ 
		@Override 
		public void keyPressed(KeyEvent e) { 
			super.keyPressed(e); 
			int key = e.getKeyCode(); 
			if(key == KeyEvent.VK_LEFT && !right){ 
				left = true; 
				up = false; 
				down = false; 
			} 
			if(key == KeyEvent.VK_RIGHT && !left){ 
				right = true; 
				up = false; 
				down = false; 
			} 

			if(key == KeyEvent.VK_UP && !down){ 
				right = false; 
				up = true; 
				left = false; 
			} 
			if(key == KeyEvent.VK_DOWN && !up){ 
				right = false; 
				down = true; 
				left = false; 
			} 
		} 
	} 
} 