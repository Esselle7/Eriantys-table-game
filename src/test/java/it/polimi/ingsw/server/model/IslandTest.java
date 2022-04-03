package it.polimi.ingsw.server.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    int[] placedStudent = {0,0,0,0,0};
    boolean motherNature = false;
    int towerCount = 0;
    TColour towerColour = TColour.BLACK;
    Island island = new Island();

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