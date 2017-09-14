package tremblay412.com.mysukan.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import tremblay412.com.mysukan.R;

public class LoginFragment extends BaseFragment {

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    private EditText ET_email, ET_password;
    private Button LoginButton;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ET_email = (EditText) rootView.findViewById(R.id.ET_email);
        ET_password = (EditText) rootView.findViewById(R.id.ET_password);
        LoginButton = (Button) rootView.findViewById(R.id.LoginButton);

        firebaseAuth = FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });

        return rootView;
    }

    private void UserLogin() {
        String email = ET_email.getText().toString();
        String password = ET_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog("Loading");

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "You are now logged in", Toast.LENGTH_SHORT).show();

                            /**
                             * If the login is successful then replace the sign in fragment with a logged in fragment.
                             */
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            AdminFragment adminFragment = new AdminFragment();
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.hide(LoginFragment.this);
                            fragmentTransaction.add(R.id.container, adminFragment);
                            fragmentTransaction.commit();
                        } else {
                            Toast.makeText(getActivity(), "Login failed. Please check your info", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        hideProgressDialog();
    }
}
