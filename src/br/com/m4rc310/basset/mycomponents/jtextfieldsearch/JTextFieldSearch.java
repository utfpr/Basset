/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.mycomponents.jtextfieldsearch;

import br.com.m4rc310.basset.teste.Aluno;
import br.com.m4rc310.utils.ComponentsUtils;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import resource.B;

/**
 *
 * @author tchulla
 */
public class JTextFieldSearch<T> extends JComponent {

    private JTextField jTextFieldCod;
    private JTextField jTextFieldDetails;
    private T object;
    private SearchConnector<T> searchAdapter;
    private JPanel jPanel1;
    private ComponentsUtils ju;
    private JLabel jLabelCod;

    public JTextFieldSearch(SearchConnector<T> searchAdapter) {
        this();
        this.searchAdapter = searchAdapter;
        initListners();
    }

    public JTextFieldSearch() {
        ju = new ComponentsUtils();
        initLayout();
    }

    private synchronized void initLayout() {

        synchronized (this) {
            setLayout(new MigLayout("inset -1"));
            jTextFieldCod = ju.getJTextField(5);
            jTextFieldCod.setHorizontalAlignment(JTextField.RIGHT);

            jTextFieldDetails = ju.getJTextField(15);
//            jPanel1 = ju.getJPanel(null);

            jLabelCod = ju.getJLabel(b("cod"));
            //        jPanel1.add(jLabelCod);

            add(jTextFieldCod);
            add(jTextFieldDetails, "growx");

//            add(jPanel1);
        }

    }

    public void setSearchAdapter(SearchConnector<T> searchAdapter) {
        this.searchAdapter = searchAdapter;
        initListners();
    }

    private void initListners() {
        if (searchAdapter != null) {
            jTextFieldCod.setInputVerifier(getInputVerifierToCod());

            jTextFieldCod.addKeyListener(getKeyListenerSearch());
            jTextFieldDetails.addKeyListener(getKeyListenerSearch());

            
            
            
            String[] values = searchAdapter.converter(object);
            if(values != null){
                ju.setText(values[0], jTextFieldCod);
                ju.setText(values[1], jTextFieldDetails);
            }
            
            ju.setBackgroundColor(Color.yellow, jTextFieldCod, jTextFieldDetails);
        }

    }

    public void setObject(T object) {
        this.object = object;
        if (object != null && searchAdapter != null) {
            System.out.println(object);
            ju.setText(searchAdapter.converter(object)[0], jTextFieldCod);
            ju.setText(searchAdapter.converter(object)[1], jTextFieldDetails);
        }
    }

    public T getObject() {
        return object;
    }
    
    public void setLabel(String text) {
        jLabelCod.setText(text);
    }

    public void setColumnSize(int codW) {
        jTextFieldCod.setColumns(codW);
    }
    public void setColumnSize(int codW, int detailsW) {
        jTextFieldCod.setColumns(codW);
        jTextFieldDetails.setColumns(codW);
    }

    public void setEnabled(boolean bln, boolean bln2) {
        ju.setEnable(bln, jTextFieldCod);
        ju.setEnable(bln2, jTextFieldDetails);
    }

    private String b(String key) {
        return B.getString(getClass().getSimpleName().toLowerCase() + "." + key);
    }

    public static void main(String[] args) {
        JTextFieldSearch jtfs = new JTextFieldSearch();
        jtfs.setObject("");

        jtfs.teste();
    }

    private void teste() {
        JDialog teste = new JDialog();
        teste.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JTextFieldSearch jtfs = new JTextFieldSearch();
        
        jtfs.setSearchAdapter(new EnderecoConnector());
        
        teste.add(jtfs);
        teste.pack();

        teste.setVisible(true);
    }

    private InputVerifier getInputVerifierToCod() {
        return new InputVerifier() {

            @Override
            public boolean verify(JComponent jc) {
                try {
                    T t = searchAdapter.get(jTextFieldCod.getText(), jTextFieldDetails.getText());
                    setObject(t);
                    return searchAdapter.isValid(t);
                } catch (Exception e) {
                    return false;
                }

//                }
//                return true;
            }
        };
    }

    private KeyListener getKeyListenerSearch() {
        return new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.isControlDown() && ke.getKeyCode() == KeyEvent.VK_F) {
                    openPopUpSearch();
                }
            }
        };
    }

    private void openPopUpSearch() {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.add(new JTextField(50));

        JOptionPane.showMessageDialog(f, "mmm");


//        JDialog d = new JDialog();
//        d.setModal(true);
//        
//        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//
//        d.setVisible(true);
    }
}
