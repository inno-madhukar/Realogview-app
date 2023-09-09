package com.example.dmmguivisible;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class CustomDateCell extends DateCell {

    public CustomDateCell(DatePicker datePicker) {
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (item.isAfter(LocalDate.now())) {
            setDisable(true);
            setStyle("-fx-background-color: #8B8B8B;"); // Optional: Change the style of disabled dates
        }
    }
}

