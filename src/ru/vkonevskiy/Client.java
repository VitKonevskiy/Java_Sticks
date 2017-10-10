package ru.vkonevskiy;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String host = "127.0.0.1";
        String letter = in.nextLine(); //(args.length < 2) ? null : args[1];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            GameInterface gameInterface = (GameInterface) registry.lookup("GameInterface");
            System.out.println(gameInterface);

            Player player = gameInterface.createPlayer(letter);
            gameInterface.Draw();

            while(!gameInterface.start());
            { }
            while (true)
            {
               if (gameInterface.allowStep(player.id)) {
                   gameInterface.getGameField().Draw();
                   System.out.println("Current step for player ID = " + player.id);
                   String step = in.nextLine();
                   int x = Character.getNumericValue(step.charAt(0));
                   int y = Character.getNumericValue(step.charAt(1));
                   try {
                       gameInterface.setMark(player.id, x, y);
                       while(gameInterface.allowStepAgain(player.id) && !gameInterface.GameIsOver())
                       {
                           gameInterface.getGameField().Draw();
                           System.out.println("Current step for player ID = " + player.id);
                           step = in.nextLine();
                           x = Character.getNumericValue(step.charAt(0));
                           y = Character.getNumericValue(step.charAt(1));
                           gameInterface.setMark(player.id, x, y);
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   if (gameInterface.GameIsOver())
                       break;
               }
            }


            /*int response2 = stub.summ(5,6);
            System.out.println("summ: " + response2);

            int[] a={3,4,56,6,7,8};
            MyArray mas = new MyArray(a);
            int response3 = stub.getMin(mas);
            System.out.println("min: " + response3);*/

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}