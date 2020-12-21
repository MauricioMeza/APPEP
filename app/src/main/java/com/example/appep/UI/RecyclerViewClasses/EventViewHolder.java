package com.example.appep.UI.RecyclerViewClasses;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    TextView textViewEventDate, textViewEventPsoldo1, textViewEventPsoldo2, textViewEventVol, textViewEventLng;
    TextView textViewEventCircTtl, textViewEventEstFArriba, textViewEventEstHBroca, textViewEventGncSup, textViewEventPrsCrrTbo, textViewEventPrsCrrRev;
    int[][] names = {{R.id.textViewEst1,R.id.textViewPrs1},{R.id.textViewEst2,R.id.textViewPrs2},{R.id.textViewEst3,R.id.textViewPrs3},{R.id.textViewEst4,R.id.textViewPrs4},{R.id.textViewEst5,R.id.textViewPrs5},
            {R.id.textViewEst6,R.id.textViewPrs6},{R.id.textViewEst7,R.id.textViewPrs7},{R.id.textViewEst8,R.id.textViewPrs8},{R.id.textViewEst9,R.id.textViewPrs9},{R.id.textViewEst10,R.id.textViewPrs10},{R.id.textViewEst11,R.id.textViewPrs11}};
     TextView[][] estroques = new TextView[11][2];

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewEventDate = (TextView) itemView.findViewById(R.id.textViewEventDateBlank);
        textViewEventPsoldo1 = (TextView) itemView.findViewById(R.id.textViewPesolodoBlank1);
        textViewEventPsoldo2 = (TextView) itemView.findViewById(R.id.textViewPesolodoBlank2);
        textViewEventVol = (TextView) itemView.findViewById(R.id.textViewVolBlank);
        textViewEventLng = (TextView) itemView.findViewById(R.id.textViewLngBlank);

        textViewEventCircTtl = (TextView) itemView.findViewById(R.id.textViewCrcPaMatrBlank);
        textViewEventEstFArriba = (TextView) itemView.findViewById(R.id.textViewEstFABlank);
        textViewEventEstHBroca =  (TextView) itemView.findViewById(R.id.textViewEstHBrcBlank);
        textViewEventGncSup = (TextView) itemView.findViewById(R.id.textViewGncSuprBlank);
        textViewEventPrsCrrTbo = (TextView) itemView.findViewById(R.id.textViewPrCrrTboBlank);
        textViewEventPrsCrrRev = (TextView) itemView.findViewById(R.id.textViewPrCrrRevBlank);

        for(int i=0; i<names.length; i++){
            estroques[i][0] = (TextView) itemView.findViewById(names[i][0]);
            estroques[i][1] = (TextView) itemView.findViewById(names[i][1]);
        }


    }
}
