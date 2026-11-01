package com.mycompany.finalp.Factories;

import com.mycompany.finalp.SuiteRoom;
import com.mycompany.finalp.Room;

public class SuiteRoomFactory extends RoomFactory {
    @Override
    public Room createRoom() { return new SuiteRoom(); }
}