package br.rafaelhorochovec.selenium.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestDocker {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		driver.navigate().to("https://rlhorochovec.github.io/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void buscaRafaelHorochovec() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// procura o nome RAFAEL HOROCHOVEC
			String nome = driver.findElement(By.xpath("//*[@id='main']/header/h1")).getText();
			Assert.assertEquals("RAFAEL HOROCHOVEC", nome);
			
			// capturar screenshot
			File imagem = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
				FileUtils.copyFile(imagem, new File("target/screenshots/"+ nome +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
}
