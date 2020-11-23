package com.wp.toeic;

public class ToeicQuestionDO {
	private int q_id;
	private String q;
	private String a;
	private String b;
	private String c;
	private String d;
	
	public ToeicQuestionDO() {
		
	}
	
	public ToeicQuestionDO(int q_id, String q, String a, String b, String c, String d) {				
		super();
		this.q_id = q_id;
		this.q = q;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}
	
}
