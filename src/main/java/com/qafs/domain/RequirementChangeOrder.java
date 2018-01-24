package com.qafs.domain;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "prototype")
public class RequirementChangeOrder {
	private Integer id;

	private Integer changeapplicant;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date onlinetime;

	private String onlinetitle;

	private Integer changedealman;

	private String orderstatus;

	private Integer status;

	private Date createtime;

	private Date updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChangeapplicant() {
		return changeapplicant;
	}

	public void setChangeapplicant(Integer changeapplicant) {
		this.changeapplicant = changeapplicant;
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
		this.onlinetitle = onlinetitle == null ? null : onlinetitle.trim();
	}

	public Integer getChangedealman() {
		return changedealman;
	}

	public void setChangedealman(Integer changedealman) {
		this.changedealman = changedealman;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus == null ? null : orderstatus.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}