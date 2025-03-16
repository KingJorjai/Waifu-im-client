package net.jorjai.bummer4.presentation;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.jorjai.bummer4.business_logic.BlInterface;
import net.jorjai.bummer4.business_logic.BusinessLogic;
import net.jorjai.bummer4.domain.Tag;

import java.util.ArrayList;
import java.util.List;

public class WaifuController {

    @FXML
    private ComboBox<Tag> categoryComboBox;

    @FXML
    private ImageView imageView;

    @FXML
    private CheckBox nsfwCheckBox;

    @FXML
    private Button searchButton;

    @FXML
    VBox infoVBox;

    private BlInterface businessLogic;

    public void initialize() {
        businessLogic = new BusinessLogic();

        // Set the items of the categoryComboBox to the versatile tags (default)
        categoryComboBox.setItems(FXCollections.observableArrayList(businessLogic.getVersatileTags()));

        // Handle nsfwCheckBox selection to update the tags
        nsfwCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                categoryComboBox.setItems(FXCollections.observableArrayList(businessLogic.getNsfwTags()));
            } else {
                categoryComboBox.setItems(FXCollections.observableArrayList(businessLogic.getVersatileTags()));
            }
        });

        // Handle search button click
        searchButton.setOnAction(btnEvent -> {
            // Search for a waifu
            List<net.jorjai.bummer4.domain.Image> imageList =businessLogic.search(categoryComboBox.getValue());
            net.jorjai.bummer4.domain.Image waifuImage = imageList.getFirst();
            Image fxImage = new Image(waifuImage.getUrl());
            imageView.setImage(fxImage);

            // Update the infoVBox
            infoVBox.getChildren().setAll(FXCollections.observableArrayList(generateDetailBoxes(waifuImage)));
        });
    }

    private HBox detailBox(String label, String value) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);

        Label labelLabel = new Label(label);
        labelLabel.getStyleClass().add("label");

        TextField valueTextField = new TextField(value);
        valueTextField.setEditable(false);
        HBox.setHgrow(valueTextField, Priority.ALWAYS);

        hbox.getChildren().addAll(labelLabel, valueTextField);
        return hbox;
    }

    private List<HBox> generateDetailBoxes(net.jorjai.bummer4.domain.Image waifuImage) {
       List<HBox> detailBoxes = new ArrayList<>();
        if (waifuImage.getArtist() != null) detailBoxes.add(detailBox("Artist: ", waifuImage.getArtist().getName()));
        if (waifuImage.getSource() != null) detailBoxes.add(detailBox("Source: ", waifuImage.getSource()));
        if (waifuImage.getUploaded_at() != null) detailBoxes.add(detailBox("Uploaded at: ", waifuImage.getUploaded_at()));
        if (waifuImage.getLiked_at() != null) detailBoxes.add(detailBox("Liked at: ", waifuImage.getLiked_at()));
        detailBoxes.add(detailBox("Favorites: ", String.valueOf(waifuImage.getFavorites())));
        detailBoxes.add(detailBox("Resolution: ", waifuImage.getWidth() + "x" + waifuImage.getHeight()));
        detailBoxes.add(detailBox("File size: ", waifuImage.getByte_size() + " bytes"));
        if (waifuImage.getDominant_color() != null) detailBoxes.add(detailBox("Dominant color: ", waifuImage.getDominant_color()));
        if (waifuImage.getExtension() != null) detailBoxes.add(detailBox("Extension: ", waifuImage.getExtension()));
        return detailBoxes;
    }
}
