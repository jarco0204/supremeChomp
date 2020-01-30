 


import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;



/**
 * @author johan
 *
 */
public class GUIGAME extends JFrame {

	//Fields of the class
	private static final long serialVersionUID = 1L;
	private JPanel north, center, south;
	private boolean boardActive;
	private ChocolateBar guiBackBone;
	private JTextField turn;
	private JTextField turnComp;
	private int rows;
	private int columns;
	private JDialog finalMessage;
	private int randomNumber;
	private boolean won;
	private int randomStart;
	
	
	//Constructor of class
	/**
	 * Constructor of the class that makes calls to several methods to initiate the GUI.
	 */
	public GUIGAME() {
		boardActive = false;
		prepareGUI();
		buildUpper();
		
		buildLower();
		
		
		Random rand = new Random();
		randomStart = rand.nextInt(2); // Determines who is the first one to make a move
		
		;
		// Center of the GUI
		
	
		
		
				
		
		
		getContentPane().add(north,BorderLayout.PAGE_START);
		getContentPane().add(center, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.PAGE_END);
		setVisible(true);
	}
	
	/**
	 * Sets the most fundamental aspects of the GUI, all related to the Frame.
	 */
	private void prepareGUI() {
		
		setTitle("The CHOMP Game");
		setSize(800, 700);
		won = true;
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		north = new JPanel(new BorderLayout());
		center = new JPanel();
		south = new JPanel(new BorderLayout());
	}
	
