package ru.vkonevskiy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GameField gameField = new GameField(5);
        /*for (int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                if (gameField.gameEdges[i][j]!=null)
                {
                    gameField.gameEdges[i][j].isSet = true;
                }
            }*/
        //gameField.gameField[0][0].botEdge.isSet=true;
        //gameField.gameField[2][1].botEdge.isSet=true;
        //gameField.gameField[2][3].botEdge.isSet=true;
        gameField.Draw();
        int counter =0;

        Scanner in = new Scanner(System.in);

        while (true) {
            counter++;
            if (counter % 2 == 0)
                System.out.println("Current step for first player");
            else
                System.out.println("Current step for second player");
            String step = in.nextLine();
            int x = Character.getNumericValue(step.charAt(0));
            int y = Character.getNumericValue(step.charAt(1));
            try {
                gameField.setMark(x, y);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
