package ru.vkonevskiy;

import java.io.Serializable;

public class Player implements Serializable{
    public int id;
    public String letter;
    public boolean stepAgain;

    Player(int _id, String _letter)
    {
        id = _id;
        letter = _letter;
        stepAgain = false;
    }
}
