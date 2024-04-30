package reversi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;






public class GUIView implements IView
{
	

	
	
	IModel model;
	IController controller;

	//player 1 objects
	JFrame Player1_Frame;
	JLabel player1lbl = new JLabel("White player – choose where to put your piece");
	drawSqr[][] Board1;
	
	//player 2 objects
	JFrame Player2_Frame;
	JLabel player2lbl = new JLabel("Black player – not your turn");
	drawSqr[][] Board2;
	
	int start = 0;
	int refresh = 0;
	
	
	@Override
	public void initialise(IModel model, IController controller) 
	{
		
		// Initiating objects

		this.model = model;
		this.controller = controller;
		
		int width = model.getBoardWidth();
		int height = model.getBoardHeight();
		

		
				// object declarations
	
		
				Player1_Frame = new JFrame();
				JButton button1 = new JButton("Greedy AI (play white)");
				JButton button2 = new JButton("Restart");
				JPanel panel1 = new JPanel();
				JPanel panel2 = new JPanel();
//				JLabel player1lbl = new JLabel("White player – choose where to put your piece");
				Player1_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Player1_Frame.setTitle("Player 1");
				
				
		
				panel1.setLayout(new GridLayout(2,1));
				panel2.setLayout(new GridLayout(height,width));
				
				player1lbl.setHorizontalAlignment(SwingConstants.CENTER);
				
				panel1.add(button1);
				panel1.add(button2);
				
				button1.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(model.getPlayer() == 1)
						controller.doAutomatedMove(1);
						
					}
				});
				
				Board1 = new drawSqr[height][width];
				
				for(int i = 0; i < height; i++)
				{
					for(int j = 0; j < width; j++)
					{
						final int row = i;
						final int col = j;
						
						
						Board1[i][j] = new drawSqr(100, 100,
								1, Color.BLACK,Color.GREEN);
						Board1[i][j].drawGreen();
						panel2.add(Board1[i][j]);
						
						Board1[i][j].addActionListener(new ActionListener() 
						{
						    @Override
			                public void actionPerformed(ActionEvent e)
						    {
						    	if(model.hasFinished()) {
						    		return;
						    	}
						    	

			                   
						    	
						    	
						    	controller.squareSelected(1, row, col);
			              
			                    
			                    
			                   
			                    
			                    

						    }
						});
					}
				}
				
				
				button2.addActionListener(new ActionListener() 
				{
					
				    @Override
	                public void actionPerformed(ActionEvent e)
				    {
	                    model.clear(3);
	                    refreshView();
	                    controller.startup();
	                   

				    }
				    
				});
				
				
				Player1_Frame.getContentPane().add(panel1, BorderLayout.SOUTH);
				Player1_Frame.getContentPane().add(panel2, BorderLayout.CENTER);
				Player1_Frame.getContentPane().add(player1lbl, BorderLayout.NORTH);
				 
				

				Player1_Frame.pack();
				Player1_Frame.setSize(400, 500);
				Player1_Frame.setVisible(true);
			
				//End of Player 1 Gui
				
				//Begininning of Player 2 GUI

				// object declaration
			
				JButton button3 = new JButton("Greedy AI (play black)");
				JButton button4 = new JButton("Restart");
				JPanel panel3 = new JPanel();
				JPanel panel4 = new JPanel();
