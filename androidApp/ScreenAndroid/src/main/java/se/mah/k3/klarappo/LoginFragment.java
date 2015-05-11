package se.mah.k3.klarappo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements ValueEventListener
{




    public LoginFragment() {
        // Required empty public constructor
    }



    public void sendQuestion(){
        EditText question = (EditText) getActivity().findViewById(R.id.question);
        Constants.question = question.getText().toString();
        Constants.myFirebaseRef.child(Constants.userName).child("Question").setValue(Constants.question);
    }

    public void sendAlts(){
        EditText alt1 = (EditText) getActivity().findViewById(R.id.editTextAlt1);
        EditText alt2 = (EditText) getActivity().findViewById(R.id.editTextAlt2);
        EditText alt3 = (EditText) getActivity().findViewById(R.id.editTextAlt3);
        EditText alt4 = (EditText) getActivity().findViewById(R.id.editTextAlt4);

        Constants.alt1 = alt1.getText().toString();
        Constants.alt2 = alt2.getText().toString();
        Constants.alt3 = alt3.getText().toString();
        Constants.alt4 = alt4.getText().toString();

        Constants.myFirebaseRef.child(Constants.userName).child("Alternative1").setValue(Constants.alt1);
        Constants.myFirebaseRef.child(Constants.userName).child("Alternative2").setValue(Constants.alt2);
        Constants.myFirebaseRef.child(Constants.userName).child("Alternative3").setValue(Constants.alt3);
        Constants.myFirebaseRef.child(Constants.userName).child("Alternative4").setValue(Constants.alt4);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.fragment_login, container, false);
        View v = returnView.findViewById(R.id.btnLogon);
        v.setOnClickListener(new View.OnClickListener() {
            //Click on loginButton
            @Override
            public void onClick(View v) {




                //In firebase you read a value by adding a listener, then it will trigger once connected and on all changes.
                //There is no readvalue as one could expect only listeners.
                //Get the ScreenNbr child
                Firebase fireBaseEntryForScreenNbr = Constants.myFirebaseRef.child("ScreenNbr");
                //Ok listen the changes will sho up in the method onDataChange
                fireBaseEntryForScreenNbr.addValueEventListener(LoginFragment.this);
            }
        });
        return returnView;
    }




    @Override
    public void onDataChange(DataSnapshot snapshot) {
        if (snapshot.getValue()!=null) {
            long val = (long) snapshot.getValue();
            String screenNbrFromFirebase = String.valueOf(val);
            Log.i("LoginFragment", "Screen nbr entered: " + val + " Value from firebase: "+screenNbrFromFirebase);
            EditText screenNumber = (EditText) getActivity().findViewById(R.id.screenNumber);
            EditText name = (EditText) getActivity().findViewById(R.id.name);

            Constants.userName = name.getText().toString();


            //Are we on the right screen
            if (screenNbrFromFirebase.equals(screenNumber.getText().toString())){
                Log.i("LoginFragment", "Logged in");


                sendQuestion();
                sendAlts();

                FragmentManager fm;
                fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new MainFragment());
                ft.commit();
            }else{
                Toast.makeText(getActivity(),"Not the correct Screen",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
