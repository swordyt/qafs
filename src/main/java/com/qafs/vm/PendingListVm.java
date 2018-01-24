package com.qafs.vm;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "prototype")
public class PendingListVm {
	private Integer id;
	private Integer orderId;
	private Date onlinetime;
	private String onlinetitle;
	private Integer confirmstatus;
	private String confirmstatusName;
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getConfirmstatusName() {
		return confirmstatusName;
	}

	public void setConfirmstatusName(String confirmstatusName) {
		this.confirmstatusName = confirmstatusName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(Date onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getOnlinetitle() {
		return onlinetitle;
	}

	public void setOnlinetitle(String onlinetitle) {
		this.onlinetitle = onlinetitle;
	}

	public Integer getConfirmstatus() {
		return confirmstatus;
	}

	public void setConfirmstatus(Integer confirmstatus) {
		this.confirmstatus = confirmstatus;
	}

}
