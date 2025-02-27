package introduction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class amazonwatch {

	public static void main(String[] args) {
		// Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\prati\\chromedriver-win64\\chromedriver.exe");
        
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
  
        driver.manage().window().maximize();
        
        // Open Amazon India
        driver.get("https://www.amazon.in");
        
        // Search for "Wrist Watches"
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Wrist Watches");
        searchBox.submit();
        
        // Apply Filters
        // Display Type: Analogue
        driver.findElement(By.xpath("//span[text()='Analogue']")).click();
        
        // Band Material: Leather
        driver.findElement(By.xpath("//span[text()='Leather']")).click();
        
        // Brand: Titan
        driver.findElement(By.xpath("//span[text()='Titan']")).click();
        
        // Discount: 25% Off or more
        driver.findElement(By.xpath("//span[contains(text(),'25% Off or more')]")).click();
        
        // Price Range ₹6000 - ₹8000
        driver.findElement(By.id("low-price")).sendKeys("6000");
        driver.findElement(By.id("high-price")).sendKeys("8000");
        driver.findElement(By.xpath("//input[@aria-labelledby='a-autoid-1-announce']")).click();
        
        // Wait for results to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get the third product
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
        if (products.size() >= 3) {
            WebElement thirdProduct = products.get(2);
            
            // Extract price, MRP, and discount percentage
            String price = thirdProduct.findElement(By.xpath(".//span[@class='a-price-whole']")).getText();
            String mrp = thirdProduct.findElement(By.xpath(".//span[@class='a-text-price']//span")) != null ? 
                         thirdProduct.findElement(By.xpath(".//span[@class='a-text-price']//span")).getText() : "N/A";
            String discount = thirdProduct.findElement(By.xpath(".//span[contains(@class,'savingsPercentage')]")) != null ? 
                              thirdProduct.findElement(By.xpath(".//span[contains(@class,'savingsPercentage')]")).getText() : "N/A";
            
            // Print details
            System.out.println("Price: ₹" + price);
            System.out.println("MRP: " + mrp);
            System.out.println("Percentage Discount: " + discount);
        } else {
            System.out.println("Less than 3 products found.");
        }
        

	}

}
