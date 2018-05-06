package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);
            GridView gridView = (GridView) findViewById(R.id.grid_view);
            gridView.setNumColumns(2);

            if(savedInstanceState == null){
                FragmentManager fragmentManager = getSupportFragmentManager();

                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legsFragment = new BodyPartFragment();
                fragmentManager.beginTransaction().add(R.id.head_container,headFragment).commit();
                fragmentManager.beginTransaction().add(R.id.body_container,bodyFragment).commit();
                fragmentManager.beginTransaction().add(R.id.leg_container,legsFragment).commit();
            }


        }
        else {
            mTwoPane = false;
        }

        MasterListFragment masterListFragment = new MasterListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.main_container,masterListFragment).commit();
    }

    @Override
    public void onImageSelected(int position) {


        Toast.makeText(this, "Position clicked = "+position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 0;

        int listIndex = position - 12*bodyPartNumber;

        switch (bodyPartNumber) {
            case 0: headIndex = listIndex;
                break;
            case 1:bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
                default:break;
        }

        Bundle b = new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });



    }
}
