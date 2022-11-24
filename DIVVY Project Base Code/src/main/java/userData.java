
public class userData {
	String userName = "";
	String userID = "";
	String vanID = "";
	
	userData(){
		this.userName = "default";
		this.userID = "0001";
		this.vanID = "ABC 123";
	}
	
	userData(String name, String ID, String vanID){
		this.userName = name;
		this.userID = ID;
		this.vanID = vanID;
	}
	
	void setUserName(String name) {
		this.userName = name;
	}
	String getUserName() {
		return userName;
	}
	
	void setUserID(String ID) {
		this.userID = ID;
	}
	String getUserID() {
		return userID;
	}
	
	void setvanID(String van) {
		this.vanID = van;
	}
	String getVanID() {
		return vanID;
	}
}
