package it.polimi.ingsw.server.model;
import it.polimi.ingsw.server.controller.Exceptions.EmptyTowerYard;
import it.polimi.ingsw.server.controller.Exceptions.GameWonException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    int towerYard;
    int[] entranceRoom = {1,2,0,0,4};
    int[] diningRoom = {0,0,0,0,0};
    Board board;

    @BeforeEach
    void setUp() {
        towerYard = 8;
        board = new Board(entranceRoom, towerYard, TColour.BLACK);
    }

    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    public void testAddStudentEntrance(){
        int[] newStudents = {1,0,2,0,1};
        int[] result = {2,2,2,0,5};
        board.addStudentEntrance(newStudents);
        assertArrayEquals(result, board.getEntranceRoom());
    }

    @Test
    public void testRemoveStudentEntrance(){
        int[] result = {0,2,0,0,4};
        try{
            board.removeStudentEntrance(0);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertArrayEquals(result, board.getEntranceRoom());
    }

    @Test
    public void testIncreaseNumberOfStudent(){
        board.increaseNumberOfStudent(1);
        assertEquals(1,board.getNumberOfStudent(1));
    }

    @Test
    public void testIncreaseTowerYard(){
        board.increaseTowerYard();
        assertEquals(9,board.getTowerYard());
    }

    @Test
    public void testDecreaseTowerYard(){
        try {
            board.decreaseTowerYard();
        } catch (EmptyTowerYard e){
            e.printStackTrace();
        }
        assertEquals(7,board.getTowerYard());
    }

}