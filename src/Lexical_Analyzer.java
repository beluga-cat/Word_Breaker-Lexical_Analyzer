import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexical_Analyzer {
     public static void main(String[] args) {
        try {
            FileInputStream wordBreakerFile = new FileInputStream("D:\\6th Semester\\CC\\Compiler Construction project\\MyProject\\src\\output.txt");
            DataInputStream in = new DataInputStream(wordBreakerFile);            
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine; 
            String temp = "";  
            
            String[][] KeyWords = {{"int","double","String", "bool", "if","else", "elseif","for","while","switch","case","class","child_of","present","super","fixed", "new","interface","constructor","shared","own"},
                                   {"INT_DT","DOUBLE_DT","STRING_DT","BOOL_DT","COND_ST","COND_ST","COND_ST","ITER_ST","ITER_ST","ITER_ST","ITER_ST","class","child_of","present","super","fixed", "new","interface","constructor","shared","own"}};
            String[][] Operators = {{"+", "-", "/", "*", "=", ">", "<", "!", "%", ">=","<=","==","+=","-=", "*=", "!=","++","--"},
                                    {"PM","PM","DM","DM","=","REL_OPERATOR","REL_OPERATOR","!","REMAINDER","CRO","CRO","CO","CO","CO","CO","CO","CO","CO"}};
            String[] Punctuators = {"[", "]", "{", "}", "(", ")", ",", ".", ";","[]","{}","()"};
            // System.out.println("("+KeyWords[0][2] + ","+ KeyWords[1][2]+ ","+"Line #01"+ ")");
            List<String> Tokens =new ArrayList<String>();
            List<String> words =new ArrayList<String>();
            while ((strLine = br.readLine()) != null) {
                temp = temp + strLine;
                // System.out.print((char)ch);
                // System.out.println (strLine);
                words.add(temp);
                temp = "";
            }
            // System.out.println(words);
            br.close();
            boolean retVal;
            // System.out.println(Tokens);
            System.out.println(words);
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            boolean flag5 = false;
            boolean flag6 = false;
            boolean flag7 = false;
            String str = "";
            
            for (int j =0; j<words.size(); j++) {
                // System.out.print(str);
                boolean flag = false;

                str = "";
                str = str + words.get(j); // str = int
                // System.out.println(str);
                // KWs
                for (int i = 0; i<21; i++) {
                    // System.out.println(KeyWords[0][i] + str);
                    retVal = KeyWords[0][i].equals(str); // int.equals(int) true
                    // System.out.println(KeyWords[0][i] + str +retVal);
                    // System.out.println(Tokens);
                    if (retVal == true) {

                        Tokens.add("("+ KeyWords[1][i] + ","+ str+ ","+"Line # "+ (j+1) + ")");
                        // System.out.println(Tokens);
                        flag = true;
                        flag1 = true;
                        break;
                    }
                }
                // isOperator
                for (int index = 0; index < 18; index++) {
                    retVal = Operators[0][index].equals(str);
                    // System.out.println(Operators[0][index] + str +retVal);
                    // System.out.println(Tokens);
                    if (retVal == true) {

                        Tokens.add("("+ Operators[1][index] + ","+ str+ ","+"Line # "+(j+1)+ ")");
                        // System.out.println(Tokens);
                        flag = true;
                        flag2 = true;
                        break;
                    }
                }
                // isPunctuator
                for (int p = 0; p < 12; p++) {
                    retVal = Punctuators[p].equals(str);
                    // System.out.println(Operators[0][index] + str +retVal);
                    // System.out.println(Tokens);
                    if (retVal == true) {

                        Tokens.add("("+ Punctuators[p] + ","+ Punctuators[p]+ ","+"Line # "+(j+1)+ ")");
                        // System.out.println(Tokens);
                        flag = true;
                        flag3 = true;
                        break;
                    }
                }
                // isChar
                if (str.startsWith("'") && str.endsWith("'")) {
                    Tokens.add("("+ "Char_Const" + ","+ str + ","+"Line # "+(j+1)+ ")");
                    flag = true;
                    flag4 = true;
                    continue;
                }
                // isString
                if (str.startsWith("\"") && str.endsWith("\"")) {
                    Tokens.add("("+ "String_Const" + ","+ str + ","+"Line # "+(j+1)+ ")");
                    flag = true;
                    flag5 = true;
                    continue;
                }
                // isInt
                String regex = "[+-]?[0-9]+";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(str);
                if (m.find() && m.group().equals(str)){
                    Tokens.add("("+ "Int_Const" + ","+ str + ","+"Line # "+(j+1)+ ")");
                    flag = true;
                    flag6 = true;
                    continue;
                }

                // isFloat
                String regex1 = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
                Pattern p1 = Pattern.compile(regex1);
                Matcher m1 = p1.matcher(str);
                if(m1.find() && m1.group().equals(str)){
                    Tokens.add("("+ "Float_Const" + ","+ str + ","+"Line # "+(j+1)+ ")");
                    flag = true;
                    flag7 = true;
                    continue;
                }


                // isID
                if (flag1 !=  true && flag5 != true) {
                    String regex2 = "\\b_*[a-zA-Z][_a-zA-Z0-9]*\\b";
                    Pattern p2 = Pattern.compile(regex2);
                    Matcher m2 = p2.matcher(str);
                    if(m2.find() && m2.group().equals(str)){
                        Tokens.add("("+ "ID" + ","+ str + ","+"Line # "+(j+1)+ ")");
                        flag = true;
                        continue;
                }
                }


                if (flag == false) {
                    Tokens.add("("+ "Invalid Lexeme" + ","+ str + ","+"Line # " +(j+1)+ ")");
                }

                

                
                // System.out.println(Tokens);
                
            }
            
            System.out.println(Tokens);
            FileWriter output1 = new FileWriter("D:\\6th Semester\\CC\\Compiler Construction project\\MyProject\\src\\LAoutput.txt"); 
                output1.write("(CP,VP,L#)"+ System.lineSeparator());
                for(String str1: Tokens) {
                    output1.write(str1+ System.lineSeparator());
                    // System.out.println(str1);
                }
            output1.close();
            
                    
        } catch (Exception e) {
            System.out.println(e.getStackTrace()[0].getLineNumber());
            
        }
     }
}
