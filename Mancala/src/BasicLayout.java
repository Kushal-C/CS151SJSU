import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;


public class BasicLayout extends LayoutTemplate
{
	private Rectangle2D.Double[] mRects;
	private int nPlayers;
	private int boardLength;
	private Image bg;
	private Image pit;
	private Image stone;
	private Image mancala;
	private static Random rand = new Random();

	BasicLayout(int nPlayers, int boardLength)
	{
		super();
		this.nPlayers = nPlayers;
		this.boardLength = boardLength;
		mRects = new Rectangle2D.Double[nPlayers];
		try
		{
			bg = ImageIO.read(new File("background1.jpg"));
			stone = ImageIO.read(new File("background2.jpg"));
		}
		catch (Exception e) { bg = null; }
	}

	
	@Override
	public void paintBoard(Graphics g, Board b, int[][] pits, int[] mancalas)
	{
		g.drawImage(bg, 0, 0, 455, 325, b);

		Graphics2D g2 = (Graphics2D) g;

		// Draw the stones.
		for (int r = 0; r < pits.length; r++)
			for (int c = 0; c < pits[r].length; c++)
				for (int s = 0; s < pits[r][c]; s++)
					drawStone(pitRects[r][c], g, b, s);
		for (int m = 0; m < mRects.length; m++)
			for(int s = 0; s < mancalas[m]; s++)
				drawStone(mRects[m], g, b, s);
		}

	@Override
	public void setSize(int w, int h)
	{
		int m = 5; // margin
		int mW = 55; // Mancala Width
		int mH = 225; // Mancala Height
		int mY = 45; // Mancala Y value
		int pD = 50; // Pit Width and Height
		int pTop = 75; // Pit Y value
		int pBottom = 190; // Pit Y value
		super.setSize(w,h);
		mRects[0] = new Rectangle2D.Double(width - m - mW, mY, mW, mH);
		mRects[1] = new Rectangle2D.Double(m, mY, mW, mH);

		int s = boardLength - 1; /* Hack var to reverse direction */
		for (int c = 0; c < boardLength; c++)
		{
			pitRects[0][c] = new Rectangle2D.Double(m + mW + m * (c + 1) + pD * c,
					pBottom, pD, pD);
			pitRects[1][c] = new Rectangle2D.Double(m + mW + m * (s + 1) + pD * s,
					pTop, pD, pD);
			--s;
		}
	}

	@Override
	public String getLayoutName() { return "Basic Layout"; }

	private void drawStone(Rectangle2D.Double r, Graphics g, Board b, int n)
	{
		rand.setSeed((int)r.getX() * n +  (int)r.getY() * n + n * n);
		int x = rand.nextInt((int)r.getWidth() - stone.getWidth(b));
		int y = rand.nextInt((int)r.getHeight() - stone.getHeight(b));
		g.drawImage(stone, (int)r.getX() + x, (int)r.getY() + y, stone.getWidth(b),
				stone.getHeight(b), b);
	}

}