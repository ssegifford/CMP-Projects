

public class Show_MS {
	
	public static void main(String[]args) {
		javax.swing.SwingUtilities.invokeLater( new Runnable(){		//Run all GUI under this method
			public void run() {
				tempGrid myFrame = new tempGrid();
			}
		});
	

}
}
