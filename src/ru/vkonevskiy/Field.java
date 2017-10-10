package ru.vkonevskiy;

import java.io.Serializable;

public class Field implements Serializable {
    public Edge leftEdge;
    public Edge topEdge;
    public Edge rightEdge;
    public Edge botEdge;
    private String letter;
    private boolean isLock;

    Field(Edge _leftEdge,Edge _topEdge, Edge _rightEdge, Edge _botEdge)
    {
        leftEdge = _leftEdge;
        rightEdge = _rightEdge;
        topEdge = _topEdge;
        botEdge = _botEdge;
        letter = " ";
        isLock = false;
    }

    public String getLetter()
    {
        return letter;
    }

    public boolean isAvailable()
    {
        boolean result = false;
        if (!isLock &&
                leftEdge.isSet &&
                rightEdge.isSet &&
                topEdge.isSet &&
                botEdge.isSet)
        {
            result = true;
        }
        return result;
    }
    public void CheckAndLock(String _letter)
    {
        if (isAvailable())
        {
            isLock = true;
            letter = _letter;
        }
    }
}
