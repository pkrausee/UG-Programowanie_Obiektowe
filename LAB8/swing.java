import java.awt.* ;
import java.awt.event.* ;
import java.util.ArrayList;
import javax.swing.*;

class Przyciski extends JFrame
{

	private JTextField t = new JTextField(20);
	private JButton cleaner = new JButton("wyczysc licznik");


	private ArrayList<JButton> buttons = new ArrayList<JButton>();

	private int yellowCounter = 0;
	private int clickCounter = 0;

	private int n,m;

	Przyciski()
	{
		m = 4; //Ilosc przyciskow
		n = 3; //Maksymalna ilosc zoltych przyciskow

		setTitle("Przyciski");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		for(int i = 1; i <= m; i++)
		{
			buttons.add(new JButton("przycisk " + i));

			cp.add(buttons.get(i - 1));

			buttons.get(i - 1).addActionListener(new YellowColor());
			buttons.get(i - 1).setBackground(Color.blue);
		}

		cp.add(cleaner);
		cp.add(t);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,200);
		setVisible(true);

		cleaner.addActionListener(new cleanClickCounter());

	}

	class YellowColor implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() instanceof JButton)
			{
				if(((JButton)e.getSource()).getBackground() == Color.blue)
				{
					clickCounter++;
					t.setText(String.valueOf(clickCounter));

					yellowCounter++;
					((JButton) e.getSource()).setBackground(Color.yellow);

					if(yellowCounter == n)
						blockBlueButtons();
				}
				else
				{
					clickCounter++;
					t.setText(String.valueOf(clickCounter));

					if(yellowCounter == n)
						unblockBlueButtons();

					yellowCounter--;
					((JButton) e.getSource()).setBackground(Color.blue);
				}
			}
		}

		private void blockBlueButtons()
		{
			for (JButton b: buttons)
			{
				if(b.getBackground() == Color.blue)
					b.setEnabled(false);
			}
		}

		private void unblockBlueButtons()
		{
			for (JButton b: buttons)
			{
				if(b.getBackground() == Color.blue)
					b.setEnabled(true);
			}
		}
	}


	class cleanClickCounter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			clickCounter = 0;
			t.setText(String.valueOf(clickCounter));
		}
	}


	//----------------------------------------MAIN
	public static void main(String[] arg)
	{
		JFrame f = new Przyciski();
		//JFrame f1 = new Przyciski();
	}
}






