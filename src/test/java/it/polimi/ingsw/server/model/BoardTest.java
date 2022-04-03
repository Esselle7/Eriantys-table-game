package it.polimi.ingsw.server.model;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    int towerYard = 8;
    int[] entranceRoom = {1,2,0,0,4};
    int[] diningRoom = {0,0,0,0,1};
    Board board = new Board(entranceRoom, towerYard, TColour.BLACK);

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
        board.removeStudentEntrance(0);
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
        board.decreaseTowerYard();
        assertEquals(7,board.getTowerYard());
    }

}