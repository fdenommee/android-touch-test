package com.touchtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity
        extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView list = (RecyclerView)findViewById(android.R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new Adapter(this));
    }

//    public void onClickMe(final View v) {
//        Toast.makeText(this, "Activity - Clicked!", Toast.LENGTH_SHORT).show();
//    }

    public class Adapter
            extends RecyclerView.Adapter<ViewHolder> {

        private final LayoutInflater layoutInflater;

        public Adapter(final Context context) {
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            final View view = layoutInflater.inflate(R.layout.item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.setPosition(position);
        }
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        @InjectView(R.id.item_position)
        TextView positionView;
        @InjectView(R.id.item_click)
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setPosition(final int position) {
            positionView.setText(String.valueOf(position));
        }
    }
}
