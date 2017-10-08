package ru.vkonevskiy;

public class Edge {
    public int x;
    public int y;
    public boolean isSet;

    Edge(int _x, int _y)
    {
        x = _x;
        y = _y;
        isSet = false;
    }
    Edge(int _x,int _y, boolean _isSet)
    {
        x = _x;
        y = _y;
        isSet = _isSet;
    }
}
