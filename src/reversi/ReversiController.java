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

		model.setBoardContents(3,3, 2);
	
		model.setBoardContents(4,4, 2);

		model.setBoardContents(3,4, 1);

		model.setBoardContents(4,3, 1);
		
		model.setPlayer(1);
		
		view.refreshView();
		
	
		
		
	}
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		view.refreshView();		
		
		
		
	}

	@Override
	public void squareSelected(int player, int x, int y) {
		this.player = player;
		this.x = x;
		this.y = y;
		int count = 0;
		
		System.out.println("Am i being executed?");
		
		if(player == 1)
		{
			System.out.println("Is it player 1?");
			if(model.getBoardContents(x, y) == 1) {
				System.out.println("You already have a counter in this square.");
				return;
				}
			
			
			

			for(i = x - 1; i < x + 2; i++) {
				
				for(j = y - 1; j < y + 2; j++) {
					
					//this if statements prevents an array out of bounds error
					if((i < 0 || i > 7) || (j < 0 || j > 7))
						continue;
					
					
//					System.out.println("(Scan: )" + i + ":" + j + "=" + model.getBoardContents(i, j));
					if(i == x && j == y)
					continue;

					//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
					if(model.getBoardContents(i, j) == 2) {
						System.out.println("(Found it): " + i + ":" + j +  "=" + "Black");
						
					
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
									count++;
									
									m += dirX;
									n += dirY;
									
									
									if(model.getBoardContents(m, n) == 1) {
										model.setBoardContents(x, y, 1);
										update();
										model.setPlayer(2); 
										break whileloop;
									}
								}
						
								
							}
						}
						System.out.println("White Count = " + count);
						

						

					}
					

			
			}
			
			}
			
			return;
		}
		
		//for player 2 GUI
		
		
		if(player == 2)
		{
			System.out.println("Is it player 2?");
			if(model.getBoardContents(x, y) == 2) {
				System.out.println("You already have a counter in this square.");
				return;
				}	
		//this for loop scans the perimeter of the selected square, looks for 8 squares maximum.
		for(i = x - 1; i < x + 2; i++) {
			
			for(j = y - 1; j < y + 2; j++) {
				
				//this if statements prevents an array out of bounds error
				if((i < 0 || i > 7) || (j < 0 || j > 7))
					continue;
				
				
				//System.out.println("(Scan: )" + i + ":" + j + "=" + model.getBoardContents(i, j));
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
								count++;
								m += dirX;
								n += dirY;
								
								
								if(model.getBoardContents(m, n) == 2) {
									model.setBoardContents(x, y, 2);
									
									update();
									model.setPlayer(1);
									break whileloop2;
								}
							}
							

						}
					}
					System.out.println("Black Count = " + count);
					

					

				}
				

		
		}
		
		}	
		
		return;
		}
		
		
		// TODO Auto-generated method stub
		
		
	}
	
	public void capture() {
		
	}

	@Override
	public void doAutomatedMove(int player) {
		// TODO Auto-generated method stub
	int opp = 0;
	int count = 0;
//	int countme = 0;
//	int a = 0;
//	int b = 0;
//	int i = 0;
//	int j = 0;
	if(player == 1)
		opp = 2;
	
	if(player == 2)
		opp = 1;
		
	
	
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
						
						
						
//						System.out.println("(Scan: )" + i + ":" + j + "=" + model.getBoardContents(i, j));
						if(i == x && j == y)
						continue;

						//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
						if(model.getBoardContents(i, j) == opp) {
						System.out.println(a + ":" + b + "= current square");
						System.out.println(i + ":" + j + "=" + model.getBoardContents(i, j));	
							
							int dirX = i - a;
							int dirY = j - b;
//							System.out.println("Dixr = " + dirX + " " + "diry = " + dirY);
							k = i;
							l = j;
							
							m = i;
							n = j;
							
							
							whileloop:
							while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
							
							
								k += dirX;
								l += dirY;
								
								
//								System.out.println()
								tmp1++;
								
								
								
								
								if((k < 0 || k > 7) || (l < 0 || l > 7))
								break;
								
//								System.out.println("k and l: " + k + ":" + l);
//								System.out.println("direction: " + model.getBoardContents(k, l));
								
								
								System.out.println(k + ":" + l + "=" + model.getBoardContents(k, l));
								if(model.getBoardContents(k, l) == player)
								{
									tmp2 = tmp1;
									System.out.println("the temps:" + "tmp1 = " + " " +  tmp1 + " " + "count = " + " " + count);
									if(tmp2 > count) {
										count = tmp2;
										highX = a;
										highY = b;
										System.out.println("Current = " + highX + ":" + highY);
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
		System.out.println("Best square for player:" + model.getPlayer() + "=" + highX + ":" + highY);
		if(player == 1) {
			System.out.println("Player 1");
			squareSelected(1, highX, highY);
			
			
		}
		
		if(player == 2) {
			System.out.println("Player 2");
			squareSelected(2, highX, highY);
			
			
		}
		
		
	}

}
