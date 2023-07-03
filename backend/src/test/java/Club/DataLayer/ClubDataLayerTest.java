package Club.DataLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class ClubDataLayerTest {

    private ClubDataLayer clubDataLayer;

    @BeforeEach
    public void beforeTestRun() {
        clubDataLayer = new ClubDataLayer() ;
    }

    @Test
    void getAllClubCategoriesTest(){
        try {
            ArrayList<HashMap<String, String>> result = clubDataLayer.getAllClubCategories();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Test failed: Exception occured- "+e.getMessage());
        }
    }
}
