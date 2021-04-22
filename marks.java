/*
 * File:     marks.java
 * Author:   Eric Lavoie   100122593
 * Date:     2017/01/18
 * Version:  17.0
 *
 * Purpose:
 *  Calculates a grades from a text file named A1P1.txt and outputs the 
 * students name, final grade and their letter grade.
 * 
 * note* the grades not calculate the same as proffesor dimonds grade
 * scheeme i marked where the problem is
 */
   import java.io.*;

   class marks
   { 
     //Creates a replace all letters method
     public static String replaceAll(String string) {
       return string.replaceAll("\\d+.*", "");
     }
     
     public static void main(String args[])
     {
       
       try{

         //reads for texfile 
         FileInputStream fstream = new FileInputStream("C:\Users\eric1\OneDrive\Desktop\A1P1.txt");
         DataInputStream in = new DataInputStream(fstream);
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
         
         //initializes global values   
         String strLine;
         String number;
         float b;
         float m;
         float f;   
         float a1; 
         float a2; 
         float a3;
         float a4;
         float a5; 
         float a6; 
         float a7;  
         
         //Reads string Line by Line 
         while ((strLine = br.readLine()) != null){
           
           // initializes values that need to be reset each line of text
           boolean op1=false;
           boolean op2 = false;
           float bMark=0;
           float mMark=0;
           float fMark=0;
           float sum =0;
           double fgrade =0; 
           int i = 0;
           int mg= 0;   
           int cheat=0;       
           int penalty=0;
           
           // formats the string into only digits seperated by spaces
           String name= strLine.replace("|", "");
           System.out.print(replaceAll(name));    
           strLine = strLine.replaceAll("[^0-9.-]"," ");
           number = strLine.replaceFirst("[.]","");
           String strClean = number.replaceAll("^\\s+", "");
           
           //splits the string into temperary value every space
           String[] tokens = strClean.split(" ");
           
           //creats an array for each temparary value
           int[] ary = new int[tokens.length];
           
           //create a temperary values from the string and loops through values
           for (String token : tokens){
             // creates and array
             ary[i++] = Integer.parseInt(token);
             int total = 0 + Integer.parseInt(token);
             
             
             // rounds decimals and excludes cheat marks
             if(token.length()>=3&&!token.equals("100")&&!token.equals("-60")){
               token = token.replace(token.substring(token.length()-2), "");
             }
             
             //final exam mark
             if (i==1){
               f=0;
               f= total;
               fMark+=f;
             }
             
             //midterm mark
             if (i==2){
               m=0;
               m= total;
               mMark+=m;
             }
             
             // bonus marks
             if (i==3){
               b=0;
               b+=total;
               bMark+=b;
               
             }
             
             // amount of times caught cheeating
             if (i==4){
               if (total>0) {
                 
                 
                 cheat+= total;
                 
               }
               
               
             }
             // diferent options for grading in the third array
             //and adds max assignment grade
             if (i==5){
               int r= 0;
               
               
               
               if (total ==1){ 
              mg = 140+(r*20);
              
              op1= true;
              
               }
               if (total ==2){ 
                 mg = 140+(r*40);
                 
                 op2= true;
               }
               
               
               if (total ==3){ 
                 mg = 140+(r*60);
               }
               
               //rest of the array tallies assignment grade
             }
             if (i==6){
               a1=0;
               a1=total;
               sum += a1;
             }
             
             
             if (i==7){
               a2=0;
               a2=total;
               sum += a2;
             }
             
             
             if (i==8){    
               a3=0;
               a3=total;
               sum += a3;
             } 
             
             if (i==9){
               a4=0;
               a4=total;
               sum += a4;
             } 
             if (i==10){
               a5=0;
               a5=total;
               sum += a5;
             } 
             if (i==11){
               a6=0;
               a6=total; 
               sum += a6;
             } 
             if (i==12){
               
               a7=0;
               a7=total;
               sum += a7;
             } 
             
             //sets penalty to -60*each time cheated
             if (cheat>=1){
               penalty= (cheat *-60); 
             }
             
             // calculates assignment grade
             double aMark = ( 100% (sum+penalty/(mg - 60)));

             // calculates the final grade 
             fgrade = 0.5 * fMark + 0.1 * mMark+ 0.1 * aMark + bMark;
             /*  problme here ^
              * 
              * the final grade should also include this part of the grading
              * scheme
              *
              * F = 0.5 * E + 0.1 * M + 0.1 * A + B
              if (E < 40){ 
              F += 0.3 * E
              }
              else{
              if (E > 50)
              F += 0.2 * max(E, M) + 0.1 * max(E, A)
              else{
              F += 0.02 * ((E - 40) * max(E, M) + (50 - E) * E)
              + 0.01 * ((E - 40) * max(E, A) + (50 - E) * E)
              }
              if (M < 40){
              F = min(F, M)
              }
              if (A < 50){
              F = min(F, A)
              F = min(F, U)
              }
              */
             
             //  sets grade cap for the first two options
             if (op1==true&&fgrade>69){
               
               fgrade = 69;
             }
             if (op2==true&&fgrade>79){
               
               fgrade = 79;
             }
             
           }
           // if the grade is greater than 100% set it to 100% and out put 
           //final grade
           if (fgrade <= 100 ) {
             
             System.out.print("\t\t"+(int)fgrade+"  ");
           }
           else{
             fgrade= 100;
             System.out.print("\t\t"+(int)fgrade+"  ");
             
           }
           
           // evaluates the finall grade to a letter grade
           if (fgrade <= 101 && fgrade >= 94) {
             System.out.println("A+");
           }
           else if (fgrade <= 94 && fgrade >= 87) {
             System.out.println("A");
           }
           else if (fgrade <= 87 && fgrade >= 80) {
             System.out.println("A-");
           }
           else if (fgrade <= 80 && fgrade >= 77) {
             System.out.println("B+");
           }
           else if (fgrade <= 77 && fgrade >= 73) {
             System.out.println("B");
           }
           else if (fgrade <= 73 && fgrade >= 70) {
             System.out.println("B-");
           }
           else if (fgrade <= 70 && fgrade >= 67) {
             System.out.println("C+");
           }
           else if (fgrade <= 67 && fgrade >= 63) {
             System.out.println("C");
           }
           else if (fgrade <= 63 && fgrade >= 60) {
             System.out.println("C-");
           }
           // assuming D is still a fail since this is a core course
           else if (fgrade <= 60 && fgrade >= 0) {
             System.out.println("F");  
           }
         }   
         //Close the input stream
         in.close();
       }
       catch (Exception e){//Catches execptions
         System.err.println("Error: " + e.getMessage());
       }
     }
   }