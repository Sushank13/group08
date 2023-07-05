package Club.DataLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubSecondDataLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class ClubDataLayerTest {

    private IClubDataLayer iclubDataLayer;
    private IClubSecondDataLayer iclubSecondDataLayer;

    @BeforeEach
    public void beforeTestRun() {

        iclubDataLayer = new ClubDataLayer();
        iclubSecondDataLayer = new ClubDataLayer();
    }

    @Test
    void getLatestRequestIdTest(){
        try {
            String result = iclubSecondDataLayer.getLatestRequestId();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Exception occured: "+e.getMessage());
        }
    }
    @Test
    void getLatestClubIdTest(){
        try {
            String result = iclubSecondDataLayer.getLatestClubId();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Exception occured: "+e.getMessage());
        }
    }
    @Test
    void getAllClubCategoriesTest(){
        try {
            ArrayList<HashMap<String, String>> result = iclubDataLayer.getAllClubCategories();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Test failed: Exception occured- "+e.getMessage());
        }
    }
}
