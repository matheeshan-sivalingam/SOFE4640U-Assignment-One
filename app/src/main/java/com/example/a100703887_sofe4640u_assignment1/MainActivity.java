package com.example.a100703887_sofe4640u_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declare variables for each element in the layout
    RadioGroup rgSize;
    Spinner spTopping;
    CheckBox cbExtraCheese;
    CheckBox cbIncludeDelivery;
    EditText etSpecialIns;
    EditText etName;
    EditText etAddress;
    EditText etPhone;
    EditText etEmail;
    Button btnSubmit;

    //Declare size and sizeName variable
    int size = 0;
    String sizeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use findViewByID to assign each variable to there respective element
        rgSize = findViewById(R.id.rgSize);
        spTopping = findViewById(R.id.spTopping);
        cbExtraCheese = findViewById(R.id.cbExtraCheese);
        cbIncludeDelivery = findViewById(R.id.cbDelivery);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmailAddress);
        etSpecialIns = findViewById(R.id.etInstruction);
        btnSubmit = findViewById(R.id.btnSubmit);

        //Set the onCheck listener for the radio group
        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    //If the user chooses 6 pizza slices
                    case R.id.rbSix:
                        //Set the size to 6
                        size = 6;
                        //Change the size name to the size of the pizza
                        sizeName = "Round Pizza 6 slices ($5.50)";
                        break;
                    //If the user chooses 8 pizza slices
                    case R.id.rbEight:
                        //Set the size to 8
                        size = 8;
                        //Change the size name of the size of the pizza
                        sizeName = "Round Pizza 8 slices ($7.99)";
                        break;
                    //If the user chooses 10 pizza slices
                    case R.id.rbTen:
                        //Set the size to 10
                        size = 10;
                        //Change the size name of the size of the pizza
                        sizeName = "Round Pizza 10 slices ($9.50)";
                        break;
                    //If the user chooses 12 pizza slices
                    case R.id.rbTwelve:
                        //Set the size to 12
                        size = 12;
                        //Change the size name of the size of the pizza
                        sizeName = "Round Pizza 12 slices ($11.38)";
                        break;
                    default:
                        break;
                }
            }
        });

        //Create and set an adapter to get the toppings from strings.xml and set it to the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.toppings, android.R.layout.simple_spinner_dropdown_item);
        spTopping.setAdapter(adapter);

        //Set the onClickListener for the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            /*When the user clicks on the submit button, store all the necessary inputted values for
            order review page, calculate the costs of pizza, and go to the next order review activity*/
            public void onClick(View view) {
                //Declare and initialize order variables
                String topping = spTopping.getSelectedItem().toString();
                Boolean extraC = cbExtraCheese.isChecked();
                Boolean delivery = cbIncludeDelivery.isChecked();
                String specialIns = etSpecialIns.getText().toString();
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                //Call cost function to determine cost of pizza
                double cost= calculateCost(topping,extraC,delivery);

                //Create a new intent
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);

                //Add necessary order information to intent
                intent.putExtra("sizeName",sizeName);
                intent.putExtra("topping",topping);
                intent.putExtra("extraC",extraC);
                intent.putExtra("delivery",delivery);
                intent.putExtra("specialIns",specialIns);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                intent.putExtra("cost",cost);

                //If the user does not input a size of pizza or fill out the contact information, inform that an input is missing
                if (rgSize.getCheckedRadioButtonId()==-1 || name.equals("") || address.equals("") || phone.equals("")||email.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Error, missing input", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Call and open the Order Review activity
                    startActivity(intent);
                }

            }
        });








    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //This function calculates the cost of the pizza
    public double calculateCost(String topping, boolean extraC, boolean delivery){
        //Set the cost to zero
        double cost = 0;

        //If the user orders a 6 slice pizza
        if (size==6) {
            //Add 5.5 to the cost
            cost = cost + 5.5;
        }
        //If the user orders a 8 slice pizza
        else if (size==8) {
            //Add 7.99 to the cost
            cost = cost + 7.99;
        }
        //If the user orders a 10 slice pizza
        else if (size ==10) {
            //Add 9.50 to the cost
            cost = cost + 9.50;
        }
        //If the user orders a 12 slice pizza
        else if (size==12) {
            //Add 11.38 to the cost
            cost = cost + 11.38;
        }

        //Add respective cost of toppings to the total cost
        if(topping.equals("Mushroom($5)")) {
            cost = cost + 5;
        }
        else if (topping.equals("Sun Dried Tomatoes($5)")) {
            cost = cost + 5;
        }
        else if (topping.equals("Chicken($7)")) {
            cost = cost + 7;
        }
        else if (topping.equals("Ground Beef($8)")){
            cost = cost + 8;
        }
        else if (topping.equals("Shrimps($5)")){
            cost = cost + 5;
        }
        else if (topping.equals("Pineapple($5)")){
            cost = cost + 5;
        }
        else if (topping.equals("Steak($9)")){
            cost = cost + 9;
        }
        else if (topping.equals("Avocado($5)")){
            cost = cost + 5;
        }
        else if (topping.equals("Tuna($5)")){
            cost = cost + 5;
        }
        else if (topping.equals("Broccoli($8)")){
            cost = cost + 8;
        }
        else if (topping.equals("Peperoni($5)")){
            cost = cost + 5;
        }
        else if (topping.equals("Bacon($6)")){
            cost = cost + 6;
        }
        else if (topping.equals("Green Peppers($5)")){
            cost = cost + 5;
        }

        //If the user checked the extra cheese checkbox
        if (extraC==true)
        {
            //Add 5 to the total cost
            cost = cost + 5;
        }

        //If the user checked the delivery checkbox
        if (delivery==true)
        {
            //Add 5 to the total cost
            cost = cost + 5;
        }

        //Return cost
        return cost;
    }
}