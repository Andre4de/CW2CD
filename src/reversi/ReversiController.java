package reversi;

public class ReversiController implements IController {

	IModel model;
	IView view;
	int player;
	int x;
	int y;
	int a = 0;
	int b = 0;
	int i = 0;
	int j = 0;
	int k = 0;
	int l = 0;
	int m = 0;
	int n = 0;
	boolean one = false;
	boolean two = false;
	int dirX = 0;
	int dirY = 0;
	int highX = 0;
	int highY = 0;
	
	

	
	
	
	@Override
	public void initialise(IModel model, IView view) {
		// TODO Auto-generated method stub
		this.model = model;
		this.view  = view;

		
		
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
		model.initialise(8, 8, view, this);

		//setting board pieces
		
		model.setBoardContents(3,3, 2);
	
		model.setBoardContents(4,4, 2);

		model.setBoardContents(3,4, 1);

		model.setBoardContents(4,3, 1);
		
		//set player to player 1
		
		model.setPlayer(1);
		
		model.setFinished(false);
		
		//setting players one and two's ability to NOT play to false.
		
		one = false;
		
		two = false;
		
		view.refreshView();
//		update();
		
	
		
		
	}
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int opp = 0;
		int black = 0;
		int white = 0;
		
		if(model.getPlayer() == 1)
			opp = 2;
		
		if(model.getPlayer() == 2)
			opp = 1;
		
		view.refreshView();
		
		outer:	
		if(model.hasFinished() == false) {
		for(int i = 0; i < model.getBoardHeight(); i++) {
					
					for(int j = 0; j < model.getBoardWidth(); j++) {
						
						if(model.getBoardContents(i, j) != 0) {
							model.setFinished(true);
							

							}
						if(model.getBoardContents(i, j) == 0) {
							model.setFinished(false);
							
							break outer;
						}
							
					}
				}
		}
		
		//if the game has finished, count how many counters and change feedback message
		if(model.hasFinished() == true) {
			
			for(int i = 0; i < model.getBoardHeight(); i++) {
				
				for(int j = 0; j < model.getBoardWidth(); j++) {
					
					if(model.getBoardContents(i, j) == 1)
						white++;
					
					if(model.getBoardContents(i, j) == 2)
						black++;
					
				}
			}
			
			//all possible outcomes of the came...
			if(white > black) {

				view.feedbackToUser(1, "White won. White " + white + " to Black " + black + "." +  " Reset game to replay.");
				view.feedbackToUser(2, "White won. White " + white + " to Black " + black + "." +  " Reset game to replay.");
			}
			
			if(white < black) {

			view.feedbackToUser(1, "Black won. Black " + black + " to White " + white + "." +  " Reset game to replay.");
			view.feedbackToUser(2, "Black won. Black " + black + " to White " + white + "." +  " Reset game to replay.");
			}
			
			if(white == black) {

			view.feedbackToUser(1, "Draw. Both players ended with " + white + " pieces. Reset game to replay.");
			view.feedbackToUser(2, "Draw. Both players ended with " + black + " pieces. Reset game to replay.");
			}
			
			return;
		}

		
	
		
		
	
		
		
		
		if(model.getPlayer() == 1) {
		
		for(int i = 0; i < model.getBoardHeight(); i++) {
			
			for(int j = 0; j < model.getBoardWidth(); j++) {
				
//				
				
				for(int a = i - 1; a < i + 2; a++) {
					
					if(model.getBoardContents(i, j) == 0) {
					
					for(int b = j - 1; b < j + 2; b++) {
						
						if((a < 0 || a > 7) || (b < 0 || b > 7))
							continue;
						
						if(a == i && b == j) {
							continue;
						}
						
						
						
						
						if(model.getBoardContents(a, b) == opp) {
//							
							
							int dirX = a - i;
							int dirY = b - j;
							
							
							k = i;
							l = j;
							
							
								while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
									
									k += dirX;
									l += dirY;
									
									
									
									if((k < 0 || k > 7) || (l < 0 || l > 7))
									break;
									
									
									if(model.getBoardContents(k, l) == model.getPlayer())
									{
										
										one = false;
										return;
									}
									
									}
							
							
										
							
						}
							
						
						
						
					}
					
					
					}
				
				}
			}
		}
		one = true;
		
