import java.io.*;

public class WesternCipher {
	private CircularArrayQueue<Character> encodingQueue;
	private CircularArrayQueue<Character> decodingQueue;
	
	public WesternCipher() {
		encodingQueue = new CircularArrayQueue<Character>(10);
		decodingQueue = new CircularArrayQueue<Character>(10);
	}
	
	public WesternCipher(int size) {
		encodingQueue = new CircularArrayQueue<>(size);
		decodingQueue = new CircularArrayQueue<>(size);
	}
	
	public String encode(String input) {
	
		for (int i = 0; i < input.length(); i++) {
			this.encodingQueue.enqueue(input.charAt(i));
		}

		Character currentChar;
		Character encodedChar = ' ';
		String encoded = "";
		boolean prevVowel = false;
		Integer numEncoded = 0;

		while (!this.encodingQueue.isEmpty()) {
			// Get the current character
			currentChar = this.encodingQueue.dequeue();
			
			if (currentChar==' ') {
				encodedChar = ' ';
			}
			else {
			
				// Case when the current character is a vowel
				if (isVowel(currentChar)) {
					// Case when the previous character was a vowel
					if (prevVowel) {
						// Convert with second table in assignment instructions
						switch(currentChar) {
							case 'A':
								encodedChar = '3';
								break;
							case 'E':
								encodedChar = '4';
								break;
							case 'I':
								encodedChar = '5';
								break;
							case 'O':
								encodedChar = '6';
								break;
							case 'U':
								encodedChar = '1';
								break;
							case 'Y':
								encodedChar = '2';
								break;
						}
	
					// Case when the previous character was not a vowel
					} else {
						// Convert with first table in assignment instructions
						switch(currentChar) {
							case 'A':
								encodedChar = '1';
								break;
							case 'E':
								encodedChar = '2';
								break;
							case 'I':
								encodedChar = '3';
								break;
							case 'O':
								encodedChar = '4';
								break;
							case 'U':
								encodedChar = '5';
								break;
							case 'Y':
								encodedChar = '6';
								break;
						}
					}
					// Tell the code that the previous character was a vowel
					prevVowel = true;
				
				// Case when the current character is not a vowel
				} else {
					char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
					// Case when the previous character was a vowel
					if (prevVowel) {
						Integer prevEncoded = numtoNum(encodedChar);
						// Get the alphabetical position of the character to encode to
						Integer alphabetPos = (chtoNum(currentChar) + 5 + 2*(encoded.length()) - 2*prevEncoded) % 26;
						
						encodedChar = alphabet[alphabetPos-1];
						
	
					// Case when the previous character was not a vowel
					} else {
						// Get the alphabetical position of the character to encode to
						Integer alphabetPos = (chtoNum(currentChar) + 5 + 2*(encoded.length())) % 26;
	
						// Convert the alphabetical position to the corresponding letter
						encodedChar = alphabet[alphabetPos-1];
						
					}
					// Tell the code that the previous character was not a vowel
					prevVowel = false;
				}

			}
			// Append the encoded character to the encoded string
			encoded += encodedChar;
			char prevChar = currentChar;
		}
		
		return encoded;
	}
	
	public String decode(String encoded) {
		
		for (int i = 0; i < input.length(); i++) {
			this.encodingQueue.enqueue(input.charAt(i));
		}

		Character currentChar;
		Character encodedChar = ' ';
		String encoded = "";
		boolean prevVowel = false;
		Integer numEncoded = 0;
	
		while (!this.encodingQueue.isEmpty()) {
			// Get the current character
			currentChar = this.encodingQueue.dequeue();
			
			if (currentChar==' ') {
				encodedChar = ' ';
			}
			else {
			
				// Case when the current character is a vowel
				if (isVowel(currentChar)) {
					// Case when the previous character was a vowel
					if (prevVowel) {
						// Convert with second table in assignment instructions
						switch(currentChar) {
							case '3':
								encodedChar = 'A';
								break;
							case '4':
								encodedChar = 'E';
								break;
							case '5':
								encodedChar = 'I';
								break;
							case '6':
								encodedChar = 'O';
								break;
							case '1':
								encodedChar = 'U';
								break;
							case '2':
								encodedChar = 'Y';
								break;
						}
	
					// Case when the previous character was not a vowel
					} else {
						// Convert with first table in assignment instructions
						switch(currentChar) {
							case '1':
								encodedChar = 'A';
								break;
							case '2':
								encodedChar = 'E';
								break;
							case '3':
								encodedChar = 'I';
								break;
							case '4':
								encodedChar = 'O';
								break;
							case '5':
								encodedChar = 'U';
								break;
							case '6':
								encodedChar = 'Y';
								break;
						}
					}
					// Tell the code that the previous character was a vowel
					prevVowel = true;
				}
			}
			// Case when the current character is not a vowel
		  else {
			char [] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			// Case when the previous character was a vowel
			if (prevVowel) {
				Integer prevEncoded = numtoNum(encodedChar);
				// Get the alphabetical position of the character to encode to
				Integer alphabetPos = (chtoNum(currentChar) + 5 + 2*(encoded.length()) - 2*prevEncoded) % 26;
				
				encodedChar = alphabet[alphabetPos-1];
				

			// Case when the previous character was not a vowel
				} else {
				// Get the alphabetical position of the character to encode to
					Integer alphabetPos = (chtoNum(currentChar) + 5 + 2*(encoded.length())) % 26;

				// Convert the alphabetical position to the corresponding letter
					encodedChar = alphabet[alphabetPos-1];
				
			}
			// Tell the code that the previous character was not a vowel
				prevVowel = false;
		}

	
	// Append the encoded character to the encoded string
		  encoded += encodedChar;
		char prevChar = currentChar;


		return encoded;
		}
	}
					
	// Helper function that checks whether a character is a vowel
	private boolean isVowel(Character input) {
		Character[] vowels = new Character[]{'A', 'E', 'I', 'O', 'U', 'Y'};
		for (char c : vowels) {
			if (input == c) {
				return true;
			}
		}
		return false;
	}

	// Helper function that gets the position of a character in the alphabet
	private Integer chtoNum(Character input) {
		return ((int)input - 64);
	}
	
	private Integer numtoNum(char input) {
		return ((int)input - 48);
	}
}