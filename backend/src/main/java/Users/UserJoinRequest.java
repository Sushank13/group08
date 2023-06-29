package Users;

public class UserJoinRequest
{
   private  String requestID;
   private String requestorEmailID;
   private String clubID;
   private String joiningReason;
   private RequestStatus requestStatus;

   public String getRequestID() {
      return requestID;
   }

   public void setRequestID(String requestID) {
      this.requestID = requestID;
   }

   public String getRequestorEmailID() {
      return requestorEmailID;
   }

   public void setRequestorEmailID(String requestorEmailID) {
      this.requestorEmailID = requestorEmailID;
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

   public RequestStatus getRequestStatus() {
      return requestStatus;
   }

   public void setRequestStatus(RequestStatus requestStatus) {
      this.requestStatus = requestStatus;
   }
}
