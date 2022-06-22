package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.Exceptions.NoStudentForColour;

import java.io.Serializable;
import java.util.Random;

/**
 * This abstract class simplifies operating with students for the classes that extend it.
 * It also stores the total number of students in the game
 */

public abstract class ManagerStudent implements Serializable {
    private int totalStudentPaws = 130;

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


    /**
     * This method adds to an array of students another array of the students to add.
     * It handles all the colour differences.
     * @param target list to add the students to (e.g. entranceRoom, diningRoom etc.)
     * @param newStudents list whose students have to be added
     * @return the list resulting from the fusion of the two lists
     */
    public int[] addStudentsToTarget(int[] target,int[] newStudents) {
        for(int c = Colour.RED; c < Colour.colourCount; c++ )
        {
            target[c] += newStudents[c];
        }
        return target;
    }

    /**
     * This method creates a random array of students to simulate extracting a number of them from the students bag
     * @param numberToGenerate number of students to generate
     * @return the students generated
     */
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

    /**
     * Random colour selected, useful for the previous methods
     * @return the selected colour
     */
    private int chooseRandomColour()
    {
        return new Random().nextInt(Colour.colourCount);
    }
}
