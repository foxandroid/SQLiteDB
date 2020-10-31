package com.example.databasetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button writedatabtn, readdatabtn, updatedatabtn, deletedatabtn;
    EditText writeid, writeitem, writeqty, readid, updateid, updateitem, updateqty, deleteid;
    TextView readidtextview, readitemtextview, readqtyitemview;
    DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DataBaseHelper(this);
        initializecomponent();
    }

    public void initializecomponent(){

        writedatabtn = findViewById(R.id.writebutton);
        readdatabtn = findViewById(R.id.readbutton);
        updatedatabtn = findViewById(R.id.updatebutton);
        deletedatabtn = findViewById(R.id.deletedatabutton);
        writeid = findViewById(R.id.inputid);
        writeitem = findViewById(R.id.inputitem);
        writeqty  = findViewById(R.id.inputqty);
        readid = findViewById(R.id.readid);
        updateid = findViewById(R.id.updateid);
        updateitem = findViewById(R.id.updateitem);
        updateqty = findViewById(R.id.updateqty);
        deleteid = findViewById(R.id.deleteid);
     //   readidtextview = findViewById(R.id.readidtextview);
        readitemtextview = findViewById(R.id.readitem);
        readqtyitemview = findViewById(R.id.readqty);

        writedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item = writeitem.getText().toString();
                String qty = writeqty.getText().toString();
                int Quantity = Integer.parseInt(qty);
                readid.setText("");
                readqtyitemview.setText("");
                readitemtextview.setText("");
                updateid.setText("");
                updateqty.setText("");
                updateitem.setText("");
                writeid.setText("");
                writeitem.setText("");
                writeqty.setText("");

                if(!item.isEmpty() && !qty.isEmpty()){

                    if (mydb.insertData(item,Quantity)){

                        Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();
                    }else{

                        Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_LONG).show();

                    }

                }

            }
        });

        readdatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = readid.getText().toString();
                Cursor res = mydb.readData(id);
                if (res.getCount() == 0){

                    Toast.makeText(MainActivity.this,"No data is available",Toast.LENGTH_LONG).show();

                }else {

                    if (res.moveToNext()){

                        String item = res.getString(1);
                        Integer qty = res.getInt(2);

                        readitemtextview.setText(item);
                        readqtyitemview.setText(qty.toString());
                        readitemtextview.setVisibility(View.VISIBLE);
                        readqtyitemview.setVisibility(View.VISIBLE);
                    }



                }

            }
        });

        updatedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readitemtextview.setText("");
                readqtyitemview.setText("");
                readid.setText("");
                String id = updateid.getText().toString();
                String item = updateitem.getText().toString();
                int qty = Integer.parseInt(updateqty.getText().toString());
                updateid.setText("");
                updateitem.setText("");
                updateqty.setText("");


                if (!id.isEmpty() && !item.isEmpty()){

                    if(mydb.updateData(id,item,qty)){

                        Toast.makeText(MainActivity.this,"Data updated Successfuly",Toast.LENGTH_LONG).show();

                    }else {

                        Toast.makeText(MainActivity.this,"Error in updation",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

        deletedatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readid.setText("");
                readqtyitemview.setText("");
                readitemtextview.setText("");
                updateid.setText("");
                updateqty.setText("");
                updateitem.setText("");
                writeid.setText("");
                writeitem.setText("");
                writeqty.setText("");
                String id = deleteid.getText().toString();
                if (!id.isEmpty()){

                   if(mydb.deleteData(id) > 0){

                       Toast.makeText(MainActivity.this,"Sucessfuly deleted",Toast.LENGTH_LONG).show();

                   }
                }

            }
        });



    }
}