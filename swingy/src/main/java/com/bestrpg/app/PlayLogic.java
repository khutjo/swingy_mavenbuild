package com.bestrpg.app;


//************************************************************************************************
//************************************************************************************************
//                                      play Logic
//************************************************************************************************
//************************************************************************************************

public class PlayLogic {
    private BasicHero basicHero;
    private WriteAction Printer;
    private GuiToConsoleController Bridge;

	public PlayLogic(BasicHero basichero, WriteAction printer, GuiToConsoleController bridge){
        basicHero = basichero;
        Printer = printer;
        Bridge = bridge;
	}

	private void ToWall(){
        int x = basicHero.getMapSize();
        char [][] map = basicHero.getNewMap();
		for (int i = 0; i < x; i++)
			for (int j = 0; j < x; j++){
				if (j == 0 || j == -1 + x || i == -1 + x || i == 0)
					if (map[i][j] == 'M'){
                        basicHero.setAtWall(true);
                        return ;
                    }
			}		
            basicHero.setAtWall(false);
	}

    private int LevelUpXp(){
       return (basicHero.getLeval() * 1000) + (((basicHero.getLeval() - 1) * (basicHero.getLeval() - 1)) * 450);
    }

    public void UnSetFight(){
        basicHero.setFight(false);
    }

    public void UnSetAtWalL(){
        basicHero.setAtWall(false);
    }

    private void UpdateMapize(){
        basicHero.setMapSize(((((basicHero.getLeval() - 1) * 5) + 10) - (basicHero.getLeval() % 2)));
    }

    public void LevelUp(){
        int levelup = LevelUpXp();
            //System.out.println("A" + levelup);
        if (basicHero.getXP() > levelup){
            //System.out.println("B");
            Bridge.SetContent(Bridge.getContent() + "\nLevelUP");
            basicHero.setLevel(1 + basicHero.getLeval());
        }
        if (basicHero.getAtWall()){
            //System.out.println("C");
            UpdateMapize();
            UnSetAtWalL();
            basicHero.setMap(new GenMap().makemap(basicHero.getLeval()));
            basicHero.setNewMap(basicHero.getMap());
            Bridge.SetContent(Bridge.getContent() + "\nmap comleted new map");
        }
    }

	private void copymap(){
        char [][] newmap = new char[basicHero.getMapSize()][basicHero.getMapSize()];
        char [][] map = basicHero.getMap();
        int mapsize = basicHero.getMapSize();
		for (int i = 0; i < mapsize; i++)
			for (int j = 0; j < mapsize; j++)
				newmap[i][j] = map[i][j];
		basicHero.setNewMap(newmap);	
	}
    /////input
	public String hardcodemove(){//       String move = hardcodemove();
            Printer.OutputplayTextln("Enter direction : ");
		String move = new ReadConsole().that();
                if (move.equals("east") || move.equals("west") 
                        || move.equals("north") || move.equals("south"))
                    return move;
                else
                    return hardcodemove();       
	}
	
	private void FindMyCoords(){
		int [] me = new int[2];
        char [][] map = basicHero.getMap();
        int mapsize = basicHero.getMapSize();
		for(int i = 0; i < mapsize; i++)
			for (int j = 0; j < mapsize; j++)
				if (map[i][j] == 'M'){
					me[0] = i;
                    me[1] = j;
                    //System.out.println(j +" "+ i);
                    basicHero.setMyCoords(me);
                }
	}

    private void MoveAdder(String move){
        int [] NewCoords = {basicHero.getMyCoords()[0], basicHero.getMyCoords()[1]};
		if (move.equals("west"))
            NewCoords[1]--;
		else if (move.equals("east"))
            NewCoords[1]++;
		else if (move.equals("north"))
            NewCoords[0]--;
		else if (move.equals("south"))
            NewCoords[0]++;
	basicHero.setMyNewCoords(NewCoords);
    }

    private void IsFight(){
        char [][] map = basicHero.getMap();
        int [] MyCoords = {basicHero.getMyNewCoords()[0], basicHero.getMyNewCoords()[1]};
        if (map[MyCoords[0]][MyCoords[1]] != '.' && map[MyCoords[0]][MyCoords[1]] != 'M'){
            basicHero.setFight(true);
            basicHero.setEnemyClass(map[MyCoords[0]][MyCoords[1]]);
        }
        else {
            basicHero.setFight(false);
            basicHero.setEnemyClass('.');
        }
            

    }

    private void PutMoveToMap(){
        // System.out.println(map[MyCoords[0]][MyCoords[1]] + " " + map[NewCoords[0]][NewCoords[1]] );
        char [][] map = basicHero.getNewMap();
        int [] MyCoords = basicHero.getMyCoords();
        int [] NewCoords = basicHero.getMyNewCoords();
        map[MyCoords[0]][MyCoords[1]] = '.';
        map[NewCoords[0]][NewCoords[1]] = 'M';
        basicHero.setNewMap(map);
    }

    public boolean DoNotTouch(){
        int len = basicHero.getMapSize();
        char [][] map1 = basicHero.getMap();
        char [][] map2 = basicHero.getNewMap();
        for (int k = 0; k < len; k++)
            for ( int g = 0; g < len; g++){
                // System.out.println(map1[k][g] + " : "+ map2[k][g]);
                if (map1[k][g] != map2[k][g])
                    return true;
            }
         return false;
    }
    
    public int SetUpMove(){
        if (DoNotTouch())
            return 2;
        copymap();
        FindMyCoords(); 
        Bridge.setTX(false).setdirection(true);
        Printer.OutputplayTextln(Bridge.getContent() + "\nEnter direction : ");
        return 1;
    }
    
    public int MakeMove(String move){
        MoveAdder(move);
        PutMoveToMap();
        ToWall();
        IsFight();
        return 1;
    }
}
