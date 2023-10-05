package com.example.ReaLogViewDMM;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Customize_Date_Cell extends DateCell {

    public Customize_Date_Cell(DatePicker datePicker) {
    }

    //disable all date from today to further.
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (item.isAfter(LocalDate.now())) {
            setDisable(true);
            setStyle("-fx-background-color: #8B8B8B;"); // Optional: Change the style of disabled dates
        }
    }
}

