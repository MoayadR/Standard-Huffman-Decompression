import java.io.*;
import java.util.*;

public class Decompress {

    public static void main(String[] args) {
        String s1 = "";
        HashMap<String , Character> table1= new HashMap<String,Character>();

        try {
            File myObj = new File("Compressed.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                s1 = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        char symbol;
        String word="";
        String code = "";
        String Decompressed ="";
        boolean Finish = false;


        for (int i=0;i<s1.length();i++)
        {
            if(s1.charAt(i) == '=')
            {
                symbol = s1.charAt(i-1);
                i++;
                while(s1.charAt(i) != ',')
                {
                    word += s1.charAt(i);
                    i++;
                }
                table1.put(word , symbol);
                word = "";
            }
            if(s1.charAt(i) == ' ')
            {
                for(int j = i+1 ; j<s1.length() ;j++)
                {
                    code += s1.charAt(j);
                    if(table1.containsKey(code))
                    {
                        Decompressed += table1.get(code);
                        code = "";
                    }
                    Finish = true;
                }

                if(Finish == true)
                    break;
            }
        }
        try {
            FileWriter myWriter = new FileWriter("Decompressed.txt");
            myWriter.write(Decompressed);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}