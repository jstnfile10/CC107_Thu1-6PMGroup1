package com.example.group1_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,studid,bday;
    Button insert,update,delete,view;
    MyDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.Name);
        contact=findViewById(R.id.contact);
        studid=findViewById(R.id.studID);
        bday=findViewById(R.id.bday);

                insert=findViewById(R.id.btn_Insert);
                update=findViewById(R.id.btn_Update);
                delete=findViewById(R.id.btn_Del);
                view=findViewById(R.id.btn_View);
                DB= new MyDBHelper(this);

                insert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameT=name.getText().toString();
                        String contactT=contact.getText().toString();
                        String studidT=studid.getText().toString();
                        String bdayT=bday.getText().toString();

                        boolean checkinsterdata = DB.insertuserdata(nameT,contactT,studidT,bdayT);
                        if(checkinsterdata==true)
                            Toast.makeText(MainActivity.this,"new entry inserted",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,"entry not  inserted",Toast.LENGTH_SHORT).show();

                    }
                });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameT=name.getText().toString();
                String contactT=contact.getText().toString();
                String studidT=studid.getText().toString();
                String bdayT=bday.getText().toString();

                boolean checkupdatedata = DB.updateuserdata(nameT,contactT,studidT,bdayT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this," entry updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this," entry not  updated",Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameT=name.getText().toString();

                boolean checkdeletedata = DB.deletedata(nameT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this," entry deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this," entry not  deleted",Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"no entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){

                    buffer.append( "name :"+res.getString(0)+"\n" );
                    buffer.append( "Contact :"+res.getString(1)+"\n" );
                    buffer.append( "student id :"+res.getString(2)+"\n" );
                    buffer.append( "date of birth :"+res.getString(3)+"\n\n" );
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}