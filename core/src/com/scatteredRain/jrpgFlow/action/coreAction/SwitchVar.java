package com.scatteredRain.jrpgFlow.action.coreAction;

import java.util.Scanner;

import com.badlogic.gdx.maps.MapProperties;
import com.scatteredRain.jrpgFlow.GlobalVariables;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.enums.CharacterFactory.AttKey;
import com.scatteredRain.jrpgFlow.general.AwlRequest;
import com.scatteredRain.jrpgFlow.general.CompletionListener;
import com.scatteredRain.jrpgFlow.util.mapFactory.MapFactory;

import lombok.AllArgsConstructor;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

public class SwitchVar implements Action{
	
	private MapProperties properties;
	private String type;
	private int id;
	
	public SwitchVar(MapProperties properties){
		this.properties = properties;
		this.id = Integer.parseInt(properties.get(MapFactory.ID, String.class));
		this.type = properties.get(MapFactory.TYPE, String.class);
	}
	

	@Override
	public void act() {
		String condition = properties.get(AttKey.SWITCH_VAR.key(), String.class);
		int varValue = 0;
		int index = 0;
		String comparator = "";
		int compareValue = 0;
		Scanner scan = new Scanner(condition);
		String cod = scan.next();
		scan.close();
		if(cod.equals("GAME_VAR")){//GAME_VAR 13 = 4
			Scanner s = new Scanner(condition);
			s.next();
			index = s.nextInt();
			varValue = GlobalVariables.globalGameVariables.getGameVariable(index);
			comparator = s.next();
			compareValue = s.nextInt();
			s.close();
			if(comparator.equals("+=")){
				GlobalVariables.globalGameVariables.setGameVariable(index, varValue+compareValue);
			}
			else if(comparator.equals("-=")){
				GlobalVariables.globalGameVariables.setGameVariable(index, varValue-compareValue);
			}
			else if(comparator.equals("=")){
				GlobalVariables.globalGameVariables.setGameVariable(index, compareValue);
			}
		}
		else if(cod.equals("SELF_VAR")){//SELF_VAR 13 = 4
			Scanner s = new Scanner(condition);
			s.next();
			index = s.nextInt();
			varValue = GlobalVariables.globalGameVariables.getCharacterVariable(GlobalVariables.currentMapID, type, id, index);
			comparator = s.next();
			compareValue = s.nextInt();
			s.close();
			if(comparator.equals("+=")){
				GlobalVariables.globalGameVariables.setCharacterVariable(GlobalVariables.currentMapID, type, id, index, varValue+compareValue);
			}
			else if(comparator.equals("-=")){
				GlobalVariables.globalGameVariables.setCharacterVariable(GlobalVariables.currentMapID, type, id, index, varValue-compareValue);
			}
			else if(comparator.equals("=")){
				GlobalVariables.globalGameVariables.setCharacterVariable(GlobalVariables.currentMapID, type, id, index, compareValue);
			}
		}
	}

}
