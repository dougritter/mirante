package br.com.mirante.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import br.com.mirante.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.createAccountButton) Button createAccountButton;
    @Bind(R.id.createAccountContainer) LinearLayout createAccountContainer;
    @Bind(R.id.newNameContainer) TextInputLayout newNameContainer;
    @Bind(R.id.newUserContainer) TextInputLayout newUserContainer;
    @Bind(R.id.newPasswordContainer) TextInputLayout newPasswordContainer;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.createAccountButton)
    void showCreateAccountContainer(View view) {
        newNameContainer.setVisibility(View.VISIBLE);
        newUserContainer.setVisibility(View.VISIBLE);
        newPasswordContainer.setVisibility(View.VISIBLE);

        newNameContainer.requestFocus();
    }



}
