package nanodegree.ed.mo.prof.appmaterial_nanodegree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

public class DynamicSurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_surface);
        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).setTitle("Eclairs");
    }
}

