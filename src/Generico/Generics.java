package Generico;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.*;

public class Generics {

    private TableRowSorter trs;

    public static boolean limpiarTabla(javax.swing.JTable tablaDatos) {
        try {
            DefaultTableModel modeloDatos = (DefaultTableModel) tablaDatos.getModel();

            if (tablaDatos.getRowCount() > 0) {
                for (int i = 0; i < tablaDatos.getRowCount(); i++) {
                    modeloDatos.removeRow(i);
                    i = i - 1;
                }
            }
            modeloDatos.fireTableDataChanged();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void limpiarText(javax.swing.JTextField txtNP, javax.swing.JTextField txtNA, javax.swing.JTextField txtV, javax.swing.JTextField txtFI, javax.swing.JTextField txtFT, javax.swing.JTextField txtUG) {
        txtNP.setText("");
        txtNA.setText("");
        txtV.setText("");
        txtFI.setText("");
        txtFT.setText("");
        txtUG.setText("");
    }

    public static void mouseListerner(javax.swing.JTable tablaCatalogo, javax.swing.JTextField txtNP, javax.swing.JTextField txtNA, javax.swing.JTextField txtV, javax.swing.JTextField txtFI, javax.swing.JTextField txtFT, javax.swing.JTextField txtUG, javax.swing.JComboBox txtP) {
        tablaCatalogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 1) {
                    txtNP.setText(tablaCatalogo.getValueAt(row, 0).toString());
                    txtNA.setText(tablaCatalogo.getValueAt(row, 1).toString());
                    txtV.setText(tablaCatalogo.getValueAt(row, 2).toString());
                    txtFI.setText(tablaCatalogo.getValueAt(row, 4).toString());
                    txtFT.setText(tablaCatalogo.getValueAt(row, 5).toString());
                    txtUG.setText(tablaCatalogo.getValueAt(row, 6).toString());
                    txtP.setSelectedItem(tablaCatalogo.getValueAt(row, 3).toString());
                }
            }
        });
    }

    public static boolean validarUrl(String url) {
        Pattern pat = Pattern.compile("^(www.)[\\w-]+(\\.[\\w-]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(url);
        return mat.find();
    }

    public static boolean validarFechas(javax.swing.JTextField txtFI, javax.swing.JTextField txtFT) {
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat formato = new SimpleDateFormat(strDateFormat);
        try {
            Date FI = formato.parse(txtFI.getText());
            Date FT = formato.parse(txtFT.getText());
            if( FT.before(FI)){
                return true;
            }else{
                return false;
            }
            
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        

        return true;
    }
}
