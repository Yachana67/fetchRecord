package com.batch.springBatch.entity;


public class Insurance {

	
	private int Policy;
	private String Category;
	private String NAME;
	private String Email;
	
	
	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Insurance(int policy, String category, String nAME, String email) {
		super();
		Policy = policy;
		Category = category;
		NAME = nAME;
		Email = email;
	}
	@Override
	public String toString() {
		return "Insurance [Policy=" + Policy + ", Category=" + Category + ", NAME=" + NAME + ", Email=" + Email + "]";
	}
	public int getPolicy() {
		return Policy;
	}
	public void setPolicy(int policy) {
		Policy = policy;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	
}
