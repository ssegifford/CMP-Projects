import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//	https://youtu.be/C6-Qqi58OT0

public class Grid  {
	
	private boolean [][] bombGrid;
	private int [][] countGrid;
	private int numRows, numColumns, numBombs;
	
	
	public Grid() {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		JPanel mainJP = new JPanel(new GridLayout());
		createBombGrid();
		createCountGrid();
		
		mainJP.add(gridForBomb, BorderLayout.WEST);
		mainJP.add(gridForCount, BorderLayout.EAST);
		mainJP.setVisible(true);
	}
	
	public Grid(int row, int column) {
		numRows = row; 
		numColumns = column;
		numBombs = 25;
		JPanel mainJP = new JPanel(new GridLayout());
		createBombGrid();
		createCountGrid();
		mainJP.add(gridForBomb, BorderLayout.WEST);
		mainJP.add(gridForCount, BorderLayout.EAST);
		mainJP.setVisible(true);
	}
	public Grid(int row, int column, int numBombs) {
		numRows = row; 
		numColumns = column;
		this.numBombs = numBombs;
		JPanel mainJP = new JPanel(new GridLayout());
		createBombGrid();
		createCountGrid();	
		mainJP.add(gridForBomb, BorderLayout.WEST);
		mainJP.add(gridForCount, BorderLayout.EAST);
		mainJP.setVisible(true);
	}
	
	
	JPanel gridForBomb;
	JButton [][]booleanGrid;
	public void createBombGrid() {
		
		Random randBoolean = new Random();
		
		gridForBomb = new JPanel(new GridLayout(numRows, numColumns));
		gridForBomb.setBorder(BorderFactory.createLineBorder(Color.black));
		gridForBomb.setBackground(Color.gray);
		bombGrid = new boolean[numRows][numColumns];
		booleanGrid = new JButton[numRows][numColumns];
		
		for(int row = 0; row < bombGrid.length; row++) {					//Set boolean array to all false
			for(int col = 0; col < bombGrid[row].length; col++) {
				bombGrid[row][col] = false;	
			}
		}
		int count = 0, temp, temp2;
		while(count<numBombs) {													//Set true to 25 random locations in the grid
			temp = randBoolean.nextInt(numRows); temp2 = randBoolean.nextInt(numColumns);
			
			if(bombGrid[temp][temp2]==false) {
			bombGrid[temp][temp2] = true;
			count++;
			}
		}

		for(int row = 0; row < bombGrid.length; row++) {
			for(int col = 0; col < bombGrid[row].length; col++) {
				booleanGrid[row][col]= new JButton();
				
				if(bombGrid[row][col]==false) {
				booleanGrid[row][col].setText("F");}
				
				else if(bombGrid[row][col]==true) {
					booleanGrid[row][col].setText("T");
				} 
				
				booleanGrid[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
				booleanGrid[row][col].setForeground(Color.ORANGE);
				booleanGrid[row][col].setBackground(Color.LIGHT_GRAY);
			//	booleanGrid[row][col].addActionListener(this);
				booleanGrid[row][col].setEnabled(true);
				
			
				gridForBomb.add(booleanGrid[row][col]);
			}
		}
	}

	JPanel gridForCount;
	public void createCountGrid() {
		countGrid = new int [numRows][numColumns];
		JButton [][]numberGrid = new JButton [numRows][numColumns];
		gridForCount = new JPanel(new GridLayout(numRows, numColumns));
		gridForCount.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		
		for(int row = 0; row < bombGrid.length; row++) { 					//Checks surrounding cells for true value
			for(int col = 0; col < bombGrid[row].length; col++) {int temp =0;
			
			//Checks first row, cannot check previous row before 0
			if(row==0) {															
					if(bombGrid[row][col]==true) {	temp++;}
					if(bombGrid[row+1][col]==true) { temp++; }
					if(col>0) {
						if(bombGrid[row][col-1]==true) { temp++; }
						if(bombGrid[row+1][col-1]==true) { temp++; }
					}
					
					if(col<bombGrid[row].length-1) {
						if(bombGrid[row+1][col+1]==true) { temp++; }
						if(bombGrid[row][col+1]==true) { temp++; }	
					}	
				}
				
				//Checks middle rows and cols
				if(row>0){	
					if(col>=0) {
						if(bombGrid[row][col]==true) {	temp++;}
						if(bombGrid[row-1][col]==true) { temp++; }
						if(row<bombGrid.length-1) {
							if(bombGrid[row+1][col]==true) { temp++; }
						}	
						
						if(col>0) {
							if(bombGrid[row][col-1]==true) { temp++; }
							if(bombGrid[row-1][col-1]==true) { temp++; }
							if(row<bombGrid.length-1) {
								if(bombGrid[row+1][col-1]==true) { temp++; }
							}	
						}
						
						if(col<bombGrid[row].length-1) {				//checks 0-8 for each row
							if(bombGrid[row][col+1]==true) { temp++; }
							if(bombGrid[row-1][col+1]==true) { temp++; }
							
							if(row<bombGrid.length-1) {
								if(bombGrid[row+1][col+1]==true) { temp++; }
							}	
						}	
						
					}
				}	
				countGrid[row][col]= temp;
				numberGrid[row][col] = new JButton(""+temp);
				
				numberGrid[row][col].setFont(new Font(Font.SANS_SERIF,Font.BOLD, 16));
				numberGrid[row][col].setForeground(Color.BLUE);
				numberGrid[row][col].setBackground(Color.LIGHT_GRAY);
			//	numberGrid[row][col].addActionListener(this);
				numberGrid[row][col].setEnabled(true);
				
				gridForCount.add(numberGrid[row][col]);
				
					
			}
			
		}
						//for [0][0] i need [0][+1] , [0][-1] , [+1][0] , [+1][+1] , [+1][-1] , [-1][0], [-1,-1] , [-1, +1]
	}
	

	public boolean isBombAtLocation(int row, int column) {
		if(bombGrid[row][column]) {
			return true;
		}
		return false;
	}

	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}

	
	public boolean[][] getBombGrid() {
		boolean[][] clone = new boolean [bombGrid.length][bombGrid.length];
		
		for(int row =0; row < bombGrid.length; row++) {
			clone[row]=bombGrid[row].clone();
		}
		return clone;
	} 
	

	public int[][] getCountGrid() {
		int [][] clone = new int [countGrid.length][countGrid.length];
		
		for(int row =0; row < countGrid.length; row++) {
			clone[row]=countGrid[row].clone();
		}
		return clone;
	} 

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public int getNumBombs() {
		return numBombs;
	}


}

