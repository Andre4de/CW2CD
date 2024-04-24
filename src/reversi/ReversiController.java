package reversi;

public class ReversiController implements IController {

	IModel model;
	IView view;
	int player;
	int x;
	int y;
	int i = 0;
	int j = 0;
	int k = 0;
	int l = 0;
	int m = 0;
	int n = 0;
	int count = 0;
	int dirX = 0;
	int dirY = 0;
	

	
	
	
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
		
	}

}
