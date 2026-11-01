package com.mycompany.finalp.Factories;

import com.mycompany.finalp.StandardRoom;
import com.mycompany.finalp.Room;

public class StandardRoomFactory extends RoomFactory {
    @Override
    public Room createRoom() { return new StandardRoom(); }
}