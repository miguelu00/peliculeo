/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.gestion;

import Controlador.ControladorPeliculas;
import Exceptions.PeliculaNoExisteException;
import GUI.Utiles.Vistas;
import gestionPeliculas.dto.Pelicula;
import gestionPeliculas.dto.PosterPelicula;
import gestionPeliculas.dto.UtilesFecha;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguelañez-PC
 */
public class ModificarPelicula extends javax.swing.JDialog {

    int codPelicula = -1;
    /**
     * Creates new form ModificarPelicula
     */
    public ModificarPelicula(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarAyudaJH();
    }
    
    /**
     * Creates new form ModificarPelicula
     */
    public ModificarPelicula(java.awt.Frame parent, boolean modal, int codPelicula) {
        super(parent, modal);
        initComponents();
        this.codPelicula = codPelicula;
        rellenarDatosModifGUI();
        this.setLocationRelativeTo(null);
        cargarAyudaJH();
    }
    
    /**
     * Creates new form ModificarPelicula
     */
    public ModificarPelicula(java.awt.Frame parent, boolean modal, Pelicula pelicula) {
        super(parent, modal);
        initComponents();
        this.codPelicula = pelicula.getCodPelicula();
        rellenarDatosModifGUI();
        cargarAyudaJH();
        this.setLocationRelativeTo(null);
    }

