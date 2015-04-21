package gasparv.sensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private String menu[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.
                getSensorList(Sensor.TYPE_ALL);
        int i=0,j=0;
        while(i<listaSensores.size()) {
            int in=listaSensores.get(i).getType();
            if(in<=20) {
                j++;
            }
            i++;
        }
        menu = new String[j];
        i=0;j=0;
        while(i<listaSensores.size()) {
            int in=listaSensores.get(i).getType();
            if(in<=20) {
                menu[j] = (listaSensores.get(i).getName());
                Log.i("Oli","El sensor "+listaSensores.get(i).getName()+"tiene id: "+listaSensores.get(i).getType());
                j++;
            }
            i++;
        }
        menu[menu.length-1]="Sensor GPS";
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        menu),
        this);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getSupportActionBar().getSelectedNavigationIndex());
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
    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        SensorManager mSensorManager = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
        Sensor Lacc = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        Sensor Prox = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        Sensor Light = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor Acce = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor Gyros = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor Grav  = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        Sensor MField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor Rvec = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        Sensor GMag = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        if(menu[position]==Acce.getName())
        {Fragment newFragment;
        newFragment = AccelerometerSensor.newInstance(position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, newFragment,String.valueOf(id));
        ft.commit();}
        if(menu[position]==Light.getName())
        {Fragment newFragment;
            newFragment = LightSensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==Rvec.getName())
        {Fragment newFragment;
            newFragment = RotationVecSensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==Grav.getName())
        {Fragment newFragment;
            newFragment = GravitySensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==Lacc.getName())
        {Fragment newFragment;
            newFragment = LinearAccSensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==GMag.getName())
        {Fragment newFragment;
            newFragment = GeoMagneticSensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==Prox.getName())
        {Fragment newFragment;
            newFragment = ProximitySensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==MField.getName())
        {Fragment newFragment;
            newFragment = MagneticF_Sensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]==Gyros.getName())
        {Fragment newFragment;
            newFragment = GyroscopeSensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        if(menu[position]=="Sensor GPS")
        {Fragment newFragment;
            newFragment = GPSSensor.newInstance(position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment,String.valueOf(id));
            ft.commit();}
        return  true;
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        public PlaceholderFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
