/*
   @author Safal Adhikari
   @class IT 219
   @date 03/01/2020
   @file: HangMan.java
   @This program will read a random word from a text file,
    prompt the user for a letter until the user has guessed all the letters
    in the word. It makes an ASCII art hangman game, where each piece of the 
    hangman gets displayed in the console window.
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 

public class HangMan
{   
   //Source File
   static final String DICTIONARYFILE = "usa.txt";
   
   static String blank = "-";
   
   public static void main(String[] args)
   throws FileNotFoundException
   {
      String randomWord = getWord();     
      
      int numberOfLives = 7;
      
      StringBuilder wordWithBlanks = createWordWithBlanks(randomWord);
      
      //Keeps prompting user until all the letters have been guessed,
      //or the user has missed 7 times. 
      while(hasMoreBlanks(wordWithBlanks) == true && numberOfLives !=0)
      {
         System.out.println("_____________________________________");
         System.out.println("Lives left:" + numberOfLives);
         
         printHangman(numberOfLives);
         System.out.println("Word: " + wordWithBlanks);
         
         String letter = getGuess();
         
         if(hasLetter(randomWord, letter))
         {
            replaceBlank(randomWord, wordWithBlanks, letter);
         }
         else
         {
            numberOfLives--;
         }
      }
      
      printHangman(numberOfLives);
      showResult(randomWord, numberOfLives);
      
   }
   
   //Opens the file and returns a random word from the file.
   public static String getWord()
   throws FileNotFoundException   
   {
      File myFile = new File(DICTIONARYFILE);
      
      Scanner file = new Scanner(myFile);
      
      String line = "";
      int count = 1;
      
      String randomWord = "";
      
      Random randomInt = new Random();
    
      int randomNum = randomInt.nextInt(61338); 
      
      while(file.hasNext())
      {                      
         count++;
         line = file.nextLine();
         
         if(count == randomNum)
         {
            randomWord = line;   
         }                                         
      }     
      return randomWord;             
   }
   
   //Prompts the user for a letter and returns it.
   public static String getGuess()
   {
      Scanner keyboard = new Scanner(System.in);
           
      System.out.print("Guess your next letter: ");
      String inputLetter = keyboard.nextLine();
      
      return inputLetter;
   }
   
   //Builds a String with blanks "-", same length as selected random word
   public static StringBuilder createWordWithBlanks(String tempRandomWord)
   {
      StringBuilder wordWithBlanks = new StringBuilder();
      
      for(int i=0; i<tempRandomWord.length(); i++)
      {
         wordWithBlanks.append(blank);
      }
      
      return wordWithBlanks;    
   }
   
   //Checks if there are any blanks on the word that is being guessed.
   public static boolean hasMoreBlanks(StringBuilder tempwordWithBlanks)
   {
      int blankIndex = tempwordWithBlanks.indexOf(blank);
      if(blankIndex >=0)
      {
         return true;
      }
      return false;
   }
   
   //Checks if the random selected word has the user input letter.
   public static boolean hasLetter(String tempRandomWord, String tempLetter)
   {
      String upperCaseWord = tempRandomWord.toUpperCase();
      String upperCaseLetter = tempLetter.toUpperCase();
      
      if(upperCaseWord.indexOf(upperCaseLetter) >=0)
      {
         return true;
      }
      return false;
   }
   
   //Replaces the blanks "-" on selected random word with the user input letter, 
   //only if the random word contains that letter.
   public static void replaceBlank(String tempRandomWord, StringBuilder tempWordWithBlanks,
                                   String tempLetter)
   {
      String upperCaseWord = tempRandomWord.toUpperCase();
      String upperCaseLetter = tempLetter.toUpperCase();
      
      int letterIndex = upperCaseWord.indexOf(upperCaseLetter);
      
      while(letterIndex >= 0)
      {
         tempWordWithBlanks.replace(letterIndex, letterIndex + 1, upperCaseLetter);
         letterIndex = upperCaseWord.indexOf(upperCaseLetter, letterIndex + 1);
      }
   }
   
   //Displays whether the user won or lost, and the word that was selected.
   public static void showResult(String tempRandomWord, int tempNumberOfLives)
   {
      if(tempNumberOfLives > 0)
      {
         System.out.println("Good guessing! You won! The word is: " + tempRandomWord);
      }
      else
      {
         System.out.println("You lose! The word is: " + tempRandomWord);
      }
   }
   
   //Print ASCII Art to show the hangman,when the user makes an incorrect guess
   public static void printHangman(int tempNumberOfLives)
   {
      if(tempNumberOfLives == 7)
      {
         System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("___|___");
			System.out.println();   
      }
      else if(tempNumberOfLives == 6)
      {
         System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
      }
      else if(tempNumberOfLives == 5)
      {
         System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
      }
      else if(tempNumberOfLives == 4)
      {
         System.out.println("   ____________");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("___|___");
      }
      else if(tempNumberOfLives == 3)
      {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");  
      }
      else if(tempNumberOfLives == 2)
      {
         System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
      }
      else if(tempNumberOfLives == 1)
      {
         System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
      else if(tempNumberOfLives == 0)
      {
         System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |          _|_");
			System.out.println("   |         / | \\");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
      }  
   }                                        
}