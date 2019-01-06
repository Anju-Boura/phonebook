package com.example.admin.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class
MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView obj;
    DbHelper db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obj=(ListView) findViewById(R.id.list);
        db=new DbHelper(this);
        ArrayList list=db.getAllContacts();
        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        obj.setAdapter(ad);
        obj.setOnItemClickListener(this);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu:
                Intent i = new Intent(getApplicationContext(),com.example.admin.addressbook.DisplayContact.class);
                Bundle databundle=new Bundle();
                databundle.putInt("id",0);
                i.putExtras(databundle);

                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView t=(TextView) view;

        int id_to_search=db.getId(t.getText().toString());
        System.out.println("id="+id_to_search);

        Bundle databundle=new Bundle();
        databundle.putInt("id",id_to_search);


        Intent i = new Intent(getApplicationContext(),com.example.admin.addressbook.DisplayContact.class);
        i.putExtras(databundle);
        startActivity(i);

    }


}
