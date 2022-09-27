package com.notification.model;

import java.sql.Timestamp;

public class NotificationVO {
	private Integer noticeID;
	private Integer memID;
	private String noticeDescription;
	private Timestamp noticeTime;
	private Integer noticeRead;

	public Integer getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(Integer noticeID) {
		this.noticeID = noticeID;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getNoticeDescription() {
		return noticeDescription;
	}

	public void setNoticeDescription(String noticeDescription) {
		this.noticeDescription = noticeDescription;
	}

	public Timestamp getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Timestamp noticeTime) {
		this.noticeTime = noticeTime;
	}

	public Integer getNoticeRead() {
		return noticeRead;
	}

	public void setNoticeRead(Integer noticeRead) {
		this.noticeRead = noticeRead;
	}
}
