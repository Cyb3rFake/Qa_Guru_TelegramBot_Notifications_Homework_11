package demoqa;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("demoqa")
public class RegistrationFormTest extends TestBase {

    final String firstName = "Jora",
                 lastName = "Kirkorov",
                 email = "test@yandex.ru",
                 mobile = "9042901111",
                 CurrentAddress = "45 h, Some st, Some city";


    @Test
    @DisplayName("Successful fill registration test")
    void fillFieldsTest() {
            step("Open registration form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        step("Fill registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("December");
            $(".react-datepicker__year-select").selectOption("2002");
            $("[aria-label$='December 17th, 2002']").click();
            $("#subjectsInput").setValue("Math").pressEnter();
            $("#subjectsInput").setValue("Computer Science").pressEnter();
            $("#hobbiesWrapper").$(byText("Reading")).click();
            $("#uploadPicture").uploadFromClasspath("img/jdun.jpg");
            $("#currentAddress").setValue(CurrentAddress);

            $("#state").click();
            $("#state").$(byText("NCR")).click();
            $("#city").click();
            $("#city").$(byText("Gurgaon")).click();

            $("#submit").click();
        });

        step("Verify from data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Jora Kirkorov"));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("test@yandex.ru"));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9042901111"));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("17 December,2002"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths, Computer Science"));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("Jdun.jpg"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text("45 h, Some st, Some city"));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Gurgaon"));
        });


    }

}
