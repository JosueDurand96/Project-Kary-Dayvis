package com.example.boosters.ui.Soporte;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.boosters.R;
import com.example.boosters.catalogo_boosters;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Soporte extends Fragment {
    Activity context;
    String sEmail,sPassword;
    EditText codigo,correo,alias,descripcion;
    Button enviar;
    private SoporteViewModel mViewModel;

    public static Soporte newInstance() {
        return new Soporte();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.soporte_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SoporteViewModel.class);
        // TODO: Use the ViewModel
        context = getActivity();
        sEmail = "dtaengineerupc@gmail.com";
        sPassword = "fO8vy62!q";
        codigo = (EditText) context.findViewById(R.id.editTextTextEmailAddress41);
        correo = (EditText) context.findViewById(R.id.editTextTextEmailAddress61);
        alias = (EditText) context.findViewById(R.id.editTextTextEmailAddress51);
        descripcion = (EditText) context.findViewById(R.id.editTextTextMultiLine1);
        enviar = (Button) context.findViewById(R.id.button14);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SoporteDAO dao = new SoporteDAO(context.getBaseContext());
                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });
                try {
                    dao.insertar(codigo.getText().toString(), correo.getText().toString(), alias.getText().toString(), descripcion.getText().toString());
                    Toast toast = Toast.makeText(context.getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sEmail));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(correo.getText().toString().trim()));
                    message.setSubject("Ticket Soporte a atender DtaEngineer");
                    message.setText(descripcion.getText().toString().trim());
                    new SendEmail().execute(message);
                } catch (DAOException e) {
                    Log.i("SoporteFragment", "====> " + e.getMessage());
                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private  class SendEmail extends AsyncTask<Message,String,String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(), "Loading...", "Please wait...", true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Success")){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send Sucessfully");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        codigo.setText("");
                        correo.setText("");
                        alias.setText("");
                        descripcion.setText("");
                    }
                });
                builder.show();
            }
            else{
                Toast.makeText(context.getApplicationContext(),"Somenthing went wrong?",Toast.LENGTH_SHORT).show();
            }
        }
    }
}