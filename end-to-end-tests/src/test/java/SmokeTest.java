//import org.junit.jupiter.api.BeforeEach;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import pageobjects.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class SmokeTest extends TestSetup {

    @BeforeMethod
    public void logIntoApplication(){
        navigateToApplication();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void authSmokeTest(){
        System.out.println("TEST AuthSmokeTest");
        RoomListingPage roomListingPage = new RoomListingPage(driver);

        assertThat(roomListingPage.roomFormExists(), is(true));
    }

    @Test
    public void roomSmokeTest() throws InterruptedException {
        SoftAssert  softAssert = new SoftAssert();

        System.out.println("TEST roomSmokeTest");
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        int initialRoomCount = roomListingPage.roomCount();

        roomListingPage.populateRoomName("109");
        roomListingPage.setRoomPrice("600");
        roomListingPage.checkWifi();
        roomListingPage.checkSafe();
        roomListingPage.checkRadio();
        roomListingPage.clickCreateRoom();

        int currentRoomCount = roomListingPage.getRoomCountWithWait(initialRoomCount+1);
        System.out.println("roomSmokeTest: Room Count: " + currentRoomCount + "Initial room count: " + initialRoomCount);
        System.out.println("---------------------------------------------------------------------");
        softAssert.assertEquals(currentRoomCount, initialRoomCount + 1);
        softAssert.assertAll();
    }

    @Test
    public void bookingSmokeTest() throws InterruptedException {
        System.out.println("TEST bookingSmokeTest");
        SoftAssert  softAssert = new SoftAssert();
        NavPage navPage = new NavPage(driver);
        navPage.clickFrontPage();

        HomePage homePage = new HomePage(driver);
        homePage.clickOpenBookingForm();

        ReservationPage reservationPage = new ReservationPage(driver);
        softAssert.assertTrue( reservationPage.bookingFormExists());
        softAssert.assertAll();
        System.out.println("---------------------------------------------------------------------");
    }

    @Test
    public void reportSmokeTest(){
        System.out.println("TEST reportSmokeTest");
        SoftAssert  softAssert = new SoftAssert();
        NavPage navPage = new NavPage(driver);
        navPage.clickReport();
        ReportPage reportPage = new ReportPage(driver);
        softAssert.assertTrue(reportPage.reportExists());
        softAssert.assertAll();
        System.out.println("---------------------------------------------------------------------");
    }

    @Test
    public void brandingSmokeTest() throws InterruptedException {
        System.out.println("TEST brandingSmokeTest");
        SoftAssert  softAssert = new SoftAssert();
        NavPage navPage = new NavPage(driver);
        navPage.clickBranding();

        BrandingPage brandingPage = new BrandingPage(driver);
        String nameValue = brandingPage.getNameValue();


        softAssert.assertTrue(nameValue.length()>0, "Branding name is empty");
        softAssert.assertAll();
        System.out.println("---------------------------------------------------------------------");
    }

    @Test
    public void messageSmokeTest(){
        System.out.println("TEST messageSmokeTest");
        SoftAssert  softAssert = new SoftAssert();
        NavPage navPage = new NavPage(driver);
        navPage.clickNotification();

        MessagePage messagePage = new MessagePage(driver);
        List<WebElement> messages = messagePage.getMessages();
        softAssert.assertTrue(messages.size()>0, "Message is empty");
        //assertThat(messages.size(), greaterThan(0));
        System.out.println("---------------------------------------------------------------------");
    }

}
