package application;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class maincontroller {
	@FXML
	private TextField msg1;
	@FXML
	private TextField output;
	@FXML
	private Button encryptbtn;
	@FXML
	private Button decryptbtn;
	@FXML
	private TextField cle;
	@FXML
	private TextField msg11;
	@FXML
	private TextField output1;
	@FXML
	private Button encryptbtn1;
	@FXML
	private Button decryptbtn1;
	@FXML
	private TextField cle1;
///////////////////////////////////////////////////////////////
///////////////////// encryption substitution////////////////////////////////
///////////////////////////////////////////////////////////////

	public void crypter(ActionEvent Event) {
		int shift=Integer.parseInt(cle.getText());
		String msg = msg1.getText();
		if (shift > 96) {
			shift = shift % 96;
		} else if (shift < 0) {
			shift = (shift % 96) + 96;
		}

		String cyphertext = "";
		int length = msg.length();

		for (int i = 0; i < length; i++) {
			char acter = msg.charAt(i);
			int ascii = (int) acter;
			char c = (char) (ascii + shift);
			if ((int) (c) > 128) {
				cyphertext += (char) (ascii - 96 + shift);
			} else if ((int) (c) < 32) {
				cyphertext += (char) (ascii + 96 + shift);
			} else {
				cyphertext += (char) (ascii + shift);
			}

		}
		output.setText(cyphertext);
		}
///////////////////////////////////////////////////////////////
///////////////////// décryption substitution////////////////////////////////
///////////////////////////////////////////////////////////////

	public void decrypter(ActionEvent Event) {
		int shift=Integer.parseInt(cle.getText());
		String msg = msg1.getText();
		if (shift > 96) {
			shift = shift % 96;
		} else if (shift < 0) {
			shift = (shift % 96) + 96;
		}

		String cyphertext = "";
		int length = msg.length();

		for (int i = 0; i < length; i++) {
			char acter = msg.charAt(i);
			int ascii = (int) acter;
			char c = (char) (ascii - shift);
			if ((int) (c) < 32) {
				cyphertext += (char) (ascii + 96 - shift);
			} else if ((int) (c) > 128) {
				cyphertext += (char) (ascii + 96 - shift);
			} else {
				cyphertext += (char) (ascii - shift);
			}

		}
		output.setText(cyphertext);
		}
	public void sortbycolumn(char arr[][], int col) {
		// using build in sort function arrays.sort
		Arrays.sort(arr, new Comparator<char[]>() {

			@Override
			public int compare(final char[] entry1, final char[] entry2) {
				// TODO Auto-generated method stub
				if ((entry1[col]) > (entry2[col]))
					return 1;
				else
					return -1;

			}

		});
	}
///////////////////////////////////////////////////////////////
///////////////////// encryption transposition////////////////////////////////
///////////////////////////////////////////////////////////////

	public  void crypttr (ActionEvent Event){
		String shift=cle1.getText();
		String msg = msg11.getText();
	// --------------------------------
	int nbcol = 0;
	int u = shift.length();
	// counting the number of lines of the table
	nbcol=msg.length()/u;
	if(msg.length()%u>0)nbcol++;
	// creating and filling the table with the key
	char t[][] = new char[u][nbcol + 1];for(
	int k = 0;k<u;k++)
	{
		t[k][0] = shift.charAt(k);
	}

	// filling the table with the message
	int y = 0;for(
	int j = 1;j<nbcol+1;j++)
	{
		for (int i = 0; i < u; i++) {
			if (y < msg.length()) {
				t[i][j] = msg.charAt(y);
			} else {
				t[i][j] = (char) 27;
			}
			y++;
		}
	}
	// display the sorted array
	int col = 1;

	 sortbycolumn(t, col - 1);
		// extracting the crypted message
		String msgcry="";
		System.out.println("the crypted message");
		for (int i = 0; i < u; i++) {
			for (int j = 1; j < nbcol + 1; j++) {
				msgcry+=t[i][j];
			}
		}
		output1.setText(msgcry);
	}

	///////////////////////////////////////////////////////////////
	///////////////////// décryption transposition////////////////////////////////
	///////////////////////////////////////////////////////////////

	public void decrypttr (ActionEvent Event){
		String shift=cle1.getText();
		String msg = msg11.getText();
		// sort the key and make a table with sortedkey as indexes
		String sortedkey;
		char tempArray[] = shift.toCharArray();
		// sort tempArray
		Arrays.sort(tempArray);
		// return new sorted string
		sortedkey = new String(tempArray);
		// --------------------------------
		int nbcol = 0;
		int u = sortedkey.length();
		// counting the number of lines of the table
		nbcol = msg.length() / u;
		if (msg.length() % u > 0)
			nbcol++;
		// creating and filling the table with the key
		char t[][] = new char[u][nbcol + 1];
		for (int k = 0; k < u; k++) {
			t[k][0] = sortedkey.charAt(k);
		}
		// filling the table with the message
		int y = 0;
		for (int i = 0; i < u; i++) {
			for (int j = 1; j < nbcol + 1; j++) {
				if (y < msg.length()) {
					t[i][j] = msg.charAt(y);
				} else {
					t[i][j] = (char) 27;
				}
				y++;
			}
		}
		String msgdec = "";
		for (int i = 1; i < nbcol + 1; i++) {
			for (int k = 0; k < u; k++) {
				for (int j = 0; j < u; j++) {

					if ((int) (shift.charAt(k)) == (int) (t[j][0])) {

						msgdec += t[j][i];
					}
				}
			}
		}
		output1.setText(msgdec);


	}
	@FXML private javafx.scene.control.Button closeButton;

	@FXML
	private void closeButtonAction(){
	    // get a handle to the stage
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	

}
