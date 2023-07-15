package com.dal.cs.backend.member.ServiceLayer;

import com.dal.cs.backend.authentication.dataLayer.ILoginDataLayer;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.MemberObject.MemberWithLoginCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceLayer {
    private static final Logger logger = LogManager.getLogger(MemberServiceLayer.class);

    IMemberDataLayer iMemberDataLayer;

    ILoginDataLayer iLoginDataLayer;

    @Autowired
    public MemberServiceLayer(IMemberDataLayer iMemberDataLayer, ILoginDataLayer iLoginDataLayer) {
        this.iMemberDataLayer = iMemberDataLayer;
        this.iLoginDataLayer = iLoginDataLayer;
    }

    /**
     * This method accepts the club details  for a new club request.
     * @param memberWithLoginCredential is the entity to which all the member details submitted by the user are mapped.
     * @return a message to the user with the request id in case request is submitted or an error message
     * if the request is not submitted
     */

    public String createNewMemberRequest(MemberWithLoginCredential memberWithLoginCredential) {
        logger.info("inside createNewMemberRequest");
        boolean createNewMemberRequestStatus = iMemberDataLayer.createNewMember(memberWithLoginCredential.getMember());
        boolean setPasswordStatus = iLoginDataLayer.createLoginCredential(memberWithLoginCredential.getEmailId(), memberWithLoginCredential.getPassword());
        if (createNewMemberRequestStatus && setPasswordStatus) {
            String message = "Your request for new member register send Successfully " ;
            logger.info("new member request created successfully");
            return message;
        }
        return null;
    }
}
