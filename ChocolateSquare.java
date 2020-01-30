
import javax.swing.*;
/**
 * Most Basic class of the project. 
 * @author Johan
 * @version 1.1.1
 *
 */

public class ChocolateSquare {
	
	//Fields
	private JButton associatedButton;
	private Boolean state;
	private Boolean soap;
	
	//Constructor
	public ChocolateSquare(Boolean stateSoap){
		
		setState(false); //setting false if the chocolate has not been eaten
		soap= stateSoap;
		associatedButton = null;
	
		
		
	}

	/**
	 * @return the state
	 */
	public Boolean getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Boolean state) {
		this.state = state;
	}
	
	public void setButton(JButton button)
	{
		associatedButton = button;
	}

	/**
	 * @return the associatedButton
	 */
	public JButton getAssociatedButton() {
		return associatedButton;
	}
}
