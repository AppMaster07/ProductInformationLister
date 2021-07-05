package sg.edu.rp.c346.id20002369.productinformationlister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class ItemListActivity extends AppCompatActivity {

    TextView etN, etD, etP;
    Button btnA, btnU, btnD;
    ListView lv;
    Spinner spnToDo, spnEx;
    ArrayAdapter adapter;
    ArrayAdapter tempAdapter;
    ArrayList<String> itemData;
    ArrayList<String> tempList;
    Calendar cal;
    String formattedDate;
    String finalFilter = "";
    String currentFilter1, currentFilter2, currentFilter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        etN = findViewById(R.id.editTextName);
        etD = findViewById(R.id.editTextDate);
        etP = findViewById(R.id.editTextPos);
        btnA = findViewById(R.id.buttonAdd);
        btnD = findViewById(R.id.buttonDel);
        btnU = findViewById(R.id.buttonUpdate);
        lv = findViewById(R.id.listView);
        spnToDo = findViewById(R.id.spinner);
        spnEx = findViewById(R.id.spinner2);


        itemData = new ArrayList<>();
        itemData.add("Expiry " + "2021-08-27" + " " + "Apple Watch");
        itemData.add("Expiry " + "2021-08-27" + " " + "Maple Watch");
        itemData.add("Expiry " + "2021-08-27" + " " + "Dapple Watch");
        itemData.add("Expiry " + "2021-09-27" + " " + "Beats headphone");
        itemData.add("Expiry " + "2021-09-27" + " " + "Cannon Camera");
        itemData.add("Expiry " + "2021-10-27" + " " + "Dyson hairdryer");
        itemData.add("Expiry " + "2021-10-27" + " " + "Intel Chip");
        itemData.add("Expiry " + "2022-01-27" + " " + "Samsung Chip");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemData);
        lv.setAdapter(adapter);

        /*tempList = new ArrayList<>();

        for (String data : itemData) {
            tempList.add(data);
        }

        tempAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tempList);
        lv.setAdapter(tempAdapter);*/

        spnToDo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // ADD
                        etN.setHint("Add item name");
                        etD.setHint("Add item expiry");
                        btnA.setEnabled(true);
                        btnU.setEnabled(false);
                        btnD.setEnabled(false);
                        etN.setEnabled(true);
                        etD.setEnabled(true);
                        etP.setEnabled(false);
                        break;
                    case 1: //UPDATE
                        etP.setHint("Select data index to update");
                        etN.setHint("Update item name");
                        etD.setHint("Update item expiry");
                        btnA.setEnabled(false);
                        btnU.setEnabled(true);
                        btnD.setEnabled(false);
                        etN.setEnabled(true);
                        etD.setEnabled(true);
                        etP.setEnabled(true);
                        if (itemData.isEmpty()) {
                            Toast.makeText(ItemListActivity.this, "You don't have any data to update", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2: //DELETE
                        etP.setHint("Select data index to delete");
                        btnA.setEnabled(false);
                        btnU.setEnabled(false);
                        btnD.setEnabled(true);
                        etN.setEnabled(false);
                        etD.setEnabled(false);
                        etP.setEnabled(true);
                        if (itemData.isEmpty()) {
                            Toast.makeText(ItemListActivity.this, "You don't have any data to delete", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameEntered = etN.getText().toString();
                String itemDateEntered = etD.getText().toString();
                itemData.add("Expiry " + itemDateEntered + " " + itemNameEntered);
                adapter.notifyDataSetChanged();
                Collections.sort(itemData);

            }
        });

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etP.getText().toString());
                if (itemData.size() == 0) {
                    Toast.makeText(ItemListActivity.this, "No data to update", Toast.LENGTH_SHORT).show();
                } else if (itemData.size() < pos) {
                    Toast.makeText(ItemListActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                } else {
                    String itemNameEntered = etN.getText().toString();
                    String itemDateEntered = etD.getText().toString();
                    itemData.set(pos, "Expiry " + itemDateEntered + " " + itemNameEntered);
                    Collections.sort(itemData);
                }
                adapter.notifyDataSetChanged();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etP.getText().toString());
                if (itemData.size() == 0) {
                    Toast.makeText(ItemListActivity.this, "No data to remove", Toast.LENGTH_SHORT).show();
                } else if (itemData.size() < pos) {
                    Toast.makeText(ItemListActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                } else {
                    itemData.remove(pos);
                }
                adapter.notifyDataSetChanged();
            }
        });

        spnEx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                cal = Calendar.getInstance();

                switch (position) {
                    case 0: // 1 month
                        cal.add(Calendar.MONTH, 1);
                        formattedDate = sdf.format(cal.getTime());
                        finalFilter = "Expiry " + formattedDate;
                        ItemListActivity.this.adapter.getFilter().filter(finalFilter);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1: //3 month
                        cal.add(Calendar.MONTH, 3);
                        formattedDate = sdf.format(cal.getTime());
                        finalFilter = "Expiry " + formattedDate;
                        ItemListActivity.this.adapter.getFilter().filter(finalFilter);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2: //6 months
                        cal.add(Calendar.MONTH, 6);
                        formattedDate = sdf.format(cal.getTime());
                        finalFilter = "Expiry " + formattedDate;
                        ItemListActivity.this.adapter.getFilter().filter(finalFilter);
                        adapter.notifyDataSetChanged();
                        break;
                    case 3: //All
                        ItemListActivity.this.adapter.getFilter().filter("");
                        adapter.notifyDataSetChanged();
                        break;
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //---------------------------------------------------------------------------------

        /*spnEx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0: // 1 month
                        customFilterList1(currentFilter1);
                        updateList1();
                        break;
                    case 1: //3 month
                        customFilterList2(currentFilter2);
                        updateList2();
                        break;
                    case 2: //6 months
                        customFilterList3(currentFilter3);
                        updateList3();
                        break;
                    case 3: //All
                        ItemListActivity.this.tempAdapter.getFilter().filter("");
                        tempAdapter.notifyDataSetChanged();
                        break;
                }
                //adapter.notifyDataSetChanged();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    // Defining filter functions
    public void customFilterList1(String filter1) {
        tempList.clear();
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        cal.add(Calendar.MONTH, 1);
        formattedDate = sdf.format(cal.getTime());
        finalFilter = "Expiry " + formattedDate;
        for (String item : itemData) {
            if (finalFilter == item) {
                tempList.add(item);
            }
        }
        currentFilter1 = filter1;
        tempAdapter.notifyDataSetChanged();
    }

    public void updateList1() {
        customFilterList1(currentFilter1);
    }


    // Defining filter functions
    public void customFilterList2(String filter2) {
        tempList.clear();
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        cal.add(Calendar.MONTH, 3);
        formattedDate = sdf.format(cal.getTime());
        finalFilter = "Expiry " + formattedDate;
        for (String item : itemData) {
            if (finalFilter == item) {
                tempList.add(item);
            }
        }
        currentFilter2 = filter2;
        tempAdapter.notifyDataSetChanged();
    }

    public void updateList2() {
        customFilterList2(currentFilter2);
    }

    // Defining filter functions
    public void customFilterList3(String filter3) {
        tempList.clear();
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        cal.add(Calendar.MONTH, 6);
        formattedDate = sdf.format(cal.getTime());
        finalFilter = "Expiry " + formattedDate;
        for (String item : itemData) {
            if (finalFilter == item) {
                tempList.add(item);
            }
        }
        currentFilter3 = filter3;
        tempAdapter.notifyDataSetChanged();
    }

    public void updateList3() {
        customFilterList3(currentFilter3);
    }*/
    }
}