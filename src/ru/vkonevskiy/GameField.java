package ru.vkonevskiy;

public class GameField {
    Field gameField[][];
    Edge gameEdges[][];
    int size;
    GameField(int _size) {
        size = _size;

        gameEdges = new Edge[9][9];
        gameEdges[0][1] = new Edge(0, 1);
        gameEdges[0][3] = new Edge(0, 3);
        gameEdges[0][5] = new Edge(0, 5);
        gameEdges[0][7] = new Edge(0, 7);

        gameEdges[1][0] = new Edge(1, 0);
        gameEdges[1][2] = new Edge(1, 2);
        gameEdges[1][4] = new Edge(1, 4);
        gameEdges[1][6] = new Edge(1, 6);
        gameEdges[1][8] = new Edge(1, 8);

        gameEdges[2][1] = new Edge(2, 1);
        gameEdges[2][3] = new Edge(2, 3);
        gameEdges[2][5] = new Edge(2, 5);
        gameEdges[2][7] = new Edge(2, 7);

        gameEdges[3][0] = new Edge(3, 0);
        gameEdges[3][2] = new Edge(3, 2);
        gameEdges[3][4] = new Edge(3, 4);
        gameEdges[3][6] = new Edge(3, 6);
        gameEdges[3][8] = new Edge(3, 8);

        gameEdges[4][1] = new Edge(4, 1);
        gameEdges[4][3] = new Edge(4, 3);
        gameEdges[4][5] = new Edge(4, 5);
        gameEdges[4][7] = new Edge(4, 7);

        gameEdges[5][0] = new Edge(5, 0);
        gameEdges[5][2] = new Edge(5, 2);
        gameEdges[5][4] = new Edge(5, 4);
        gameEdges[5][6] = new Edge(5, 6);
        gameEdges[5][8] = new Edge(5, 8);

        gameEdges[6][1] = new Edge(6, 1);
        gameEdges[6][3] = new Edge(6, 3);
        gameEdges[6][5] = new Edge(6, 5);
        gameEdges[6][7] = new Edge(6, 7);

        gameEdges[7][0] = new Edge(7, 0);
        gameEdges[7][2] = new Edge(7, 2);
        gameEdges[7][4] = new Edge(7, 4);
        gameEdges[7][6] = new Edge(7, 6);
        gameEdges[7][8] = new Edge(7, 8);

        gameEdges[8][1] = new Edge(8, 1);
        gameEdges[8][3] = new Edge(8, 3);
        gameEdges[8][5] = new Edge(8, 5);
        gameEdges[8][7] = new Edge(8, 7);

        gameField = new Field[4][4];
        gameField[0][0] = new Field(gameEdges[1][0],
                gameEdges[0][1],gameEdges[1][2],gameEdges[2][1]);
        gameField[0][1] = new Field(gameEdges[1][2],
                gameEdges[0][3],gameEdges[1][4],gameEdges[2][3]);
        gameField[0][2] = new Field(gameEdges[1][4],
                gameEdges[0][5],gameEdges[1][6],gameEdges[2][5]);
        gameField[0][3] = new Field(gameEdges[1][6],
                gameEdges[0][7],gameEdges[1][8],gameEdges[2][7]);

        gameField[1][0] = new Field(gameEdges[3][0],
                gameEdges[2][1],gameEdges[3][2],gameEdges[4][1]);
        gameField[1][1] = new Field(gameEdges[3][2],
                gameEdges[2][3],gameEdges[3][4],gameEdges[4][3]);
        gameField[1][2] = new Field(gameEdges[3][4],
                gameEdges[2][5],gameEdges[3][6],gameEdges[4][5]);
        gameField[1][3] = new Field(gameEdges[3][6],
                gameEdges[2][7],gameEdges[3][8],gameEdges[4][7]);

        gameField[2][0] = new Field(gameEdges[5][0],
                gameEdges[4][1],gameEdges[5][2],gameEdges[6][1]);
        gameField[2][1] = new Field(gameEdges[5][2],
                gameEdges[4][3],gameEdges[5][4],gameEdges[6][3]);
        gameField[2][2] = new Field(gameEdges[5][4],
                gameEdges[4][5],gameEdges[5][6],gameEdges[6][5]);
        gameField[2][3] = new Field(gameEdges[5][6],
                gameEdges[4][7],gameEdges[5][8],gameEdges[6][7]);

        gameField[3][0] = new Field(gameEdges[7][0],
                gameEdges[6][1],gameEdges[7][2],gameEdges[8][1]);
        gameField[3][1] = new Field(gameEdges[7][2],
                gameEdges[6][3],gameEdges[7][4],gameEdges[8][3]);
        gameField[3][2] = new Field(gameEdges[7][4],
                gameEdges[6][5],gameEdges[7][6],gameEdges[8][5]);
        gameField[3][3] = new Field(gameEdges[7][6],
                gameEdges[6][7],gameEdges[7][8],gameEdges[8][7]);
    }

