package ru.vkonevskiy;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
public class Server implements GameInterface {

    Semaphore semaphore;
    private static GameField gameField;
    private Player []player;
    private int counterPlayer = 0;
    private final int MaxPlayers = 2;
    private final int GameFieldSize = 5;
    private int counterStep = 0;
    private int currentStepPlayer = 0;

    public Server()
    {
        gameField = new GameField(GameFieldSize);
        player = new Player[MaxPlayers];
        currentStepPlayer = 0;
        semaphore = new Semaphore(1);
    }

    @Override
    public boolean start() {
        boolean result = true;
        if (player[0]==null || player[1]==null)
            result = false;
        return result;
    }

    @Override
    public GameField getGameField() {
        return gameField;
    }

    @Override
    public void Draw() throws RemoteException
    {
        gameField.Draw();
    }

    @Override
    public boolean allowStep(int id) throws RemoteException {
        boolean result = false;
        try {
            semaphore.acquire();
            if ((!player[0].stepAgain) && (!player[1].stepAgain))
                counterStep++;
            if (counterStep % 2 == 0)
                currentStepPlayer = 0;
            else
                currentStepPlayer = 1;

            for (int i = 0; i < player.length; i++) {
                if (player[i].id == id)
                    result = (i == currentStepPlayer);
            }
            //semaphore.acquire();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Player createPlayer(String letter) throws RemoteException {
        player[counterPlayer] = new Player(counterPlayer,letter);
        counterPlayer++;
        return player[counterPlayer-1];
    }

    @Override
    public boolean allowStepAgain(int id) throws RemoteException {
        return player[id].stepAgain;
    }

    @Override
    public boolean GameIsOver() throws RemoteException {
        return gameField.GameIsOver(player[0],player[1]);
    }

    @Override
    public void setMark(int id, int x, int y) throws RemoteException {
        try {
            gameField.setMark(player[id], x, y);
            if (gameField.GameIsOver(player[0],player[1]) || !player[id].stepAgain)
                semaphore.release();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            GameInterface gameInterface = (GameInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("GameInterface", gameInterface);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}