import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class NPCBuilder {
    @FXML
    Button createTemplate;
    @FXML
    Spinner healthVal;
    @FXML
    Spinner initiativeVal;
    @FXML
    RadioButton multiattackButton;
    @FXML
    Spinner armourClassVal;
    @FXML
    TextField nameField;

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if (e.getSource().equals(createTemplate)) {
            NPCTemplate newTemplate = new NPCTemplate(nameField.getText(), (int) initiativeVal.getValue(),
                    multiattackButton.isSelected(), (int) healthVal.getValue(), (int) armourClassVal.getValue());
            Controller.addTemplate(newTemplate);

            NPCTemplate[] p = Controller.getTemplates().toArray(new NPCTemplate[0]);
            for (NPCTemplate pp : p) {
                System.out.println(pp.toString());
            }
        }
    }
}
