import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);
        }

        try {
            File input_file = new File(args[0]);
            Scanner in = new Scanner(input_file);
            File output_file = new File(args[1]);
            PrintWriter out = new PrintWriter(output_file);

            LazyBinarySearchTree binarySearchTree = new LazyBinarySearchTree();

            while (in.hasNext()) 
            {
                boolean validLine = false;
                String line = in.nextLine();
                String lineCopy = new String(line);
                
                if (line.contains("Insert"))
                {
                    try 
                    {
                         // RegEx to extract the number value in line
                        int currentVal = Integer.parseInt(lineCopy.replaceAll("\\D+",""));
                        out.println(binarySearchTree.insert(currentVal));
                    } 
                    catch (Exception e)
                    {
                        out.println ("Error in insert: IllegalArgumentException raised");
                    }
                    validLine = true;
                }
                if (line.contains("Delete"))
                {
                    try 
                    {
                        // RegEx to extract the number value in line
                        int currentVal = Integer.parseInt(lineCopy.replaceAll("\\D+",""));
                        out.println(binarySearchTree.delete(currentVal));   
                    } 
                    catch (Exception e)
                    {
                        out.println ("Error in delete: IllegalArgumentException raised");
                    }
                    validLine = true;
                }
                if (line.contains("FindMin"))
                {
                    out.println(binarySearchTree.findMin());
                    validLine = true;
                }
                if (line.contains("FindMax"))
                {
                    out.println(binarySearchTree.findMax());
                    validLine = true;
                }
                if (line.contains("Contains"))
                {
                    try 
                    {
                        // RegEx to extract the number value in line
                        int currentVal = Integer.parseInt(lineCopy.replaceAll("\\D+",""));
                        out.println(binarySearchTree.contains(currentVal));   
                    } 
                    catch (Exception e)
                    {
                        out.println ("Error in contains: IllegalArgumentException raised");
                    }
                    validLine = true;
                }
                if (line.contains("Height"))
                {
                    out.println(binarySearchTree.height());
                    validLine = true;
                }
                if (line.contains("Size"))
                {
                    out.println(binarySearchTree.size());
                    validLine = true;
                }
                if (line.contains("PrintTree"))
                {
                    out.println(binarySearchTree.toString());
                    validLine = true;
                }

                if (!validLine)
                {
                    out.println ("Error in Line: " + line);
                }
            }

            in.close();
            out.close();
        } 
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

    }
}