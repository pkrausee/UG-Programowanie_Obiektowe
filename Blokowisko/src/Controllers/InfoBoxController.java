package Controllers;

import Models.InfoBox;

import java.awt.*;

public class InfoBoxController {
    public void setInfoBox(InfoBox infoBox) {
        infoBox.setLayout(new GridLayout(2, 1));

        infoBox.add(infoBox.getText1());
        infoBox.add(infoBox.getText2());

        infoBox.setSize(400, 200);
        infoBox.setLocationRelativeTo(null);
        infoBox.setVisible(false);
    }
}
