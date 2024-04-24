package reversi;

public class ReversiController implements IController {

	IModel model;
	IView view;
	int player;
	int x;
	int y;
	int count = 0;
	

	
	
	
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
		
		
		
		if(player == 1)
		{
			if(model.getBoardContents(x, y) == 1) {
				System.out.println("You already have a counter in this square.");
				return;
				}
			count = 0;
			int i = 0;
			int j = 0;
//			outerloop:
			for(i = x - 1; i < x + 2; i++) {
				
				for(j = y - 1; j < y + 2; j++) {
					
					//this if statements prevents an array out of bounds error
					if((i < 0 || i > 7) || (j < 0 || j > 7))
						continue;
					
					
//					System.out.println("(Scan: )" + i + ":" + j + "=" + model.getBoardContents(i, j));
					if(i == x && j == y)
					continue;
					if(model.getBoardContents(i, j) == 1) {
						System.out.println("(Found it)" + i + ":" + j +  "=" + "White");
				

					}
					//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
					if(model.getBoardContents(i, j) == 2) {
						System.out.println("(Found it): " + i + ":" + j +  "=" + "Black");
						
					
						int dirX = i - x;
						int dirY = j - y;
						
						int k = i;
						int l = j;
						
						int m = i;
						int n = j;
						
						System.out.println("Direction = " + dirX + ":" + dirY);
						whileloop:
						while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
							
							k += dirX;
							l += dirY;
							
							System.out.println("While: " + k + ":" + l);
							
							if((k < 0 || k > 7) || (l < 0 || l > 7))
							break;
							
							System.out.println("check val m n: " + m + ":" + n);
							if(model.getBoardContents(k, l) == 1)
							{
								System.out.println("You are able to play here.");
								while((m > 0 || m <= 7) || (n > 0 || n <= 7)) {
									model.setBoardContents(m, n, 1);
									count++;
									
									m += dirX;
									n += dirY;
									System.out.println("While2: " + k + ":" + l);
									
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
						System.out.println("You cannot select a square here.");

						

					}
					

			
			}
			
			}
			
			return;
		}
		
		//for player 2 GUI
		
		
		if(player == 2)
		{
			if(model.getBoardContents(x, y) == 2) {
				System.out.println("You already have a counter in this square.");
				return;
				}	
		//this for loop scans the perimeter of the selected square, looks for 8 squares maximum.
		count = 0;
		int i = 0;
		int j = 0;
//		outerloop:
		for(i = x - 1; i < x + 2; i++) {
			
			for(j = y - 1; j < y + 2; j++) {
				
				//this if statements prevents an array out of bounds error
				if((i < 0 || i > 7) || (j < 0 || j > 7))
					continue;
				
				
//				System.out.println("(Scan: )" + i + ":" + j + "=" + model.getBoardContents(i, j));
				if(i == x && j == y)
				continue;
				if(model.getBoardContents(i, j) == 2) {
					System.out.println("(Found it)" + i + ":" + j +  "=" + "Black");
			

				}
				//This if statement scans the perimeter of selecteed square and looks for counter of opposite colour.
				if(model.getBoardContents(i, j) == 1) {
					System.out.println("(Found it): " + i + ":" + j +  "=" + "White");
					
				
					int dirX = i - x;
					int dirY = j - y;
					
					int k = i;
					int l = j;
					
					int m = i;
					int n = j;
					
					System.out.println("Direction = " + dirX + ":" + dirY);
					whileloop2:
					while((k > 0 || k <= 7) || (l > 0 || l <= 7)) {
						
						k += dirX;
						l += dirY;
						
						System.out.println("While: " + k + ":" + l);
						
						if((k < 0 || k > 7) || (l < 0 || l > 7))
						break;
						
						System.out.println("check val m n: " + m + ":" + n);
						if(model.getBoardContents(k, l) == 2)
						{
							System.out.println("You are able to play here.");
							while((m > 0 || m <= 7) || (n > 0 || n <= 7)) {
								model.setBoardContents(m, n, 2);
								count++;
								m += dirX;
								n += dirY;
								System.out.println("While2: " + k + ":" + l);
								
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
					System.out.println("You cannot select a square here.");

					

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
		
	}

}
