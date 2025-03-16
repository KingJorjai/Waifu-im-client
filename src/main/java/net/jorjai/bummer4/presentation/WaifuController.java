package net.jorjai.bummer4.presentation;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.jorjai.bummer4.business_logic.BlInterface;
import net.jorjai.bummer4.business_logic.BusinessLogic;
import net.jorjai.bummer4.domain.Tag;

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

    private BlInterface businessLogic;

    public void initialize() {
        businessLogic = new BusinessLogic();

        // Set the items of the categoryComboBox to the versatile tags (default)
        categoryComboBox.setItems(FXCollections.observableArrayList(businessLogic.getVersatileTags()));

        // Set listeners
        nsfwCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                categoryComboBox.setItems(FXCollections.observableArrayList(businessLogic.getNsfwTags()));
            } else {
                categoryComboBox.setItems(FXCollections.observableArrayList(businessLogic.getVersatileTags()));
            }
        });
        searchButton.setOnAction(btnEvent -> {
            // Search for a waifu
            List<net.jorjai.bummer4.domain.Image> imageList =businessLogic.search(categoryComboBox.getValue());
            Image image = new Image(imageList.getFirst().getUrl());
            imageView.setImage(image);
        });
    }
}
