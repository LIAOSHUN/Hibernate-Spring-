package com.actfav.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActFavVO implements Serializable{
	private Integer memID;
	private Integer actID;
	private LocalDateTime actFavDate;
	
	
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public LocalDateTime getActFavDate() {
		return actFavDate;
	}
	public void setActFavDate(LocalDateTime actFavDate) {
		this.actFavDate = actFavDate;
	}
}
