package it.polimi.ingsw.client.model;


public class ClientColour {
    private final int colourCount;
    String[] studentColours;

    public ClientColour() {
        colourCount = 5;
        studentColours = new String[colourCount];
        studentColours[0] = "RED";
        studentColours[1] = "GREEN";
        studentColours[2] = "BLUE";
        studentColours[3] = "PINK";
        studentColours[4] = "YELLOW";
    }

    public int getColourCount() {
        return colourCount;
    }

    public String[] getStudentColours() {
        return studentColours;
    }
}