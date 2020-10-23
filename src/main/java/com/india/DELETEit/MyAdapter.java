package com.india.DELETEit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class MyAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> appName;
    ArrayList<Drawable> icon;
    ArrayList<String> appSize;

    private CheckBoxCheckedListner clistner;
   SelectedAppList list = new SelectedAppList();
    ArrayList<Boolean> checkboxdb = new ArrayList<Boolean>();






    MyAdapter(Context context, ArrayList<String> appName, ArrayList<Drawable> icon) {
        super(context, R.layout.row, R.id.app_name, appName);
        this.context = context;
        this.appName = appName;
        this.icon = icon;
        this.appSize = appSize;

    }

    class MyViewHolder {

        ImageView app_icon;
        TextView app_name;
        CheckBox checkBox;
        TextView app_size;


        MyViewHolder(View v) {
            app_icon = v.findViewById(R.id.app_icon);
            app_name = v.findViewById(R.id.app_name);
            checkBox = v.findViewById(R.id.checkbox);


        }

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Animation animFadeIn;
        View row = convertView;
        MyViewHolder holder = null;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);

        } else {

            holder = (MyViewHolder) row.getTag();

        }


        holder.app_icon.setImageDrawable(icon.get(position));
        holder.app_name.setText(appName.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (clistner != null) {

                    clistner.getCheckboxCheckedListner(position);


                }

            }

        });





    animFadeIn = AnimationUtils.loadAnimation(getContext(),
             R.anim.animlefttoright);


     row.startAnimation(animFadeIn);


        return row;
    }



    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }




    public interface CheckBoxCheckedListner {
     void getCheckboxCheckedListner(int position);

    }
    public void setCheckesListner(CheckBoxCheckedListner checkListner){
        this.clistner = checkListner;
    }

}
