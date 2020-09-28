package com.hibernate.compositeKey;
public class People {
    PeoplePK peopleKey = new PeoplePK();
    private int age;
    public PeoplePK getPeopleKey() {
        return peopleKey;
    }
    public void setPeopleKey(PeoplePK peopleKey) {
        this.peopleKey = peopleKey;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
	
	public People() {
	}
    public People(PeoplePK peopleKey, int age) {
		this.peopleKey = peopleKey;
		this.age = age;
	}
	@Override
	public String toString() {
		return "People [peopleKey=" + peopleKey + ", age=" + age + "]";
	}
    
}

