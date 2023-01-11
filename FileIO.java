import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class FileIO {

    public static void main(String[] agrs) {
        System.out.println("Welcome to the Java File IO program!");
        System.out.println("This program will take your input, write it to a file" + 
        " ,then print out the contents of that same file to your screen.");
        System.out.println("Please follow the insctructions that appear on your screen." + '\n');
        
        // ------
        // writing user input to a file
        // ------
    
        Scanner input = new Scanner(System.in);
        String fname = null; // the file name

        // get the file name from the user
        System.out.println("Please enter the file name: ");
        fname = input.nextLine(); 
        File file = new File(fname);
        
        // actual value null is not used because of the NullPointerException 
        String decision = "null";
        
        // if the file already exists, ask the user if they want to delete the contents of the file
        // of if they want to add to it
        if (file.exists()) { 
                System.out.println("The file " + fname + " already exists.");
                System.out.println("Would you like to overwrite the contents or add to the file?");
                System.out.println("Enter 'O' for overwrite, 'A' for add.");
                // hold the user's decision
                decision = input.nextLine();
        }
 
        // making the variable output not local to try block
        PrintWriter output = null; 

        // if the user wants to delete the contents of the file 
        // OR if the file does not already exist
        if (decision.equals("O") || decision.equals("o") || decision.equals("null")) { // both lower and uppercase o are accounted for
            try {
            output = new PrintWriter(new FileOutputStream(file)); // if test.txt exists, all its data will be overwritten
            }
            // file with specific path does not exist - inform the user of the exception
            catch (FileNotFoundException fnf) {
                System.out.println("*** ERROR - FILE NOT FOUND ***");
                System.exit(0); // exit the program
            }
            // broad "catch all" exception to be safe
            catch (IOException ioe) {
                System.out.println("*** ERROR - IO EXCEPTION");
                System.exit(0); // exit the program
            }
        }
        // if the user wants to append to the file
        else if (decision.equals("A") || decision.equals("a")) { // both cases are accounted for 
            try {
                output = new PrintWriter(new FileOutputStream(file, true)); // contents will NOT be overwritten because of true
                }
                // file with specific path does not exist - inform the user of the exception
                catch (FileNotFoundException fnf) {
                    System.out.println("*** ERROR - FILE NOT FOUND ***");
                    System.exit(0); // exit the program
                }
                // broad "catch all" exception to be safe
                catch (IOException ioe) {
                    System.out.println("*** IO Error Thrown. ***");
                    System.exit(0); // exit the program
                }
            }
            // incase the user enters anything other than O or A
            else {
                System.out.println("Error - input not correct. Please restart.");
                System.exit(0); // exit the program
            }

            // inform the user that nextLine() reads input until '\n' (the escaping sequence)
            System.out.println("NOTE: This program separates sentences using the enter key. Separate words using spaces, and sentences using the enter key.");
            // ask the user how many sentences/words they will enter
            System.out.println("Please enter the number of sentences that will be inputted: ");
            // create an array that will hold the number of desired entries
            int numOfSentences = input.nextInt();
            String[] words = new String[numOfSentences];
            // prompt the user to begin inputting
            System.out.println("Please enter the sentences you would like to write to the file, separating them using the enter key.");
            // remind them that the only numbers of entries they specified will be received.
            System.out.println("NOTE: Any input over the max number of entries will not be written to the file.");
            
            // get the first entry
            String word = input.nextLine(); // omitting this line results in the first entry being ""
            // get entries until the array is full
            for(int count = 0; count < words.length; count++) {
                word = input.nextLine();
                words[count] = word; // add the sentence/word to the array
            }
            // print the contents of the array to the file       
            for(int i = 0; i < words.length; i++) {
                output.println(words[i]); // println inserts '\n' between each element
            }
            // MUST close the output
            output.close();
            input.close();

        // -----
        // printing contents of the file 
        // NOTE - this program prints the contents of the same file
        // that was written to (the file that the user supplied)
        // -----
        
        // create a scanner object that will collect contents from the file
        Scanner input2 = null; 

        // NOTE - try/catch block is used again for the same file incase
        // problems occur after manipulating the file above
        try {
        input2 = new Scanner(new FileInputStream(file));
        }
        // file with specific path does not exist - inform the user of the exception
        catch(FileNotFoundException fnf) {
            System.out.println("*** ERROR - The file could not be found. ***");
            System.exit(0); // exit the program
        }
        // broad "catch all" exception to be safe
        catch (IOException ioe) {
            System.out.println("*** ERROR - IO EXCEPTION. ***");
            System.exit(0); // exit the program
        }
        
        // let the user know what is being printed to the screen
        System.out.println("Contents of file " + fname + ":");
        
        // a try block is used here incase the contents of the file cannot be read
        try {
            while (input2.hasNextLine()) { // while there are lines of text in the file
                String line = input2.nextLine();
                System.out.println(line); // print the lines to the screen, NOT TO A FILE
            }
        }
        // InputMisMatchException - Thrown by a Scanner to indicate that the token retrieved does not match the 
        //  pattern for the expected type, or that the token is out of range for the expected type.
        // - docs.oracle.com
        catch(InputMismatchException ime) { 
            System.out.println("InputMismatchException thrown. Please inspect the file and retry.");
        }
        input2.close();
    }
}