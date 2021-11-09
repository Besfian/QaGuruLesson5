package ru.mail.besfian;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationsPage {
    public final String URL = "https://demoqa.com/automation-practice-form";
    private final String FORM_TEXT = "Student Registration Form";
    private final String MODAL_TEXT = "Thanks for submitting the form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            modalTitle = $("#example-modal-sizes-title-lg"),
            formFirstNameInput = $("#firstName"),
            formLastNameInput = $("#lastName"),
            formEmailInput = $("#userEmail"),
            formUserNumberInput = $("#userNumber"),
            formDateOfBirthInput = $("#dateOfBirthInput"),
            formSubmit = $("#submit"),
            formSubjectsInput = $("#subjectsInput"),
            formUploadPictureInput = $("#uploadPicture"),
            formCurrentAddressInput = $("#currentAddress");

    public RegistrationsPage openPage() {
        open(URL);
        return this;
    }

    public RegistrationsPage validateFormTitle() {
        formTitle.shouldHave(text(FORM_TEXT));
        return this;
    }

    public RegistrationsPage typeFirstNameInput(String firstName) {
        formFirstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationsPage typeLastNameInput(String lastName) {
        formLastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationsPage typeEmailInput(String email) {
        formEmailInput.setValue(email);
        return this;
    }

    public RegistrationsPage typeUserNumberInput(String userNumber) {
        formUserNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationsPage typeDateOfBirthInput(String year, String month, String day) {
        formDateOfBirthInput.click();
        $(".react-datepicker__year-select").click();
        $(byText(year)).click();
        $(".react-datepicker__month-select").click();
        $(byText(month)).click();
        $(byText(day)).click();
        return this;
    }

    public RegistrationsPage typeHobbiesInput(List hobbies) {
        for (int index = 0; index < hobbies.size(); index++) {
            $(byText(String.valueOf((hobbies.get(index))))).click();
        }
        return this;
    }

    public RegistrationsPage typeGenderInput(String gender) {
        switch (gender) {
            case "MALE":
                $("[for='gender-radio-1']").click();
                break;
            case "FEMALE":
                $("[for='gender-radio-2']").click();
                break;
            case "OTHER":
                $("[for='gender-radio-3']").click();
                break;
        }
        return this;
    }

    public RegistrationsPage typeSubjectsInput(String subjects, String fullSubjects) {
        formSubjectsInput.sendKeys(subjects);
        $(byText(fullSubjects)).click();
        return this;
    }

    public RegistrationsPage typePictureInput(String picture) {
        formUploadPictureInput.uploadFromClasspath(picture);
        return this;
    }

    public RegistrationsPage typeAddressInput(String address) {
        formCurrentAddressInput.setValue(address);
        return this;
    }

    public RegistrationsPage typeStateAndCityInput(String state, String city) {
        $(byText("Select State")).scrollTo().click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationsPage submitClick() {
        formSubmit.scrollTo().click();
        return this;
    }

    RegistrationsPage checkResults(String key, String meaning) {
        String meaning1 = meaning;
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(meaning));
        return this;
    }


    RegistrationsPage checkResults(String key, List hobbies) {
        String stringHobbies;
        {
            switch (hobbies.size()) {
                case 1:
                    stringHobbies = "" + hobbies.get(0);
                    $(".table-responsive").$(byText(key)).parent().shouldHave(text(stringHobbies));
                    break;
                case 2:
                    stringHobbies = hobbies.get(0) + ", " + hobbies.get(1);
                    $(".table-responsive").$(byText(key)).parent().shouldHave(text(stringHobbies));
                    break;
                case 3:
                    stringHobbies = hobbies.get(0) + ", " + hobbies.get(1) + ", " + hobbies.get(2);
                    $(".table-responsive").$(byText(key)).parent().shouldHave(text(stringHobbies));
                    break;
            }
        }
        return this;
    }
}