//				player2lbl = new JLabel("Black player – not your turn");
			
				button3.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(model.getPlayer() == 2)
						controller.doAutomatedMove(2);
						
						
					}
				});
			
				// manipulating objects for GUI
				Player2_Frame = new JFrame();
				Player2_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Player2_Frame.setTitle("Player 2");
				
				panel3.setLayout(new GridLayout(2,1));
				panel4.setLayout(new GridLayout(height,width));
				
				player2lbl.setHorizontalAlignment(SwingConstants.CENTER);
				
				panel3.add(button3);
				panel3.add(button4);
				
				Board2 = new drawSqr[height][width];
				
				for(int i = height - 1; i > -1; i--)
				{	
					for(int j = width - 1; j > -1; j--)
					{
						final int col = j;
						final int row = i;
						
						Board2[i][j] = new drawSqr(100, 100,
								1, Color.BLACK,Color.GREEN);
						Board2[i][j].repaint();
						panel4.add(Board2[i][j]);
						Board2[i][j].addActionListener(new ActionListener() 
						{
						    @Override
			                public void actionPerformed(ActionEvent e)
						    {
						    	if(model.hasFinished()) {
						    		return;
						    	}
						    	

						    	
						    	
						    	
			                    controller.squareSelected(2, row, col);

			                    model.getBoardContents(row, col);
			                    
			                    
			                    
			                   
			                    

						    }
						});
					}
				}
				

				button4.addActionListener(new ActionListener() 
				{
				    @Override
	                public void actionPerformed(ActionEvent e)
				    {
	                    model.clear(3);
	                    
	                    

	                   
	                    refreshView();
	                    controller.startup();
	                    
	    
				    }
				});


				Player2_Frame.getContentPane().add(panel3, BorderLayout.SOUTH);
				Player2_Frame.getContentPane().add(panel4, BorderLayout.CENTER);
				Player2_Frame.getContentPane().add(player2lbl, BorderLayout.NORTH);
				 
			
			
				Player2_Frame.pack();
				Player2_Frame.setSize(400, 500);
				Player2_Frame.setVisible(true);
				
			

			
		//End of Player 2 GUI
				
					

		
	}
	
		
	
	
		
	//Draw Square Class begins
	
	

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub
		
		int width = model.getBoardWidth();
		int height = model.getBoardHeight();

		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
			
				if(model.getBoardContents(i, j) == 2)
				{
					Board1[i][j].drawBlack();
					Board2[i][j].drawBlack();

					
				}
				

				if(model.getBoardContents(i, j) == 1)
				{
					Board1[i][j].drawWhite();
					Board2[i][j].drawWhite();

					
				}
				
				
				if(model.getBoardContents(i, j) == 3)
				{	
					//
					Board1[i][j].drawGreen();
					Board2[i][j].drawGreen();

					
					
				}
				
				
			}
		}
		
		if(model.getPlayer() == 1) {
			feedbackToUser(1, "White player – choose where to put your piece.");
			feedbackToUser(2, "Black player – not your turn");

			
		}
		
		if(model.getPlayer() == 2) {
			feedbackToUser(2, "Black player – choose where to put your piece");
			feedbackToUser(1, "White player – not your turn");

			
		}
		
		 
				

			
	}

	@Override
	public void feedbackToUser(int player, String message) {
		// TODO Auto-generated method stub

		if(player == 1) {
			
			player1lbl.setText(message);
			return;
			
		}
		
		if(player == 2) {
			
			player2lbl.setText(message);
			return;
			
		}
	}
	


		
		
	public class drawSqr extends JButton
	{
		private static final long serialVersionUID = 1L;
		
		
		
		private Color sqrCol; 
		private Color borderColor;
		private Color OvalColor;
		private Color OppColor;
		private Color highlight = Color.WHITE;
		private int borderSize; 
		
		
		public drawSqr(int width, int height,
				int borderSize, Color borderCol,Color sqrCol)
		{
			this.borderSize = borderSize;
			this.borderColor = borderCol;
			this.sqrCol = sqrCol;
			setContentAreaFilled(false); // Set to false to allow custom painting
	        setFocusPainted(false); // Disable default focus painting
			setMinimumSize( new Dimension(width, height) );
			setPreferredSize( new Dimension(width, height) );
			
			  addMouseListener(new MouseAdapter() 
			  {
		            @Override
		            public void mouseEntered(MouseEvent e) 
		            {
		                setBorder(BorderFactory.createLineBorder(highlight));
		            }

		            @Override
		            public void mouseExited(MouseEvent e) {
		                setBorder(BorderFactory.createLineBorder(borderColor));
		            }
		        });
			
			  

				
		}
		
		
		
		
		public void drawBlack() {
			OvalColor = Color.WHITE;
			OppColor = Color.BLACK;
			repaint();
		}
		
		public void drawWhite() {
			OvalColor = Color.BLACK;
			OppColor = Color.WHITE;
			repaint();
		}
		
		public void drawGreen() {
			OvalColor = Color.GREEN;
			OppColor = Color.GREEN;
			repaint();
		}
	
		


		
		public Color getDrawColor()
		{
			return sqrCol;
		}

		public void setDrawColor(Color drawColor)
		{
			this.sqrCol = drawColor;
		}

		public Color getBorderColor()
		{
			return borderColor;
		}

		public void setBorderColor(Color borderColor)
		{
			this.borderColor = borderColor;
		}

		public int getBorderSize()
		{
			return borderSize;
		}

		public void setBorderSize(int borderSize)
		{
			this.borderSize = borderSize;
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			
			Graphics2D g2d = (Graphics2D) g.create();
			
				g2d.setColor(OppColor);
				g2d.drawRect(borderSize, borderSize, getWidth()-borderSize*2, getHeight()-borderSize*2);
			
				g2d.setColor(borderColor);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				
				g2d.setColor(sqrCol);
				g2d.fillRect(borderSize, borderSize, getWidth()-borderSize*2, getHeight()-borderSize*2);
			
			
			
				
				
				
				g2d.setColor(OvalColor);
				g2d.drawOval(7, 7, 48 - 7 * 2, 48 - 7 * 2);
				
				g2d.setColor(OppColor);
				g2d.fillOval(7, 7, 48 - 7 * 2, 48 - 7 * 2);
					
				
			
			

			g2d.dispose();
			
		}
		
	}		
			
	


	
	

	
}
	

	

	


