package ru.vologda.myfrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExampleFragments extends Fragment {
    private static final String arg_text = "argText";

    private  String text;
    private  int number;

     public static ExampleFragments newInstance(String text, int number){
         ExampleFragments fragment = new ExampleFragments();
         Bundle args = new Bundle();
         args.putString("argText", text);
         args.putInt("argNumber",number);
         fragment.setArguments(args);
         return fragment;
     }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v =inflater.inflate(R.layout.example_fragment, container,false);
        TextView textView = v.findViewById(R.id.text_view_fragment);
        if(getArguments() != null) {
            text = getArguments().getString("argText");
            number = getArguments().getInt("argNumber");
            textView.setText(text+number);
        }

        return v;
    }
}
