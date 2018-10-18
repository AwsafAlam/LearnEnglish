package com.example.awsaf.learnenglish.view.TaskViews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.awsaf.learnenglish.R;

import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Translation extends Fragment {

    TextToSpeech t1;
    ImageView btn;
    TextView speak;

    public Translation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translation, container, false);

        btn = view.findViewById(R.id.speak);
        speak = view.findViewById(R.id.Speak_text);


        t1=new TextToSpeech(view.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setSpeechRate(0.8f);
                t1.speak(speak.getText().toString() , TextToSpeech.QUEUE_FLUSH , null);
            }
        });

        return view;

    }

}
