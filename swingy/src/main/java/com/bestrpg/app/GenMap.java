package com.bestrpg.app;


import java.util.Random;

//************************************************************************************************
//************************************************************************************************
//                                      generate map
//************************************************************************************************
//************************************************************************************************


public class GenMap {

    public int CalMap(int i){
        return ((((i - 1) * 5) + 10) - (i % 2));
    }

    private char [][] Addop(int level){
        int linesize = CalMap(level);
        Random addspice = new Random();
        Random chooseop = new Random(addspice.nextInt(1000));
        char [][]MapCoord = new char[linesize][linesize];
        int holdren;
    
        for (int j = 0; j < linesize; j++){
           for (int i = 0; i < linesize; i++){
                holdren = chooseop.nextInt(100);
                if (holdren < 30)
                    MapCoord[j][i] = 'A';
                else if (holdren > 90)
                    MapCoord[j][i] = 'C';
                else if (holdren > 45 && holdren < 50)
                    MapCoord[j][i] = 'B';
                else
                    MapCoord[j][i] = '.';
            }
        }
        return MapCoord;
    }

    public char [][] makemap(int level){
        int set = CalMap(level) / 2;
        char [][]map = Addop(level);
        map[set][set] = 'M';
        return map;
    } 
}   