    public void Draw() {
        System.out.println("    012345678");
        for (int i = 0; i < 9; i++) {
            System.out.print("i="+ i + " ");
            for (int j = 0; j < 9; j++) {
                if (gameEdges[i][j] != null) {

                    if (gameEdges[i][j].isSet) {
                        if (i % 2 == 0)
                            System.out.print('-');
                        else
                        {
                            System.out.print("|");
                        }
                    } else {
                        System.out.print("*");
                    }
                } else {
                    if (i%2==0)
                        System.out.print("#");
                    else
                    {
                        int newX = i/2;
                        int newY = j/2;
                        if (newX<4)
                            System.out.print(gameField[newX][newY].getLetter());
                    }
                }
            }
            System.out.println(" ");
        }
    }

    public void setMark(Player player,int x,int y) throws Exception {
        int counterProbablyField = 0;
        if (gameEdges[x][y]!=null)
            if(!gameEdges[x][y].isSet) {
                gameEdges[x][y].isSet = true;

                Field[] probablyFields = getProbablyFields();
                for(int k=0;k<probablyFields.length;k++)
                {
                    if(probablyFields[k]!= null)
                    {
                        counterProbablyField++;
                        probablyFields[k].CheckAndLock(player.letter);
                        player.stepAgain = true;
                    }
                }
                if (counterProbablyField==0)
                {
                    player.stepAgain = false;
                }
                Draw();
            }
            else
                throw new Exception("already Setted");
        else
            throw new Exception("current edge row-column is NULL!");
    }

    public Field[] getProbablyFields()
    {
        int counter=0;
        Field result[] = new Field[2];
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
            {
                if(gameField[i][j].isAvailable())
                {
                    result[counter] = gameField[i][j];
                    counter++;
                }
            }
        return result;
    }

    public boolean GameIsOver(String firstLetter, String secondLetter)
    {
        int leter1 = 0;
        int leter2 = 0;
        boolean result = true;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                if (gameField[i][j].getLetter().equals(" "))
                {
                    result = false;
                }

        if (result)
        {
            for(int i=0;i<4;i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    if (gameField[i][j].getLetter().equals(firstLetter))
                    {
                        leter1++;
                    }
                    if (gameField[i][j].getLetter().equals(secondLetter))
                    {
                        leter2++;
                    }
                }
            }
            if (leter1>leter2)
            {
                System.out.println("Player with letter '"+firstLetter+"' WIN! Congratulations!");
            }
            else if (leter2>leter1)
            {
                System.out.println("Player with letter '"+secondLetter+"' WIN! Congratulations!");
            }
            else
            {
                System.out.println("nobody Win!");
            }
            System.out.println("\tResuts:");
            System.out.println("'"+firstLetter+"' = "+ leter1);
            System.out.println("'"+secondLetter+"' = "+ leter2);
            System.out.print("Thank you for game!");

        }
        return result;
    }
}
