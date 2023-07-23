package tcptexttranslatorGUIbasedClient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * This class represents the front-end of client side
 * 
 * @author Mirza Sahid Afridi
 */

public class ClientFrame extends JFrame implements ActionListener{

	// Private frame components
	private JLabel title, lblAns;
	private JButton bm, arb, krn;
	private JTextField input;
	
	// Private attributes for frame size
	private int width = 700;
	private int height = 300;
	
	// private attribute for obtain input
	private String textinput = "";
	private String language = "";
	
	// private attribute for checking button is pressed or not
	private boolean pressed = false;
	
	public ClientFrame()
	{
		// Default frame setting
		getContentPane().setLayout(new BorderLayout());
		this.setTitle("TCP Translator: ");
		this.setSize(new Dimension(width, height));
		
		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//center window
		this.setLocationRelativeTo(null);
		
		
		// display label to display for enter text for translate
		this.title = new JLabel("Enter text for translation: ");
		this.lblAns = new JLabel("");
		
		// settings for text field input
		this.input = new JTextField (20);
		
		// button for target language selection
		this.bm = new JButton("Malay");
		this.arb = new JButton("Arabic");
		this.krn = new JButton("Korean");
		
		// add action listener for these buttons
		bm.addActionListener(this);
		arb.addActionListener(this);
		krn.addActionListener(this);
		
		loadForm();
	}
	
	
	/** private top panel of frame
	 * @param font
	 * @return Swing components organized in panel
	 */
	private JPanel topPanel(Font font)
	{
		JPanel panel = new JPanel();
		title.setFont(new Font("Inter", Font.PLAIN, 20));
		input.setFont(font);
		input.setOpaque(true);
		title.setOpaque(true);
		panel.add(title);
		panel.add(input);
		return panel;
	}
	
	/** private center panel of frame
	 * @param font
	 * @return Swing components organized in panel
	 */
	private JPanel centerPanel(Font font)
	{
		JPanel panel = new JPanel();
		bm.setFont(font);
		arb.setFont(font);
		krn.setFont(font);
		bm.setOpaque(true);
		arb.setOpaque(true);
		krn.setOpaque(true);
		panel.add(bm);
		panel.add(arb);
		panel.add(krn);
		return panel;
	}
	
	
	/* private bottom panel of frame
	 * @param font
	 * @return Swing components organized in panel
	 */
	private JPanel bottomPanel(Font font)
	{
		JPanel panel = new JPanel();
		lblAns.setOpaque(true);
		lblAns.setFont(font);
		panel.add(lblAns);
		return panel;
	}
	

	/**
	 * This method define a font to a generic style.
	 * 
	 * @return font object
	 */
	private Font getFontStyle() 
	{
		
		Font font = new Font ("Inter", Font.PLAIN, 30);
		
		return font;
		
	}

	// arrange the swings components to the frame
	private void loadForm()
	{
		Font font = this.getFontStyle();
		
		JPanel top = this.topPanel(font);
		getContentPane().add(top, BorderLayout.NORTH);
		
		JPanel center = this.centerPanel(font);
		getContentPane().add(center, BorderLayout.CENTER);
		
		JPanel btm = this.bottomPanel(font);
		getContentPane().add(btm, BorderLayout.SOUTH);
	}
	
	
	public void setAnsLbl(String translated)
	{
		lblAns.setText(translated);
	}
	
	public void updateConnectionStatus (boolean connStatus) 
	{
		
		// Default status. Assuming for the worst case scenario.
		this.setTitle("TCP Translator: No connection to server.");
		
		// Validate status of connection
		if (connStatus)
			this.setTitle("TCP Translator: Connection has established.");
	}
	
	/**
	 * getter for language input
	 * 
	 * @return String
	 * 
	 */
	public String getLanguage() 
	{
		return this.language;
	}
	
	/**
	 * getter for text input to be translated
	 * 
	 * @return String
	 * 
	 */
	public String getText() 
	{
		return this.textinput;
	}
	
	/*
	 * set the font to arabic font
	 * 
	 */
	private void setFonttoArabic()
	{
		lblAns.setFont(new Font("Arabic",Font.PLAIN,30));
	}
	
	/*
	 * set the font to korean font
	 * 
	 */
	private void setFonttoKorean()
	{
		lblAns.setFont(new Font("Malgun Gothic", Font.PLAIN, 30));
	}
	
	/*
	 * check whether the button is pressed or not
	 * 
	 * @return boolean
	 */
	public boolean ispressed()
	{
		return pressed;
	}
	
	/*
	 * check whether the button of updation for text
	 * 
	 *
	 */
	public void updatebtn(boolean b)
	{
		pressed = b;
	}
	
	/**
	 * implemented method for action listener of button
	 * 
	 * @param e: Action event of button
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getSource()== bm)
		{
			updatebtn(true);
			textinput = input.getText();
			language = "malay";	
			lblAns.setFont(getFontStyle());	
			TextTranslatorClientApp.btnPressed();

		}
		else if(e.getSource()== arb)
		{
			updatebtn(true);
			textinput = input.getText();
			language = "arab";
			setFonttoArabic();
			TextTranslatorClientApp.btnPressed();

		}
		else
		{
			updatebtn(true);
			textinput = input.getText();
			language = "korean";
			setFonttoKorean();
			TextTranslatorClientApp.btnPressed();
		}
		
	}
}