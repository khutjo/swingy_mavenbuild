package com.bestrpg.app;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kmaputla
 */
public class DeleteLoser {
    
        private Stack<String> getFileData(String openfile) {
		Stack<String> fileData = new Stack<String>();
		// Iterator<String> tempIterator;
		String HoldString;

//		System.out.println("--->B");
		File InFile = new File(openfile);
		//System.out.println("hello");
		try{Scanner FileReader = new Scanner(InFile);
				while (FileReader.hasNextLine()){
					HoldString = FileReader.nextLine();
					HoldString = HoldString.trim();
					fileData.push(HoldString);
				}
				FileReader.close();
		}catch (FileNotFoundException e){
				return (null);
		}
		return (fileData);
	}
    
    public DeleteLoser(BasicHero loser){
        Stack<String> basefile = getFileData("../HeroDataFile/SavedHero.bin");
        String losername = loser.getHeroName()+".sgy";
        try{
            boolean start = true;
            Iterator<String> Newfilenames = basefile.iterator();
            FileWriter fw=new FileWriter("../HeroDataFile/SavedHero.bin");
            String tempstring;
            while (Newfilenames.hasNext()){
                tempstring = Newfilenames.next();
                if (!tempstring.equals(losername)){
                    if (start){
                        fw.write(tempstring);
                    start = false;
                }else
                    fw.write("\n"+tempstring);
                }
            }
            fw.close();
            File file = new File("../HeroDataFile/"+losername);
            file.delete();
        }catch(Exception e){
            System.out.println(e);
            System.out.println("AutoSavefull failed");
            System.exit(0);
           }
    }
}
