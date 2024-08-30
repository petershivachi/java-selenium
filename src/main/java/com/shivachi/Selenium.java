package com.shivachi;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Selenium {
    Random random = new Random();

    private String firstName, lastName, dateOfBirth, gender, password, phone;
    int randomNumber = random.nextInt(9000) + 1000;

    public void dataIn(){
        Scanner inputTaker = new Scanner(System.in);
        System.out.println("\nEnter First Name e.g 'John' : ");
        firstName = inputTaker.nextLine();
        System.out.println("\nEnter Last Name e.g 'Doe' : ");
        lastName = inputTaker.nextLine();
        System.out.println("\nDate of Birth e.g '02 3 1989' : ");
        dateOfBirth = inputTaker.nextLine();
        System.out.println("\nGender e.g ' 1:F, 2:M, 3:Not say, 4:Custom' : ");
        gender = inputTaker.nextLine();
        System.out.println("\nPassword : ");
        password = inputTaker.nextLine();
        System.out.println("\nPhone Number : ");
        phone = inputTaker.nextLine();
    }

    public void googleAccountCreation(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://accounts.google.com/signup/v2/createaccount?flowName=GlifWebSignIn&flowEntry=SignUp");

            // Fill in the required fields (username, password, etc.)
            driver.findElement(By.id("firstName")).sendKeys(firstName);
            driver.findElement(By.id("lastName")).sendKeys(lastName);

            WebElement nextButton = driver.findElement(By.className("VfPpkd-LgbsSe"));
            nextButton.click();

            // Wait for birthday fields to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement dayField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("day")));

            // Fill in the birthday
            String[] birthdayElements = dateOfBirth.split(" ");
            Select monthDropdown = new Select(driver.findElement(By.id("month")));
//            monthDropdown.selectByValue(birthdayElements[1]);
//            dayField.clear();
//            dayField.sendKeys(birthdayElements[0]);
            monthDropdown.selectByValue(birthdayElements[1]);
            driver.findElement(By.id("day")).sendKeys(birthdayElements[0]);
            driver.findElement(By.id("year")).sendKeys(birthdayElements[2]);

            // Select gender
            Select genderDropdown = new Select(driver.findElement(By.id("gender")));
            genderDropdown.selectByValue(gender);
            nextButton = driver.findElement(By.className("VfPpkd-LgbsSe"));
            nextButton.click();

            // Wait and create custom email if option is available
            TimeUnit.SECONDS.sleep(2);
            if (!driver.findElements(By.id("selectionc4")).isEmpty()) {
                WebElement createOwnOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("selectionc4")));
                createOwnOption.click();
            }

            String username = String.format("%s.%s%d", firstName, lastName, randomNumber);
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("Username")));
            usernameField.clear();
            usernameField.sendKeys(username);

            nextButton = driver.findElement(By.className("VfPpkd-LgbsSe"));
            nextButton.click();

            // Enter and confirm password
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));
            passwordField.clear();
            passwordField.sendKeys(password);

            WebElement confirmPasswordField = driver.findElement(By.name("PasswdAgain"));
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys(password);

            nextButton = driver.findElement(By.className("VfPpkd-LgbsSe"));
            nextButton.click();

            // Phone number verification
//            WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));
//            passwordField.clear();
//            passwordField.sendKeys(phone);
//
//
//            nextButton = driver.findElement(By.className("VfPpkd-LgbsSe"));
//            nextButton.click();

            // Skip phone number and recovery email steps (if possible)
            WebElement skipButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button span.VfPpkd-vQzf8d"))).get(0);
            skipButtons.click();

            // Agree to terms
            WebElement agreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button span.VfPpkd-vQzf8d")));
            agreeButton.click();

            System.out.println("Your Gmail was successfully created:");
            System.out.printf("gmail: %s@gmail.com\npassword: %s%n", username, password);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
//            driver.quit();
        }
    }

    public void jumiaAccountCreation(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.jumia.com");

            // Click on "Login" or "Sign Up"
            driver.findElement(By.linkText("Sign in")).click();

            // Click on "Sign in with Google"
            driver.findElement(By.xpath("//button[contains(text(),'Google')]")).click();

            // Perform the login with Google credentials
            driver.findElement(By.id("identifierId")).sendKeys("your.google.account@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            // Further steps to complete Google authentication

            // Fill in any additional details required by Jumia
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            driver.quit();
        }
    }


    public void searchProductAndAddToCart(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.jumia.com");

            // Perform login steps (reuse the code from above)
            // ...

            // Search for the product
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("ailyons hd-199a electric dry iron box silver & black (1 yr wrty)");
            searchBox.submit();

            // Wait and click on the product
            // ...
            driver.findElement(By.xpath("//a[contains(@href, 'ailyons-hd-199a-electric-dry-iron')]")).click();

            // Add to cart
            driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();

            // Navigate to cart
            driver.findElement(By.linkText("Cart")).click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public void sendReport(){
        String to = "recipient@example.com";
        String from = "your.email@example.com";
        String host = "smtp.example.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Automation Report");
            message.setText("Report");

            Transport.send(message);
            System.out.println("Report sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
