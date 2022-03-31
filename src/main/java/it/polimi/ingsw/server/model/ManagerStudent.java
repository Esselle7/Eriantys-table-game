package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This abstract class is the student manager
 * that allows class (that extends from that) to
 * perform action in array list of students
 */

public abstract class ManagerStudent {

    /**
     * This method make the fusion
     * of two lists given in input by
     * adding position per position
     * each cell value
     * @param target first list to add,
     *               it is the basic list (e.g.
     *               entranceRoom, diningRoom etc.)
     * @param newStudents second list to add,
     *                    usually used as the
     *                    variations to add to the
     *                    first list
     * @return return the target list modified
     */
    public List<Integer> addStudentsToTarget(List<Integer> target,List<Integer> newStudents) {
        for(int c = Colour.RED; c < Colour.colourCount; c++ )
        {
            target.set(c,target.get(c) + newStudents.get(c));
        }
        return target;
    }

    /**
     * This method allows to increase
     * the number of students of a specified
     * colour
     * @param target list where we have to
     *               increase the students count
     * @param studentColour colour of the students to increase
     * @return target list modified
     */
    public List<Integer> addStudentToTarget(List<Integer> target,int studentColour) {
        target.add(studentColour, target.get(studentColour) + 1);
        return target;
    }

    /**
     * This method allows to decrease
     * the number of students of a specified
     * colour
     * @param target list where we have to
     *               decrease the students count
     * @param studentColour colour of the students to decrease
     * @return target list modified
     */
    public List<Integer> removeStudentFromTarget(List<Integer> target,int studentColour) {
        target.set(studentColour,target.get(studentColour) - 1);
        return target;
    }

    public List<Integer> generateStudents(int numberToGenerate)
    {
        List<Integer> result = new ArrayList<>(Colour.colourCount);
        for(int i = 0; i < numberToGenerate; i++)
            addStudentToTarget(result,chooseRandomColour());
        return result;
    }

    private int chooseRandomColour()
    {
        return new Random().nextInt(Colour.colourCount);
    }


}
