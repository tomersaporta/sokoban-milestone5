package model.policy.moveType;

import java.util.HashMap;

/**
 *<h1>MoveType factory</h1> 
 *create moveType according to the string type
 */

public class MoveTypeFactory {
	
	private interface Creator{
		public IMoveType create();
	}
	
	private class MoveUpCreator implements Creator{
		@Override
		public IMoveType create() {
			return new MoveUp();
		}
	}
	
	private class MoveDownCreator implements Creator{
		@Override
		public IMoveType create() {
			return new MoveDown();
		}
	}
		
	private class MoveRightCreator implements Creator{
		@Override
		public IMoveType create() {
			return new MoveRight();
		}
		
	}
	
	private class MoveLeftCreator implements Creator{
		@Override
		public IMoveType create() {
			return new MoveLeft();
		}
		
	}
	
	HashMap<String, Creator> moveTypeCreator;
	
	public MoveTypeFactory() {
		this.moveTypeCreator = new HashMap<String, Creator>();
		this.moveTypeCreator.put("UP", new MoveUpCreator());
		this.moveTypeCreator.put("DOWN", new MoveDownCreator());
		this.moveTypeCreator.put("LEFT", new MoveLeftCreator());
		this.moveTypeCreator.put("RIGHT", new MoveRightCreator());
	}
	/**
	 * create moveType according to the string type
	 * @param type This is the key in the HashMap who map between the string(key)and the suitable creator(value)
	 * @return new moveType 
	 *
	 */
	public IMoveType create(String type){
		Creator c=moveTypeCreator.get(type);
		
		if (c!=null)return c.create();
		return null;
	}
	
	
}
