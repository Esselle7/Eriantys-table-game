package it.polimi.ingsw.server.model;

import java.util.Random;

/**
 * This abstract class is the student manager
 * that allows class (that extends from that) to
 * perform action in array list of students.
 * It also store the total number of student
 * paws
 */

public abstract class ManagerStudent {
    private int totalStudentPaws = 130;
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
    public int[] addStudentsToTarget(int[] target,int[] newStudents) {
        for(int c = Colour.RED; c < Colour.colourCount; c++ )
        {
            target[c] += newStudents[c];
        }
        return target;
    }

    public int getTotalStudentPaws() {
        return totalStudentPaws;
    }

    public void setTotalStudentPaws(int totalStudentPaws) {
        this.totalStudentPaws = totalStudentPaws;
    }

    private void decreaseStudentPaws(int numberToDecrease)
    {
        totalStudentPaws-=numberToDecrease;
    }


    public int[] generateStudents(int numberToGenerate)
    {
        setTotalStudentPaws(getTotalStudentPaws()-numberToGenerate);
        if(getTotalStudentPaws()>=0)
        {
            int[] result = new int[Colour.colourCount];
            for(int i = 0; i < numberToGenerate; i++)
                result[chooseRandomColour()]++;
            decreaseStudentPaws(numberToGenerate);
            return result;
        }
        return null; // exception to return
    }

    private int chooseRandomColour()
    {
        return new Random().nextInt(Colour.colourCount);
    }


}
