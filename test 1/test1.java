import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTests {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        String expectedTitle = "Dynamic Controls";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Page title is correct: " + actualTitle);
        } else {
            System.out.println("Page title is incorrect");
        }

        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        if (removeButton.isEnabled()) {
            System.out.println("'Remove' button is enabled");
        } else {
            System.out.println("'Remove' button is disabled");
        }

        removeButton.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//button[contains(text(),'Add')]"), "Add"));

        WebElement messageElement = driver.findElement(By.xpath("//p[@id='message']"));
        String messageText = messageElement.getText();
        if (messageText.equals("It's gone!")) {
            System.out.println("Text 'It's gone!' is displayed");
        } else {
            System.out.println("Text 'It's gone!' is not displayed");
        }

    
        driver.get("http://the-internet.herokuapp.com/add_remove_elements");

        for (int i = 0; i < 3; i++) {
            WebElement addElementButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
            addElementButton.click();
        }

        WebElement lastDeleteButton = driver.findElement(By.cssSelector("button.added-manually:last-child"));
        System.out.println("Text of the last 'Delete' button: " + lastDeleteButton.getText());

        int deleteButtonCount = driver.findElements(By.cssSelector("button.added-manually")).size();
        if (deleteButtonCount == 3) {
            System.out.println("Count of 'Delete' buttons is 3");
        } else {
            System.out.println("Count of 'Delete' buttons is not 3");
        }

        lastDeleteButton.click();

        deleteButtonCount = driver.findElements(By.cssSelector("button.added-manually")).size();
        if (deleteButtonCount == 2) {
            System.out.println("Count of 'Delete' buttons has changed to 2");
        } else {
            System.out.println("Count of 'Delete' buttons has not changed");
        }


        driver.quit();
    }
}
