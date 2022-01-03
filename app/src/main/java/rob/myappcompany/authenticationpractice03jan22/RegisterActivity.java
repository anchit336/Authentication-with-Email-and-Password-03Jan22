package rob.myappcompany.authenticationpractice03jan22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rob.myappcompany.authenticationpractice03jan22.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        //When register Button click run clickRegisterBtn method
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegisterBtn();
            }
        });

        //When click Login here that time open Login Activity
        binding.registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
            }
        });
    }

    //When register Button click that time run this method
    private void clickRegisterBtn() {

        String email = binding.registerEmail.getText().toString();
        String password = binding.registerPassword.getText().toString();

        //When email address editText is Empty show this message
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(RegisterActivity.this, "Enter Email address", Toast.LENGTH_SHORT).show();
            binding.registerEmail.requestFocus();

        //When password editText is Empty show toast message
        } else if (TextUtils.isEmpty(password)){

            Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
            binding.registerPassword.requestFocus();

        // When user give email address and password
        } else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }


}