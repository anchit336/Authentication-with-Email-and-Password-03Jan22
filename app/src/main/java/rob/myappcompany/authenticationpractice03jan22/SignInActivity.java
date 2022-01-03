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

import rob.myappcompany.authenticationpractice03jan22.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLoginBtn();
            }
        });

        binding.loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
            }
        });
    }

    private void clickLoginBtn() {

        String email = binding.loginEmail.getText().toString();
        String password = binding.loginPassword.getText().toString();

        //When email address editText is Empty show this message
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(SignInActivity.this, "Enter Email address", Toast.LENGTH_SHORT).show();
            binding.loginEmail.requestFocus();

            //When password editText is Empty show toast message
        } else if (TextUtils.isEmpty(password)){

            Toast.makeText(SignInActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
            binding.loginPassword.requestFocus();

            // When user give email address and password
        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(SignInActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}