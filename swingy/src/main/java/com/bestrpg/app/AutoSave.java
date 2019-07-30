package com.bestrpg.app;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class AutoSave {
    private String FileName;
    private BasicHero Hero;

    
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
    
    
    private boolean booleanToString (boolean bool){
        if (bool)
            return true;
        return false;
    }

    private void WrtieTheHash(FileWriter fw) throws Exception{
        int len = Hero.getMapSize();
        fw.write("\n");
        for (int k = 0 ; k < len; k++)
            fw.write("#");
    }

    private void WriteTheMap(FileWriter fw) throws Exception{
        WrtieTheHash(fw);
        int len = Hero.getMapSize();
        char [][] map = Hero.getMap();
        for (int k = 0; k < len; k++){
            fw.write("\n");
            for (int g = 0; g < len; g++)
                fw.write(map[k][g]);
        }
        WrtieTheHash(fw);
        map = Hero.getNewMap();
        for (int k = 0; k < len; k++){
            fw.write("\n");
            for (int g = 0; g < len; g++)
                fw.write(map[k][g]);
        }
        WrtieTheHash(fw);
    }

    public void SaveStatus(){
        try{    
            FileWriter fw=new FileWriter(FileName);   
            fw.write(Hero.getHeroName());
            fw.write("\n"+Hero.EnumToStringHeroClass());
            fw.write("\n"+Integer.toString(Hero.getLeval()));
            fw.write("\n"+Integer.toString(Hero.getXP()));
            fw.write("\n"+Hero.EnumToStringHeroEfacsEnum());
            fw.write("\n"+Hero.getEnemyClass());
            fw.write("\n"+booleanToString(Hero.getFight()));
            fw.write("\n"+booleanToString(Hero.getAtWall()));
            fw.write("\n"+Integer.toString(Hero.getMyCoords()[0]));
            fw.write("\n"+Integer.toString(Hero.getMyCoords()[1]));
            fw.write("\n"+Integer.toString(Hero.getMyNewCoords()[0]));
            fw.write("\n"+Integer.toString(Hero.getMyNewCoords()[1]));
            fw.write("\n"+Integer.toString(Hero.getMapSize()));
            WriteTheMap(fw);
            fw.close();    
           }catch(Exception e){
                System.out.println(e);
                System.out.println("AutoSave failed but you can keep playing");
           }
    }

    public AutoSave (BasicHero hero){
        FileName = "../HeroDataFile/"+hero.getHeroName()+".sgy";
        Hero = hero;
        // SaveStatus();
    }    
    
    public AutoSave savefull (){
        Stack<String> basefile = getFileData("../HeroDataFile/SavedHero.bin");
        basefile.push(Hero.getHeroName()+".sgy");
        try{  
            Iterator<String> Newfilenames = basefile.iterator();
            FileWriter fw=new FileWriter("../HeroDataFile/SavedHero.bin");
            if (Newfilenames.hasNext()){
                    fw.write(Newfilenames.next());  
            }
            while (Newfilenames.hasNext())
                fw.write("\n"+Newfilenames.next());
                
            fw.close();    
           }catch(Exception e){
                System.out.println(e);
                System.out.println("AutoSavefull failed");
                System.exit(0);
           }
        return this;
    }  
}

