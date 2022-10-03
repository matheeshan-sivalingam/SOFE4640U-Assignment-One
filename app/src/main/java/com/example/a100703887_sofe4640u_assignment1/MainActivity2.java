package com.example.a100703887_sofe4640u_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    //Declare variables for each element in the layout
    TextView size;
    TextView topping;
    TextView EC;
    TextView ID;
    TextView name;
    TextView address;
    TextView phone;
    TextView email;
    TextView specialIns;
    TextView cost;
    Button orderAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Use findViewByID to assign each variable to there respective element
        size = findViewById(R.id.lblSize);
        topping = findViewById(R.id.lblToppingResponse);
        EC = findViewById(R.id.lblExtraCheeseResponse);
        ID = findViewById(R.id.lblDeliveryResponse);
        name = findViewById(R.id.lblNameResponse);
        address = findViewById(R.id.lblAddressResponse);
        phone = findViewById(R.id.lblPhoneNumberResponse);
        email = findViewById(R.id.lblEmailAddressResponse);
        specialIns = findViewById(R.id.lblSpecialInsResponse);
        cost = findViewById(R.id.lblTotalResponse);
        orderAgain = findViewById(R.id.btnOrderAgain);

        //Set the text based on the size of the pizza ordered
        size.setText(getIntent().getStringExtra("sizeName"));
        //Set the text based on the topping ordered
        topping.setText(getIntent().getStringExtra("topping"));

        //If the user has checked the extra cheese checkbox
        if (getIntent().getExtras().getBoolean("extraC")==true)
        {
            //Set the extra cheese text to yes
            EC.setText("Yes");
        }
        //If the user has not
        else {
            //Set the extra cheese text to no
            EC.setText("No");
        }

        //If the user has checked the include delviery checbox
        if (getIntent().getExtras().getBoolean("delivery")==true)
        {
            //Set the include delivery text to yes
            ID.setText("Yes");
        }
        //If the user has not
        else {
            //Set the include delivery text to no
            ID.setText("No");
        }

        //Get the user contact information and cost
        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        email.setText(getIntent().getStringExtra("email"));
        specialIns.setText(getIntent().getStringExtra("specialIns"));
        double costValue = getIntent().getDoubleExtra("cost",0.00);
        //Format the cost to two decimal
        cost.setText(String.format("$%.2f",costValue));

        //Set the onClickListener to the orderAgain button
        orderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            //If the user clicks on the order Again Button
            public void onClick(View view) {
                //Go back to the order activity
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}