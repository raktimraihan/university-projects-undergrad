import java.util.*;

import javax.swing.GroupLayout.Alignment;

import java.io.*;
import java.nio.file.StandardCopyOption;
import java.lang.*;
public class main {
	public static String[][] input_read_and_return_as_Array(File fp) throws IOException{
		try{
			Scanner take= new Scanner(fp);
			
			
			ArrayList<String> arrList = new ArrayList<String>();
			while(take.hasNextLine()){
				arrList.add(take.nextLine());
				//System.out.println(arrList);
			}
			//System.out.println("Size= "+arrList.size());
			String[] arr= arrList.toArray(new String [arrList.size()]);
			for(String E: arr){
				//System.out.println("ARRAY = "+E);
			}
			
			//System.out.println("Size of array= "+arr[0].length());
			String[] temp2= arr[0].split(" ");
			
			String[][] arr2D = new String [arr.length][temp2.length];
			for(int i=0;i<arr2D.length;i++){
				String[] temp = arr[i].split(" ");	
				for(int j=0;j<arr2D[0].length;j++){			
					arr2D[i][j]= temp[j];
					//System.out.println("ARR 2D["+i+"] ["+j+"] "+arr2D[i][j]);
				}
			}
			return arr2D;
		}
		catch(IOException o){
			throw new IOException(o);
		}
	}
	
	
	public static String DectoBinary(int num){
		if(num==0){
			return "00000000000000000000000000000000".toString();
		}
		
		else if(num<0) return Integer.toBinaryString(num);
		else {
			String str= String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0');
			return str;
		}
		
	}

