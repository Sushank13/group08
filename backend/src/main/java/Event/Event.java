package Event;

import java.sql.Date;
import java.sql.Time;

public class Event
{
   private String eventID;
   private String eventName;
   private String description;
  private String venue;
  private String clubID;
  private String organizerEmailID;
  private String eventImage;
  private Date startDate;
  private Date endDate;
  private Time startTime;
  private Time endTime;
  private String eventTopic;

   public String getEventID() {
      return eventID;
   }

   public void setEventID(String eventID) {
      this.eventID = eventID;
   }

   public String getEventName() {
      return eventName;
   }

   public void setEventName(String eventName) {
      this.eventName = eventName;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getVenue() {
      return venue;
   }

   public void setVenue(String venue) {
      this.venue = venue;
   }

   public String getClubID() {
      return clubID;
   }

   public void setClubID(String clubID) {
      this.clubID = clubID;
   }

   public String getOrganizerEmailID() {
      return organizerEmailID;
   }

   public void setOrganizerEmailID(String organizerEmailID) {
      this.organizerEmailID = organizerEmailID;
   }

   public String getEventImage() {
      return eventImage;
   }

   public void setEventImage(String eventImage) {
      this.eventImage = eventImage;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Time getStartTime() {
      return startTime;
   }

   public void setStartTime(Time startTime) {
      this.startTime = startTime;
   }

   public Time getEndTime() {
      return endTime;
   }

   public void setEndTime(Time endTime) {
      this.endTime = endTime;
   }

   public String getEventTopic() {
      return eventTopic;
   }

   public void setEventTopic(String eventTopic) {
      this.eventTopic = eventTopic;
   }
}
