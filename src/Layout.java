import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
 
public class Layout extends JFrame implements ActionListener{
	
	JButton encryptButton;
	JButton decryptButton;
	JTextArea originalArea;
	JTextArea encryptArea;
	Encryptor encryptor;
	Map<String , EncryptionStrategy> encryptionMap;
	JComboBox<String[]> comboBox;
	
	
    public Layout(String string) {
		super(string);
	}

	public void addComponentsToPane(Container pane) {
    	
        Box box = Box.createVerticalBox();
    	originalArea = createAJTextArea("Original", box);
    	encryptArea = createAJTextArea("Verschlüsselt", box);
    	Box buttons = Box.createHorizontalBox();
    	encryptButton = createButton("Encrypt", buttons);
    	decryptButton = createButton("Decrypt", buttons);
    	comboBox = new JComboBox(encryptionMap.keySet().toArray());
    	comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    	comboBox.addActionListener(this);
    	Object selectedStrategy = comboBox.getSelectedItem();
    	EncryptionStrategy strategy = encryptionMap.get(selectedStrategy);
    	encryptor.setStrategy(strategy);
    	buttons.add(comboBox);
    	box.add(buttons);
    	pane.add(box);
    }
    
    private JTextArea createAJTextArea(String title, Container container){
    	JTextArea textArea = new JTextArea();
    	textArea.setBorder(new TitledBorder(title));
    	textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    	textArea.setLineWrap(true);
    	textArea.setWrapStyleWord(true);
    	container.add(textArea);
    	return textArea;
    }
 
    private JButton createButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(this);
        container.add(button);
        return button;
    }
    
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == this.encryptButton){
        	encryptArea.setText(encryptor.encrypt(originalArea.getText()));
        }
        else if(e.getSource() == this.decryptButton){
          originalArea.setText(encryptor.decrypt(encryptArea.getText()));
        } else if (e.getSource() == this.comboBox){
        	Object selectedStrategy = comboBox.getSelectedItem();
        	EncryptionStrategy strategy = encryptionMap.get(selectedStrategy);
        	encryptor.setStrategy(strategy);
        }
    }
    
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        encryptor = new Encryptor();
        encryptionMap = new HashMap<String, EncryptionStrategy>();
        encryptionMap.put("Copy", new CopyEncryption());
        encryptionMap.put("Reverse", new ReverseEncryption());
        
        //Set up the content pane.
        addComponentsToPane(this.getContentPane());
 
        //Display the window.
        this.pack();
        this.setVisible(true);
    }
}