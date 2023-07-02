package Club.DataLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class ClubDataLayerTest {

    ClubDataLayer clubDataLayer = new ClubDataLayer() ;

    @Test
    void getAllClubCategoriesTest(){
        try {
            ArrayList<HashMap<String, String>> result = clubDataLayer.getAllClubCategories();
            System.out.println("result = " + result);
        }
        catch (Exception e) {
            System.out.println("Exception occured!");
            System.out.println(e);
        }
    }
}
