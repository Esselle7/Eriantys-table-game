package it.polimi.ingsw.server.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    int[] placedStudent = {0,0,0,0,0};
    boolean motherNature;
    int towerCount;
    TColour towerColour;
    Island island;

    @BeforeEach
    void setUp() {
        motherNature = false;
        towerCount = 0;
        towerColour = TColour.BLACK;
        island = new Island();
    }

    @AfterEach
    void tearDown() {
        island = null;
    }
    @Test
    public void testNumberOfStudentByColour(){
        int test = island.numberOfStudentByColour(1);
        assertEquals(0, test);
    }

    @Test
    public void testSetPlacedStudent(){
        island.setPlacedStudent(0);
        assertEquals(1, island.getPlacedStudent()[0]);
    }

    @Test
    public void testUnifyIsland(){
        /*
         * to test this method we just need
         * to generate two island, call the method,
         * verify the total number of students for
         * each color, verify the color of the
         * tower and (what else?)
         */
    }


}