package controller.commands;

public abstract class Command implements ICommand {
	
	private String params;

	public String getParams() {
		return params;
	}
	
	/**
	 * Initialize the parameters necessary for the receiver
	 */
	@Override
	public void setParams(String params) {
		this.params=params;

	}

}
