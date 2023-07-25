package com.dal.cs.backend.Club.ClassObject;

public class JoinClubRequest
{
    private String requestID;
    private String requesterEmailID;
    private String clubID;
    private String joiningReason;
    private String requestStatus;

    public JoinClubRequest(String requestID, String requesterEmailID, String clubID, String joiningReason, String requestStatus) {
        this.requestID = requestID;
        this.requesterEmailID = requesterEmailID;
        this.clubID = clubID;
        this.joiningReason = joiningReason;
        this.requestStatus = requestStatus;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequesterEmailID() {
        return requesterEmailID;
    }

    public void setRequesterEmailID(String requesterEmailID) {
        this.requesterEmailID = requesterEmailID;
    }

    public String getClubID() {
        return clubID;
    }

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public String getJoiningReason() {
        return joiningReason;
    }

    public void setJoiningReason(String joiningReason) {
        this.joiningReason = joiningReason;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
