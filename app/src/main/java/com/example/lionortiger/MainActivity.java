package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{

        One,Two, No
    }
    int i;
    Player currentplayer = Player.One;
    Player playerchoices[] = new Player[9];
    int[][] winnerrowscolumns = {{0,1,2},{3,4,5},{6,7,8},          //rows
            {0,3,6},{1,4,7},{2,5,8},                               //columns
            {0,4,8},{2,4,6}};                                      //diagonal
    private boolean gameover = false;
    private Button reset;
    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridlayout);
        playerchoices[0] = Player.No;
        playerchoices[1] = Player.No;
        playerchoices[2] = Player.No;
        playerchoices[3] = Player.No;
        playerchoices[4] = Player.No;
        playerchoices[5] = Player.No;
        playerchoices[6] = Player.No;
        playerchoices[7] = Player.No;
        playerchoices[8] = Player.No;
        reset = findViewById(R.id.btnreset);
        reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               resetthegame();
           }
       });

    }
    public void imageviewistapped(View imageview){
        ImageView tappedimageview = (ImageView)imageview;
        int tag = Integer.parseInt(tappedimageview.getTag().toString());

        if(playerchoices[tag]==Player.No && gameover == false) {

            tappedimageview.setTranslationX(-2000);
            playerchoices[tag] = currentplayer;
            if (currentplayer == Player.One) {
                tappedimageview.setImageResource(R.drawable.lion);
                currentplayer = Player.Two;
                i++;
            } else if (currentplayer == Player.Two) {
                tappedimageview.setImageResource(R.drawable.tiger);
                currentplayer = Player.One;
                i++;
            }
            tappedimageview.animate().translationXBy(2000).alpha(1).rotationY(1800).setDuration(2000);
            //Toast.makeText(this, tappedimageview.getTag().toString(), Toast.LENGTH_SHORT);

            for (int[] winnercolumns : winnerrowscolumns) {

                if ((playerchoices[winnercolumns[0]] == playerchoices[winnercolumns[1]] &&
                        playerchoices[winnercolumns[1]] == playerchoices[winnercolumns[2]] &&
                        playerchoices[winnercolumns[0]] != Player.No)||i>=8) {
                    if(playerchoices[winnercolumns[0]] == playerchoices[winnercolumns[1]] &&
                            playerchoices[winnercolumns[1]] == playerchoices[winnercolumns[2]] &&
                            playerchoices[winnercolumns[0]] != Player.No){
                        reset.setVisibility(View.VISIBLE);
                        gameover=true;
                    if (currentplayer == Player.One) {
                        Toast.makeText(this, "Player Two won!", Toast.LENGTH_LONG).show();
                    } else if (currentplayer == Player.Two) {
                        Toast.makeText(this, "Player One won!", Toast.LENGTH_LONG).show();
                    }
                    } else if(i>=8) {
                        reset.setVisibility(View.VISIBLE);
                        gameover=true;
                        Toast.makeText(this, "Its a draw!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }

    }
     // Reset Game function
   private void resetthegame(){

        for(int index = 0; index<gridLayout.getChildCount(); index++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.4f);
        }
        currentplayer = Player.One;

        playerchoices[0] = Player.No;
        playerchoices[1] = Player.No;
        playerchoices[2] = Player.No;
        playerchoices[3] = Player.No;
        playerchoices[4] = Player.No;
        playerchoices[5] = Player.No;
        playerchoices[6] = Player.No;
        playerchoices[7] = Player.No;
        playerchoices[8] = Player.No;

        gameover = false;
        i = 0;

    }
}