	public static String hexadecimal_output(String str){
		char[] ch_arr=new char[8];
		int k=0;
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)=='0'||str.charAt(i)=='1'){
			ch_arr[k]=str.charAt(i);
			k++;
			}
		}
		//System.out.println(str);
		
		String first_half="";
		String hexa="";
		String second_half="";
		
		
			 StringBuilder br2=new StringBuilder();
			   for(int m=0;m<ch_arr.length/2;m++){
				   br2.append(ch_arr[m]);
			   }
			first_half=br2.toString();
			 StringBuilder br3=new StringBuilder();
		for(int i=(ch_arr.length/2);i<ch_arr.length;i++){
			br3.append(ch_arr[i]);
			
		}
		second_half=br3.toString();
		//System.out.println(second_half);
		
		 hexa=hexa_output(first_half)+hexa_output(second_half);
		return hexa;
	}
	public static String hexa_output(String str){
		if(str.equals("0000")){
			return "0";
			
		}
		else if(str.equals("0001")){
			return "1";
		}
		else if(str.equals("0010")){
			return "2";
		}
		else if(str.equals("0011")){
			return "3";
		}
		else if(str.equals("0100")){
			return "4";
		}
		else if(str.equals("0101")){
			return "5";
		}
		else if(str.equals("0110")){
			return "6";
		}
		else if(str.equals("0111")){
			return "7";
		}
		else if(str.equals("1000")){
			return "8";
		}
		
		else if(str.equals("1001")){
			return "9";
		}
		else if(str.equals("1010")){
			return "A";
		}
		else if(str.equals("1011")){
			return "B";
		}
		else if(str.equals("1100")){
			return "C";
		}
		else if(str.equals("1101")){
			return "D";
		}
		else if(str.equals("1110")){
			return "E";
		}
		else if(str.equals("1111")){
			return "F";
		}
		else if(str.isEmpty()){
			return "Not available";
		}
		else return "";
		
		
	}
	
	public static void main(String args[]) throws IOException, NumberFormatException{
		try{
		System.out.println();
		File rtype= new File("rtype.txt");
		String[][] rtype_array= input_read_and_return_as_Array(rtype);
		
		File itype= new File("itype.txt");
		String[][] itype_array= input_read_and_return_as_Array(itype);
		
		File jtype= new File("jtype.txt");
		String[][] jtype_array= input_read_and_return_as_Array(jtype);
		
		File register= new File("register.txt");
		String[][] register_array= input_read_and_return_as_Array(register);
		
		
		
		File input = new File("input.txt");
		FileReader fr= new FileReader(input);
		BufferedReader br = new BufferedReader(fr);
		String line="";
		String[] final_machinelng_inBinary= new String[4];
		
		File fp= new File("output.txt");
		if(!fp.exists()){
			fp.createNewFile();
		}
		PrintWriter pw=new PrintWriter(fp);
		
		File fp1= new File("output_hex.txt");
		if(!fp1.exists()){
			fp1.createNewFile();
		}
		PrintWriter pw1=new PrintWriter(fp1);
		pw1.println("v2.0 raw");
		
		//System.out.println("---------------------");
		
		
		
		while((line=br.readLine())!=null) {
			
			   System.out.println(line);  //directly print the instruction
			   pw.println(line);
			   
			   ArrayList<String> output_arrList= new ArrayList<String>();
			   output_arrList.add(line);
			   //System.out.println(output_arrList);
			   String[] output_array= output_arrList.toArray(new String[output_arrList.size()]);
			   String[] split_output= output_array[0].split("\\s{1,}");
			   String for_disp_j = "";
			   
			   if(split_output.length>1){
				   for_disp_j = split_output[1];
			   split_output[1]= split_output[1].substring(0, split_output[1].length()-1);
			   }
			   /*for(String e: split_output){
				   System.out.println("Splited Output "+e);
			   }*/
			   System.out.println("-----------------------------------");
			   pw.println("-----------------------------------");
			   //R type operation checking
			   
			   int flag=1;
			   if(split_output[0].equals("add")||(split_output[0].equals("sub"))){
			   for(int i=0;i<rtype_array.length;i++){
				   
				   if(rtype_array[i][0].equals(split_output[0])&& split_output.length==3){
					   
				   for(int j=1;j<rtype_array[0].length;j++){
				       		
				    	    final_machinelng_inBinary[0]=rtype_array[i][j-1];
				    	    final_machinelng_inBinary[3]=rtype_array[i][j];
				    	    
				       }
				   	for(int k=0; k<register_array.length;k++){
				   		
					   if(register_array[k][0].equals(split_output[1])){
						  
							   final_machinelng_inBinary[2]=register_array[k][1];
				   		}
					   if(register_array[k][0].equals(split_output[2])){
							  
						   final_machinelng_inBinary[1]=register_array[k][1];
						   flag=0;
			   		}
				   	}
				   
				   	}
				   
			     }
			   }
			   
			   
			   
			   
			   
			   //addi,beq operation
			   else if((split_output[0].equals("addi")||split_output[0].equals("beq"))&& split_output.length==3){
				   //final_machinelng_inBinary[3]=null;
			   for(int i=0;i<itype_array.length;i++){
				   
				   if(itype_array[i][0].equals(split_output[0])){
					   
					   final_machinelng_inBinary[0]=itype_array[i][1];
					   			   	  }
			   			}
				   for(int k=0; k<register_array.length;k++){
				   		
					   if(register_array[k][0].equals(split_output[1])){
						  
							   final_machinelng_inBinary[1]=register_array[k][1];
				   		}
					  
				   	}
				   int number=0;
				   try{
					    number= Integer.parseInt(split_output[2]);
				   }
				   catch(NumberFormatException p){
					   System.out.println("For addi operation TWO register is not allowed.");
				   }
				   for(int k=0; k<register_array.length;k++){
				   		
					   if(register_array[k][0].equals(split_output[2])==false){
					
				   if(split_output[0].equals("addi")&& number<8 && number>=0 ){
					   String temp= DectoBinary(number).substring(29, 32);
					   final_machinelng_inBinary[2]=temp;
					   flag=0;
				   		}
				   if(split_output[0].equals("beq")&& number<8 && number>=0){
					   
					   String temp= DectoBinary(number).substring(29, 32);
					   final_machinelng_inBinary[2]=temp;
					   flag=0;
					   
				   				}
				   
					   		}
				   
				   		}
				  
				   
	              	}
			   
			   //stli opertion
			   else if(split_output[0].equals("slti")&& split_output.length==3 && !split_output[1].equals("$t3")){
				   //final_machinelng_inBinary[3]=null;
			   for(int i=0;i<itype_array.length;i++){
				   
				   if(itype_array[i][0].equals(split_output[0])){
					   
					   final_machinelng_inBinary[0]=itype_array[i][1];
					   			   	  }
			   			}
				   for(int k=0; k<register_array.length;k++){
				   		
					   if(register_array[k][0].equals(split_output[1]) && !split_output[1].equals("$t3")){
						  
							   final_machinelng_inBinary[1]=register_array[k][1];
				   		}
					  
				   	}
				   int number=0;
				   try{
					    number= Integer.parseInt(split_output[2]);
				   }
				   catch(NumberFormatException p){
					   System.out.println("For addi operation TWO register is not allowed.");
				   }
				   for(int k=0; k<register_array.length;k++){
				   		
					   if(register_array[k][0].equals(split_output[2])==false){
					
				   
				   if(split_output[0].equals("slti")&& number<8 && number>=0){
					   
					   String temp= DectoBinary(number).substring(29, 32);
					   final_machinelng_inBinary[2]=temp;
					   flag=0;
					   
				   				}
				   
					   		}
				   
				   		}
				  
				   
	              	}
			   
			   
			   //lw and store word
			   	else if((split_output[0].equals("lw")||split_output[0].equals("sw"))&& split_output.length==2){
				   
				   for(int i=0; i<itype_array.length;i++){
					   
					   if(itype_array[i][0].equals(split_output[0])){
						   
						   final_machinelng_inBinary[0]=itype_array[i][1];
						   			   	  }					   
				   			}
				   		
				   char[] char_arr= split_output[1].toCharArray();
				   int value=(int)Character.getNumericValue(char_arr[0]);
				   int value1=value;
				  
				   if(value<8 && (char_arr[1]=='(')){
					   StringBuilder br2=new StringBuilder();
					   for(int k=2;k<char_arr.length;k++){
						   br2.append(char_arr[k]);
					   }
					   String str=br2.toString();
					   
				   
				   for(int k=0; k<register_array.length && !str.equals("$t3");k++){
				   		if(register_array[k][0].equals(str)){
				   			final_machinelng_inBinary[1]= register_array[k][1];
				   		}
				   }
				   if((value1)< 8 && (value1)>=0 && !str.equals("$t3")){
					   
					   final_machinelng_inBinary[2]= DectoBinary(value1).substring(29,32);
					   flag=0;
				   }
				   
				   }
			   }
			   
			   
			   //jump
			   
			   	else if((split_output[0].equals("j")||split_output[0].equals("disp"))&& split_output.length==2){
			   		for(int i=0; i<jtype_array.length;i++){
						   
						   if(jtype_array[i][0].equals(split_output[0])){
							   
							   final_machinelng_inBinary[0]=jtype_array[i][1];
							   			   	  }					   
					   			}
			   	
			   		int value=0;
			   		try{
			   			value=Integer.parseInt(for_disp_j);
			   		}
			   		catch(NumberFormatException e){
			   			System.out.println(for_disp_j+" is out of range maximun number can be hold as address x<32. ");
			   		}
			   		
			   		if(value>=0 && value<=31){
			   			final_machinelng_inBinary[1]= DectoBinary(value).substring(27,32);
			   			flag=0;
			   		}
			   		if(split_output[0].equals("disp")){
			   			final_machinelng_inBinary[1]="00000";
			   			flag=0;
			   		}
			   		
			   	}
			   
			   //disp type operation
			   	else if(split_output[0].equals("disp")&& split_output.length==1){
			   		for(int i=0; i<jtype_array.length;i++){
						   
						   if(jtype_array[i][0].equals(split_output[0])){
							   
							   final_machinelng_inBinary[0]=jtype_array[i][1];
							   			   	  }					   
					   			}
			   	
			   		
			   		if(split_output[0].equals("disp")){
			   			final_machinelng_inBinary[1]="00000";
			   			flag=0;
			   		}
			   		
			   	}
			   
				
			   
			   if(flag==1 || final_machinelng_inBinary[1]==null){
				   System.out.println("Instruction is not typed properly");
				   pw.println("Instruction is not typed properly");
				   for(int i=0;i<final_machinelng_inBinary.length;i++){
					   final_machinelng_inBinary[i]=null;
				   }
			   }
		    
			   System.out.println("-----------------------------------");
			   pw.println("-----------------------------------");
			   //System.out.println(final_machinelng_inBinary.length);
			   
			   ArrayList<String> binary_output=new ArrayList<>();
			   //generating final output
			   
			   System.out.println("Input from text File=         " +output_arrList);
			   pw.println("Input from text File=             "+output_arrList);
			   System.out.print("Equivalent binary conversion= ");
			   pw.print("Equivalent binary conversion= ");
			   for(int k=0;k<final_machinelng_inBinary.length && final_machinelng_inBinary[k]!=null;k++){
				   binary_output.add(final_machinelng_inBinary[k]);
				   
			   }
			   if(binary_output.isEmpty()){
				   System.out.print("Output in binary format is not available");
				   pw.print("Output in binary format is not available");
			   }
			   else System.out.print(binary_output); pw.print(binary_output);
			   
			   System.out.println();
			   pw.println();
			   
			   String str1=binary_output.toString();
			   
			   //System.out.println(str1);
			   System.out.print("Hexa Decimal Format output= ");
			   pw.print("Hexa Decimal Format output= ");
			   if(hexadecimal_output(str1).isEmpty()){
				   System.out.println("Not available");
				   pw.print("Not available");
			   }
			   else System.out.println("   "+hexadecimal_output(str1)); pw.println(hexadecimal_output(str1)); 
			   
			   
			   if(!hexadecimal_output(str1).isEmpty()){
				 pw1.println(hexadecimal_output(str1));
				 
			   }
			   
			   System.out.println("-----------------------------------");
			   System.out.println();
			   pw.println();
			   System.out.println();
			   pw.println();
			   
			   
			   
			   for(int i=0;i<final_machinelng_inBinary.length;i++){
				   final_machinelng_inBinary[i]=null;
			   }
			  // break;
		}
		pw.close();
		pw1.close();
		}
		catch(IOException o){
			throw new IOException(o);
		}
		
	
	
	
	
	
	
	}
}
