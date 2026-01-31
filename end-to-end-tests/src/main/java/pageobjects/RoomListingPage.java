package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RoomListingPage extends BasePage {


    By wifiCheck = By.xpath("//label[text()='Wifi']/preceding-sibling::input");
    By safeCheck = By.xpath("//label[text()='Safe']/preceding-sibling::input");
    By radioCheck = By.xpath("//label[text()='Radio']/preceding-sibling::input");
    By inptRoomPrice = By.xpath("//input[@id=\"roomPrice\"]");
    By roomForm =  By.xpath("//div[contains(@class,'room-form')]");
    By lstRooms = By.xpath("//div[@data-type='room']");


    public RoomListingPage(WebDriver driver){
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-2.rowHeader")));
    }

    public void populateRoomName(String roomName) throws InterruptedException {
        By roomNameLocator = By.id("roomName");
        WebElement roomNameField = driver.findElement(roomNameLocator);
        roomNameField.sendKeys(roomName);
        Thread.sleep(200);
    }

    public void clickCreateRoom() throws InterruptedException {
        By createRoomLocator = By.id("createRoom");
        WebElement createRoom = driver.findElement(createRoomLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", createRoom);
        System.out.println("👆 Clic forzado (JS) enviado al botón Crear.");
    }

    public int roomCount() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By listRoomLocator = By.cssSelector("div[data-type~=\"room\"]");
        List<WebElement> roomList = driver.findElements(listRoomLocator);
        roomList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[data-type~=\"room\"]")));
        System.out.println("List Room Size:" + roomList.size());
        return roomList.size();
    }

    public int getRoomCountWithWait(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Damos hasta 15 segundos
        By listRoomLocator = By.cssSelector("div[data-type~=\"room\"]");
        List<WebElement> roomList = driver.findElements(listRoomLocator);
        try {
            // Esta línea es mágica: Espera hasta que la lista tenga EXACTAMENTE el tamaño esperado
            // Nota: Debes usar el localizador By, no la lista @FindBy directamente para el wait
            roomList = wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[data-type~='room']"), expectedCount));
        } catch (TimeoutException e) {
            System.out.println("⚠️ El nuevo elemento no apareció a tiempo. Retornando conteo actual.");
        }

        // Ahora sí, obtenemos el tamaño real (sea el nuevo o el viejo si falló)
        return roomList.size();
    }



    public void clickFirstRoom() {
        By listRoomLocator = By.cssSelector("div[data-type~=\"room\"]");
        List<WebElement> roomList = driver.findElements(listRoomLocator);
        roomList.get(0).click();
    }

    public void checkWifi() {
        WebElement wifi = driver.findElement(wifiCheck);
        wifi.click();
    }

    public void checkSafe() {
        WebElement chkSafe = driver.findElement(safeCheck);
        chkSafe.click();
    }

    public void checkRadio() {
        WebElement chkRadio = driver.findElement(radioCheck);
        chkRadio.click();
    }

    public Boolean roomFormExists() {
        WebElement frmForm = driver.findElement(roomForm);
        return frmForm.isDisplayed();
    }

    public void setRoomPrice(String price) throws InterruptedException {
        WebElement roomPrice =   driver.findElement(inptRoomPrice);
        Thread.sleep(1000);
        roomPrice.sendKeys(price);
    }
}
