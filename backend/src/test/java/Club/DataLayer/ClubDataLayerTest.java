package Club.DataLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ClubDataLayerTest {
    private ClubDataLayer clubDataLayer;

    @BeforeEach
    public void beforeTestRun() {
        clubDataLayer = new ClubDataLayer() ;
    }

    @Test
    void getLatestRequestIdTest(){
        try {
            String result = clubDataLayer.getLatestRequestId();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Exception occured: "+e.getMessage());
        }
    }
    @Test
    void getLatestClubIdTest(){
        try {
            String result = clubDataLayer.getLatestClubId();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Exception occured: "+e.getMessage());
        }
    }

}
