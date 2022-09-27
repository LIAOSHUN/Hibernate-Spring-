package com.notification.model;

import java.util.List;

;

public interface NotificationDAO_interface {
	 public void insert(NotificationVO notificationVO);
	    public void update(NotificationVO notificationVO);
	    public void delete(Integer noticeID);
	    public NotificationVO findByPrimaryKey(Integer noticeID);
	    public List<NotificationVO> getAll();
}
