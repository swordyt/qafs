package com.qafs.domain.bk;

public class RequirementChangeOrderWithBLOBs extends RequirementChangeOrder {
    private String onlinecontent;

    private String developmentengineer;

    private String operationengineer;

    private String testengineer;

    private String relatedsystem;

    private String networkconfigurationchange;

    private String changesystemorder;

    private String databasechange;

    private String configurationfilechange;

    private String rollbackplan;

    private String upyesandno;

    private String comment;

    public String getOnlinecontent() {
        return onlinecontent;
    }

    public void setOnlinecontent(String onlinecontent) {
        this.onlinecontent = onlinecontent == null ? null : onlinecontent.trim();
    }

    public String getDevelopmentengineer() {
        return developmentengineer;
    }

    public void setDevelopmentengineer(String developmentengineer) {
        this.developmentengineer = developmentengineer == null ? null : developmentengineer.trim();
    }

    public String getOperationengineer() {
        return operationengineer;
    }

    public void setOperationengineer(String operationengineer) {
        this.operationengineer = operationengineer == null ? null : operationengineer.trim();
    }

    public String getTestengineer() {
        return testengineer;
    }

    public void setTestengineer(String testengineer) {
        this.testengineer = testengineer == null ? null : testengineer.trim();
    }

    public String getRelatedsystem() {
        return relatedsystem;
    }

    public void setRelatedsystem(String relatedsystem) {
        this.relatedsystem = relatedsystem == null ? null : relatedsystem.trim();
    }

    public String getNetworkconfigurationchange() {
        return networkconfigurationchange;
    }

    public void setNetworkconfigurationchange(String networkconfigurationchange) {
        this.networkconfigurationchange = networkconfigurationchange == null ? null : networkconfigurationchange.trim();
    }

    public String getChangesystemorder() {
        return changesystemorder;
    }

    public void setChangesystemorder(String changesystemorder) {
        this.changesystemorder = changesystemorder == null ? null : changesystemorder.trim();
    }

    public String getDatabasechange() {
        return databasechange;
    }

    public void setDatabasechange(String databasechange) {
        this.databasechange = databasechange == null ? null : databasechange.trim();
    }

    public String getConfigurationfilechange() {
        return configurationfilechange;
    }

    public void setConfigurationfilechange(String configurationfilechange) {
        this.configurationfilechange = configurationfilechange == null ? null : configurationfilechange.trim();
    }

    public String getRollbackplan() {
        return rollbackplan;
    }

    public void setRollbackplan(String rollbackplan) {
        this.rollbackplan = rollbackplan == null ? null : rollbackplan.trim();
    }

    public String getUpyesandno() {
        return upyesandno;
    }

    public void setUpyesandno(String upyesandno) {
        this.upyesandno = upyesandno == null ? null : upyesandno.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}