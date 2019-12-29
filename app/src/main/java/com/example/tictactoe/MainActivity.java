package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView boardCells[][]=new ImageView[3][3];
    Board b=new Board();
    GridLayout layout_board;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init()
    {
        layout_board=(GridLayout) findViewById(R.id.layout_board);
        final int green=Color.parseColor("#f5c7c3");
        final int white=Color.parseColor("#5a410e");
        final Button reset=findViewById(R.id.reset);
        final Button title=findViewById(R.id.title);
        final Button player=findViewById(R.id.player);
        reset.setBackgroundColor(green);
        reset.setTextColor(white);
        //title.setBackgroundColor(green);
        title.setTextColor(white);
        title.setBackgroundResource(R.drawable.bg);
        player.setBackgroundColor(green);
        player.setTextColor(white);
        reset.setText(" RESTART ");
        title.setText(" TIC TAC TOE ");
        player.setText("O");

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset.setText(" RESTART ");
                title.setText(" TIC TAC TOE ");
                player.setText("O");
                b=new Board();
                rebuild_board();
            }
        });

        loadBoard();

        final Drawable drawO=new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
                p.setColor(Color.parseColor("#0e275a"));
                canvas.drawOval(105.0f,95.0f,145.0f,135.0f,p);
                p.setColor(Color.parseColor("#f5c7c3"));
                canvas.drawOval(110.0f,100.0f,140.0f,130.0f,p);
            }
            @Override
            public void setAlpha(int alpha) {
            }
            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {
            }
            @Override
            public int getOpacity() {
                return PixelFormat.TRANSPARENT;
            }
        };
        final Drawable drawX=new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
                p.setColor(Color.parseColor("#f63a13"));
                p.setStrokeWidth(5.0f);
                canvas.drawLine(105.0f,95.0f,145.0f,135.0f,p);
                canvas.drawLine(145.0f,95.0f,105.0f,135.0f,p);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.TRANSPARENT;
            }
        };

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                final int x=i,y=j;
                boardCells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!b.isEnd()) {
                            char symbol = b.Turn();
                            boolean flag = b.move(x,y);
                            Log.d("debug","valid move : "+flag);
                            Log.d("debug", "STATES : \n" + b.print());
                            if (!flag) {
                                b.Turn();
                                Toast.makeText(MainActivity.this, "Already filled...", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            char res = b.checkEnd();
                            boardCells[x][y].setImageDrawable((symbol == 'X') ? drawX : drawO);
                            Log.d("debug", "result onClick : " + res + "|");
                            Log.d("debug", "symbol : " + symbol);
                            if (res == 'X') {
                                title.setText(" PLAYER 2 WON !! ");
                                Log.d("debug","player : "+player.getText());
                            }
                            else if (res == 'O') {
                                title.setText(" PLAYER 1 WON !! ");
                                Log.d("debug","player : "+player.getText());
                            }
                            else if (res == 'D') {
                                title.setText(" DRAW !! ");
                                player.setText(" ");
                            }
                            if (b.isEnd())
                            {
                                player.setText(" ");
                                reset.setText(" RESET ");
                            }
                            else
                                player.setText("" + ((symbol == 'X') ? 'O' : 'X'));
                        }
                        else
                            Toast.makeText(MainActivity.this,"No move possible",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    protected void loadBoard() {
        layout_board.setBackgroundColor(Color.parseColor("#ffffff"));
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                boardCells[i][j]=new ImageView(this);
                GridLayout.LayoutParams param=new GridLayout.LayoutParams();
                param.rowSpec=GridLayout.spec(i);
                param.columnSpec=GridLayout.spec(j);
                param.width=250;
                param.height=230;
                param.
                param.bottomMargin=2;
                param.topMargin=2;
                param.leftMargin=2;
                param.rightMargin=2;
                boardCells[i][j].setLayoutParams(param);
                boardCells[i][j].setBackgroundColor(Color.parseColor("#f5c7c3"));
                layout_board.addView(boardCells[i][j]);
            }
        }
    }

    protected void rebuild_board() {
        b=new Board();
        layout_board.removeAllViews();
        init();
    }
}
