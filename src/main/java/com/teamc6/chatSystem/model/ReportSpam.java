package com.teamc6.chatSystem.model;

import java.util.Date;

public class ReportSpam {
    private long id;

    private Date timeReport;

    private User reportUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeReport() {
        return timeReport;
    }

    public void setTimeReport(Date timeReport) {
        this.timeReport = timeReport;
    }

    public User getReportUser() {
        return reportUser;
    }

    public void setReportUser(User reportUser) {
        this.reportUser = reportUser;
    }
}
