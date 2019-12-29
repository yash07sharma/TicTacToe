package com.example.tictactoe;

public class Board {
    boolean end=false, turn=false;
    char cells[][]=new char[3][3], symbol;
    public Board()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                cells[i][j]=' ';
        }
        end=false;turn=false;
    }
    public char checkEnd()
    {
        end=true;
        for(int i=0;i<3;i++)
        {
            if(cells[i][0]==cells[i][1]&&cells[i][1]==cells[i][2]&&cells[i][0]!=' ')
                return cells[i][0];
            if(cells[0][i]==cells[1][i]&&cells[1][i]==cells[2][i]&&cells[0][i]!=' ')
                return cells[0][i];
        }
        if(cells[0][0]==cells[1][1] && cells[1][1]==cells[2][2] && cells[2][2]!=' ')
            return cells[0][0];
        if(cells[0][2]==cells[1][1] && cells[1][1]==cells[2][0] && cells[2][0]!=' ')
            return cells[0][2];

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(cells[i][j]==' ')
                {
                    end=false;
                    return ' ';
                }
        return 'D';
    }
    public boolean isEnd()
    {
        return end;
    }
    public boolean move(int i,int j)
    {
        if(cells[i][j]==' ')
        cells[i][j]=symbol;
        else
            return false;
        return true;
    }

    public char Turn() {
        if(turn)
            symbol='X';
        else
            symbol='O';
        turn=!turn;
        return symbol;
    }
    public String print()
    {
        String s="";
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                s+=cells[i][j]+" ";
            s+="\n";
        }
        return s;
    }
}
