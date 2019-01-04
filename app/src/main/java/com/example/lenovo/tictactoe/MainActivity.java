package com.example.lenovo.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    TextView tPlayer1,tPlayer2;
    Button bReset;
    Button b_[][]=new Button[3][3];
    Boolean player1=true;
    int roundCounts=0,player1points=0,player2points=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tPlayer1=(TextView)findViewById(R.id.tPlayer1);
        tPlayer2=(TextView)findViewById(R.id.tPlayer2);
        bReset=(Button)findViewById(R.id.bReset);
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player1points=0;
                player2points=0;
                tPlayer1.setText("Player 1 : "+player1points);
                tPlayer2.setText("Player 2 : "+player2points);


                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        b_[i][j].setText("");
                    }
                }
                roundCounts=0;
                player1=true;

            }
        });


        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonId="b_"+i+j;
                int resourceId=getResources().getIdentifier(buttonId,"id",getPackageName());
                b_[i][j]=findViewById(resourceId);
                b_[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(!((Button)v).getText().toString().equals(""))
                        {
                            return;
                        }
                        if(player1)
                        {
                            ((Button)v).setText("X");
                        }
                        else
                        {
                            ((Button)v).setText("0");
                        }

                        roundCounts++;

                        if(checkForWin())
                        {
                            if(player1)
                            {
                                player1wins();
                            }
                            else
                            {
                                player2wins();
                            }

                        }
                        else if(roundCounts==9)
                        {
                            draw();
                        }

                        else
                        {
                            player1=!player1;
                        }

                    }              });
            }
        }

    }




    Boolean checkForWin()
    {
        int i,j;
        String[][] check=new String[3][3];
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                check[i][j]=b_[i][j].getText().toString();
            }
        }
        for(i=0;i<3;i++)
        {
            if( (check[i][0].equals(check[i][1])) && (check[i][0].equals(check[i][2]))  &&  (!check[i][0].equals("")) )
            {
                return true;
            }

        }

        for(i=0;i<3;i++)
        {
            if(check[0][i].equals(check[1][i]) && (check[0][i].equals(check[2][i]))&&(!check[0][i].equals("")))
            {
                return true;
            }
        }
        if(check[0][0].equals(check[1][1])  &&  check[0][0].equals(check[2][2]) && (!check[0][0].equals("")))
        {
            return true;
        }


        if(check[0][2].equals(check[1][1])  &&  check[0][2].equals(check[2][0]) && (!check[0][2].equals("")))
        {
            return true;
        }
        return false;
    }

    void player1wins()
    {
        player1points++;
        Toast.makeText(this, "player 1 wins!", Toast.LENGTH_SHORT).show();
        tPlayer1.setText("Player 1 : "+player1points);
        tPlayer2.setText("Player 2: "+player2points);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b_[i][j].setText("");
            }
        }
        roundCounts=0;
        player1=true;

    }
    void player2wins()
    {
        player2points++;
        Toast.makeText(this, "player 2 wins!", Toast.LENGTH_SHORT).show();
        tPlayer1.setText("Player 1 : "+player1points);
        tPlayer2.setText("Player 2: "+player2points);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b_[i][j].setText("");
            }
        }
        roundCounts=0;
        player1=true;

    }
    void draw()
    {
        Toast.makeText(this, "DRAW!!", Toast.LENGTH_SHORT).show();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                b_[i][j].setText("");
            }
        }
        roundCounts=0;
        player1=true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCounts",roundCounts);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1",player1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCounts=savedInstanceState.getInt("roundCounts");
        player1points=savedInstanceState.getInt("player1points");
        player2points=savedInstanceState.getInt("player2points");
        player1=savedInstanceState.getBoolean("player1");
    }
}