	/**
	 * Function to correctly establish the upper part of the GUI
	 */
	private void buildUpper()
	{
		//ICON AND Title
		ImageIcon icon = new ImageIcon("C:\\Users\\johan\\OneDrive\\Escritorio\\logo.JPG");
		Image image = icon.getImage();
		Image newImg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		JLabel titleIcon = new JLabel("El Juego de CHOMPIRAS",icon, JLabel.CENTER);
		//Set the position of the text, relative to the icon:
		titleIcon.setVerticalTextPosition(JLabel.CENTER);
		titleIcon.setHorizontalTextPosition(JLabel.RIGHT);
		north.add(titleIcon,BorderLayout.NORTH);
		
		
		//Buttons
		JLabel titleSize = new JLabel("Please Select Size:");
		JPanel buttonGroup = new JPanel();
		JRadioButton small = new JRadioButton("Small");
		JRadioButton medium = new JRadioButton("Medium");
		
		JRadioButton large = new JRadioButton("Large");
		ButtonGroup bg = new ButtonGroup(); //ButtonGroup is not a component
		bg.add(small);
		bg.add(medium);
		bg.add(large);
		buttonGroup.add(titleSize);
		buttonGroup.add(small);
		buttonGroup.add(medium);
		buttonGroup.add(large);
		ActionListener selectedActionList = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				if(aButton.getText().equals("Small"))
					{rows = 2;
					columns = 10;
					buildCenter(2,10);}
				else if (aButton.getText().equals("Medium"))
					{rows = 3;
					columns = 10;
					buildCenter(3,10);}
				else if(aButton.getText().equals("Large"))
					
				{	rows = 5;
					columns = 10;
					buildCenter(5,10);}
					
				
				}};
				
			
		
		small.addActionListener(selectedActionList );
		medium.addActionListener(selectedActionList );
		large.addActionListener(selectedActionList );
		
		
		north.add(buttonGroup,BorderLayout.CENTER);
		
		JTextArea rules = new JTextArea("Steps: "+"\nI.Select Size of the board."+"\nII.Press Play to begin.");
		rules.setBackground(Color.PINK);
		rules.setEditable(false);
		north.add(rules, BorderLayout.EAST);
		
		
	
	}
	/**
	 * It build the chocolate bar, it depends on the size selected. Here I will be making usage of the guiToBackBone to link my GUI to 
	 * the object chocolatebar.
	 */
	private void buildCenter(int rows, int columns) {
		guiBackBone = new ChocolateBar(columns, rows);
		
		// If the user has selected a previous size, then it needs to be removed
		if (center.getComponentCount()!= 0)
		{
			center.removeAll();
			validate();
			repaint();
		}
		// Create a JPANEL to hold all the buttons
		JPanel chocolateBar = new JPanel(new GridLayout(rows,columns));// those variables depend on the size selected
		chocolateBar.setSize(300, 200);
		
		//ActionListener for when a JButton is pressed
		ActionListener masterMove = new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				
				
				/*if (guiBackBone.allSquaresEaten())
				{
					
					JPanel containerCompo= new JPanel();
					containerCompo.setLayout(new GridLayout(2,1));
					JButton close = new JButton("CLOSE");
					close.addActionListener(e ->
					{
						this.dispose();
					});
					close.setBackground(Color.red);
					JButton restart = new JButton("RESTART");
					restart.setBackground(Color.green);
					restart.addActionListener(e ->
					{
						finalMessage.dispose();
						dispose();
						new GUIGAME();
					
					});
					containerCompo.add(close);
					containerCompo.add(restart);
					finalMessage.add(containerCompo);
					finalMessage.setSize(300, 450);
					finalMessage.setLocationRelativeTo(this);
					finalMessage.setVisible(true);
					finalMessage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
			*/	
				JButton selectedButton = ((JButton) action.getSource());
			//	playerMove= selectedButton.getModel().isEnabled(); // player has selected a square and can procceed.
				
				String name = selectedButton.getName();
				Integer position = Integer.valueOf(name);
				if (position == 0)
				{
					won = false;
					
					
					
					
				}
				/*First row has elements from 0-9
				 * Second Row has elements from 10-19
				 * Third Row has elements from 20-29
				 * Fourth Row has element from 30-39
				 * Fifth row has elements from 40-549
				*/ 
				if (position<=9)
				{
					guiBackBone.chomp(0, position);
					
				}
				else if (position<=19)
				{
					guiBackBone.chomp(1, position-10);
					
				}
				else if (position <=29)
				{
					guiBackBone.chomp(2, position-20);
					
				}
				else if(position <=39)
				{
					guiBackBone.chomp(3, position-30);
					
				}
				else if (position <=49)
				{
					guiBackBone.chomp(4, position-40);
					
				}
				
				
				
				
			
				
				randomStart= 0; // COmputer's turn
				initiateGame(randomStart);
				 
			
				
				
			}
		};
		
		//Creation of buttons
		for (int i=0; i<(rows * columns); i++) {
			if (i== 0) 
			{
				
				ImageIcon icon = new ImageIcon("C:\\Users\\johan\\OneDrive\\Escritorio\\sign.jpg");
				Image image = icon.getImage();
				Image newImg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(newImg);
				JButton chocolateSquare = new JButton(icon);
				chocolateSquare.addActionListener(masterMove);
				chocolateSquare.setName(i+"");
				
				// Code Snippet to link the GUIGAME to the ChocolateBar class and ChocolateSquare
				guiBackBone.addButtonToSquare(chocolateSquare, chocolateSquare.getName());
				
				chocolateSquare.setBackground(Color.orange);
				chocolateSquare.setPreferredSize(new Dimension(70,75));
				chocolateBar.add(chocolateSquare);
				
			}
			else {
				
				JButton chocolateSquare = new JButton();
				chocolateSquare.setName(i+"");
				//System.out.println("The name 1 of chocolateSquare is: " + chocolateSquare.getName());
				guiBackBone.addButtonToSquare(chocolateSquare, chocolateSquare.getName());
				chocolateSquare.addActionListener(masterMove);
				
				chocolateSquare.setPreferredSize(new Dimension(70,75));
				chocolateSquare.setBackground(new Color(139,69,19));
				chocolateBar.add(chocolateSquare);}
			}
		
		boardActive = true; // Condition to prevent a major bug in my system
		
		center.add(chocolateBar);
		center.revalidate();
		center.repaint();
	}
	
	/**
	 * Build the lower part of the GUI
	 */
	private void buildLower() {
		
		turn = new JTextField("Your Turn");
		turn.setBackground(Color.pink);
		turn.setSize(100, 50);
		turn.setEditable(false);
		turn.setVisible(false); // SHow it when it is player's turn
		turnComp = new JTextField("Computer's Turn");
		turnComp.setBackground(Color.green);
		turnComp.setSize(100, 50);
		turnComp.setEditable(true);
		turnComp.setVisible(false); // Show it when it is computer's turn
		south.add(turn, BorderLayout.WEST);
		south.add(turnComp,BorderLayout.EAST);
		
		//Reset and Play Buttons
		JButton reset = new JButton("Reset Game");
		reset.addActionListener(e->{
			this.dispose();
			new GUIGAME();
		});
		JButton play = new JButton("Play");
		play.addActionListener(e->{
			if (boardActive== false) // Player has not selected the board yet 
			{
				JDialog message = new JDialog(this, "Warning");
				JLabel l= new JLabel("Please select a size to begin.");
				message.add(l);
				message.setSize(200,100);
				message.setVisible(true);
			}
			else
			{
				
				initiateGame(randomStart);
				
			}
		}
		);
		JPanel setButtons= new JPanel();
		setButtons.add(reset);
		setButtons.add(play);
		
		south.add(setButtons, BorderLayout.NORTH);
		
	}
	
