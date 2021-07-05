package sg.edu.rp.c346.id20002369.productinformationlister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView IvD;
    Button btn;
    String logoClicked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IvD = findViewById(R.id.imageView);
        btn = findViewById(R.id.button);
        registerForContextMenu(IvD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });
    }
        @Override
        public void onCreateContextMenu (ContextMenu menu, View v,
                ContextMenu.ContextMenuInfo menuInfo){
            super.onCreateContextMenu(menu, v, menuInfo);

            menu.add(0, 0, 0, "Website");

            if (v == IvD) {
                logoClicked = "clicked";
            }
        }

        @Override
        public boolean onContextItemSelected (MenuItem item){
            int id = item.getItemId();
            if (logoClicked.equalsIgnoreCase("clicked")) {
                if (id == 0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.web)));
                    startActivity(intent);
                    return true;
                }
            }
            return super.onContextItemSelected(item);
        }
    }