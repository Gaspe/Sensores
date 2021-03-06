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

public class ProximitySensor extends Fragment implements SensorEventListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SensorManager mSensorManager;
    private Sensor mProxSensor;
    private EditText mEditTextX;
    private Button mButton;
    private boolean mStarted;
    public static ProximitySensor newInstance(int sectionNumber) {
        ProximitySensor fragment = new ProximitySensor();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_proximity_sensor, container, false);
        mEditTextX = (EditText) rootView.findViewById(R.id.editText);
        mButton = (Button) rootView.findViewById(R.id.buttonAccel);
        mEditTextX.setText("");
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
    }
    @Override
    public void onStop() {
        super.onStop();
        stopCapturing();
        mEditTextX.setText("");
    }

    public void startCapture(Context context) {
        mStarted = true;
        mButton.setText("Detener Sensor");
        mSensorManager = (SensorManager) getActivity().getSystemService(context.SENSOR_SERVICE);
        mProxSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(this, mProxSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopCapturing() {
        mStarted = false;
        mButton.setText("Iniciar Sensor");
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this, mProxSensor);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        stopCapturing();
        mEditTextX.setText("");
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        mEditTextX.setText(event.values[0] + "");
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}