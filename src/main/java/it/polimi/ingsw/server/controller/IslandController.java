package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Island;
import it.polimi.ingsw.server.model.PlayGround;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.TColour;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows to verify the influence count on an island as
 * well as updating the islands asset right after the influence status of an
 * island has been modified
 */

public class IslandController {

    private PlayGround playGround = null;

    public IslandController()
    {
    }

    public void setPlayGround(PlayGround playGround) {
        this.playGround = playGround;
    }

    public PlayGround getPlayGround() {
        return this.playGround;
    }

    /**
     * This method calculates (only for 2-3 player modes) the influence count for an island
     * @return the player that has the highest influence
     */
    public Player checkInfluence(){
        int counter = 0;
        int maxCounter = 0;
        Player maxPlayer = null;
        Island mothernatureIsland = playGround.getIslandWithMotherNature();
        for (Player player : playGround.getPlayersList()){
            for(int index = 0; index < playGround.getProfessorsControl().length; index++){
                if(player.getNickname().equals(playGround.getProfessorsControl()[index]))
                    counter = counter + mothernatureIsland.getPlacedStudent()[index];
            }
            if (counter > maxCounter){
                maxPlayer = player;
                maxCounter = counter;
            }
            counter = 0;
        }
        return maxPlayer;
    }


    /**
     * This method checks whether the islands on the right or on the left of the
     * island taken as a parameter have the same color of their tower, if they do, then
     * a new island is created, the 2 islands to unify get removed from the islands array in playground
     * and a new island is added in their place
     * @param island from which the propagation has to start
     */
    public void islandUnification(Island island){
        for(Island nearbyIsland: island.getNearbyIslands()) {
            if (nearbyIsland.getTowerColour() == island.getTowerColour()) {
                Island newIsland = island.unifyIslands(nearbyIsland);
                List<Island> newIslandsList = playGround.getIslands();
                newIslandsList.set(playGround.getIslands().indexOf(island), newIsland);
                newIslandsList.remove(nearbyIsland);
                playGround.setIslands(newIslandsList);
                //Set the new nearby islands
                List<Island> newNearbyIslands = new ArrayList<>();
                if(newIslandsList.indexOf(newIsland) == 0){
                    newNearbyIslands.add(newIslandsList.get(newIslandsList.size() - 1));
                    newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(newIsland) + 1));
                } else if(newIslandsList.indexOf(newIsland) == newIslandsList.size()-1){
                    newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(newIsland) - 1));
                    newNearbyIslands.add(newIslandsList.get(0));
                } else {
                    newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(newIsland) - 1));
                    newNearbyIslands.add(newIslandsList.get(newIslandsList.indexOf(newIsland) + 1));
                }
                newIsland.setNearbyIslands(newNearbyIslands);
                island = newIsland;
                newIsland.setTowerColour(nearbyIsland.getTowerColour());
            }
        }
    }
}
