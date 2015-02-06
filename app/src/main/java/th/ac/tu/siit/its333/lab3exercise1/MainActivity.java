package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;
    TextView tvGP,tvCR,tvGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

          tvGP = (TextView) findViewById(R.id.tvGP);
        tvCR = (TextView) findViewById(R.id.tvCR);
        tvGPA = (TextView) findViewById(R.id.tvGPA);

        calculate();
        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

        public void calculate(){

             cr = 0;         // Credits
             gp = 0.0;    // Grade points
            gpa = 0.0;   // Grade point average

           for(int i = 0;i<listCodes.size();i++)
           {
               String a = listCodes.get(i);
               int b = listCredits.get(i);
               String s = listGrades.get(i);

               cr+=b;

               if(s.equals("A"))
                   gp += 4.00 * b;
               else if(s.equals("B+"))
                   gp += 3.50 * b;
               else if(s.equals("B"))
                   gp += 3.00 * b;
               else if(s.equals("C+"))
                   gp += 2.50 * b;
               else if(s.equals("C"))
                   gp += 2.00 * b;
               else if(s.equals("D+"))
                   gp += 1.50 * b;
               else if(s.equals("D"))
                   gp += 1.00 * b;
               else if(s.equals("F"))
                   gp += 0;


           }


            gpa = gp/cr;

            if(cr==0)
                gpa =0;

            TextView bb = (TextView)findViewById(R.id.tvGP);
            TextView cc = (TextView)findViewById(R.id.tvCR);
            TextView aa = (TextView)findViewById(R.id.tvGPA);


            bb.setText(Double.toString(gp));
            cc.setText(Integer.toString(cr));
            aa.setText(Double.toString(gpa));


        }
    public void buttonClicked(View v) {
        listCodes.clear();
        listGrades.clear();
        listCredits.clear();

         cr = 0;         // Credits
         gp = 0.0;    // Grade points
         gpa = 0.0;   // Grade point average

        calculate();

        TextView bb = (TextView)findViewById(R.id.tvGP);
        TextView cc = (TextView)findViewById(R.id.tvCR);
        TextView aa = (TextView)findViewById(R.id.tvGPA);

        bb.setText(Double.toString(gp));
        cc.setText(Integer.toString(cr));
        aa.setText(Double.toString(gpa));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if (requestCode == 55){
            if (resultCode == RESULT_OK){
                listCodes.add(data.getStringExtra("code"));
                listCredits.add(Integer.parseInt(data.getStringExtra("credit")));
                listGrades.add(data.getStringExtra("grade"));
                calculate();
            }


            else if (resultCode == RESULT_CANCELED){



            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void CourseActivity (View v){
        Intent i = new Intent(this, CourseActivity.class);
        startActivityForResult(i, 55);
    }
    public void CourseListActivity(View v) {
        Intent i = new Intent(this, CourseListActivity.class);
        String show = "";
        int index ;
        for(index = 0;index<listCodes.size();index++){
            show += listCodes.get(index) + "(" + listCredits.get(index) + " credits) = " + listGrades.get(index) + "\n";


        }

        //EditText etInput = (EditText)findViewById(R.id.etInput);
      i.putExtra("toList", show);
        startActivity(i);
    }
}
