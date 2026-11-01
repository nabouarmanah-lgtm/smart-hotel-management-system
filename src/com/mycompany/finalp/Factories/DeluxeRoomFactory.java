package com.mycompany.finalp.Factories;

import com.mycompany.finalp.DeluxRoom;
import com.mycompany.finalp.Room;

public class DeluxeRoomFactory extends RoomFactory {
    @Override
    public Room createRoom() { return new DeluxRoom(); }
}