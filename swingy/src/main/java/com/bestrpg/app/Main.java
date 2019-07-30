package com.bestrpg.app;

import java.util.Stack;

public class Main {


	public static void main (String [] args){
            WriteAction printer = new WriteAction(args);
            Stack<BasicHero> HoldSavedHeros = new ReadHeroFileData().GetSavedHeros();
            GuiToConsoleController bridge = GuiToConsoleController.getBridgeIntance();
            if (bridge.getView())
                bridge.rungui(HoldSavedHeros, printer);
            else
                bridge.runconsole(HoldSavedHeros, printer);
 	
	}

}
