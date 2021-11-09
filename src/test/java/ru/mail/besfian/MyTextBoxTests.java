package ru.mail.besfian;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class MyTextBoxTests {
    @BeforeAll
    static void disclosure() {
        Configuration.startMaximized = true;
    }

    @Test
    void formTest() {
        StudentData faker = new StudentData();
        RegistrationsPage registrationsPage = new RegistrationsPage();
        List hobbies = new ArrayList();
        hobbies.add("Sports");
        hobbies.add("Reading");
        hobbies.add("Music");

        registrationsPage.openPage()
                .validateFormTitle()
                .typeFirstNameInput(faker.firstName)
                .typeLastNameInput(faker.lastName)
                .typeEmailInput(faker.userEmail)
                .typeGenderInput(faker.gender)
                .typeUserNumberInput(faker.userNumber)
                .typeDateOfBirthInput(faker.yearOfBirthInput, faker.monthOfBirthInput, faker.dayOfBirthInput)
                .typeHobbiesInput(hobbies)
                .typeSubjectsInput("e", "English")
                .typePictureInput("Test.jpg")
                .typeAddressInput(faker.address)
                .typeStateAndCityInput("NCR", "Delhi")
                .submitClick()
                .checkResults("Student Name", faker.firstName + " " + faker.lastName)
                .checkResults("Student Email", faker.userEmail)
                .checkResults("Gender", faker.gender)
                .checkResults("Mobile", faker.userNumber)
                .checkResults("Date of Birth", faker.dayOfBirthInput + " " + faker.monthOfBirthInput + "," + faker.yearOfBirthInput)
                .checkResults("Subjects", "English")
                .checkResults("Hobbies", hobbies)
                .checkResults("Picture", "Test.jpg")
                .checkResults("Address", faker.address)
                .checkResults("State and City", "NCR Delhi");
    }


}
