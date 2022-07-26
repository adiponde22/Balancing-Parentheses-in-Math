import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main 
{
  public static void main(String[] args) throws FileNotFoundException
  {
  //new object for file
  File myFile = new File("words.txt");
  //new scanner that scans file object
  Scanner fileScan = new Scanner(myFile);
  
  String inputString = "";
  //while loop to scan file next line
  while (fileScan.hasNext())
 {
    inputString = fileScan.nextLine();
    ParenthesisBalancing pB = new ParenthesisBalancing(inputString);
    pB.setStack(); 
  }  
  }
}
//this is where the magic happens 
//class to check for balanced parenthesis
class ParenthesisBalancing
{
  private String origString;
  //constructor
  public ParenthesisBalancing (String p)
  {
    origString = p;
  }
  //sets stack and checks for balanced parenthesis
  public void setStack()
  {
    Stack<String> openBracketStack = new Stack<String>();
    Stack<Integer> indexTrackerStack = new Stack<Integer>();
    
    //for loop to cycle through each character in the string
    for (int i = 0; i < origString.length(); i++)
    {
      char lm = origString.charAt(i);
      String ch = String.valueOf(lm);
      
      
      //test case to set open brackets in stack
      if (ch.equals("(") || ch.equals("[") || ch.equals("{"))
      {
        
        openBracketStack.add(ch);
        indexTrackerStack.add(i+1);


      } 
      //test cases to pop brackets if their closed counterpart is in stack
      else if (ch.equals(")") || ch.equals("]") || ch.equals("}"))
      {
      //if stack is empty at this point that means there was a bracket closed without opening, so break
        if (openBracketStack.isEmpty())
        {
          indexTrackerStack.push(i+1);
          openBracketStack.push(ch);
          break;
        }
        //keeps track of the bracket in stack, because stack cant be compared directly
          String bracket = openBracketStack.peek();
        
        //test cases to only pop if closed bracket has open counterpart at the top of the stack
          if (ch.equals(")"))
          {
            
            if (bracket.equals("("))
            {
              openBracketStack.pop();
              indexTrackerStack.pop();
              
            }

            else 
            {
              break;
            }
          }

        else if (ch.equals("]"))
            {
                if (bracket.equals("["))
              {
                openBracketStack.pop();
                indexTrackerStack.pop();
              }

              else 
              {
                break;
              }
            }

        else if (ch.equals("}"))
            {
                if (bracket.equals("{"))
              {
                openBracketStack.pop();
                indexTrackerStack.pop();
              }

              else 
              {
                break;
              }

            }
      }
  }

//once all test cases are complete, the string is either balanced or unbalanced
//balanced if the stack is empty, since both stacks will be popped 
  if (openBracketStack.isEmpty() && indexTrackerStack.isEmpty())
  {
    System.out.println(" ");
     System.out.println("String is BALANCED: " + origString);
     System.out.println(" ");
  }
//unbalanced if either stack is not empty
  else if (!indexTrackerStack.isEmpty())
    {
      System.out.println(" ");
     System.out.println("String is NOT BALANCED: " + origString);
     System.out.println("CHARACTER: '" + openBracketStack.pop() + "'");
     System.out.println("INDEX: " + indexTrackerStack.peek());
     System.out.println(" ");
    }
//clear the stacks just incase for next line of string
    openBracketStack.clear();
    indexTrackerStack.clear();


}

}