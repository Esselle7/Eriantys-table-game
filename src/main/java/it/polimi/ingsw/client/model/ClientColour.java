package it.polimi.ingsw.client.model;


public class ClientColour {
    private final int colourCount;
    String[] studentColours;

    public ClientColour() {
        colourCount = 5;
        studentColours = new String[colourCount];
        studentColours[0] = "Red";
        studentColours[1] = "Green";
        studentColours[2] = "Blue";
        studentColours[3] = "Pink";
        studentColours[4] = "Yellow";
    }

    public int getColourCount() {
        return colourCount;
    }

    public String[] getStudentColours() {
        return studentColours;
    }
}