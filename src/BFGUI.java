import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.*;



public class BFGUI implements Runnable{

	public void run() {
		
		GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.gridx = 0; 
		constraints.gridy = 0; 
	
		// GUI frame
		final JFrame frame = new JFrame("Brainfuck");
		frame.setLocation(300, 300);
		frame.setPreferredSize(new Dimension(650, 600));
		
		// control panel
		final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
		// text input
		final JTextArea editor = new JTextArea(); 
		editor.setLineWrap(true);
		final JScrollPane editorWrap = new JScrollPane(editor); 
		editorWrap.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		frame.add(editorWrap, BorderLayout.CENTER); 
		
		
		// console panel 
		final JPanel consolePanel = new JPanel(); 
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
		final JScrollPane consoleWrap = new JScrollPane(consolePanel);
		consoleWrap.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		frame.add(consoleWrap, BorderLayout.PAGE_END);
		
		
		// input label
		constraints.gridy = 0; 
		final JLabel inputLabel = new JLabel("Input"); 
		consolePanel.add(inputLabel, constraints); 
		
		
		// input box
		constraints.gridy = 1; 
		final JTextArea input = new JTextArea(); 
		consolePanel.add(input, constraints); 
		
		
		
		// console label
		constraints.gridy = 2; 
        final JLabel consoleLabel = new JLabel("Console");
        consoleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        consolePanel.add(consoleLabel, constraints);
        
        
        
        // console content - the output in the console
        constraints.gridy = 3; 
        final JTextArea consoleContent = new JTextArea(); 
        consoleContent.setPreferredSize(new Dimension(600, 150));
        consoleContent.setEditable(false); 
        consoleContent.setLineWrap(true);
        consolePanel.add(consoleContent, constraints); 
        
		
		

		
		
		// run button 
        final JButton run = new JButton("Run");
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String commands = editor.getText(); 
            	
            	String inputText = input.getText(); 
            	
            	String consoleText = Interpreter.interpret(commands, inputText); 
            	
            	consoleContent.setText(consoleText);

            }
        });
        control_panel.add(run);
        
        
        // clear button 
        final JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	// remove text
            	editor.setText(""); 
            	consoleContent.setText("");
            	
            }
        });
        control_panel.add(clear);
		
		
		// about button 
        final JButton about = new JButton("About");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	String aboutThis = "[ \n\nWelcome to Brainfuck! \n\n"; 
            	
            	aboutThis += "Brainfuck is an esoteric, minimalist programming language with just 8 commands:";
            	
            	aboutThis += "\n + - < > . , [ ] \nEvery other character is treated as a comment!\n";
            	
            	aboutThis += "\nBrainfuck simulates a Turing Machine, and the commands manipulate it: \n"; 
            	
            	aboutThis += "\"<\" and \">\" move the pointer of the machine to the left and right respectively \n"; 
            	
            	aboutThis += "\"+\" and \"-\" increment and decriment the cell that the pointer is on by 1 \n";
            	
            	aboutThis += "\".\" writes the output of the current cell, and \",\" reads input to it \n"; 
            	
            	aboutThis += "\"[\" jumps forward to after the matching \"]\" if the current cell is 0 \n"; 
            	
            	aboutThis += "\"]\" jumps backward to after the matching \"[\" if the current cell is not 0 \n\n"; 
            	
            	aboutThis += "These last two can be used to create loops, which is really the only way to implement any logic";
            	
            	aboutThis += "\nThey can also be used to comment out any valid commands, as this is right now!\n\n";
            	
            	aboutThis += "Let's see what you can make! Good luck!\n\n]";
            	
            	
            	// remove text
            	editor.setText(aboutThis); 
            	
            }
        });
        control_panel.add(about);
		
		
		
        
        
		// hello world button 
        final JButton hello = new JButton("Hello World");
        hello.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	// remove text
            	editor.setText("[\nHere's the Brainfuck for Hello World:\n]\n\n++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."); 
            	
            }
        });
        control_panel.add(hello);
        
        
        // show items
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	
	
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args)  {
		SwingUtilities.invokeLater(new BFGUI());
	}
}