// Methods that manage the Game once it has started
/**
 * This is the main method that will control the game
 * 
 * 
 */
	private void initiateGame(int random)
	{
		
		
		
		
		
		
			if (random ==1) // Player's move
			{
				// wait until the user makes a move
				turnComp.setVisible(false);
				turn.setVisible(true);
				south.revalidate();
				south.repaint();
				
				
				
			}
			else // Comp start
			{
				turn.setVisible(false);
				turnComp.setVisible(true);
				south.revalidate();
				south.repaint();
				Timer waitForMove = new Timer(1000, e-> {
					
					compMakeMove();
					
				});
				waitForMove.setRepeats(false);
				waitForMove.start();
				
				
				
			
				
				
				
			
		}
		
	}
	
	/**
	 * This method makes the computer make a move. The first strategy that I will be using to perform the moves of the computer is to
	 * mimimize the squares chomped.
	 */
	private void compMakeMove()
	{
		
		
		if (guiBackBone.allSquaresEaten() && won)
		{
			// do something for when all squares have been eaten
			finalMessage = new JDialog(this, "YOU WON 1 BILLION DOLLARS");
			finalMessage.setModal(true);
			JPanel containerCompo= new JPanel();
			containerCompo.setLayout(new GridLayout(2,1));
			JButton close = new JButton("CLOSE");
			close.addActionListener(e ->
			{
				this.dispose();
			});
			close.setBackground(Color.red);
			JButton restart = new JButton("RESTART");
			restart.setBackground(Color.green);
			restart.addActionListener(e ->
			{
				finalMessage.dispose();
				dispose();
				new GUIGAME();
			
			});
			containerCompo.add(close);
			containerCompo.add(restart);
			finalMessage.add(containerCompo);
			finalMessage.setSize(300, 450);
			finalMessage.setLocationRelativeTo(this);
			finalMessage.setVisible(true);
			finalMessage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			
				
		
		}
		else if(guiBackBone.allSquaresEaten() && !won)
		{
			JDialog myMessage = new JDialog();
			myMessage.setModal(true);
			myMessage.setTitle("YOU LOST... COMPUTER MORE INTELLIGENT" );
			myMessage.setSize(300, 300);
			myMessage.setLocationRelativeTo(this);
			JPanel containerCompo= new JPanel();
			containerCompo.setLayout(new GridLayout(2,1));
			JButton close = new JButton("CLOSE");
			close.addActionListener(e ->
			{
				myMessage.dispose();
				this.dispose();
			});
			close.setBackground(Color.red);
			JButton restart = new JButton("RESTART");
			restart.setBackground(Color.green);
			restart.addActionListener(e ->
			{
				
				myMessage.dispose();
				dispose();
				new GUIGAME();
			
			});
			containerCompo.add(close);
			containerCompo.add(restart);
			myMessage.add(containerCompo);
			myMessage.setVisible(true);	
		}
			
		
		else
		{
			//randomly try to chomp a square
			Random rand = new Random();
			
			randomNumber = rand.nextInt(columns*rows-1)+1; // It can't select the marked square
			
			while(guiBackBone.isNumberChomped(randomNumber))
			{
				randomNumber = rand.nextInt(columns*rows-1)+1;
				
				
			}
			//BY here the number randomly obtained must be in the array of chomped numbers.
			if (randomNumber<=9)
			{
				Timer timer = new Timer(1500,new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				guiBackBone.chomp(0, randomNumber);
			}
				});
				guiBackBone.getArray().get(0).get(randomNumber).getAssociatedButton().setBackground(Color.black);
				
				timer.setRepeats(false);
				timer.start();
				
				
				 
				
				
			}
			else if (randomNumber<=19)
			{
				
				Timer timer = new Timer(1500,new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				guiBackBone.chomp(1, randomNumber-10);
			}
				});
				guiBackBone.getArray().get(1).get(randomNumber-10).getAssociatedButton().setBackground(Color.black);
				
				timer.setRepeats(false);
				timer.start();
					
				   
				   
				  
				
				
				
			}
			else if (randomNumber <=29)
			{
				
				Timer timer = new Timer(1500,new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				guiBackBone.chomp(2, randomNumber-20);
			}
				});
				guiBackBone.getArray().get(2).get(randomNumber-20).getAssociatedButton().setBackground(Color.black);
				
				timer.setRepeats(false);
				timer.start();
				
				
			}
			else if(randomNumber <=39)
			{
				
				Timer timer = new Timer(1500,new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				guiBackBone.chomp(3, randomNumber-30);
			}
				});
				guiBackBone.getArray().get(3).get(randomNumber-30).getAssociatedButton().setBackground(Color.black);
				
				timer.setRepeats(false);
				timer.start();
				
			
			}
			else if (randomNumber <=49)
			{
				
				Timer timer = new Timer(1000,new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				guiBackBone.chomp(4, randomNumber-40);
			}
				});
				guiBackBone.getArray().get(4).get(randomNumber-40).getAssociatedButton().setBackground(Color.black);
				
				timer.setRepeats(false);
				timer.start();
				
				
			}
			
			randomStart=1; // Time for player to make a move
			
			initiateGame(randomStart);
			
		// Check to see if all squares but one have been chomped; thus, confirming that the user lost.
			
			
			
		}
		
			
		}
		
		
				
	
	
	/**
	 * @param args
	 */
	public static void main(String [] args) {
		
	
	GUIGAME myGame = new GUIGAME();
	
	
	
	}
	

}