    /**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelHead = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jPanelContent = new javax.swing.JPanel();
        jPanelLabels = new javax.swing.JPanel();
        jLabelTituloPeli = new javax.swing.JLabel();
        jLabelCodPelicula = new javax.swing.JLabel();
        jLabelGeneroPeli = new javax.swing.JLabel();
        jLabelFechaEstreno = new javax.swing.JLabel();
        jLabelAnio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelInputs = new javax.swing.JPanel();
        jTextFieldCodPelicula = new javax.swing.JTextField();
        jTextFieldTituloPeli = new javax.swing.JTextField();
        jComboBoxGenero = new javax.swing.JComboBox<>();
        jSpinnerFechaEstreno = new javax.swing.JSpinner();
        jSpinnerAnioPeli = new javax.swing.JSpinner();
        customJLabelImagen1 = new pruebaimageresize_cambio.CustomJLabelImagen();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR PELICULA");

        jPanelHead.setMinimumSize(new java.awt.Dimension(125, 45));
        jPanelHead.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Volver atras");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanelHead.add(jLabel1);

        jPanel2.setLayout(new java.awt.CardLayout());

        jButtonAceptar.setBackground(new java.awt.Color(153, 255, 102));
        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAceptar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAceptar.setText("ACEPTAR CAMBIOS");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAceptar, "card2");

        jPanelHead.add(jPanel2);

        getContentPane().add(jPanelHead, java.awt.BorderLayout.PAGE_START);

        jPanelLabels.setMinimumSize(new java.awt.Dimension(100, 200));
        jPanelLabels.setPreferredSize(new java.awt.Dimension(100, 200));
        jPanelLabels.setLayout(new java.awt.GridBagLayout());

        jLabelTituloPeli.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTituloPeli.setLabelFor(jTextFieldTituloPeli);
        jLabelTituloPeli.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanelLabels.add(jLabelTituloPeli, gridBagConstraints);

        jLabelCodPelicula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelCodPelicula.setLabelFor(jTextFieldCodPelicula);
        jLabelCodPelicula.setText("Cod. Pelicula");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanelLabels.add(jLabelCodPelicula, gridBagConstraints);

        jLabelGeneroPeli.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelGeneroPeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelGeneroPeli.setLabelFor(jComboBoxGenero);
        jLabelGeneroPeli.setText("Genero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanelLabels.add(jLabelGeneroPeli, gridBagConstraints);

        jLabelFechaEstreno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFechaEstreno.setLabelFor(jSpinnerFechaEstreno);
        jLabelFechaEstreno.setText("Fecha de Estreno");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanelLabels.add(jLabelFechaEstreno, gridBagConstraints);

        jLabelAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAnio.setLabelFor(jSpinnerAnioPeli);
        jLabelAnio.setText("Anio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        jPanelLabels.add(jLabelAnio, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Imagen Pelicula");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.3;
        jPanelLabels.add(jLabel2, gridBagConstraints);

        jPanelInputs.setLayout(new java.awt.GridBagLayout());

        jTextFieldCodPelicula.setEditable(false);
        jTextFieldCodPelicula.setEnabled(false);
        jTextFieldCodPelicula.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelInputs.add(jTextFieldCodPelicula, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelInputs.add(jTextFieldTituloPeli, gridBagConstraints);

        jComboBoxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acción", "Aventura", "Ciencia Ficción", "Fantasía", "Drama", "Psicológica", "Suspense" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelInputs.add(jComboBoxGenero, gridBagConstraints);

        jSpinnerFechaEstreno.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(), null, java.util.Calendar.DAY_OF_MONTH));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelInputs.add(jSpinnerFechaEstreno, gridBagConstraints);

        jSpinnerAnioPeli.setModel(new javax.swing.SpinnerListModel(new String[] {"2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900", "1899", "1898", "1897", "1896", "1895", "1894", "1893", "1892", "1891", "1890", "1889", "1888", "1887", "1886", "1885", "1884", "1883", "1882", "1881", "1880", "1879", "1878", "1877", "1876", "1875", "1874", "1873", "1872", "1871", "1870", "1869", "1868", "1867", "1866", "1865", "1864", "1863", "1862", "1861", "1860", "1859", "1858", "1857", "1856", "1855", "1854", "1853", "1852", "1851", "1850", "1849", "1848", "1847", "1846", "1845", "1844", "1843", "1842", "1841", "1840", "1839", "1838", "1837", "1836", "1835", "1834", "1833", "1832", "1831", "1830", "1829", "1828", "1827", "1826", "1825", "1824", "1823", "1822", "1821", "1820", "1819", "1818", "1817", "1816", "1815", "1814", "1813", "1812", "1811", "1810", "1809", "1808", "1807", "1806", "1805", "1804", "1803", "1802", "1801", "1800"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanelInputs.add(jSpinnerAnioPeli, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.4;
        jPanelInputs.add(customJLabelImagen1, gridBagConstraints);

        javax.swing.GroupLayout jPanelContentLayout = new javax.swing.GroupLayout(jPanelContent);
        jPanelContent.setLayout(jPanelContentLayout);
        jPanelContentLayout.setHorizontalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContentLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanelLabels, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanelInputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelContentLayout.setVerticalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInputs, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanelLabels, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (JOptionPane.showConfirmDialog(this, "¿Cancelar la edición de la Película?", "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.CANCEL_OPTION) {
            return;
        }
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        this.jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 13));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        this.jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        Pelicula p = new Pelicula();
        p.setCodPelicula(Integer.parseInt(jTextFieldCodPelicula.getText()));
        p.setTitulo(jTextFieldTituloPeli.getText());
        p.setFechaEstreno(
                UtilesFecha.spinnerFechaToStringDate(jSpinnerFechaEstreno.getValue())
        );
        p.setGenero(jComboBoxGenero.getSelectedItem().toString());
        p.setAnio(Integer.parseInt(jSpinnerAnioPeli.getValue().toString()));
        if (jTextFieldTituloPeli.getText().isBlank()) {
            Vistas.mostrarErrorGUI(rootPane, "TÍTULO OBLIGATORIO", "La película debe tener un título válido!");
            return;
        }
        
        Pelicula editada = null;
        try {
            PosterPelicula peli = new PosterPelicula(p.getCodPelicula(), p.getTitulo(), Files.readAllBytes(customJLabelImagen1.getImgSelected().toPath()));
            editada = ControladorPeliculas.editarPelicula(p, peli);
        } catch (PeliculaNoExisteException err) {
            Vistas.mostrarErrorGUI(rootPane, "ERROR! Pelicula no encontrada o API no iniciada!", err.getMessage());
            this.setVisible(false);
            this.dispose();
            return;
        } catch (IOException ioe) {
            Vistas.mostrarErrorGUI(rootPane, "ERROR! No se pudo leer la imágen correctamente!", ioe.getMessage());
            return;
        }
        
        if (editada != null) {
            Vistas.mostrarMensajeGUI(rootPane, "COMPLETADO!", "<html>"
                    + "La película <b>" + p.getCodPelicula() + "- " + p.getTitulo() + "</b> se ha actualizado correctamente!"
                            + "</html>");
            this.setVisible(false);
            this.dispose();
            return;
        }
        Vistas.mostrarErrorGUI(rootPane, "ERROR AL ACTUALIZAR LA PELÍCULA!", "La película no se ha podido actualizar correctamente. Compruebe la conexión a la BBDD e inténtelo de nuevo!");
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModificarPelicula dialog = new ModificarPelicula(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    public void rellenarDatosModifGUI() {
        if (codPelicula == -1) {
            Vistas.mostrarErrorGUI(rootPane, "NADA SELECCIONADO!", "ERROR! NO SE HA SELECCIONADO NINGUNA PELICULA!");
            return;
        }
        
        Pelicula p = ControladorPeliculas.getPeliculaByCodPelicula(codPelicula);
        PosterPelicula poster = ControladorPeliculas.getPosterByCodPelicula(codPelicula);
        
        this.jTextFieldCodPelicula.setText(String.valueOf(p.getCodPelicula()));
        this.jTextFieldTituloPeli.setText(p.getTitulo());
        this.jComboBoxGenero.setSelectedItem(p.getGenero());
        this.jSpinnerFechaEstreno.setValue(
                UtilesFecha.fechaStrToDate(p.getFechaEstreno())
        );
        this.jSpinnerAnioPeli.setValue(String.valueOf(p.getAnio()));
        this.customJLabelImagen1.setImgRoot(poster.getTempImg());
    }
    
    /**
     * Habilitará el acceso a la ayuda de JavaHelp para esta ventana de la aplicación.
     */
    private void cargarAyudaJH() {
        try {
            // Carga el fichero de 'script' de ayuda (help.hs)
            File fichero = new File("src" + File.separator + "help" + File.separator + "help.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            hb.enableHelpKey(this.getContentPane(), "ayudaGestionPeliculas", helpset); //panel con el foco default
        } catch (HelpSetException hsExc) {
            System.err.println(hsExc.getMessage());
        } catch (MalformedURLException urlErr) {
            System.err.println("Formato de URL incorrecto!");
            System.err.println(urlErr.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pruebaimageresize_cambio.CustomJLabelImagen customJLabelImagen1;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JComboBox<String> jComboBoxGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAnio;
    private javax.swing.JLabel jLabelCodPelicula;
    private javax.swing.JLabel jLabelFechaEstreno;
    private javax.swing.JLabel jLabelGeneroPeli;
    private javax.swing.JLabel jLabelTituloPeli;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelHead;
    private javax.swing.JPanel jPanelInputs;
    private javax.swing.JPanel jPanelLabels;
    private javax.swing.JSpinner jSpinnerAnioPeli;
    private javax.swing.JSpinner jSpinnerFechaEstreno;
    private javax.swing.JTextField jTextFieldCodPelicula;
    private javax.swing.JTextField jTextFieldTituloPeli;
    // End of variables declaration//GEN-END:variables
}
