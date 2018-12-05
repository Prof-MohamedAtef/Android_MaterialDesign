package nanodegree.ed.mo.prof.appmaterial_nanodegree.Animation.CoordinatedMotion;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nanodegree.ed.mo.prof.appmaterial_nanodegree.R;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void multipleElementsClick(View view) {
        startActivity(new Intent(this, MultipleElementsActivity.class));
    }

    public  void multipleChaoticElementsClick(View view) {
        startActivity(new Intent(this, MultipleChaoticElements.class));
    }

    public void curvedMotionClick(View view) {
        startActivity(new Intent(this, CurvedMotionGridActivity.class));
    }

    public void sizeChangeClick(View view) {
        startActivity(new Intent(this, SizeChangeActivity.class));
    }
}
