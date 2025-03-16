package net.jorjai.bummer4.presentation;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import net.jorjai.bummer4.business_logic.BlInterface;
import net.jorjai.bummer4.business_logic.BusinessLogic;
import net.jorjai.bummer4.domain.Tag;

public class WaifuController {

    @FXML
    private ComboBox<Tag> categoryComboBox;

    @FXML
    private ImageView imageView;

    @FXML
    private CheckBox nsfwCheckBox;

    @FXML
    private Button searchButton;

    private BlInterface blInterface;

    public void initialize() {
        blInterface = new BusinessLogic();

        // Set the items of the categoryComboBox to the versatile tags (default)
        categoryComboBox.setItems(FXCollections.observableArrayList(blInterface.getVersatileTags()));

        // Set listeners
        nsfwCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                categoryComboBox.setItems(FXCollections.observableArrayList(blInterface.getNsfwTags()));
            } else {
                categoryComboBox.setItems(FXCollections.observableArrayList(blInterface.getVersatileTags()));
            }
        });
    }
}
