package grafica;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.F1app;

public class ProveedorMotores extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(ProveedorMotores.class.getName());

    private final javax.swing.JTextField nombreField = new javax.swing.JTextField();
    private final javax.swing.JComboBox<ForeignKeyOption> proveedorCombo = new javax.swing.JComboBox<>();
    private final javax.swing.JTable motoresTable = new javax.swing.JTable();

    public ProveedorMotores() {
        initComponents();
        WindowLayout.configure(this);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proveedores de motores");

        javax.swing.JLabel title = new javax.swing.JLabel("Proveedores de motores");
        javax.swing.JLabel nombreLabel = new javax.swing.JLabel("Nombre");
        javax.swing.JLabel proveedorLabel = new javax.swing.JLabel("Proveedor");
        javax.swing.JButton backButton = new javax.swing.JButton("Atrás");
        javax.swing.JButton addButton = new javax.swing.JButton("Aceptar");

        motoresTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Proveedor"}
        ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        javax.swing.JScrollPane tableScrollPane = new javax.swing.JScrollPane(motoresTable);

        backButton.addActionListener(event -> {
            new VentanaOpciones().setVisible(true);
            setVisible(false);
        });
        addButton.addActionListener(event -> agregarProveedorMotor());
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent event) {
                cargarDatos();
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
                                                .addGap(120)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(nombreLabel)
                                                        .addComponent(proveedorLabel))
                                                .addGap(30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(proveedorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(245)
                                                .addComponent(backButton)
                                                .addGap(35)
                                                .addComponent(addButton))
                                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 900,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(title)
                                .addGap(45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nombreLabel)
                                        .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(proveedorLabel)
                                        .addComponent(proveedorCombo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(backButton)
                                        .addComponent(addButton))
                                .addGap(30)
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void cargarDatos() {
        F1app f1 = F1app.getInstance();
        proveedorCombo.removeAllItems();
        DefaultTableModel model = (DefaultTableModel) motoresTable.getModel();
        model.setRowCount(0);

        String[] proveedores = f1.Consultardatabase(
                "ID_proveedores,nombre", "PROVEEDORES ORDER BY nombre");
        if (proveedores == null) {
            logger.severe("No se pudieron cargar los proveedores.");
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los proveedores.");
            return;
        }
        for (String row : proveedores) {
            String[] data = DatabaseRows.split(row);
            proveedorCombo.addItem(new ForeignKeyOption(data[0], data[1]));
        }

        String[] motores = f1.Consultardatabase(
                "nombre,proveedor",
                "(SELECT pm.nombre AS nombre, p.nombre AS proveedor, "
                + "pm.ID_proveedores AS ID_proveedores FROM PROV_MOTORES pm "
                + "INNER JOIN PROVEEDORES p ON pm.ID_proveedores = p.ID_proveedores) "
                + "AS proveedores_motores ORDER BY ID_proveedores");
        if (motores == null) {
            logger.severe("No se pudieron cargar los proveedores de motores.");
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los proveedores de motores.");
            return;
        }
        for (String row : motores) {
            String[] data = DatabaseRows.split(row);
            model.addRow(new Object[]{data[0], data[1]});
        }
    }

    private void agregarProveedorMotor() {
        String nombre = nombreField.getText().trim();
        ForeignKeyOption proveedor = (ForeignKeyOption) proveedorCombo.getSelectedItem();
        if (nombre.isEmpty() || proveedor == null) {
            JOptionPane.showMessageDialog(this, "Completa el nombre y selecciona un proveedor.");
            return;
        }

        F1app f1 = F1app.getInstance();
        String[] existente = f1.Consultardatabase(
                "ID_proveedores", "PROV_MOTORES WHERE ID_proveedores = " + proveedor.getId());
        if (existente == null) {
            JOptionPane.showMessageDialog(this, "No se pudo verificar el proveedor seleccionado.");
            return;
        }
        if (existente.length > 0) {
            JOptionPane.showMessageDialog(this, "Ese proveedor ya tiene un registro de motor.");
            return;
        }

        String nombreSql = nombre.replace("'", "''");
        f1.Insertintodatabase("PROV_MOTORES", "ID_proveedores,nombre",
                "'" + proveedor.getId() + "','" + nombreSql + "'");

        String[] guardado = f1.Consultardatabase(
                "nombre", "PROV_MOTORES WHERE ID_proveedores = " + proveedor.getId());
        if (guardado == null || guardado.length == 0) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el proveedor de motor.");
            return;
        }

        ((DefaultTableModel) motoresTable.getModel()).addRow(new Object[]{nombre, proveedor.toString()});
        nombreField.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ProveedorMotores().setVisible(true));
    }
}
