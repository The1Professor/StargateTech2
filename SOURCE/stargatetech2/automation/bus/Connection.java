package stargatetech2.automation.bus;

public enum Connection {
	DISCONNECTED, DEVICE, CABLE;
	
	public boolean isConnected(){
		return this != DISCONNECTED;
	}
	
	public boolean hasPlug(){
		return this == DEVICE;
	}
}