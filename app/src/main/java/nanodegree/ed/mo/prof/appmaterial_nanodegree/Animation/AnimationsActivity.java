package nanodegree.ed.mo.prof.appmaterial_nanodegree.Animation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Interpolator;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import nanodegree.ed.mo.prof.appmaterial_nanodegree.R;

public class AnimationsActivity extends Activity {

    @BindView(R.id.interpolator_spinner)Spinner interpolatorSpinner;
    @BindView(R.id.duration_spinner)Spinner duratorSpinner;
    @BindView(R.id.textView)TextView textView;

    private static final String PACKAGE = "nanodegree.ed.mo.prof.appmaterial_nanodegree.";
    private static final String PACKAGE_V4 = "android.support.v4.view.animation.";
    private int duration;
    private Interpolator interpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnItemSelected({R.id.duration_spinner})
    void durationSelected(Spinner spinner, int position){
        String durationString=(String)spinner.getAdapter().getItem(position);
        switch (durationString){
            case "100ms":
                duration = 100;
            case "900ms":
                duration = 900;
                break;
            case "1500ms":
                duration = 1500;
                break;
            case "3000ms":
                duration = 3000;
                break;
            default:
                duration = 300;
                break;
        }

        // Kick off transition
        int item = interpolatorSpinner.getSelectedItemPosition();
//        onItemSelected(interpolatorSpinner, position);
    }

    String findFullInterpolatorPath(String className) {
        if (className.equals("FastOutLinearInInterpolator") || className.equals("FastOutSlowInInterpolator") || className.equals("LinearOutSlowInInterpolator"))
            return PACKAGE_V4 + className;
        else if (className.startsWith("-"))
            return null;
        else return PACKAGE + className;
    }

    @OnItemSelected({R.id.interpolator_spinner})
    void onItemSelected(Spinner spinner, int position) {
        String interpolatorName = (String) spinner.getAdapter().getItem(position);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        textView.setTranslationY(metrics.heightPixels);

        try {
            String path = findFullInterpolatorPath(interpolatorName);
            if (path == null)
                return;

            interpolator = (Interpolator) Class.forName(path).newInstance();
            textView.animate().setInterpolator(interpolator)
                    .setDuration(duration)
                    .setStartDelay(500)
                    .translationYBy(-metrics.heightPixels)
                    .start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @OnItemSelected(value = R.id.interpolator_spinner, callback = OnItemSelected.Callback.NOTHING_SELECTED)
    void onNothingSelected(){

    }
}
