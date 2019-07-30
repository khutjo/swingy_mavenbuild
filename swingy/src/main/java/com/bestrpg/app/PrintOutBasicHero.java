package com.bestrpg.app;


//import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

//************************************************************************************************
//************************************************************************************************
//                                      Print Out Basic Hero
//************************************************************************************************
//************************************************************************************************


public class PrintOutBasicHero {
//////for testomng	
	public void PrintHeroDatafull (BasicHero holdbasic){
		System.out.println("\nname : "+holdbasic.getHeroName());
		System.out.println("HeroClass : "+holdbasic.EnumToStringHeroClass());
		System.out.println("level : "+holdbasic.getLeval());
		System.out.println("XP : "+holdbasic.getXP());
		System.out.println("Artefacs : "+holdbasic.EnumToStringHeroEfacsEnum());
		int x = holdbasic.getMapSize();
		System.out.println("mapsize : "+x);
		System.out.println("coords : "+holdbasic.getMyCoords()[0] +" "+ holdbasic.getMyCoords()[1]);
		System.out.println("enemyclass : "+holdbasic.getEnemyClass());
		System.out.println("atwall : "+holdbasic.getAtWall());
		System.out.println("fight : "+holdbasic.getFight());
		char [][] mpa = holdbasic.getMap();
		for (int i = 0; i < x; i++){
			System.out.print("\t");
			for (int j = 0; j < x; j++)
				System.out.print(mpa[i][j]);
			System.out.print("\n");
		}

	}

	public void PrintHeroData (BasicHero holdbasic){
		System.out.println("\nname : "+holdbasic.getHeroName());
		System.out.println("HeroClass : "+holdbasic.EnumToStringHeroClass());
		System.out.println("level : "+holdbasic.getLeval());
		System.out.println("XP : "+holdbasic.getXP());
		System.out.println("Artefacs : "+holdbasic.EnumToStringHeroEfacsEnum());
	}

	public void readoutdata (Stack<BasicHero> src){
		Iterator<BasicHero> holdherodata = src.iterator();
	
		while (holdherodata.hasNext()){
			PrintHeroData(holdherodata.next());
		}
	}

}
