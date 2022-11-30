package com.game.ptiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String>{

    //Classe adapter qui permet de remplir la listview avec tous les paramètres nécessaires

    String [] NomNiveau;
    String [] difficulte;
    String[] hs;
    int[] ImageNiveau;
    Context mContext;

    public MyAdapter(@NonNull Context context, String [] ListName, String [] difficulte, int[] ListImage,String[] hs) {
        super(context, R.layout.customlv);
        this.NomNiveau = ListName;
        this.ImageNiveau = ListImage;
        this.difficulte=difficulte;
        this.mContext = context;
        this.hs = hs;
    }

    @Override
    public int getCount() {
        return NomNiveau.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.customlv, parent, false);
            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.lvlimg);
            mViewHolder.mDiff = (TextView) convertView.findViewById(R.id.difficulte);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.lvlname);
            mViewHolder.mHs = (TextView) convertView.findViewById(R.id.hs);

            convertView.setTag(mViewHolder);
        }else {mViewHolder = (ViewHolder) convertView.getTag();

        }
            mViewHolder.mImage.setImageResource(ImageNiveau[position]);
            mViewHolder.mDiff.setText(difficulte[position]);
            mViewHolder.mName.setText(NomNiveau[position]);
            mViewHolder.mHs.setText(hs[position]);


        return convertView;
    }
    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mDiff;
        TextView mHs;
    }
}
