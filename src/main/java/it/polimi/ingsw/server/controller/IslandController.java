package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Island;
import it.polimi.ingsw.server.model.PlayGround;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.TColour;
import it.polimi.ingsw.server.model.Board;

import java.util.ArrayList;
import java.util.List;

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
     * @return the tower colour of the player that has the highest influence
     */
    public TColour checkInfluence(){
        //lista player = (player1, player2 ecc)
        //lista control = (player2, player1 ecc)
        //lista placedstudent = (7, 5, 3, 4 ecc)
        int counter = 0;
        int max_counter = 0;
        Player maxplayer = null;
        Island mothernatureisland = playGround.getIslandWithMotherNature();
        maxplayer = null;
        for (Player player : playGround.getPlayersList()){
            for(int index = 0; index < playGround.getProfessorsControl().length; index++){
                if(player.equals(playGround.getProfessorsControl()[index]))
                    counter = counter + mothernatureisland.getPlacedStudent()[index];
            }
            if (counter > max_counter){
                maxplayer = player;
                max_counter = counter;
            }
            counter = 0;
        }
        return maxplayer.getPlayerBoard().getTowerColour();
    }


    /**
     * This method checks whether the islands on the right or on the left of the
     * island taken as a parameter have the same color of their tower, if they do, then
     * a new island is created, the 2 islands to unify get removed from the islands array in playground
     * and a new island is added in their place
     * @param island from which the propagation has to start
     */
    public void island_unification(Island island){
        for(Island nearbyisland: island.getNearbyIslands()) {
            if (nearbyisland.getTowerColour() == island.getTowerColour()) {
                Island new_island = island.unifyIslands(nearbyisland);
                List<Island> new_islands_list = playGround.getIslands();
                //new_islands_list.add(playGround.getIslands().indexOf(island), new_island);
                new_islands_list.set(playGround.getIslands().indexOf(island), new_island);
                //new_islands_list.remove(new_islands_list.indexOf(island));
                new_islands_list.remove(new_islands_list.indexOf(nearbyisland));
                playGround.setIslands(new_islands_list);
                //Set the new nearby islands
                List<Island> new_nearby_islands = new ArrayList<Island>();
                if(new_islands_list.indexOf(new_island) == 0){
                    new_nearby_islands.add(new_islands_list.get(new_islands_list.size() - 1));
                    new_nearby_islands.add(new_islands_list.get(new_islands_list.indexOf(new_island) + 1));
                } else if(new_islands_list.indexOf(new_island) == new_islands_list.size()-1){
                    new_nearby_islands.add(new_islands_list.get(new_islands_list.indexOf(new_island) - 1));
                    new_nearby_islands.add(new_islands_list.get(0));
                } else {
                    new_nearby_islands.add(new_islands_list.get(new_islands_list.indexOf(new_island) - 1));
                    new_nearby_islands.add(new_islands_list.get(new_islands_list.indexOf(new_island) + 1));
                }
                new_island.setNearbyIslands(new_nearby_islands);
                island = new_island;
                new_island.setTowerColour(nearbyisland.getTowerColour());
            }
        }
    }
}
