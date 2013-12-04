
public class Main {

	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	final Layout frame = new Layout("Encrypt & Decrypt");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.createAndShowGUI();
            }
        });
    }
	
}