		model.setPlayer(2);
		view.feedbackToUser(2, "Black player – choose where to put your piece");
		view.feedbackToUser(1, "White player – not your turn");

		return;
		}
		
		if(model.getPlayer() == 2) {
			
		
		for(int i = 0; i < model.getBoardHeight(); i++) {
			
			for(int j = 0; j < model.getBoardWidth(); j++) {
				
				if(model.getBoardContents(i, j) == 0) {
				
				for(int a = i - 1; a < i + 2; a++) {
					
					for(int b = j - 1; b < j + 2; b++) {
						
						if((a < 0 || a > 7) || (b < 0 || b > 7))
							continue;
						
						if(a == i && b == j) {
							continue;
						}
						
						
						
						
//						
						
						if(model.getBoardContents(a, b) == opp) {
							
							
							int dirX = a - i;
							int dirY = b - j;
							
							
							k = i;
							l = j;
							
							
								while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
									
									k += dirX;
									l += dirY;
									
									
									
									if((k < 0 || k > 7) || (l < 0 || l > 7))
									break;
									
									
									if(model.getBoardContents(k, l) == model.getPlayer())
									{	
										two = false;
										return;
									}
									
									}
							
							
										
							
						}
							
						
						
						
					}
					
					
					}
				
				
			}
			}
		}
		System.out.println("Player 2 can go = " + two);
		two = true;
		
		model.setPlayer(1);
		view.feedbackToUser(1, "White player – choose where to put your piece");
		view.feedbackToUser(2, "Black player – not your turn");

		return;
		}
		
	
		
		
		if(one == true && two == true)
			model.setFinished(true);
		
		
		if(model.hasFinished() == true) {
			for(int i = 0; i < model.getBoardHeight(); i++) {
				
				for(int j = 0; j < model.getBoardWidth(); j++) {
					
					if(model.getBoardContents(i, j) == 1)
						white++;
					
					if(model.getBoardContents(i, j) == 2)
						black++;
					
				}
			}
			
			if(white > black) {
			
				
				view.feedbackToUser(1, "White won. White " + white + " to Black " + black + "." +  " Reset game to replay.");
				view.feedbackToUser(2, "White won. White " + white + " to Black " + black + "." +  " Reset game to replay.");
			}	
			
			if(white < black) {
				
			view.feedbackToUser(1, "Black won. Black " + black + " to White " + white + "." +  " Reset game to replay.");
			view.feedbackToUser(1, "Black won. Black " + black + " to White " + white + "." +  " Reset game to replay.");
			}
			if(white == black) {
				
			view.feedbackToUser(1, "Draw. Both players ended with " + white + " pieces. Reset game to replay.");
			view.feedbackToUser(2, "Draw. Both players ended with " + black + " pieces. Reset game to replay.");
			}
			
			return;
		}
		
		
		
	}

	@Override
	public void squareSelected(int player, int x, int y) {
		this.player = player;
		this.x = x;
		this.y = y;
		
		
		if(model.getPlayer() != player) {
			view.feedbackToUser(player, "It is not your turn!");
			return;
		}
		
		if(player == 1)
		{
			
			if(model.getBoardContents(x, y) == 1 || model.getBoardContents(x, y) == 2) {
				return;
				}
			
			
			

			for(i = x - 1; i < x + 2; i++) {
				
				for(j = y - 1; j < y + 2; j++) {
					
					//this if statements prevents an array out of bounds error
					if((i < 0 || i > 7) || (j < 0 || j > 7))
						continue;
					
					
//					
					if(i == x && j == y)
					continue;

					//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
					if(model.getBoardContents(i, j) == 2) {
						
						
					
						int dirX = i - x;
						int dirY = j - y;
						
						
						k = i;
						l = j;
						
						m = i;
						n = j;
						
						
						whileloop:
						while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
							
							k += dirX;
							l += dirY;
							
							
							
							if((k < 0 || k > 7) || (l < 0 || l > 7))
							break;
							
							
							if(model.getBoardContents(k, l) == 1)
							{
								
								while((m > 0 || m <= 7) || (n > 0 || n <= 7)) {
									model.setBoardContents(m, n, 1);
									
									
									m += dirX;
									n += dirY;
									
									
									if(model.getBoardContents(m, n) == 1) {
										model.setBoardContents(x, y, 1);
										 
										break whileloop;
									}
								}
						
								
							}
						}
						model.setPlayer(2);
						update();
						

						

						

					}
					

			
			}
			
			}
			
			return;
		}
		
		//for player 2 GUI
		
		
		if(player == 2)
		{
			
			if(model.getBoardContents(x, y) == 1 || model.getBoardContents(x, y) == 2) {
				
				return;
				}	
		//this for loop scans the perimeter of the selected square, looks for 8 squares maximum.
		for(i = x - 1; i < x + 2; i++) {
			
			for(j = y - 1; j < y + 2; j++) {
				
				//this if statements prevents an array out of bounds error
				if((i < 0 || i > 7) || (j < 0 || j > 7))
					continue;
				
				
				
				if(i == x && j == y)
				continue;

				//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
				if(model.getBoardContents(i, j) == 1) {
				
					
				
					dirX = i - x;
					dirY = j - y;
					
					k = i;
					l = j;
					
					m = i;
					n = j;
					
				
					whileloop2:
					while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
						
						k += dirX;
						l += dirY;
						
					
						
						if((k < 0 || k > 7) || (l < 0 || l > 7))
						break;
						
						
						if(model.getBoardContents(k, l) == 2)
						{
							
							while((m > 0 || m <= 7) || (n > 0 || n <= 7)) {
								model.setBoardContents(m, n, 2);
								
								m += dirX;
								n += dirY;
								
								
								if(model.getBoardContents(m, n) == 2) {
									model.setBoardContents(x, y, 2);
									
									
									break whileloop2;
								}
							}
							

						}
					}
					
					model.setPlayer(1);
					update();

					

					

				}
				

		
		}
		
		}	
		
		return;
		}
		
		
		// TODO Auto-generated method stub
		
		
	}
	
	@Override
	public void doAutomatedMove(int player) {
		// TODO Auto-generated method stub
	int opp = 0;
	int count = 0;

	if(player == 1)
		opp = 2;
	
	if(player == 2)
		opp = 1;
		
	if(model.hasFinished()) 
		return;
	
	if(player != model.getPlayer()) {
		view.feedbackToUser(player, "It is not your turn!");
		return;
		}
	
	 for(a = 0; a < model.getBoardHeight(); a++) {
		 
		 for(b = 0; b < model.getBoardWidth(); b++) {
			 
			 int tmp1 = 0;
			 int tmp2 = 0;
			 
			 if(model.getBoardContents(a, b) == 0) {
			 
				for(i = a - 1; i < a + 2; i++) {
					
					for(j = b - 1; j < b + 2; j++) {
						
						
						
						//this if statements prevents an array out of bounds error
						if((i < 0 || i > 7) || (j < 0 || j > 7))
							continue;
						
						
						
//					
						if(i == x && j == y)
						continue;

						//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
						if(model.getBoardContents(i, j) == opp) {
						
							
							int dirX = i - a;
							int dirY = j - b;
//			
							k = i;
							l = j;
							
							m = i;
							n = j;
							
							
							whileloop:
							while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
							
							
								k += dirX;
								l += dirY;
								
								
//								
								tmp1++;
								
								
								
								
								if((k < 0 || k > 7) || (l < 0 || l > 7))
								break;
								
//							
								if(model.getBoardContents(k, l) == player)
								{
									tmp2 = tmp1;
									
									if(tmp2 > count) {
										count = tmp2;
										highX = a;
										highY = b;
										
										break whileloop;
										
									}
									
								}
							}
							
							

							

						}
						

				
				}
				
				}
			 
		 }
				
				
			 
		 }
	 }
//		
		if(player == 1) {
			
			squareSelected(1, highX, highY);
			
			
		}
		
		if(player == 2) {
			
			squareSelected(2, highX, highY);
			
			
		}
		
		
	}

}
