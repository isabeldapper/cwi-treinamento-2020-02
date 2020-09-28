package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.*;
import utils.Browser;
import utils.Utils;

import static org.junit.Assert.*;

@Feature("Site de ecommerce")
public class SetupTest extends BaseTests{

    @Test
    @Story("Abrir o site")
    public void testOpeningBrowserAndLoadPage(){
        assertTrue(Browser.geCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()));
        System.out.println("Abrimos o navegador e carregamos a URL");
    }

    @Test
    @Story("Realizar login")
    public void testLogin(){
        Browser.geCurrentDriver().findElement(By.className("login")).click();
        System.out.println("click sign in");
        assertTrue(Browser.geCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()
                .concat("index.php?controller=authentication&back=my-account")));
        Browser.geCurrentDriver().findElement(By.id("email")).sendKeys("rrsetcwi+autopractice@gmail.com");
        System.out.println("preencheu email");
        Browser.geCurrentDriver().findElement(By.id("passwd")).sendKeys("teste123");
        System.out.println("preencheu senha");
        Browser.geCurrentDriver().findElement(By.id("SubmitLogin")).click();
        System.out.println("click login");
        assertTrue(Browser.geCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()
                .concat("index.php?controller=my-account")));
        System.out.println("validou a url da minha conta");
        assertTrue(Browser.geCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("validou minha conta");
    }

    @Test
    public void testSearch(){

        String quest = "DRESS";
        String questResultQtd = "7 results have been found";

        //iniciar as páginas
        HomePage home = new HomePage();
        SearchPage search = new SearchPage();

        //fazer a pesquisa
        home.doSearch(quest);

        //validar a pesquisa
        assertTrue(search.isSearchPage());
        assertEquals(search.getTextLighter().replace("\"", ""), quest);
        assertThat(search.getTextHeading_counter(), CoreMatchers.containsString(questResultQtd));
    }

    @Test
    @Story("Acessar categoria")
    public void testAccessCategoryTShirts(){
        //iniciar as páginas
        HomePage home = new HomePage();
        CategoryPage category = new CategoryPage();

        //clicar na categoria
        home.clickCategoryTShirts();
        Browser.geCurrentDriver().findElement(By.linkText("T-SHIRTS")).click();

        //validar se ocorre o direcionamento
        assertTrue(category.isPageTShirts());
    }

    @Test
    @Story("acessar pagina de produto")
    public void testAddProductToProductPage(){
        //acessar a categoria TShirts
        testAccessCategoryTShirts();

        //iniciar as páginas
        CategoryPage category = new CategoryPage();
        ProductPage pdp = new ProductPage();

        //salva nome do produto na pagina de categoria
        String nameProductCategory = category.getProductNameCategory();

        //click more e redireciona para produto
        category.clickProductAddProductPage();

        //verificar se está em detalhes
        assertEquals(pdp.getProductNamePDP(), nameProductCategory);
    }

    @Test
    @Story("Adicionar no carrinho")
    public void testAddProductToCartPage(){
        //acessar página do produto
        testAddProductToProductPage();

        //iniciar as páginas
        ProductPage pdp = new ProductPage();
        CartPage cart = new CartPage();

        //salvar o nome do produto
        String nameProductPDP = pdp.getProductNamePDP();

        //clicar no botão de Add
        pdp.clickButtonAddToCart();

        //clicar no botão da modal
        pdp.clickButtonModalProceedToCheckout();

        //validação de nome produto
        assertEquals(cart.getNameProductCart(), nameProductPDP);

    }
}



//    @Test
//    public void testCreateAccount() {
//        Browser.geCurrentDriver().findElement(By.className("login")).click();
//        System.out.println("clicou em sign in");
//        assertTrue(Browser.geCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()
//                .concat("index.php?controller=authentication&back=my-account")));
//        Browser.geCurrentDriver().findElement(By.id("email")).sendKeys("icristinadapper@gmail.com");
//        System.out.println("preencheu email");
//        Browser.geCurrentDriver().findElement(By.id("SubmitCreate")).click();
//        System.out.println("clicou em create account");
//        assertTrue(Browser.geCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()
//                .concat("index.php?controller=authentication&back=my-account#account-creation")));
//        System.out.println("redirecionou para formulário");
//    }

