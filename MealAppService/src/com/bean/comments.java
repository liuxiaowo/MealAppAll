package com.bean;

public class comments {
	private int id;
	private int breakfastid;
	private int userid;

	private String username;
	private String createtime;
	private String body;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getBreakfastid() {
		return breakfastid;
	}

	public void setBreakfastid(int breakfastid) {
		this.breakfastid = breakfastid;
	}

}
