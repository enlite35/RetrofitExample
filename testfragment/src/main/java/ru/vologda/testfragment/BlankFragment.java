package ru.vologda.testfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.xml.transform.Templates;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEXT = "text";
    private  static final String PLAYER = "player";
    private static final String ROBOT ="robot";

    // TODO: Rename and change types of parameters
    private String mText;
    private int player;
    private int robot;
    boolean endgame = false;


   private Button buttonFragment;
   private TextView mainText,textPlayer, textRobot;
    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String text, int p, int r) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        args.putInt(PLAYER,p);
        args.putInt(ROBOT,r);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(TEXT);
            player=getArguments().getInt(PLAYER);
            robot = getArguments().getInt(ROBOT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        buttonFragment = view.findViewById(R.id.button_fragment);
        mainText = view.findViewById(R.id.textView_fragment);
        textPlayer = view.findViewById(R.id.playerText);
        textRobot = view.findViewById(R.id.robotText);
        textPlayer.setText("Твои жизни: "+player);
        textRobot.setText("Жизни Бота: "+robot);
        mainText.setText(mText);
        if(player==0) {
            mainText.setText("Вы проиграли...\n Нажмите Продолжить чтобы сыграть ещё раз");
            endgame = true;
        }
        if(robot==0) {
            mainText.setText("Вы победили!!!\n Нажмите Продолжить чтобы сыграть ещё раз");
            endgame = true;
        }
        //editTextFragment.setText(mText);
       // editTextFragment.requestFocus();
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String sendBackText = mainText.getText().toString();
            if(!endgame)   sendBack(sendBackText);
            else sendBack("reset");
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void sendBack(String sendBackText) {
        if (mListener != null) {
            mListener.onFragmentInteraction(sendBackText);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String sendBackText);
    }
}
