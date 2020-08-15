package edmt.dev.kotlineatitv2client;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class halamanpertama extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halamanpertama);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
             try
             {
                 sleep(7000);

             }
             catch (Exception e)
             {
                 e.printStackTrace();

             }
             finally
             {
                 Intent welcomeintent = new Intent(halamanpertama.this, )

             }
            }
        }
    }
}
