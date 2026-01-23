package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RoomListingPage extends BasePage {

    @FindBy(how = How.ID, using = "roomName")
    private WebElement txtRoomName;

    @FindBy(how = How.ID, using = "createRoom")
    private WebElement btnCreate;

    @FindBy(how = How.ID, using = "wifiCheckbox")
    private WebElement chkWifi;

    @FindBy(how = How.CSS, using = "div[data-type~=\"room\"]")
    private List<WebElement> lstRooms;

    @FindBy(how = How.ID, using = "safeCheckbox")
    private WebElement chkSafe;

    @FindBy(how = How.ID, using = "radioCheckbox")
    private WebElement chkRadio;

    @FindBy(how = How.CSS, using = ".room-form")
    private WebElement frmForm;

    @FindBy(how = How.ID, using = "roomPrice")
    private WebElement inptRoomPrice;

    public RoomListingPage(WebDriver driver){
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-2.rowHeader")));
    }

    public void populateRoomName(String roomName) throws InterruptedException {
        txtRoomName.sendKeys(roomName);
        Thread.sleep(200);
    }

    public void clickCreateRoom() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", btnCreate);
        System.out.println("👆 Clic forzado (JS) enviado al botón Crear.");
    }

    public int roomCount() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        lstRooms = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[data-type~=\"room\"]")));
        System.out.println("List Room Size:" + lstRooms.size());
        return lstRooms.size();
    }
    public int getRoomCountWithWait(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Damos hasta 15 segundos

        try {
            // Esta línea es mágica: Espera hasta que la lista tenga EXACTAMENTE el tamaño esperado
            // Nota: Debes usar el localizador By, no la lista @FindBy directamente para el wait
            lstRooms = wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[data-type~='room']"), expectedCount));
        } catch (TimeoutException e) {
            System.out.println("⚠️ El nuevo elemento no apareció a tiempo. Retornando conteo actual.");
        }

        // Ahora sí, obtenemos el tamaño real (sea el nuevo o el viejo si falló)
        return lstRooms.size();
    }



    public void clickFirstRoom() {
        lstRooms.get(0).click();
    }

    public void checkWifi() {
        chkWifi.click();
    }

    public void checkSafe() {
        chkSafe.click();
    }

    public void checkRadio() {
        chkRadio.click();
    }

    public Boolean roomFormExists() {
        return frmForm.isDisplayed();
    }

    public void setRoomPrice(String price) throws InterruptedException {
        Thread.sleep(1000);
        inptRoomPrice.sendKeys(price);
    }
}
