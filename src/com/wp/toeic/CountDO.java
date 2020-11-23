package com.wp.toeic;

public class CountDO {
	private String id;
	private int count;
	private int score;
	
	public CountDO(String id, int count, int score) {
		super();
		this.id = id;
		this.count = count;
		this.score = score;
	}
	
	public CountDO() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
