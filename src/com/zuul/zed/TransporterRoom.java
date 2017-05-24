package com.zuul.zed;

/**
 * Created by nilso on 5/22/2017.
 */
public class TransporterRoom extends Room {
    private Scenario scene;

    /**
     * Create a room that can beam you anywhere.
     */
    public TransporterRoom(String description, Scenario scene)
    {
        super(description);
        this.scene = scene;
    }

    /**
     * Describe the exits. Since this is a transporter room, you see no
     * clear exits.
     */
    public String getExitString()
    {
        return "You feel quite dizzy. Something is strange.\n" +
                "You cannot really see the exits...";
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction)
    {
        return scene.getRandomRoom();
    }
}
