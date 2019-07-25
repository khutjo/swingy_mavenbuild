package com.bestrpg.app;



public class BasicHero {

	private String HeroName;
	private HeroClassEnum HeroClass;
	private int Level;
	private int XP;
	private HeroArtefacsEnum HeroEfacs;
	private char [][] Map;
	private char [][] NewMap;
	private int MapSize;
	private int [] MyCoords;
	private int [] MyNewCoords;
	private char EnemyClass;
	private boolean AtWall;
	private boolean Fight;

	public enum HeroArtefacsEnum {
		Attack,
		Defense,
		HitPoints,
		none
	}

	public enum HeroClassEnum {
		Attack,
		Defense
	}

	public BasicHero stringsetHeroEfacs(String efacs){ 

		if (efacs.equals("Attack"))
			HeroEfacs = HeroArtefacsEnum.Attack;
		else if (efacs.equals("Defense"))
			HeroEfacs = HeroArtefacsEnum.Defense;
		else if (efacs.equals("HitPoints"))
			HeroEfacs = HeroArtefacsEnum.HitPoints;
		else if (efacs.equals("none"))
			HeroEfacs = HeroArtefacsEnum.none;

		return this;
	}

	public BasicHero stringsetHeroClass(String heroclass){
	
		if (heroclass.equals("Attack"))
			HeroClass = HeroClassEnum.Attack;
		else if (heroclass.equals("Defense"))
			HeroClass = HeroClassEnum.Defense;

		return this;
	}


	public BasicHero stringsetFight(String fight){
		
		if (fight.equals("true"))
			Fight = true;
		else if (fight.equals("false"))
			Fight = false;

		return this;
	}


	public BasicHero stringsetAtwall(String atwall){
		
		if (atwall.equals("true"))
			AtWall = true;
		else if (atwall.equals("false"))
			AtWall = false;

		return this;
	}

	public BasicHero stringsetMyCoords(String X, String Y,String newX, String newY){
		int [] holdcoords = new int[2];
		int [] holdnewcoords = new int[2];

		holdcoords[0] = Integer.parseInt(Y);
		holdcoords[1] = Integer.parseInt(X);
		MyCoords = holdcoords;

		holdnewcoords[0] = Integer.parseInt(newY);
		holdnewcoords[1] = Integer.parseInt(newX);
		MyNewCoords = holdnewcoords;

		return this;
	}

	public BasicHero stringsetEnamyClass(String enemyclass){
		
		char [] HoldChar = enemyclass.toCharArray();
		EnemyClass = HoldChar[0];

		return this;
	}

	public BasicHero setHeroName(String name){HeroName = name; return this;}
	public BasicHero setHeroClass(HeroClassEnum heroclass){HeroClass = heroclass; return this;}
	public BasicHero setLevel(int level){Level = level; return this;}
	public BasicHero setXP(int xp){XP = xp; return this;}
	public BasicHero setHeroEfacs(HeroArtefacsEnum heroefacs){HeroEfacs = heroefacs; return this;}
	public BasicHero setMap(char [][] map){Map = map; return this;}
	public BasicHero setNewMap(char [][] map){NewMap = map; return this;}
	public BasicHero setMapSize(int mapsize){MapSize = mapsize; return this;}
	public BasicHero setMyCoords(int [] mycoords){MyCoords = mycoords; return this;}
	public BasicHero setMyNewCoords(int [] mycoords){MyNewCoords = mycoords; return this;}
	public BasicHero setEnemyClass(char enemyclass){EnemyClass = enemyclass; return this;}
	public BasicHero setAtWall(boolean atwall){AtWall = atwall; return this;}
	public BasicHero setFight(Boolean fight){Fight = fight; return this;}

	public String EnumToStringHeroClass(){
		if (HeroClass.equals(HeroClassEnum.Attack))
			return "Attack";
		else if (HeroClass.equals(HeroClassEnum.Defense))
			return "Defense";
		return "error";
	}

	public String EnumToStringHeroEfacsEnum(){

		if (HeroEfacs.equals(HeroArtefacsEnum.Attack))
			return "Attack";
		else if (HeroEfacs.equals(HeroArtefacsEnum.Defense))
			return "Defense";
		else if (HeroEfacs.equals(HeroArtefacsEnum.HitPoints))
			return "HitPoints";
		else if (HeroEfacs.equals(HeroArtefacsEnum.none))
			return "none";
		return "error";
	}

	public String getHeroName(){return HeroName;}
	public HeroClassEnum getHeroClass(){return HeroClass;}
	public int getLeval(){return Level;}
	public int getXP(){return XP;}
	public HeroArtefacsEnum getHeroEfacs(){return HeroEfacs;}
	public char [][] getMap(){return Map;}
	public char [][] getNewMap(){return NewMap;}
	public int getMapSize(){return MapSize;}
	public int [] getMyCoords(){return MyCoords;}
	public int [] getMyNewCoords(){return MyNewCoords;}
	public char getEnemyClass(){return EnemyClass;}
	public boolean getAtWall(){return AtWall;}
	public boolean getFight(){return Fight;}

}
