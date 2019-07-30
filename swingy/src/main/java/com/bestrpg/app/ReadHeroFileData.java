package com.bestrpg.app;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

//************************************************************************************************
//************************************************************************************************
//                                      Read Hero File Data
//************************************************************************************************
//************************************************************************************************

public class ReadHeroFileData {
	
	public Stack<String> getFileData(String openfile) {
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

	

	public Stack<Stack<String>> getHeroData(Stack<String> filenamedata){
		Iterator<String> filename = filenamedata.iterator();
		Stack<Stack<String>> Heros = new Stack<Stack<String>>();
		String HoldString;

		while (filename.hasNext()){
			HoldString = filename.next();
			//System.out.println(HoldString);
			Heros.push(getFileData("../HeroDataFile/"+HoldString));
		}	
		return Heros;
	}

	private BasicHero MakeHero(Stack<String> filedata){
		
		BasicHero basichero = new BasicHero();
		Iterator<String> filelines = filedata.iterator();

		basichero.setHeroName(filelines.next());
		basichero.stringsetHeroClass(filelines.next());
		basichero.setLevel(Integer.parseInt(filelines.next()));
		basichero.setXP(Integer.parseInt(filelines.next()));
		basichero.stringsetHeroEfacs(filelines.next());
		basichero.stringsetEnamyClass(filelines.next());
		basichero.stringsetFight(filelines.next());
		basichero.stringsetAtwall(filelines.next());
		basichero.stringsetMyCoords(filelines.next(), filelines.next(), filelines.next(), filelines.next());
		basichero.setMapSize(Integer.parseInt(filelines.next()));
		filelines.next();
		int holdmapsize = basichero.getMapSize();
		char [][] map = new char[holdmapsize][holdmapsize];
		for (int i = 0; i < holdmapsize; i++){
			map[i] = filelines.next().toCharArray();
		}
		basichero.setMap(map);
		filelines.next();
		char [][] newmap = new char[holdmapsize][holdmapsize];
		for (int i = 0; i < holdmapsize; i++){
			newmap[i] = filelines.next().toCharArray();
		}
		basichero.setNewMap(newmap);
		return basichero;
	}

	public Stack<BasicHero> SortHeroData(Stack<Stack<String>> HeroData){
		Stack<BasicHero> SortedHero = new Stack<BasicHero>();
		Iterator<Stack<String>> Hero = HeroData.iterator();

		while (Hero.hasNext()){
			SortedHero.push(MakeHero(Hero.next()));
		}
		return SortedHero;
	}

        public Stack<BasicHero> GetSavedHeros (){

		Stack<String> HeroFileNameData = getFileData("../HeroDataFile/SavedHero.bin");
		if (HeroFileNameData == null){
//                    System.out.println("hi");
                    return null;
                }

		Stack<Stack<String>> Heros = getHeroData(HeroFileNameData);
		Stack<BasicHero> Basichero = SortHeroData(Heros);
	//	readoutdata(Basichero);
		return Basichero;
	}
}
