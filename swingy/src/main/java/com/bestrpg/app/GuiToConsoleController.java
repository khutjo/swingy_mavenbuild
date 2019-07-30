package com.bestrpg.app;


import java.util.*;


public class GuiToConsoleController {
   private WriteAction Printer;
   private PlayLogic playlogic;
   private Fight fightlogic ;
   private AutoSave autosave;
   private ChooseHero herochooser;
   private static GuiToConsoleController HoldBridge;
   private CreateHero createhero;
   private ReadConsole readline;
   private BasicHero Hero;
   private String content;
   private String infoscreen;
   private String Choose;
   private String Direction;
   private String TextField;
   private int callorder;
   private boolean direction;
   private boolean choose;
   private boolean textfield;
   private boolean startgame;
   private boolean view;
   private boolean RX;
   private boolean TX;
   private boolean herochosen;
   
   private GuiToConsoleController(){

   }
   
   public static GuiToConsoleController getBridgeIntance(){
       if (HoldBridge == null)
           HoldBridge = new GuiToConsoleController();
       return HoldBridge;
   }
   
 
   public String getContent(){return content;}
   public String getInfoScreen(){return infoscreen;}
   public String getChoose (){return Choose;}
   public String getDirection (){return Direction;}
   public String getTextField (){return TextField;}
   public boolean getdirection (){return direction;}
   public boolean getchoose (){return choose;}
   public boolean gettextfield (){return textfield;}
   public boolean getstartgame (){return startgame;}
   public boolean getView(){return view;}
   public boolean getTX(){return TX;}
   public boolean getRX(){return RX;}

   
   public GuiToConsoleController SetContent (String input){content = input; return this;}
   public GuiToConsoleController SetHero (BasicHero input){Hero = input; return this;}
   public GuiToConsoleController setInfoscreen (String input){infoscreen = input; return this;}
   public GuiToConsoleController setChoose (String input){Choose = input; return this;}
   public GuiToConsoleController setDirection (String input){Direction = input; return this;}
   public GuiToConsoleController setTextField (String input){TextField = input; return this;}
   public GuiToConsoleController setdirection (boolean state){direction = state; return this;}
   public GuiToConsoleController setchoose (boolean state){choose = state; return this;}
   public GuiToConsoleController settextField (boolean state){textfield = state; return this;}
   public GuiToConsoleController setstartgame (boolean state){startgame = state; return this;}
   public GuiToConsoleController setView (boolean state){view = state; return this;}
   public GuiToConsoleController setTX (boolean state){TX = state; return this;}
   public GuiToConsoleController setRX (boolean state){RX = state; return this;}
   
   private void RunLogic(String input){
        if (callorder == 0){
            playlogic.LevelUp();
            callorder += playlogic.SetUpMove();
        }else if (callorder == 1){
            callorder += playlogic.MakeMove(input);
            autosave.SaveStatus();
        }
        if (callorder == 2)
            callorder += fightlogic.SetUpFight();
        else if (callorder == 3){
            if (input.equals("yes"))
            callorder += fightlogic.EngageFight(true);
            else if (input.equals("no"))
            callorder += fightlogic.EngageFight(false);
            autosave.SaveStatus();
        }
        if (callorder == 4)
            callorder += fightlogic.DoTheyGetAnArtefacs();
        else if (callorder == 5)
            callorder += fightlogic.GiveThemanefacs(input);
        if (callorder > 5){
            callorder = 0;
            playlogic.LevelUp();
            callorder += playlogic.SetUpMove();
        }

        autosave.SaveStatus();
            Printer.PrintOutStates(Hero, false);
   }
   
   private boolean IsGameOn(){
       if (!startgame){
           if (Choose.equals("yes")){
                startgame = true;
                Choose = "";
                IsHeroChosen();
           }
           else if (Choose.equals("no"))
                   System.exit(0);
           return true;
       }
       return false;
   }
   
   private boolean IsHeroChosen(){
       if (!herochosen){
           //System.out.println("chooser in");
           if (callorder == 0)
                callorder += herochooser.availableheros();
           else if (callorder == 1)
                callorder += herochooser.HeroChoice(TextField, Hero);
           if (callorder == 2)
                callorder += createhero.SetUpCreateHero();
           else if (callorder == 3)    
                callorder += createhero.getnewHeroName(TextField);
           else if (callorder == 4)
                callorder += createhero.getnewHeroClass(TextField);
           else if (callorder == 5){
                callorder = createhero.Save(Choose);
                autosave = new AutoSave(Hero).savefull();
           }
           if (callorder == -1){
               callorder = 0;
               callorder += herochooser.availableheros();
           }
           if (callorder > 6){
               callorder = 0;
               content = "";
                herochosen = true;
                playlogic = new PlayLogic(Hero, Printer, this);
                fightlogic = new Fight(Hero, Printer, this);
                autosave = new AutoSave(Hero);
                RunLogic("start");
           }
           return true;
       }
       return false;
   }
   
   public void Getrequet(){
       
       if (IsGameOn())
           return ;
       if (IsHeroChosen())
           return ;
           
       
       switch (Choose) {
           case "yes":
               RunLogic(Choose);
               break;
           case "no":
               RunLogic(Choose);
               break;
           default:
               break;
       }switch (Direction) {
           case "north":
               RunLogic(Direction);
               break;
           case "west":
               RunLogic(Direction);
               break;
           case "east":
               RunLogic(Direction);
               break;
           case "south":
               RunLogic(Direction);
               break;
           default:
               break;
       }

        Direction = "";
        Choose = "";
   }
   
   public void rungui(Stack<BasicHero> savedheros, WriteAction printer){

        Printer = printer;
        createhero = new CreateHero(savedheros, printer, this);
        herochooser = new ChooseHero(savedheros, printer, this);
        callorder = 0;

   }
   
   private boolean validinput(){
       String input = readline.that();
        if (getdirection()){
            if (input.equals("north") || input.equals("south") 
                    || input.equals("west") || input.equals("east")){
            setDirection(input).setTX(true).setdirection(false);
            Getrequet();
            return true;}
        }
        if (getchoose()){
            if (input.equals("yes") || input.equals("no")){
                setChoose(input).setTX(true).setchoose(false);
            Getrequet();
            return true;
            }
        }
        if (gettextfield()){
            setTextField(input).setTX(true).settextField(false);
            Getrequet();
            return true;
        }
        if (input.equals("stats") && herochosen){
            Printer.PrintOutStates(Hero, true);
            Printer.RePrint();
            return true;
        }else if (input.equals("exit"))
            return false;
        else 
            Printer.RePrint();
        return true;
   }
   
   public void runconsole(Stack<BasicHero> savedheros, WriteAction printer){

        Printer = printer;
        herochooser = new ChooseHero(savedheros, printer, this);
        createhero = new CreateHero(savedheros, printer, this);
        readline = new ReadConsole();
        callorder = 0;
        boolean run = true;
        printer.OutputplayTextln("Enter YES to start game\nor No To Exit\n");
        while (run){
            run = validinput();
        }
   }
}
