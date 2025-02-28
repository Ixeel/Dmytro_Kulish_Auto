package patterns.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

public class InstructionsPage {
    private WebDriver driver;
    private Path filepath = Paths.get("target/carList.txt");
    private Path pdfFilePath = Paths.get("target/Front windshield wipers on Audi TT.pdf");
    public InstructionsPage() {
        this.driver = BrowserFactory.getDriver();
    }
    private By ButtonCarOption = By.id("brandSelectDropdown");
    private By downloadButton = By.xpath("//a//a[contains(@href, \"Front windshield\")]");
    private List<String> requiredElements =  Arrays.asList("Audi", "BMW", "Ford", "Porsche", "Fiat");

    public InstructionsPage clickButtonCarOption() {
        driver.findElement(ButtonCarOption).click();
        return this;
    }
    public Path getPath() {
        return filepath;
    }
    public Path getPDFFilePath() {
        return pdfFilePath;
    }

    public InstructionsPage clickDownloadButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        driver.findElement(downloadButton).click();
        waitForDownloadOfFile("target/Front windshield wipers on Audi TT.pdf");
        return this;
    }

    public boolean readFile() {
        try (FileReader reader = new FileReader("target/carList.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Set<String> readLines = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                readLines.add(line.trim());
            }
            boolean allRequiredLinesPresent = true;

            for (String element : requiredElements) {
                if (!readLines.contains(element)) {
                    allRequiredLinesPresent = false;
                }
            }
            return allRequiredLinesPresent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> GetElementsAndWriteInFile() {
        List<WebElement> elements = driver.findElements(By.cssSelector("li.brand-select-dropdown_item"));
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            textList.add(element.getText());
        }
        writeFile(textList);
        return textList;
    }
    public boolean waitForDownloadOfFile(String pdfFilePath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(webDriver -> Files.exists(Paths.get(pdfFilePath)));
    }
    public void writeFile(List <String> textlist) {
        try {
            Files.write(filepath,textlist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}