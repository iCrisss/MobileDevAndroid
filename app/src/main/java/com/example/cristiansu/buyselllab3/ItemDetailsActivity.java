package com.example.cristiansu.buyselllab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cristiansu.buyselllab3.Repository.DAO.ItemsDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.Item;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ItemDetailsActivity extends AppCompatActivity {


    private ItemsDataSource itemsDataSource;

    private ColumnChartView columnChartView;
    private ColumnChartData chartData;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        itemsDataSource = new ItemsDataSource(this);

        columnChartView = (ColumnChartView) findViewById(R.id.chart);
        generateDefaultData();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            final Item listingItem = (Item) bundle.get("item");
            final User user = (User) bundle.get("user");

            final TextView itemName = (TextView) findViewById(R.id.textItemDetailName);
            final TextView itemPrice = (TextView) findViewById(R.id.textItemDetailPrice);

            itemName.setText(listingItem.getName());
            itemPrice.setText(String.valueOf(listingItem.getPrice()));

            final Button backToListing = (Button) findViewById(R.id.buttonDetailBackToListing);
            backToListing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent listingIntent = new Intent(ItemDetailsActivity.this, ListingActivity.class);
                    listingIntent.putExtra("user", user);
                    startActivity(listingIntent);
                }
            });
        }

        else {
            Intent listingIntent = new Intent(ItemDetailsActivity.this, ListingActivity.class);
            startActivity(listingIntent);
        }
    }

    private void generateDefaultData() {
        int numSubcolumns = 1;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.

        try {
            itemsDataSource.openReadable();
            List<Item> itemList = itemsDataSource.getAllItems();

            List<Column> columns = new ArrayList<>();
            List<SubcolumnValue> values;
            for (int i = 0; i < itemList.size(); ++i) {
                Item item = itemList.get(i);
                values = new ArrayList<>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue((float) itemList.get(i).getPrice(), ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            chartData = new ColumnChartData(columns);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Items");
                    axisY.setName("Prices");
                }
                chartData.setAxisXBottom(axisX);
                chartData.setAxisYLeft(axisY);
            } else {
                chartData.setAxisXBottom(null);
                chartData.setAxisYLeft(null);
            }

            columnChartView.setColumnChartData(chartData);
        }
        catch (Exception e) {

        }
    }
}
