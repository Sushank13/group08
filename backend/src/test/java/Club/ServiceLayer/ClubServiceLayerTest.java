package Club.ServiceLayer;

import com.dal.cs.backend.Club.ServiceLayer.ClubServiceLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class ClubServiceLayerTest
{
    private ClubServiceLayer clubServiceLayer;

    @BeforeEach
    public void beforeTestRun() {
        clubServiceLayer = new ClubServiceLayer() ;
    }
    @Test
    public void getAllClubCategoriesTest() {
        try {
            ArrayList<HashMap<String, String>> result = clubServiceLayer.getAllClubCategories();
            System.out.println("result: \n" + result);
        }
        catch (Exception e) {
            fail("Test failed: Exception occured- "+e.getMessage());
        }
    }
}
