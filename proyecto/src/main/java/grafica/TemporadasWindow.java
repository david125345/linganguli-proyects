package grafica;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.F1app;

public class TemporadasWindow extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(TemporadasWindow.class.getName());

    private final javax.swing.JTextField yearField = new javax.swing.JTextField();
    private final javax.swing.JTable seasonsTable = new javax.swing.JTable();

    public TemporadasWindow() {
        initComponents();
        WindowLayout.configure(this);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Temporadas");
        setPreferredSize(new java.awt.Dimension(700, 500));

        javax.swing.JLabel title = new javax.swing.JLabel("Temporadas");
        javax.swing.JLabel yearLabel = new javax.swing.JLabel("Año de temporada");
        javax.swing.JButton backButton = new javax.swing.JButton("Atrás");
        javax.swing.JButton addButton = new javax.swing.JButton("Aceptar");

        seasonsTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Año"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        javax.swing.JScrollPane tableScrollPane = new javax.swing.JScrollPane(seasonsTable);

        backButton.addActionListener(event -> {
            new VentanaOpciones().setVisible(true);
            setVisible(false);
        });
        addButton.addActionListener(event -> addSeason());
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent event) {
                loadSeasons();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(title)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(110)
                                                .addComponent(yearLabel)
                                                .addGap(35)
                                                .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(180)
                                                .addComponent(backButton)
                                                .addGap(35)
                                                .addComponent(addButton))
                                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 528,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(title)
                                .addGap(50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(yearLabel)
                                        .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(backButton)
                                        .addComponent(addButton))
                                .addGap(30)
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void addSeason() {
        String yearText = yearField.getText().trim();
        if (yearText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes completar el año de la temporada.");
            return;
        }

        final int year;
        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero válido.");
            return;
        }

        F1app f1 = F1app.getInstance();
        String[] existingSeasons = f1.Consultardatabase(
                "ID_temporada", "temporada WHERE año_temporada = " + year);
        if (existingSeasons == null) {
            logger.severe("No se pudo verificar si la temporada ya existe.");
            JOptionPane.showMessageDialog(this, "No se pudo consultar la base de datos.");
            return;
        }
        if (existingSeasons.length > 0) {
            JOptionPane.showMessageDialog(this, "Esa temporada ya existe.");
            return;
        }

        f1.Insertintodatabase("temporada", "año_temporada", "'" + year + "'");
        loadSeasons();
        yearField.setText("");
    }

    private void loadSeasons() {
        DefaultTableModel model = (DefaultTableModel) seasonsTable.getModel();
        model.setRowCount(0);

        String[] seasons = F1app.getInstance().Consultardatabase(
                "año_temporada", "temporada ORDER BY año_temporada");
        if (seasons == null) {
            logger.severe("No se pudieron cargar las temporadas desde la base de datos.");
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las temporadas.");
            return;
        }
        for (String season : seasons) {
            model.addRow(new Object[]{DatabaseRows.stripTrailingSeparator(season)});
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TemporadasWindow().setVisible(true));
    }
}
