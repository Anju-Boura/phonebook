package com.example.admin.addressbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DisplayContact extends AppCompatActivity {

    EditText name, phone, email, address;

    Button btn;
    String namevalue, phonevalue, emailvalue, addressvalue;
    DbHelper db;
    int id_to_update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DbHelper(this);
        setContentView(R.layout.activity_display_contact);
        System.out.println("display");
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        btn = (Button) findViewById(R.id.button);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            int value = b.getInt("id");
            if (value > 0) {
                System.out.println("hello");
                Cursor res = db.getResult(value);
                id_to_update = value;
                res.moveToFirst();
                namevalue = res.getString(res.getColumnIndex("NAME"));
                System.out.println("name=" + namevalue);
                phonevalue = res.getString(res.getColumnIndex("PHONE"));
                emailvalue = res.getString(res.getColumnIndex("EMAIL"));
                addressvalue = res.getString(res.getColumnIndex("ADDRESS"));
                btn.setVisibility(View.INVISIBLE);
                name.setText(namevalue);
                phone.setText(phonevalue);
                email.setText(emailvalue);
                phone.setText(phonevalue);
                address.setText(addressvalue);
                name.setFocusable(false);
                phone.setFocusable(false);
                email.setFocusable(false);
                address.setFocusable(false);
                name.setClickable(false);
                phone.setClickable(false);
                email.setFocusable(false);
                address.setFocusable(false);








            }


        }


    }

    public void save(View v) {
        namevalue = name.getText().toString();
        phonevalue = phone.getText().toString();
        emailvalue = email.getText().toString();
        addressvalue = address.getText().toString();

        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            int value = extras.getInt("id");

            if (value > 0) {
                if (db.update(id_to_update, namevalue, phonevalue, emailvalue, addressvalue)) {
                    Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), com.example.admin.addressbook.MainActivity.class);
                    startActivity(i);

                }
                else
                    {
                    Toast.makeText(this, "not done", Toast.LENGTH_SHORT).show();

                }

            }
            else {
                if (db.insertdata(namevalue, phonevalue, emailvalue, addressvalue)) {
                    System.out.print("insert");
                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), com.example.admin.addressbook.MainActivity.class);
                    startActivity(i);
                }
                else





                    {
                    Toast.makeText(this, "not done", Toast.LENGTH_SHORT).show();

                }
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        Bundle b = getIntent().getExtras();
        if(b!=null) {
            int value = b.getInt("id");
            if (value > 0) {
                inflater.inflate(R.menu.display_contact, menu);

            }
        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_contact:
                btn.setVisibility(View.VISIBLE);
                name.setEnabled(true);
               // name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);
                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);
                address.setEnabled(true);
                address.setFocusableInTouchMode(true);
                address.setClickable(true);


                return true;

            case  R.id.delete_contact :
                AlertDialog.Builder builder =new AlertDialog.Builder(this);
                builder.setMessage("ARE YOU SURE YOU WANT TO DELETE !!!!!")
                        .setPositiveButton("YES",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){
                                db.deletecontacts(id_to_update);

                                Toast.makeText(getBaseContext(),"deleted successfully",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),com.example.admin.addressbook.MainActivity.class);
                            startActivity(i);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog d=builder.create();
                d.setTitle(
                        "ARE YOU SURE"
                );
                d.show();
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }
    }
}
