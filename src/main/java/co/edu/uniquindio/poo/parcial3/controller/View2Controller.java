package co.edu.uniquindio.poo.parcial3;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class View2Controller implements Initializable {

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TableColumn<Product, String> colId;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, Integer> colStock;

    @FXML
    private TableColumn<Product, String> colCategory;

    @FXML
    private TextField txtSearch;

    private ObservableList<Product> productList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadSampleData();
        setupContextMenu();
    }

    private void setupTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    private void loadSampleData() {
        productList = FXCollections.observableArrayList(
            new Product("P001", "Laptop Dell", 1200.00, 15, "Electrónica"),
            new Product("P002", "Mouse Logitech", 25.50, 50, "Accesorios"),
            new Product("P003", "Teclado Mecánico", 85.00, 30, "Accesorios"),
            new Product("P004", "Monitor Samsung", 350.00, 20, "Electrónica"),
            new Product("P005", "Webcam HD", 65.00, 40, "Accesorios")
        );
        tableProducts.setItems(productList);
    }

    /**
     * Configura el menú contextual para la tabla
     * Se muestra al hacer clic derecho en una fila
     */
    private void setupContextMenu() {
        tableProducts.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();

            // Opción 1: Ver Detalles
            MenuItem viewDetailsItem = new MenuItem("Ver Detalles");
            viewDetailsItem.setOnAction(event -> {
                Product selected = row.getItem();
                if (selected != null) {
                    showAlert("Detalles", "Producto: " + selected.getName() +
                            "\nPrecio: $" + selected.getPrice() +
                            "\nStock: " + selected.getStock());
                }
            });

            // Opción 2: Editar
            MenuItem editItem = new MenuItem("Editar");
            editItem.setOnAction(event -> {
                Product selected = row.getItem();
                if (selected != null) {
                    handleEditProduct(selected);
                }
            });

            // Separador
            SeparatorMenuItem separator = new SeparatorMenuItem();

            // Opción 3: Aumentar Stock
            MenuItem increaseStockItem = new MenuItem("Aumentar Stock");
            increaseStockItem.setOnAction(event -> {
                Product selected = row.getItem();
                if (selected != null) {
                    selected.setStock(selected.getStock() + 10);
                    tableProducts.refresh();
                    showAlert("Éxito", "Stock aumentado para: " + selected.getName());
                }
            });

            // Opción 4: Eliminar
            MenuItem deleteItem = new MenuItem("Eliminar");
            deleteItem.setOnAction(event -> {
                Product selected = row.getItem();
                if (selected != null) {
                    handleDeleteProduct(selected);
                }
            });

            contextMenu.getItems().addAll(
                viewDetailsItem,
                editItem,
                separator,
                increaseStockItem,
                deleteItem
            );

            // Solo mostrar el menú contextual en filas no vacías
            row.contextMenuProperty().bind(
                Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );

            return row;
        });
    }

    @FXML
    private void handleAdd() {
        showAlert("Agregar", "Funcionalidad para agregar producto");
    }

    @FXML
    private void handleEdit() {
        Product selected = tableProducts.getSelectionModel().getSelectedItem();
        if (selected != null) {
            handleEditProduct(selected);
        } else {
            showAlert("Advertencia", "Seleccione un producto para editar");
        }
    }

    @FXML
    private void handleDelete() {
        Product selected = tableProducts.getSelectionModel().getSelectedItem();
        if (selected != null) {
            handleDeleteProduct(selected);
        } else {
            showAlert("Advertencia", "Seleccione un producto para eliminar");
        }
    }

    @FXML
    private void handleRefresh() {
        loadSampleData();
        showAlert("Actualizado", "Datos actualizados correctamente");
    }

    private void handleEditProduct(Product product) {
        showAlert("Editar", "Editando producto: " + product.getName());
    }

    private void handleDeleteProduct(Product product) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmar eliminación");
        confirmAlert.setHeaderText("¿Está seguro de eliminar este producto?");
        confirmAlert.setContentText(product.getName());

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                productList.remove(product);
                showAlert("Éxito", "Producto eliminado correctamente");
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Clase interna para representar un producto
     */
    public static class Product {
        private String id;
        private String name;
        private double price;
        private int stock;
        private String category;

        public Product(String id, String name, double price, int stock, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.category = category;
        }

        // Getters y Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }

        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
    }
}
