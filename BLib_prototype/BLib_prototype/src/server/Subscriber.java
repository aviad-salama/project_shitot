package server;

public class Subscriber {
	private String SID, histID;
	private String name, pNumber, email;
	
	public Subscriber (String sid, String name, String histID, String pNumber, String email)
	{
		this.email=email;
		this.SID=sid;
		this.pNumber=pNumber;
		this.histID=histID;
		this.name=name;
		
		
	}
	 
	public void setSID(String s) 
	{
        this.SID=s;
    }

    public void setHistID(String hid)
    {
        this.histID=hid;
    }

    public String getSID() {
        return SID;
    }

    public String getHistID() {
        return histID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPNumber() {
        return pNumber;
    }

    public void setPNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        
        
        return "subscriber_id" +" = " + SID +
        ", subscriber_name ='" + name + 
        "', history_id=" + histID +
        ", subscriber_phone_number='" + pNumber +  
        "', subscriber_email='" + email +'\'' ;
    }

    
}

	
	


