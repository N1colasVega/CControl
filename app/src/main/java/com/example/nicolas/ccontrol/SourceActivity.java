package com.example.nicolas.ccontrol;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nicolas.ccontrol.add_delete.DeleteCatActivity;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;
import com.example.nicolas.ccontrol.fragment.SrcFragment;

public class SourceActivity extends AppCompatActivity {
    //Переменные
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorce);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SrcFragment srcfragment = (SrcFragment) getSupportFragmentManager().findFragmentByTag("srcfragment");
        if(srcfragment == null){
            srcfragment = new SrcFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, srcfragment, "stcfragment");
            transaction.commit();
        }
        Intent intent = getIntent();
        id = intent.getIntExtra("catid",23);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)return;
        if(data.getIntExtra("deletecatstate",0) == 1)finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.changeCategory:
                break;
            case R.id.deleteCategory:
                intent = new Intent(this,DeleteCatActivity.class);
                intent.putExtra("categoryID", id);
                startActivityForResult(intent, 1);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
