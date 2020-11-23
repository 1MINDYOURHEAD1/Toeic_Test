package com.wp.toeic;

public class AnswerDO {
	private int q_id;
	private int answer;
	
	public AnswerDO() {
		
	}
	
	public AnswerDO(int q_id, int answer) {
		super();
		this.q_id = q_id;
		this.answer = answer;
	}
	
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}

}
