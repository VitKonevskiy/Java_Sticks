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
        Player player[] = new Player[2];
        player[0] = new Player(0,"A");
        player[1] = new Player(1,"B");
        gameField.Draw();
        int counter = -1;
        int stepPlayer=0;

        Scanner in = new Scanner(System.in);

        while (true) {
            if ((!player[0].stepAgain)&&(!player[1].stepAgain))
                counter++;
            if (counter % 2 == 0)
            {
                stepPlayer = 0;
            }
            else
            {
                stepPlayer = 1;
            }
            System.out.println("Current step for player ID = "+player[stepPlayer].id);
            String step = in.nextLine();
            int x = Character.getNumericValue(step.charAt(0));
            int y = Character.getNumericValue(step.charAt(1));
            try {
                gameField.setMark(player[stepPlayer],x, y);
                if( gameField.GameIsOver(player[0].letter,player[1].letter))
                {
                    break;
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
