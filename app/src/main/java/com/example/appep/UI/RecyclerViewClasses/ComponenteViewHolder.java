package com.example.appep.UI.RecyclerViewClasses;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.R;

public class ComponenteViewHolder extends RecyclerView.ViewHolder {

    TextView compTitle, compResVol, compResCap, compTitleLong;
    EditText compEditLong, compEditOD, compEditID;
    ImageView compImage;
    Button compRefs;
    int componentId;

    public ComponenteViewHolder(View itemView) {
        super(itemView);

        compTitle = (TextView) itemView.findViewById(R.id.textViewCompTitle);
        compTitleLong = (TextView) itemView.findViewById(R.id.textViewLong);
        compResVol = (TextView) itemView.findViewById(R.id.textViewNumVol);
        compResCap = (TextView) itemView.findViewById(R.id.textViewNumCap);

        compEditLong = (EditText) itemView.findViewById(R.id.editTextNumLong);
        compEditOD = (EditText) itemView.findViewById(R.id.editTextNumOD);
        compEditID = (EditText) itemView.findViewById(R.id.editTextNumID);

        compImage = (ImageView) itemView.findViewById(R.id.imageViewComp);

        compRefs = (Button) itemView.findViewById(R.id.buttonRef);
    }
}
