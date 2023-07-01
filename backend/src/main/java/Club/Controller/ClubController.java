package Club.Controller;

import Club.ClassObject.Club;
import Club.ServiceLayer.IClubServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubController
{
   @Autowired
   IClubServiceLayer iClubServiceLayer;

   /**
    * This method accepts the club details  for a new club request.
    * @param club is the entity to which all the club details submitted by the user are mapped.
    * @return a message to the user with the request id in case request is submitted or an error message
    * if the request is not submitted
    */
   @RequestMapping(method = RequestMethod.POST,value="/registerClub")
   public String createClubRequest(@RequestBody Club club)
   {
      String message=iClubServiceLayer.createNewClubRequest(club);
      return message;
   }
}
