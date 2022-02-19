package com.newlec.notice;

import java.util.Date;

public class Notice {
	int id;
	String title;
	String writer_Id;
	String content;
	Date regdate;
	int hit;
	String files;
	
	public Notice(int id, String title, String writer_Id, String content, Date regdate, int hit, String files) {
		this.id = id;
		this.title = title;
		this.writer_Id = writer_Id;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.files = files;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getWriter_Id() {
		return writer_Id;
	}

	public String getContent() {
		return content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public int getHit() {
		return hit;
	}

	public String getFiles() {
		return files;
	}
	
}
