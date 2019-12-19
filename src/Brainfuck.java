import java.util.*;

import java.io.*;


public class Brainfuck {

	
	public static void main(String[] args) throws Exception {
		
		
		// create a reader, BufferedReader, and scanner
		Reader rdr = null; 
		BufferedReader reader = null; 
		Scanner scanner = new Scanner(System.in); 
		try {
			rdr = new FileReader("brainfuck.txt");
			reader = new BufferedReader(rdr); 
		} catch (FileNotFoundException e) {
			System.out.println("File not Found"); 
		} 
		
		
		
		
		
		
		String commands = ""; // holds all commands, necessary for looping
		
		// load program into commands string
		while (reader.ready()) {
			commands += reader.readLine(); 
		}
		rdr.close();
		reader.close(); 
		

		// pointers for the tape and commands
		int dataPointer = 0; // represents the index of the tape that the pointer is on
		int commandPointer = 0; // represents the index of the current command
		
		
		// represents an infinite, initially empty tape 
		Map<Integer, Byte> tape = new TreeMap<Integer, Byte>(); 
		
		while (commandPointer < commands.length()) {

			
			
			
			
			char input = commands.charAt(commandPointer); 
			
			
			
			// if the tape doesn't have a value for the current index, put 0
			if (!tape.containsKey(dataPointer)) {
				byte init = 0; 
				tape.put(dataPointer, init); 
			}
			
			
			// if command is '>', move data pointer to the right
			if (input == '>') {
				dataPointer++; 
				commandPointer++; 
			} 
			
			// if command is '<', move data pointer to the left
			else if (input == '<') {
				dataPointer--; 
				commandPointer++; 
			} 
			
			// if command is '+', increment cell at pointer
			else if (input == '+') {
				tape.put(dataPointer, (byte) (tape.get(dataPointer) + 1)); 
				commandPointer++; 
			} 
			
			// if command is '-', decrement cell at pointer
			else if (input == '-') {
				tape.put(dataPointer, (byte) (tape.get(dataPointer) - 1));
				commandPointer++; 
			} 
			
			// if command is '.', print contents of current cell
			else if (input == '.') { 
				System.out.print((char) (tape.get(dataPointer).intValue())); 
				commandPointer++; 
			} 
			
			// if command is ',', put keyboard input into current cell
			else if (input == ',') { // ,
				if (scanner.hasNext()) {
					tape.put(dataPointer, (byte) scanner.nextByte());
				} 
				commandPointer++; 
			} 
			
			
			
			// if command is '[', move command pointer forward to matching ']'
			else if (input == '[') { // [
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
							throw new Exception("Invalid loop"); 
						}
					}
				} 
				commandPointer++; // we want the next cell after the ']'
			} 
			
			// if command is ']', move command pointer back to matching '['
			else if (input == ']') { // ]
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
							throw new Exception("Invalid loop"); 
						}
					}
				} 
				
				commandPointer++; // we want the next cell after the '['
				
			}
			
			else {
				commandPointer++; 
			}
			
			
			
			
			
		}
		
		scanner.close();
		
	}
}




