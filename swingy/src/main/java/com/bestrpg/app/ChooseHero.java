package com.bestrpg.app;


import java.util.Iterator;
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
public class ChooseHero {
    private Stack<BasicHero> SavedHeros;
    private GuiToConsoleController Bridge;
    private WriteAction Printer;
    private BasicHero save;
    private int herocount;
   
   public ChooseHero(Stack<BasicHero> savedheros, WriteAction printer, GuiToConsoleController bridge){
       SavedHeros = savedheros;
       Printer = printer;
       Bridge = bridge;
   }
   
   private int counthsavedheros(Stack<BasicHero> hero){
       Iterator<BasicHero> heroiter = hero.iterator();
       int count = 0;
       while (heroiter.hasNext()){
            heroiter.next();
           count++;}
       
       herocount = count;
       return count++;
   }
   
   public int availableheros(){
        int savedherocount = counthsavedheros(SavedHeros);
        int index = 1;
        String herolist = "hello ";
        if (savedherocount > 0){
            BasicHero holdhero;
            herolist = "choose a hero\n"+
                    "enter hero name to view hero stat or -create to create a new hero\n\n";
            Iterator<BasicHero> heroiter = SavedHeros.iterator();
            while (heroiter.hasNext()){
                holdhero = heroiter.next();
                herolist = herolist + Integer.toString(index) + " " 
                        + holdhero.getHeroName()+"\n";
                index++;
            }
            
        }else {
            herolist = "no saved heros available enter -create\n";
        }
            Bridge.setTX(false).settextField(true);
            Printer.OutputplayTextln(herolist);
        return 1;
   }
   
   public int HeroChoice(String input, BasicHero hero){
        if (input.equals("-create")){
            Bridge.setInfoscreen("");
            return 1;
        }
        else if (input.equals("-save") && save != null){
            Bridge.SetHero(save);
            return 7;
        }
        else if (herocount > 0){
            boolean found = false;
            hero = null;
            String names = "enter -save to use hero \n\nchoose a hero\n"+
                    "enter hero name to view hero stat or -create to create a new hero\n";
            int index = 1;
            BasicHero holdhero;
            Iterator<BasicHero> nameiter = SavedHeros.iterator();
            while (nameiter.hasNext()){
                holdhero = nameiter.next();
                if (holdhero.getHeroName().equals(input)){
                    save = holdhero;
                    found = true;
                    Bridge.setTX(false).settextField(true);
                    Printer.PrintOutStates(holdhero, !Bridge.getView());
                }
                names = names + Integer.toString(index) + " " 
                        + holdhero.getHeroName()+"\n";
                index++; 
            }
            if (!found){
                availableheros();
                return 0;
            }
            Printer.OutputplayTextln(names);
            return 0;
        }else{
            availableheros();
            return 0;
        }
   }
}
