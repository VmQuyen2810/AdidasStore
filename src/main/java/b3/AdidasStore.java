package b3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdidasStore extends Application {
    private VBox selectedBox = null;
    private ImageView productImageView;

    @Override
    public void start(Stage primaryStage) {
        HBox mainLayout = new HBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: white;"); // Nền trắng toàn bộ

        // Panel chi tiết sản phẩm
        VBox productDetailsPane = new VBox(15);
        productDetailsPane.setStyle("-fx-background-color: white; -fx-padding: 20px;");
        productDetailsPane.setMinWidth(400);

        productImageView = new ImageView();
        productImageView.setFitHeight(300);
        productImageView.setFitWidth(300);

        Label productNameLabel = new Label();
        productNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 22px;");
        productNameLabel.setWrapText(true);

        Label productPriceLabel = new Label();
        productPriceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        productPriceLabel.setWrapText(true);

        Label productBrandLabel = new Label();
        productBrandLabel.setWrapText(true);

        Label productDescriptionLabel = new Label();
        productDescriptionLabel.setWrapText(true);

        productDetailsPane.getChildren().addAll(
                productImageView, productNameLabel,
                productPriceLabel, productBrandLabel, productDescriptionLabel
        );

        // Grid danh sách sản phẩm
        GridPane productGrid = new GridPane();
        productGrid.setHgap(15);
        productGrid.setVgap(15);
        productGrid.setPadding(new Insets(10));
        productGrid.setStyle("-fx-background-color: white;");

        String[] productNames = {
                "4DFWD PULSE SHOES", "FORUM MID SHOES", "SUPERNOVA SHOES", "Adidas",
                "Adidas", "4DFWD PULSE SHOES", "4DFWD PULSE SHOES", "FORUM MID SHOES"
        };
        String[] productPrices = {
                "$160.00", "$100.00", "$150.00", "$160.00",
                "$120.00", "$160.00", "$160.00", "$100.00"
        };
        String[] imageFiles = {
                "img1.png", "img2.png", "img3.png", "img4.png",
                "img5.png", "img6.png", "img1.png", "img2.png"
        };

        for (int i = 0; i < productNames.length; i++) {
            VBox productBox = new VBox(10);
            productBox.setStyle("-fx-background-color: rgb(245,245,245); -fx-background-radius: 10px; -fx-padding: 15px;");

            Image productImage;
            try {
                productImage = new Image(getClass().getResourceAsStream("/" + imageFiles[i]));
            } catch (Exception e) {
                System.err.println("Không thể tải ảnh: " + imageFiles[i]);
                productImage = null;
            }

            ImageView imageView = new ImageView(productImage);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);

            Label nameLabel = new Label(productNames[i]);
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            nameLabel.setWrapText(true);
            nameLabel.setMaxWidth(200);

            Label descriptionLabel1 = new Label("This product is excluded from all promotional discounts and offers");
            Label descriptionLabel2 = new Label("NMD City Stock 2");
            descriptionLabel1.setWrapText(true);
            descriptionLabel2.setWrapText(true);

            Label priceLabel = new Label(productPrices[i]);
            priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            priceLabel.setWrapText(true);

            Label brandLabel = new Label("Adidas");
            brandLabel.setWrapText(true);

            Label descriptionLabel = (i == 2 || i == 3 || i == 4) ? descriptionLabel2 : descriptionLabel1;

            HBox priceBrandBox = new HBox(30);
            priceBrandBox.getChildren().addAll(brandLabel, priceLabel);

            productBox.getChildren().addAll(nameLabel, descriptionLabel, imageView, priceBrandBox);

            // Bắt sự kiện click
            final Image clickedImage = productImage;
            final String clickedDescription = descriptionLabel.getText();
            final String clickedName = nameLabel.getText();
            final String clickedPrice = priceLabel.getText();
            final String clickedBrand = brandLabel.getText();

            productBox.setOnMouseClicked(event -> {
                if (selectedBox != null) {
                    selectedBox.setStyle("-fx-background-color: rgb(245,245,245); -fx-background-radius: 10px; -fx-padding: 15px;");
                }

                productBox.setStyle("-fx-background-color: #e0f0ff; -fx-background-radius: 10px; -fx-padding: 15px;");

                selectedBox = productBox;

                productImageView.setImage(clickedImage);
                productNameLabel.setText(clickedName);
                productPriceLabel.setText(clickedPrice);
                productBrandLabel.setText(clickedBrand);
                productDescriptionLabel.setText(clickedDescription);
            });

            productGrid.add(productBox, i % 4, i / 4);
        }

        ScrollPane scrollPane = new ScrollPane(productGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: white;");

        mainLayout.getChildren().addAll(productDetailsPane, scrollPane);

        Scene scene = new Scene(mainLayout, 1400, 600);
        primaryStage.setTitle("Adidas Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
