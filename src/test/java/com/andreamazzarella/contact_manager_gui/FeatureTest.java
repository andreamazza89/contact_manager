package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.ContactsRepository;
import com.andreamazzarella.contact_manager.InMemoryContactsRepository;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;

public class FeatureTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws IOException {
        ContactsRepository myRepo = new InMemoryContactsRepository();

        ViewRouter viewRouter = new ViewRouter(myRepo);

        Scene scene = new Scene(viewRouter.generateRoot(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void CreateContactThenSeeIt() {
        clickOn("#saveContactsTab");
        clickOn("#newFirstName").write("test Name").push(KeyCode.ENTER);
        clickOn("#newLastName").write("test Surname").push(KeyCode.ENTER);
        clickOn("#newTelephoneNumber").write("073367").push(KeyCode.ENTER);
        clickOn("#newAge").write("44").push(KeyCode.ENTER);

        clickOn("#saveContact");

        verifyThat("#contactSavingAlerts", NodeMatchers.hasText("Contact saved!"));
    }
}
