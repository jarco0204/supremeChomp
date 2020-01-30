/**
 * 
 */

import java.util.HashMap;
import javax.swing.JButton;

import java.awt.Color;
import java.util.ArrayList;
/**
 * @author johan
 *
 */
public class ChocolateBar {

	/**
	 * Main method to test how my class works and to detect any bug
	 * @param args
	 */
	public static void main(String[] args) {
		ChocolateBar laBarraMagica = new ChocolateBar(10,3); // columns and rows
		
		System.out.println();
		laBarraMagica.chomp(1, 8); // Remember that you have row 0 and column 9
		
		
		/*System.out.println(laBarraMagica);
		for(int i=0; i<4; i++)
		{
			
			System.out.println(laBarraMagica.mainArray.get(i));
		}
		System.out.println(laBarraMagica.soapBitten());
		for(int i=0; i<4; i++)
		{
			System.out.println();
			for (int j=0; j<4; j++) {
			System.out.print(laBarraMagica.mainArray.get(i).get(j).getState());}
		}
		
		System.out.println();
		
		laBarraMagica.chomp(0, 0);
		for(int i=0; i<4; i++)
		{
			System.out.println();
			for (int j=0; j<4; j++) {
			System.out.print(laBarraMagica.mainArray.get(i).get(j).getState());}
		}
		*/
		
	}
	
	//Fields
	
	private HashMap<Integer,ArrayList<ChocolateSquare>> mainArray; // The Integer represents the row. Small = 3x10 medium 4x12 large 6x14
	private int columns;
	private int rows;
	private boolean arrayOfSquares[][]; // To Know what squares have been chomped
	
	//Constructor
	/**
	 * Depending on the size selected, the bar will be created.
	 * @param columns
	 * @param rows
	 */
	public ChocolateBar(int columns , int rows)
	
