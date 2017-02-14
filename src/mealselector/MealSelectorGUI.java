package mealselector;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;


public class MealSelectorGUI
{
	
	public static JFrame			frmKellysMealPicker;
	public static ArrayList<Meal>	meals			= new ArrayList<Meal>();
	int								calorieLimit	= 0;
	int								lowerBounds		= 0;
	int								upperBounds		= 0;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@SuppressWarnings("static-access")
			public void run()
			{
				try
				{
					MealSelectorGUI window = new MealSelectorGUI();
					window.frmKellysMealPicker.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public MealSelectorGUI() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException
	{
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	private void initialize() throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		frmKellysMealPicker = new JFrame();
		frmKellysMealPicker.setTitle("Kelly's Meal Picker!");
		frmKellysMealPicker.setBounds(100, 100, 500, 350);
		frmKellysMealPicker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKellysMealPicker.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		JButton btnShowMeThe = new JButton(
				"<html><center>Show me the BALL GAME!<br />(I mean meals lol)</center></html>");
		btnShowMeThe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		// Check for file and create it if necessary
		if (!methods.checkFile()) methods.createFile();
		calorieLimit = methods.readCalories();
		lowerBounds = methods.readLowerTolerance();
		upperBounds = methods.readUpperTolerance();
		meals = methods.readMeals();
		
		// Everything should be read at this point.
		btnShowMeThe.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				if (arg0.getButton() == MouseEvent.BUTTON3)
				{
					// Put easter egg here
					// Toolkit.getDefaultToolkit().getImage(TorrentServer.class.getResource("/res/icon.png"))
					// System.out.println("Trying to make the image...");
					// Image img =
					// Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mealselector/res/heart.png"));
					
					// System.out.println("Image created successfully...");
					// Icon icon = new ImageIcon();
					
					JOptionPane.showMessageDialog(frmKellysMealPicker,
							"You found the easter egg!  I love you so so\n"
									+ "much Kelly, I hope you're having a good day\n"
									+ "and I hope seeing this makes you smile <3\n"
									+ "You're the most wonderful girlfriend in the\n"
									+ "world and I'm so happy I was able to win you\n"
									+ "over and make you mine! Well, it's really\n"
									+ "fucking late now and this is the last thing\n"
									+ "I had to do on this program so I'm gonna send\n"
									+ "it over to you and go to sleep! Let me know\n"
									+ "when you find this!\n\nLove always,\nYour man",
							"Easter Egg", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (arg0.getButton() == MouseEvent.BUTTON1)
				{
					// Put method to pick meals here.
					methods.pickMeals(meals, calorieLimit, lowerBounds, upperBounds);
				}
			}
			
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			
			@Override
			public void mouseExited(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
			}
			
			
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		frmKellysMealPicker.getContentPane().add(btnShowMeThe, "cell 0 0,push ,width 65%,alignx center,height 65%");
		btnShowMeThe.setFocusable(false);
	}
}
