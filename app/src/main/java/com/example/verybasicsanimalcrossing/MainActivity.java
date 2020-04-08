package com.example.verybasicsanimalcrossing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    TextView point, textView3;
    ImageButton guess;
    RadioButton bat, dog, shark;
    RadioGroup animals;
    SeekBar batS, dogS, sharkS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        textView3.setVisibility(View.INVISIBLE);

        //Su kien khi chon mot con vat:
        animals.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonbat:
                        Toast.makeText(MainActivity.this, "Bạn cược Rơi sẽ về nhất!", 0).show();
                        break;
                    case R.id.radioButtondog:
                        Toast.makeText(MainActivity.this, "Bạn cược Chó sẽ về nhất!", 0).show();
                        break;
                    case R.id.radioButtonshark:
                        Toast.makeText(MainActivity.this, "Bạn cược Cá mập sẽ về nhất!", 0).show();
                        break;
                }
            }
        });

        //Su kien sau khi nhan nut TRY
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dog.isChecked() && !shark.isChecked() && !bat.isChecked()){
                    Toast.makeText(MainActivity.this, "Ban chua dat cuoc!", 0).show();
                }else{
                    DisableRadioCheck();
                    dogS.setProgress(0);
                    batS.setProgress(0);
                    sharkS.setProgress(0);
                    guess.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    final CountDownTimer countDownTimer = new CountDownTimer(10000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            addRandom(dogS);
                            addRandom(batS);
                            addRandom(sharkS);

                            if (kiemtravedich()){
                                this.cancel();
                                dog.setChecked(false);
                                bat.setChecked(false);
                                shark.setChecked(false);
                                guess.setVisibility(View.VISIBLE);
                                EnableRadioCheck();
                            }

                        }

                        @Override
                        public void onFinish() {
                            this.start();

                        }
                    }.start();
                }

            }
        });
    }

    private boolean kiemtravedich() {
        if(dogS.getProgress() == dogS.getMax()){
            Toast.makeText(MainActivity.this, "Cho da ve dich!", 0).show();
            if(dog.isChecked()) {
                Toast.makeText(MainActivity.this, "Ban da doan dung!", 0).show();
                addPoint();
            }
            else {
                Toast.makeText(MainActivity.this, "Ban da doan sai", 0).show();
                subPoint();
            }
            return true;
        }
        else if(sharkS.getProgress() == sharkS.getMax()){
            Toast.makeText(MainActivity.this, "Ca map da ve dich!", 0).show();
            if(shark.isChecked()) {
                Toast.makeText(MainActivity.this, "Ban da doan dung!", 0).show();
                addPoint();
            }
            else {
                Toast.makeText(MainActivity.this, "Ban da doan sai", 0).show();
                subPoint();
            }
            return true;
        }
        else if(batS.getProgress() == batS.getMax()){
            Toast.makeText(MainActivity.this, "Roi da ve dich!", 0).show();
            if(bat.isChecked()) {
                Toast.makeText(MainActivity.this, "Ban da doan dung!", 0).show();
                addPoint();
            }
            else {
                Toast.makeText(MainActivity.this, "Ban da doan sai", 0).show();
                subPoint();
            }
            return true;
        }

        return false;
    }

    private void addPoint(){// dug cong 50
        int currP = Integer.parseInt(point.getText().toString());
        currP +=50;
        point.setText(String.valueOf(currP));
        textView3.setText("+50");
        textView3.setVisibility(View.VISIBLE);
    }

    private void subPoint(){
        int currP = Integer.parseInt(point.getText().toString());
        currP -=20;
        point.setText(String.valueOf(currP));
        textView3.setText("-20");
        textView3.setVisibility(View.VISIBLE);
    }

    private void addRandom(SeekBar anna){
        Random random = new Random();
        int curr = anna.getProgress();
        int add = random.nextInt(15);
        anna.setProgress(curr + add);
    }

    private void anhxa() {
        point = (TextView) findViewById(R.id.textViewMark);
        guess = (ImageButton) findViewById(R.id.button);
        animals = (RadioGroup) findViewById(R.id.radioGroup);
        bat = (RadioButton) findViewById(R.id.radioButtonbat);
        dog = (RadioButton) findViewById(R.id.radioButtondog);
        shark = (RadioButton) findViewById(R.id.radioButtonshark);
        dogS = (SeekBar) findViewById(R.id.seekBarDog);
        batS = (SeekBar) findViewById(R.id.seekBarBat);
        sharkS = (SeekBar) findViewById(R.id.seekBarShark);
        textView3 = (TextView)findViewById(R.id.textView3);
    }

    private void EnableRadioCheck(){
        boolean cc = true;
        dog.setEnabled(cc);
        bat.setEnabled(cc);
        shark.setEnabled(cc);
    }

    private void DisableRadioCheck(){
        boolean cc = false;
        dog.setEnabled(cc);
        bat.setEnabled(cc);
        shark.setEnabled(cc);
    }
}
