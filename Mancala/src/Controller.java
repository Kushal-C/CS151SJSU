
public class Controller {
	//Main program execution, holds a board class and a game class
	Board b;
	Game g;

	public static void main(String[] args)
	{
		Controller c = new Controller();
	}

	public Controller()
	{
		b = new Board();
		g = new Game(4);
	}


}
