package br.ce.wcaquino.appium.test;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.appium.core.BaseTest;
import br.ce.wcaquino.appium.core.DriverFactory;
import br.ce.wcaquino.appium.page.FormularioPage;
import br.ce.wcaquino.appium.page.MenuPage;

public class FormularioTeste extends BaseTest {

	private MenuPage menu = new MenuPage();
	private FormularioPage page = new FormularioPage();

	@Before
	public void inicializarAppium() throws MalformedURLException {
		menu.acessarFormulario();
	}

	@Test
	public void devePreencherCampoTexto() throws MalformedURLException {
		page.escreverNome("Thiago");
		assertEquals("Thiago", page.obterNome());
	}

	@Test
	public void deveInteragirCombo() throws MalformedURLException {
		page.selecionarCombo("Nintendo Switch");
		Assert.assertEquals("Nintendo Switch", page.obterValorCombo());
	}

	@Test
	public void deveInteragirSwichCheckbox() throws MalformedURLException {

		Assert.assertFalse(page.isCheckMarcado());
		Assert.assertTrue(page.isCheckSwitchMarcado());

		page.clicarCheck();
		page.clicarSwitch();

		Assert.assertTrue(page.isCheckMarcado());
		Assert.assertFalse(page.isCheckSwitchMarcado());
	}

	@Test
	public void DeveRealizarCadastro() throws MalformedURLException {

		// Preencher formulario
		page.escreverNome("Thiago");
		page.selecionarCombo("PS4");
		page.clicarCheck();
		page.clicarSwitch();

		// Clicar em Salvar
		page.salvar();

		// Validar campos
		Assert.assertEquals("Nome: Thiago", page.obterNomeCadastrado());
		Assert.assertEquals("Console: ps4", page.obterConsoleCadastrado());
		Assert.assertFalse(page.obterSwitchCadastrado().endsWith("Marcado"));
		// Assert.assertTrue(page.obterCheckCadastrado().endsWith("Off"));
	}

	@Test
	public void DeveRealizarCadastroDemorado() throws MalformedURLException {
		
		
		// Preencher formulario
		page.escreverNome("Thiago");
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		// Clicar em Salvar
		page.salvarDemorado();
		//esperar(3000);
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Nome: Thiago']")));
		// Validar campos
		Assert.assertEquals("Nome: Thiago", page.obterNomeCadastrado());
	}
}
