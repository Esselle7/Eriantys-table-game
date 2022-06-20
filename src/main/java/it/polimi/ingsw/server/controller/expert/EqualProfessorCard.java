package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class EqualProfessorCard extends CharacterCard{

    public EqualProfessorCard(){
        super(2,12);
        setDescription("during this turn you can take control of all the professors even if you have an equal amount of students compared to the player that controls them");
    }

    /**
     * This card updates the professor control array by setting the current player as the priority player and then
     * recalculating professor controls
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getGameMoves().setPriorityPlayer(turnHandler.getCurrentPlayer());
        turnHandler.getGameMoves().checkProfessorsControl();
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Professor control updated accordingly"));
    }

    /**
     * There's no priority player
     */
    @Override
    public void resetCard(TurnHandler turnHandler){
        turnHandler.getGameMoves().setPriorityPlayer(null);
    }
}
