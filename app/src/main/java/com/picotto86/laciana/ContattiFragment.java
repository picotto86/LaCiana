package com.picotto86.laciana;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by picot_000 on 03/12/2014.
 */
public class ContattiFragment extends android.app.Fragment {

    public ContattiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contatti, container, false);

        ImageButton button=(ImageButton)rootView.findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pavia.mauro@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Impressioni La Ciana");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });

        return rootView;
    }
}
