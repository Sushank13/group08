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
   @RequestMapping(method = RequestMethod.POST,value="/registerClub")
   public String createClubRequest(@RequestBody Club club)
   {
      String message=iClubServiceLayer.createNewClubRequest(club);
      return message;
   }
}
