package com.hibernate.compositeKey;

public class PeoplePK implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4849476667482641280L;

	private String name;

    private String type;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    public int hashCode() {
        return super.hashCode();
    }
    
	public PeoplePK() {
	}
	public PeoplePK(String name, String type) {
		this.name = name;
		this.type = type;
	}

}

