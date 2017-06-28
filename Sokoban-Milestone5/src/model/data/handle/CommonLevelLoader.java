package model.data.handle;

import java.util.HashMap;

public abstract class CommonLevelLoader implements LevelLoader {
	
	private HashMap<Character,String> typeOfElement;
	
	public CommonLevelLoader() {
		this.typeOfElement=new HashMap<Character, String>();
		this.typeOfElement.put('@', "movable");
		this.typeOfElement.put('A', "movable");
		this.typeOfElement.put('o', "unmovable");
		this.typeOfElement.put('#', "unmovable");
		this.typeOfElement.put(' ', "unmovable");
	}

	public HashMap<Character, String> getTypeOfElement() {
		return typeOfElement;
	}

	public void setTypeOfElement(HashMap<Character, String> typeOfElement) {
		this.typeOfElement = typeOfElement;
	}

}
