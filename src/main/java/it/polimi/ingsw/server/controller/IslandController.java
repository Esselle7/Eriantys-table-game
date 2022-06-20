package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.controller.Exceptions.GameWonException;
import it.polimi.ingsw.server.model.Colour;
import it.polimi.ingsw.server.model.Island;
import it.polimi.ingsw.server.model.PlayGround;
import it.polimi.ingsw.server.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows to verify the influence count on an island as
 * well as updating the islands' asset right after the influence status of an
 * island has been modified.
 */
public class IslandController {
    private int bannedColour;

    public IslandController()
    {
        bannedColour = Colour.colourCount +1;
    }

    public void setBannedColour(int bannedColour) {
        this.bannedColour = bannedColour;
    }

    public int getBannedColour() {
        return bannedColour;
    }

    /**
     * This method calculates the influence count for an island
     * @param inputIsland the island on which influence has to be calculated
     * @param playGround the playground on which everything resides
     * @return the player that has the highest influence on it, null if there is a draw
     */
    public Player checkInfluence(Island inputIsland, PlayGround playGround){
        int counter = 0;
        int maxCounter = 0;
        boolean draw = false;
        Player maxPlayer = null;
        for (Player player : playGround.getPlayersList()){
            for(int index = 0; index < playGround.getProfessorsControl().length; index++){
                if(player.getNickname().equals(playGround.getProfessorsControl()[index]) && index != getBannedColour())
                    counter = counter + inputIsland.getPlacedStudent()[index];
            }
            if(player.getPlayerBoard().getTowerColour().equals(inputIsland.getTowerColour()) && !inputIsland.isTowersBanned()) {
                counter = counter + inputIsland.getTowerCount();
            }
            counter = counter + player.getExtraInfluence();
            if (counter > maxCounter){
                maxPlayer = player;
                maxCounter = counter;
                draw = false;
            } else if(counter == maxCounter){
                draw = true;
            }
            counter = 0;
        }
        if(draw)
            return null;
        else
            return maxPlayer;
    }


    /**
     * This method checks whether the islands on the right or on the left of the
     * island taken as a parameter have the same color of their tower, if they do, then
     * a new island is created, the 2 islands to unify get removed from the islands array in playground
     * and a new island is added in their place
     * @param island from which the propagation has to start
     * @param playGround the playground on which everything resides
     * @throws GameWonException in case there are 3 or fewer islands remaining
     */
    public void islandUnification(Island island, PlayGround playGround) throws GameWonException{
        this.updateNearbyIslands(island,playGround);
        for(Island nearbyIsland: island.getNearbyIslands()) {
            if (nearbyIsland.getTowerColour() == island.getTowerColour()) {
                Island newIsland = island.unifyIslands(nearbyIsland);
                List<Island> newIslandsList = playGround.getIslands();
                newIslandsList.set(playGround.getIslands().indexOf(island), newIsland);
                newIslandsList.remove(nearbyIsland);
                playGround.setIslands(newIslandsList);
                this.updateNearbyIslands(newIsland,playGround);
                island = newIsland;
                newIsland.setTowerColour(nearbyIsland.getTowerColour());
                playGround.setIslandWithMotherNature(newIsland);
            }
        }
        if(playGround.getIslands().size() <= 3)
            throw new GameWonException();
    }
    /**
     * This method updates island.NearbyIslands by looking at the intended Island's position
     * in PlayGround.getIslands().
     * After the execution, the first element of island.getNearbyIslands() is the island previous/on the left
     * compared to island while the second and last element is the island after/on the right
     * @param island whose nearby islands have to be updated
     * @param playGround the playground on which everything resides
     */
    public void updateNearbyIslands(Island island, PlayGround playGround){
        List<Island> newIslandsList = playGround.getIslands();
        List<Island> newNearbyIslands = new ArrayList<>();
        if(newIslandsList.indexOf(island) == 0){
            newNearbyIslands.add(newIslandsList.get(newIslandsList.size() - 1));
            newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(island) + 1));
        } else if(newIslandsList.indexOf(island) == newIslandsList.size()-1){
            newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(island) - 1));
            newNearbyIslands.add(newIslandsList.get(0));
        } else {
            newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(island) - 1));
            newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(island) + 1));
        }
        island.setNearbyIslands(newNearbyIslands);
    }
}
