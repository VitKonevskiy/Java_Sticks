package ru.vkonevskiy;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote
{
    public void Draw() throws RemoteException;
    public boolean GameIsOver() throws RemoteException;
    public void setMark(int id,int x,int y) throws RemoteException;
    public Player createPlayer(String letter) throws RemoteException;
    public boolean allowStep(int id) throws RemoteException;
    public boolean start() throws RemoteException;
    public GameField getGameField() throws RemoteException;
    public boolean allowStepAgain(int id) throws RemoteException;
}
