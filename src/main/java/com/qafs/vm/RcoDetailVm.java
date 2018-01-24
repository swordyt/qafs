package com.qafs.vm;

import java.util.List;

import com.qafs.domain.RequirementChangeOrderWithBLOBs;

public class RcoDetailVm extends RequirementChangeOrderWithBLOBs {
	private String changeapplicantName;
	private String changedealmanName;
	private String orderstatusName;
	private List<String> developmentengineerName;
	private List<String> operationengineerName;
	private List<String> testengineerName;

	public String getChangeapplicantName() {
		return changeapplicantName;
	}

	public void setChangeapplicantName(String changeapplicantName) {
		this.changeapplicantName = changeapplicantName;
	}

	public String getChangedealmanName() {
		return changedealmanName;
	}

	public void setChangedealmanName(String changedealmanName) {
		this.changedealmanName = changedealmanName;
	}

	public String getOrderstatusName() {
		return orderstatusName;
	}

	public void setOrderstatusName(String orderstatusName) {
		this.orderstatusName = orderstatusName;
	}

	public List<String> getDevelopmentengineerName() {
		return developmentengineerName;
	}

	public void setDevelopmentengineerName(List<String> developmentengineerName) {
		this.developmentengineerName = developmentengineerName;
	}

	public List<String> getOperationengineerName() {
		return operationengineerName;
	}

	public void setOperationengineerName(List<String> operationengineerName) {
		this.operationengineerName = operationengineerName;
	}

	public List<String> getTestengineerName() {
		return testengineerName;
	}

	public void setTestengineerName(List<String> testengineerName) {
		this.testengineerName = testengineerName;
	}

}
