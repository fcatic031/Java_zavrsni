package zavrsni.view;

import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Autorizacija {
    private JButton btnAutoriziraj;
    private JTextField txtEmail;
    private JLabel lblLozinka;
    private JLabel lblEmail;
    public JPanel panel;
    private JLabel lblEmailError;
    private JLabel lblLozinkaError;
    private JPasswordField txtLozinka;

    public Autorizacija() {
    btnAutoriziraj.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            var email = txtEmail.getText();
            reset();

            if (email.isEmpty()){
                error(txtEmail);
                lblEmailError.setText("Email obavezno");
                return;
            }

            if (!EmailValidator.getInstance().isValid(email)){
                error(txtEmail);
                lblEmailError.setText("Email nije validan");
                return;
            }

            if (txtLozinka.getPassword().length==0){
                error(txtLozinka);
                lblLozinkaError.setText("Lozinka obavezno");
                return;
            }

            System.out.println("Uspjeh");
        }
    });
}

    private void error(JComponent c){
        c.setBackground(Color.RED);
        c.requestFocus();
    }

    private void reset(){
        lblEmailError.setText("");
        txtEmail.setBackground(Color.WHITE);
        txtLozinka.setBackground(Color.WHITE);
    }
}
