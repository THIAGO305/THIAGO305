package br.ce.wcaquino.appium.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculadoraAsusTeste {

	@Test
	public void devePreencherCampoTexto() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("deviceName", "emulator-5554");
		desiredCapabilities.setCapability("automationName", "uiautomator2");
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				"C:\\Users\\Thiago.Digitrack\\eclipse-workspace\\CursoAppium\\src\\main\\resources\\original.apk");

		AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
				desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Selecionar formulario
		List<MobileElement> elementosEncontrados = driver.findElements(By.className("android.widget.TextView"));
		//for (MobileElement elemento : elementosEncontrados) {
		//	System.out.println(elemento.getText());
		//}
		elementosEncontrados.get(1).click();
		
		
		// escrever nome
		MobileElement camponome = driver.findElement(MobileBy.AccessibilityId("nome"));
		camponome.sendKeys("Thiago");

		// checar nome escrito
		String text = camponome.getText();
		Assert.assertEquals("Thiago", text);

		driver.quit();
	}

}
