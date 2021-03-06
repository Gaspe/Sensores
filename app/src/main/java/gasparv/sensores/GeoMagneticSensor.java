package gasparv.sensores;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
public class GeoMagneticSensor extends Fragment implements SensorEventListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SensorManager mSensorManager;
    private Sensor mAcelSensor;
    private EditText mEditTextX;
    private EditText mEditTextY;
    private EditText mEditTextZ;
    private Button mButton;
    private boolean mStarted;
    public static GeoMagneticSensor newInstance(int sectionNumber) {
        GeoMagneticSensor fragment = new GeoMagneticSensor();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_geo_magnetic_sensor, container, false);
        mEditTextX = (EditText) rootView.findViewById(R.id.editText);
        mEditTextY = (EditText) rootView.findViewById(R.id.editText1);
        mEditTextZ = (EditText) rootView.findViewById(R.id.editText2);
        mButton = (Button) rootView.findViewById(R.id.buttonAccel);
        mEditTextX.setText("");
        mEditTextY.setText("");
        mEditTextZ.setText("");
        mStarted = false;
        mButton.setText("Iniciar Sensor");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mStarted) {
                    startCapture(getActivity());
                } else {
                    stopCapturing();
                }
            }
        });

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        stopCapturing();
        mEditTextX.setText("");
        mEditTextY.setText("");
        mEditTextZ.setText("");
    }
    @Override
    public void onStop() {
        super.onStop();
        stopCapturing();
        mEditTextX.setText("");
        mEditTextY.setText("");
        mEditTextZ.setText("");
    }

    public void startCapture(Context context) {
        mStarted = true;
        mButton.setText("Detener Sensor");

        mSensorManager = (SensorManager) getActivity().getSystemService(context.SENSOR_SERVICE);
        mAcelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        mSensorManager.registerListener(this, mAcelSensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void stopCapturing() {
        mStarted = false;
        mButton.setText("Iniciar Sensor");
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this, mAcelSensor);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        stopCapturing();
        mEditTextX.setText("");
        mEditTextY.setText("");
        mEditTextZ.setText("");
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        mEditTextX.setText(event.values[0] + "");
        mEditTextY.setText(event.values[1] + "");
        mEditTextZ.setText(event.values[2] + "");
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
