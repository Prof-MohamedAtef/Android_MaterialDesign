package nanodegree.ed.mo.prof.appmaterial_nanodegree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.Animator;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String baconTitle = "Bacon";
    static String baconText = "Bacon ipsum dolor amet pork belly meatball kevin spare ribs. Frankfurter swine corned beef meatloaf, strip steak.";
    static String veggieTitle = "Veggie";
    static String veggieText = "Veggies es bonus vobis, proinde vos postulo essum magis kohlrabi welsh onion daikon amaranth tatsoi tomatillo melon azuki bean garlic.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).setTitle(getString(R.string.app_name));
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.list_item, parent, false));
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                viewHolder.text1.setText(baconTitle);
                viewHolder.text2.setText(baconText);
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });

    }

    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text1;
        TextView text2;
        static int green;
        static int white;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);
            itemView.setOnClickListener(this);

            if (green == 0)
                green = itemView.getContext().getResources().getColor(R.color.green);
            if (white == 0)
                white = itemView.getContext().getResources().getColor(R.color.background_material_light);
        }

        @Override
        public void onClick(View view) {
            boolean isVeggie = ((ColorDrawable)view.getBackground()) != null && ((ColorDrawable)view.getBackground()).getColor() == green;

            TransitionManager.beginDelayedTransition((ViewGroup) view);
            int finalRadius = Math.max(view.getWidth(), view.getHeight()) / 2;

            if (isVeggie) {
                text1.setText(baconTitle);
                text2.setText(baconText);
                view.setBackgroundColor(white);
            } else {
                Animator anim = ViewAnimationUtils.createCircularReveal(view, (int) view.getWidth()/2, (int) view.getHeight()/2, 0, finalRadius);
                text1.setText(veggieTitle);
                text2.setText(veggieText);
                view.setBackgroundColor(green);
                anim.start();
            }
        }
    }
}

