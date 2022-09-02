import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;


public class Word_Breaker {
    public static void main(String[] args) {

        try {
            try (FileReader myFile = new FileReader("D:\\6th Semester\\CC\\Compiler Construction project\\MyProject\\src\\filename.txt")) {
                int ch;
                String temp = "";   
                char[] Operators = {'+', '-', '/', '*', '=' , '>', '<','!', '%'}; //'>=','<=','==','+=','-=', '*=', '!=','++','--'
                
                char op1;
                char[] Punctuators = {'[', ']', '{', '}', '(', ')', ',', '.', ';',':'};
                
                List<String> words =new ArrayList<String>();
                

                while ((ch = myFile.read())!=-1) {
                    // System.out.println((char)ch);
                    
                    temp = temp + (char)ch; 
                    // Float
                    if (ch == 46 && temp.charAt(0) != (char)34) {
                        // System.out.println(temp);
                        if (temp != "") {
                            words.add(temp);
                            
                            words.remove(words.size() - 1);
                            continue;
                        }
                         
                    }
                    // temp = name
                    // Operators
                    for (int i = 0; i < Operators.length; i++) {
                        if ((char)ch == Operators[i]) {
                            temp = temp.replace(Character.toString(Operators[i]), "");
                            
                            op1 = Operators[i];
                            if (op1 == '<' || op1 == '>'|| op1 == '='|| op1 == '+'|| op1 == '-'|| op1 == '*'|| op1 == '/'|| op1 == '!') {
                                if (temp != "") {
                                    
                                    words.add(temp);
                                    temp = "";
                                }
                                
                                continue;
                            }
                            if (temp != "") {
                                words.add(temp);
                                temp = "";
                            }
                            temp = "";
                            continue;
                        }                   
                    }
                    
                    // Punctuators
                    for (int i = 0; i < Punctuators.length; i++) {
                        if ((char)ch == Punctuators[i]) {
                            temp = temp.replace(Character.toString(Punctuators[i]), "");
                            if (temp != "") {
                                words.add(temp);
                                // System.out.println(temp);
                                temp = "";
                            }
                            temp = "";
                            continue;
                        }                    
                    }

                    
                    // String temp = "
                    if ((ch == 34 && (1 == temp.length())) || (ch == 34 && temp.length() > 1)) {
                        // System.out.println(temp);
                        // temp = temp.replace("\"", "");
                        
                        if (temp.length() > 1 && temp.endsWith("\"")) {
                            words.add(temp);
                            temp = "";
                        }
                                                                                        
                        continue;
                    } else if (ch == 34 && temp.charAt(0) == (char)34) {
                        // System.out.println(temp); 
                        temp = temp.replace("\"", "");
                        // System.out.println(temp);
                        if (temp != "") {
                            words.add(temp);
                            temp = "";
                        }
                        temp = "";              

                    }
                    
                    // Space
                    if (ch == 32) {                    
                        // System.out.println(temp);
                        temp = temp.substring(0, temp.length() - 1);
                        if (temp != "") {
                            words.add(temp);
                            temp = "";
                        }
                        temp = "";                    
                        continue;
                    }
                    
                }
                words.add(temp);
                words.remove(null);
                System.out.println(words);
                
                FileWriter output = new FileWriter("D:\\6th Semester\\CC\\Compiler Construction project\\MyProject\\src\\output.txt"); 
                for(String str: words) {
                    output.write(str + System.lineSeparator());
                }
output.close();
            }
        } catch (Exception e) {
           System.out.println("An error Occured");
        } 
    }
}
