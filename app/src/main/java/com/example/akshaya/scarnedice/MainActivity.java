package com.example.akshaya.scarnedice;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends AppCompatActivity {
    public int usr_scr=0, usr_tscr=0,com_scr=0,com_tscr=0;

    TextView tview;
    TextView tview2;
    ImageView image;
    Button hold;
    Button roll;
    Handler timerHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tview = (TextView) findViewById(R.id.textView);
        tview2 = (TextView) findViewById(R.id.textView2);
        image = (ImageView) findViewById(R.id.imageView);
        roll = (Button) findViewById(R.id.button);
        hold = (Button) findViewById(R.id.button2);


    }
    public int rollDice(View view)
    {
        Random r = new Random();
        int dice;
        dice = (r.nextInt(6) + 1);

        switch (dice)
        {
            case 1: image.setImageResource(R.drawable.dice1);
                break;
            case 2: image.setImageResource(R.drawable.dice2);
                break;
            case 3: image.setImageResource(R.drawable.dice3);
                break;
            case 4: image.setImageResource(R.drawable.dice4);
                break;
            case 5: image.setImageResource(R.drawable.dice5);
                break;
            case 6: image.setImageResource(R.drawable.dice6);
                break;
        }
        return dice;
    }
    public void holdDice(View view)
    {
        usr_scr = usr_scr + usr_tscr;
        tview.setText("Your score :"+usr_scr);
        usr_tscr=0;
        hold.setEnabled(false);
        roll.setEnabled(false);
        computerTurn(view);
        hold.setEnabled(true);
        roll.setEnabled(true);
    }
    public void resetDice(View view)
    {
        usr_tscr =0;
        usr_scr =0;com_scr=0;com_tscr=0;
        tview.setText("Your score : 0");
        tview2.setText("Computer Score : 0");
    }
    public void userTurn(View view)
    {
        roll.setEnabled(true);
        hold.setEnabled(true);
        int dice = rollDice(view);
        if(dice!=1)
        {
            usr_tscr += dice;
            tview.setText("Your score :"+usr_scr+ "Your turn score:"+usr_tscr);
        }
        else if(dice == 1)
        {
            usr_tscr=0;
            tview.setText("Your score :"+usr_scr+ "Your turn score:"+usr_tscr);
            hold.setEnabled(false);
            roll.setEnabled(false);
            computerTurn(view);
            hold.setEnabled(true);
            roll.setEnabled(true);
        }
        if(com_scr >= 100)
        {
            tview2.setText("Computer wins");
        }
        else if(usr_scr >= 100)
        {
            tview.setText("You win!!");
        }

    }
    public void computerTurn(final View view)
    {
        int dice;
        /*while(com_tscr < 10)
        {
            dice = rollDice(view);
            if (dice == 1)
            {
                com_tscr = 0;
                tview2.setText("Computer score :" + com_scr + " Computer rolled a 1!");
                break;
            }
            else
            {
                com_tscr += dice;
                tview2.setText("Computer Score :" + com_scr + " Computer turn Score :" + com_tscr);
                final android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rollDice(view);
                    }
                },5000);
            }
        }*/
       while(com_tscr<5)
        {
            dice = rollDice(view);
            if(dice==1)
            {
                com_tscr = 0 ;
                tview2.setText("Computer score :"+com_scr+" Computer rolled a 1!");
                break;
            }
            else
            {
                com_tscr+=dice;
                tview2.setText("Computer Score :"+com_scr+" Computer turn Score :"+com_tscr);
                rollDice(view);
            }
        }
        com_scr += com_tscr;
        tview2.setText("Computer score :"+com_scr+" Computer Holds!");
        if(com_scr >= 100)
        {
            tview2.setText("Computer wins");
        }
        else if(usr_scr >= 100)
        {
            tview.setText("You win!!");
        }
    }

}
