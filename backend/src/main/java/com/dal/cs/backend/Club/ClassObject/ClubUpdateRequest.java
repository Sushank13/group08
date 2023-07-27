package com.dal.cs.backend.Club.ClassObject;

import com.dal.cs.backend.Club.Enum.RequestStatus;
import com.dal.cs.backend.Club.Enum.RequestType;

public class ClubUpdateRequest {
    String requestID;
    String clubID;
    String requesterEmailID;
    String categoryID;
    String categoryName;
    String clubName;
    String description;
    String facebookLink;
    String instagramLink;
    String location;
    String meetingTime;
    String clubImage;
    String rules;
    RequestType requestType;
    RequestStatus requestStatus;

    public ClubUpdateRequest(String requestID, String clubID, String requesterEmailID, String categoryID, String categoryName, String clubName, String description, String facebookLink, String instagramLink, String location, String meetingTime, String clubImage, String rules, RequestType requestType, RequestStatus requestStatus) {
        this.requestID = requestID;
        this.clubID = clubID;
        this.requesterEmailID = requesterEmailID;
        this.categoryID = categoryID;
        this.clubName = clubName;
        this.description = description;
        this.facebookLink = facebookLink;
        this.instagramLink = instagramLink;
        this.location = location;
        this.meetingTime = meetingTime;
        this.clubImage = clubImage;
        this.rules = rules;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
        this.categoryName = categoryName;
    }
}