	{
		
		mainArray = new HashMap <Integer,ArrayList<ChocolateSquare>>();
		arrayOfSquares = new boolean[rows][columns]; // All values set to false by default
		arrayOfSquares[0][0]= true; // Giving me too much trouble
		this.columns = columns;
		this.rows= rows;
		mapValues(columns, rows); // rows = y and columns = x
	}
	
	
	/**
	 * THis method links a String (the name of a JButton ) to one of the chocolateSquare
	 */
	public HashMap <Integer,ArrayList<ChocolateSquare>> getArray()
	{
		return mainArray;
	}
	public void addButtonToSquare(JButton myButton, String nameOfButton)
	{
		try {
			int position = Integer.valueOf(nameOfButton); // Integer to locate the ChocolateSquare in HashMap
			if (position<=9)
			{
				mainArray.get(0).get(position).setButton(myButton);
			}
			else if (position<=19)
			{
				mainArray.get(1).get(position-10).setButton(myButton);
			}
			else if (position <=29)
			{
				mainArray.get(2).get(position-20).setButton(myButton);
			}
			else if(position <=39)
			{
				mainArray.get(3).get(position-30).setButton(myButton);
			}
			else if (position <=49)
			{
				mainArray.get(4).get(position-40).setButton(myButton);
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("The name of button is :" + nameOfButton + "Not a number");
		}
		
		
		/*
		 * First row has elements from 0-9
		 * Second Row has elements from 10-19
		 * Third Row has elements from 20-29
		 * Fourth Row has element from 30-39
		 * Fifth row has elements from 40-549
		 */
		
		
		
	}
	/**
	 * This method checks to see if the soap was bitten or not, if yes then the game is over.
	 * @return condition to finish the game
	 */
	public boolean soapBitten()
	{
	
		return  mainArray.get(0).get(0).getState();
		
	}
	
	/**
	 * For this method, instead of deleting items from the arrayList, it will put a void where the item was. This way I still I'm able to have the 2d representation of the array.
	 * @param rowBit
	 * @param columnBit
	 * @return
	 */
	
	private void mapValues(int col, int ro) {
		for (int i=0; i<ro; i++)
		{
			if (i == 0) {
				
				
				mainArray.put(i, createArrayforColums( col, true)); // I think it changes itself to Integer by itself
				
			}
			else
			{
				mainArray.put(i, createArrayforColums( col, false));
			}
			
				
			
		}
	}
	/**
	 * This is a support method for the mapValues one that creates several ArrayList that contain the chocolate squares.
	 * @param x
	 * @param addSoap
	 * @return Array to add to the Hashmap
	 */
	private ArrayList<ChocolateSquare> createArrayforColums(int x, boolean addSoap )
	{
		
		ArrayList<ChocolateSquare> papiArray = new ArrayList<ChocolateSquare>();
		if (addSoap == true) {
			
			for (int i =0 ; i<x; i++)
			{
				// First Square is the soap 
				if (i ==0) {
					
					papiArray.add(new ChocolateSquare(true));}
				else {
				// All others square are chocolate
				papiArray.add(new ChocolateSquare(false));
				}			
				
			}
		
			return papiArray;
		}
		else 
		{
		
			for (int i =0; i<x; i++)
			{
				papiArray.add(new ChocolateSquare(false));
			}
			return papiArray;
		
		}
	
		
	}
	/**
	 * Method to mimic the behaviour of eating the chocolates squares
	 * @param rowBit
	 * @param columnBit
	 * @return boolean to indicate next player's move
	 */
	
	public boolean chomp(int rowBit, int columnBit) {
		
		
		for (int i = rowBit; i< rows; i++)
		{
			for (int j= columnBit; j < columns;j++ )
			{
				
				mainArray.get(i).get(j).setState(true); // Sets the state of a chocolate square to true.
				
				arrayOfSquares[i][j]= true; // marks the square that has been eaten
				
				if (mainArray.get(i).get(j).getAssociatedButton() != null) {
					mainArray.get(i).get(j).getAssociatedButton().setBackground(Color.GREEN);
					 mainArray.get(i).get(j).getAssociatedButton().setVisible(false); // Sets the button pressed to not visible.
						
				}
				
				
			}
		}
		return true;
	}
	
	/**
	 * Method to determine if the number obtained from random() in GUIGAME is valid.
	 * 
	 */
	public boolean isNumberChomped(int numberObtained)
	{
		if (numberObtained<=9)
			{
			return mainArray.get(0).get(numberObtained).getState(); // Return true if the chocolate square has been eaten
					}
		else if(numberObtained<=19)
		{
			return mainArray.get(1).get(numberObtained-10).getState();
		}
		else if(numberObtained<=29)
		{
			return mainArray.get(2).get(numberObtained-20).getState();
		}
		else if(numberObtained<=39)
		{
			return mainArray.get(3).get(numberObtained-30).getState();
		}
		else 
		{
			return mainArray.get(4).get(numberObtained-40).getState();
		}
		
		
	}
	
	/*
	 * Check to see if the square (0,0) has to be eaten by the computer
	 */
	public boolean allSquaresEaten()
	{
		
		ArrayList<Boolean> values = getStateBar();
		// bar still has squares
		if (values.contains(false))
		{
			return false;
		}
		else
		{
			// no more squares
			return true;
		}
	}
		
		
	/*	for (int i =0; i<rows; i++)
		{
			for (int j=0;i<columns; i++)
			{
				
					
				if(arrayOfSquares[i][j]==false)
				{
					return false;
				}
				else {
					; // do nothing
				}
				
			}
		}
		System.out.println("Here");
		return true;
		// Now that all squares are here; time to check if all but the first one are true
	*/
		
		
		
		
	
	
	 //This method helps by returning an arrayList of boolean values that tell the GUIGame to hide or show the chocolate squares.
	
	
	public ArrayList<Boolean> getStateBar()
	{
		ArrayList<Boolean> arrayToReturn = new ArrayList<Boolean>();
		for (int i = 0; i< rows; i++)
		{
			for (int j= 0; j < columns;j++ )
			{
				if (i== 0 && j==0) {
					;
				}
				else
				{
				arrayToReturn.add(mainArray.get(i).get(j).getState());
				}
			}
		}
		return arrayToReturn;
	}
	 
	
}
