import java.util.Map;
import java.util.TreeMap;

public class Interpreter {
	public static String interpret(String commands, String input) {
		String output = ""; 
		
		
		
		// pointers for the tape and commands
		int dataPointer = 0; // represents the index of the tape that the pointer is on
		int commandPointer = 0; // represents the index of the current command
		int inputPointer = 0; 
				
				
		// represents an infinite, initially empty tape 
		Map<Integer, Byte> tape = new TreeMap<Integer, Byte>(); 
				
		while (commandPointer < commands.length()) {

					
					
					
					
			char thisCommand = commands.charAt(commandPointer); 
					
					
					
			// if the tape doesn't have a value for the current index, put 0
			if (!tape.containsKey(dataPointer)) {
				byte init = 0; 
				tape.put(dataPointer, init); 
			}
					
					
			// if command is '>', move data pointer to the right
			if (thisCommand == '>') {
				dataPointer++; 
				commandPointer++; 
			} 
					
			// if command is '<', move data pointer to the left
			else if (thisCommand == '<') {
				dataPointer--; 
				commandPointer++; 
			} 
					
			// if command is '+', increment cell at pointer
			else if (thisCommand == '+') {
				tape.put(dataPointer, (byte) (tape.get(dataPointer) + 1)); 
				commandPointer++; 
			} 
					
			// if command is '-', decrement cell at pointer
			else if (thisCommand == '-') {
				tape.put(dataPointer, (byte) (tape.get(dataPointer) - 1));
				commandPointer++; 
			} 
					
			// if command is '.', print contents of current cell
			else if (thisCommand == '.') { 
				output += (char) tape.get(dataPointer).intValue(); 
				commandPointer++; 
			} 
					
			// if command is ',', put keyboard input into current cell
			else if (thisCommand == ',') { 
				if (inputPointer < input.length()) {
					tape.put(dataPointer, (byte) (input.charAt(inputPointer)));
				} else {
					continue; 
				}
				commandPointer++; 
			} 
					
					
					
			// if command is '[', move command pointer forward to matching ']'
			else if (thisCommand == '[') { 
				if (tape.get(dataPointer) == 0) {
					
					int numFound = 0; // number of other '[' found between, means must skip one ']'
							
					commandPointer++; // always move one to the right to begin
							
					while (numFound != 0 || commands.charAt(commandPointer) != ']') {
						if (commands.charAt(commandPointer) == '[') {
							numFound++; 
						} else if (commands.charAt(commandPointer) == ']') {
							numFound--; 
						}
						commandPointer++; 
						if (commandPointer >= commands.length()) {
							output = "Error: Invalid Loop";
							return output; 
						}
					}
				} 
				commandPointer++; // we want the next cell after the ']'
			} 
					
			// if command is ']', move command pointer back to matching '['
			else if (thisCommand == ']') { 
				if (tape.get(dataPointer) != 0) {
							
					int numFound = 0; // number of other ']' found between, means must skip one '['
							
					commandPointer--; // always move one to the left to begin
							
					while (numFound != 0 || commands.charAt(commandPointer) != '[') {
								
						if (commands.charAt(commandPointer) == ']') {
									numFound++; 
						} else if (commands.charAt(commandPointer) == '[') {
							numFound--; 
						}
						commandPointer--; 
						if (commandPointer < 0) {
							output = "Error: Invalid Loop";
							return output; 
						}
					}
				} 
						
				commandPointer++; // we want the next cell after the '['
						
			}
					
			else {
				commandPointer++; 
			}
					
					
					
					
					
		}
		
		
		
		
		
		
		
		return output; 
	}
}
