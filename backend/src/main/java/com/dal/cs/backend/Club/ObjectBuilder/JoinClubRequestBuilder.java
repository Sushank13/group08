package com.dal.cs.backend.Club.ObjectBuilder;

public class JoinClubRequestBuilder
{
    private String requestID;
    private String requesterEmailID;
    private String clubID;
    private String joiningReason;
    private String requestStatus;

    public JoinClubRequestBuilder setRequestID(String requestID) {
        this.requestID = requestID;
        return this;
    }

    public JoinClubRequestBuilder setRequesterEmailID(String requesterEmailID) {
        this.requesterEmailID = requesterEmailID;
        return this;
    }

    public JoinClubRequestBuilder setClubID(String clubID) {
        this.clubID = clubID;
        return this;
    }

    public JoinClubRequestBuilder setJoiningReason(String joiningReason) {
        this.joiningReason = joiningReason;
        return this;
    }

    public JoinClubRequestBuilder setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }
}
