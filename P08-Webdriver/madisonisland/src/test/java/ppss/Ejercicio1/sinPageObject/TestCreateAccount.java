package ppss.Ejercicio1.sinPageObject;
//import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.time.Duration;
import java.util.Arrays;

public class TestCreateAccount {

    WebDriver driver;
    ChromeOptions co;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        co = new ChromeOptions();
        co.setBrowserVersion("131");
        co.addArguments("--log-level=3");
       // co.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        driver = new ChromeDriver(co); // driver representa el navegador (se abre una sesion)

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://demo.magento.recolize.com/");

    }
    @Tag("OnlyOnce")
    @Test
    public void S1_scenario_createAccount_should_create_new_account_in_the_demo_store_when_this_account_does_not_exist() throws NoSuchElementException {

        // 1 . Verificar que el titulo de la pagina de inicio es correcto
        Assertions.assertEquals("Madison Island", driver.getTitle(), "El título de la página no es el esperado");

        // 2 . Seleccionamos "Account"
        // y seleecionamos el hiperenlace "Login"
        driver.findElement(By.cssSelector("skip-link skip-account")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click(); // Usando el atributo title para encontrar el enlace de login
        //driver.findElement(By.linkText("Log In")).click(); // Usando linkText cuando el texto es exacto

        // 3 . Verificamos si el titulo de la pagina es el correcto
        Assertions.assertEquals("Customer Login", driver.getTitle(), "El título de la página de login no es el esperado");

        // 4 . Seleccionamos el boton "Create Account"
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //driver.findElement(By.linkText("Create an Account")).click();

        // 5 . Verificamos que estamos en la pagina correcta usando el titulo de la misma "Create new Customer Account"
        Assertions.assertEquals("Create New Customer Account", driver.getTitle(), "El título de la página no es el esperado");

        // 6 . Rellenamos los campos con los datos de la cuenta
        driver.findElement(By.id("firstname")).sendKeys("Paula");
        driver.findElement(By.id("middlename")).sendKeys("Maria");
        driver.findElement(By.id("lastname")).sendKeys("Galvez Romero de Avila");
        driver.findElement(By.id("email_address")).sendKeys("pgrd1@gcloud.ua.es");
        driver.findElement(By.id("password")).sendKeys("myPassword");
        //driver.findElement(By.id("confirmation")).sendKeys("myPassword"); // Omitimos intencionalmente la confirmación de la contraseña para probar la validación
        driver.findElement(By.cssSelector("button[title='Register']")).click();

        // 7 . Verificamos que nos aparece el mensaje "This is a required field."
        // debajo del campo que nos hemos dejado vacio
        String validationMessage = driver.findElement(By.id("advice-required-entry-confirmation")).getText();
        Assertions.assertEquals("This is a required field.", validationMessage, "El mensaje de validación no es el esperado");
        
        // 8 . Rellenamos el campo que nos falta y volvemos a enviar los datos del formulario.
        driver.findElement(By.id("confirmation")).sendKeys("myPassword");
        driver.findElement(By.cssSelector("button[title='Register']")).click();

        // 9 . Vamos a retroceder a la pagina anterior ya que aparecerá un error en pantalla
        // pero all volver a la pagina anterior comprobaremos que a pesar del error, la cuenta se ha creado
        if (driver.getPageSource().contains("Fatal error")) {
            // Navegar atrás
            driver.navigate().back();
            Assertions.assertEquals("My Account", driver.getTitle());
        }
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}

