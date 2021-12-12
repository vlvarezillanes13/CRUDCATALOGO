package negocio;

import Generico.Generics;
import entidades.Catalogo;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class negCatalogo {

    private static final DAOCatalogo daoCatalogo = new DAOCatalogo();
    private final Catalogo catag = new Catalogo();

    public static void listaTodo(javax.swing.JTable tablaCatalogos) {
        try {
            ArrayList<Catalogo> listaCat = daoCatalogo.listarTodo();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("NumeroInterno");
            modelo.addColumn("NumeroAplicacion");
            modelo.addColumn("Version");
            modelo.addColumn("Plataforma");
            modelo.addColumn("FechaInicio");
            modelo.addColumn("FechaTermino");
            modelo.addColumn("UrlGit");
            for (Catalogo c : listaCat) {
                Object fila[] = new Object[7];
                fila[0] = c.getNumeroInterno();
                fila[1] = c.getNombreAplicaion();
                fila[2] = c.getVersion();
                fila[3] = c.getPlataforma();
                fila[4] = c.getFechaInicio();
                fila[5] = c.getFechaTermino();
                fila[6] = c.getUrlGit();
                modelo.addRow(fila);
            }
            tablaCatalogos.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas con la conexión a la base de datos para carga de datos", "Mensajes", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void Filtrar(javax.swing.JTextField txtBusqueda, javax.swing.JTable tablaCatalogos) {
        try {
            ArrayList<Catalogo> listaCat = daoCatalogo.listarTodo();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("NumeroInterno");
            modelo.addColumn("NumeroAplicacion");
            modelo.addColumn("Version");
            modelo.addColumn("Plataforma");
            modelo.addColumn("FechaInicio");
            modelo.addColumn("FechaTermino");
            modelo.addColumn("UrlGit");
            for (Catalogo c : listaCat) {
                if (String.valueOf(c.getNumeroInterno()).startsWith(txtBusqueda.getText())) {
                    Object fila[] = new Object[7];
                    fila[0] = c.getNumeroInterno();
                    fila[1] = c.getNombreAplicaion();
                    fila[2] = c.getVersion();
                    fila[3] = c.getPlataforma();
                    fila[4] = c.getFechaInicio();
                    fila[5] = c.getFechaTermino();
                    fila[6] = c.getUrlGit();
                    modelo.addRow(fila);
                }
            }
            tablaCatalogos.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas con la conexión a la base de datos para carga de datos", "Mensajes", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void agregar(javax.swing.JTextField txtNP, javax.swing.JTextField txtNA, javax.swing.JTextField txtV, javax.swing.JComboBox txtP, javax.swing.JTextField txtFI, javax.swing.JTextField txtFT, javax.swing.JTextField txtUG, javax.swing.JTable tableCatalogo) {
        if (txtNP.getText().equals("") || txtNA.getText().equals("") || txtV.getText().equals("") || txtFI.getText().equals("") || txtFT.getText().equals("") || txtUG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }else if (Generics.validarFechas(txtFI,txtFT)) {
            JOptionPane.showMessageDialog(null, "Fecha de Termino es anterior a Inicio", "Mensaje", JOptionPane.ERROR_MESSAGE); 
        }else if (!Generics.validarUrl(txtUG.getText())) {
            JOptionPane.showMessageDialog(null, "URL GIT invalida", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String strDateFormat = "yyyy-MM-dd";
                SimpleDateFormat formato = new SimpleDateFormat(strDateFormat);

                float NP = Float.valueOf(txtNP.getText());
                String NA = txtNA.getText();
                float V = Float.valueOf(txtV.getText());
                String P = txtP.getSelectedItem().toString();
                Date FI = formato.parse(txtFI.getText());
                Date FT = formato.parse(txtFT.getText());
                String UG = txtUG.getText();
                Catalogo c = new Catalogo(NP, NA, V, P, FI, FT, UG);
                if (daoCatalogo.agregar(c)) {
                    JOptionPane.showMessageDialog(null, "Catalogo registrado satisfactoriamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    if (Generics.limpiarTabla(tableCatalogo)) {
                        listaTodo(tableCatalogo);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo registrar catalogo.", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException | NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void modificar(javax.swing.JTextField txtNP, javax.swing.JTextField txtNA, javax.swing.JTextField txtV, javax.swing.JComboBox txtP, javax.swing.JTextField txtFI, javax.swing.JTextField txtFT, javax.swing.JTextField txtUG, javax.swing.JTable tableCatalogo) {
        if (txtNP.getText().equals("") || txtNA.getText().equals("") || txtV.getText().equals("") || txtFI.getText().equals("") || txtFT.getText().equals("") || txtUG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }else if (Generics.validarFechas(txtFI,txtFT)) {
            JOptionPane.showMessageDialog(null, "Fecha de Termino es anterior a Inicio", "Mensaje", JOptionPane.ERROR_MESSAGE); 
        }else if (!Generics.validarUrl(txtUG.getText())) {
            JOptionPane.showMessageDialog(null, "URL GIT invalida", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String strDateFormat = "yyyy-MM-dd";
                SimpleDateFormat formato = new SimpleDateFormat(strDateFormat);

                float NP = Float.valueOf(txtNP.getText());
                String NA = txtNA.getText();
                float V = Float.valueOf(txtV.getText());
                String P = txtP.getSelectedItem().toString();
                Date FI = formato.parse(txtFI.getText());
                Date FT = formato.parse(txtFT.getText());
                String UG = txtUG.getText();
                Catalogo c = new Catalogo(NP, NA, V, P, FI, FT, UG);
                if (daoCatalogo.modificar(c)) {
                    JOptionPane.showMessageDialog(null, "Catalogo modificado satisfactoriamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    if (Generics.limpiarTabla(tableCatalogo)) {
                        listaTodo(tableCatalogo);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar catalogo.", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException | NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void eliminar(javax.swing.JTextField txtNP, javax.swing.JTextField txtNA, javax.swing.JTextField txtV, javax.swing.JComboBox txtP, javax.swing.JTextField txtFI, javax.swing.JTextField txtFT, javax.swing.JTextField txtUG, javax.swing.JTable tableCatalogo) {
        if (txtNP.getText().equals("") || txtNA.getText().equals("") || txtV.getText().equals("") || txtFI.getText().equals("") || txtFT.getText().equals("") || txtUG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }else if (Generics.validarFechas(txtFI,txtFT)) {
            JOptionPane.showMessageDialog(null, "Fecha de Termino es anterior a Inicio", "Mensaje", JOptionPane.ERROR_MESSAGE); 
        }else if (!Generics.validarUrl(txtUG.getText())) {
            JOptionPane.showMessageDialog(null, "URL GIT invalida", "Mensaje", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String strDateFormat = "yyyy-MM-dd";
                SimpleDateFormat formato = new SimpleDateFormat(strDateFormat);

                float NP = Float.valueOf(txtNP.getText());
                String NA = txtNA.getText();
                float V = Float.valueOf(txtV.getText());
                String P = txtP.getSelectedItem().toString();
                Date FI = formato.parse(txtFI.getText());
                Date FT = formato.parse(txtFT.getText());
                String UG = txtUG.getText();
                Catalogo c = new Catalogo(NP, NA, V, P, FI, FT, UG);
                if (daoCatalogo.eliminar(c)) {
                    JOptionPane.showMessageDialog(null, "Catalogo eliminado satisfactoriamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    if (Generics.limpiarTabla(tableCatalogo)) {
                        listaTodo(tableCatalogo);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar catalogo.", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException | NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
