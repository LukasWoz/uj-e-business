describe("Testy aplikacji sklepu", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173");
  });

  it("[1/21] Strona główna ładuje się z nagłówkiem", () => {
    cy.get("h1").should("contain", "Sklep").and("be.visible");
    cy.get(".nav-links").should("exist");
    cy.get("nav.nav-links").should("have.css", "display", "block");
    cy.get(".header").should("be.visible");
  });

  it("[2/21] Renderuje nagłówek i zawartość główną", () => {
    cy.get(".header").should("exist");
    cy.get(".header").should("have.css", "background-color", "rgb(248, 249, 250)");
    cy.get(".main-content").should("exist");
  });

  it("[3/21] Linki w nagłówku są widoczne", () => {
    cy.get(".nav-links").within(() => {
      cy.contains("Produkty").should("exist");
      cy.contains("Koszyk").should("exist");
      cy.contains("Płatności").should("exist");
    });
    cy.get(".nav-links a").first().should("have.attr", "href");
  });

  it("[4/21] Wyświetla komponent produktów", () => {
    cy.contains("Produkty").click();
    cy.get("h2").should("contain", "Produkty");
    cy.get(".product-card").should("have.length.at.least", 1);
    cy.get(".product-card").first().within(() => {
      cy.get("strong").should("exist");
      cy.get("p").should("contain", "zł");
    });
  });

  it("[5/21] Dodaje jeden produkt do koszyka", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Koszyk").click();
    cy.get("li").should("have.length.at.least", 1);
    cy.get("li").first().should("contain.text", "zł");
    cy.get("li").first().should("contain.text", "Biurko");
  });

  it("[6/21] Dodanie tego samego produktu wielokrotnie zwiększa jego ilość", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click().click();
    cy.contains("Koszyk").click();
    cy.get("li").first().should("contain", "× 2");
    cy.get("li").should("have.length", 1);
    cy.contains("Razem:").should("exist");
  });

  it("[7/21] Link do Koszyka działa poprawnie", () => {
    cy.contains("Koszyk").click();
    cy.url().should("include", "/cart");
    cy.get("h2").should("contain", "Koszyk");
  });

  it("[8/21] Wyświetla zawartość koszyka", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Koszyk").click();
    cy.get("li").should("have.length.at.least", 1);
    cy.contains("Razem:");
  });

  it("[9/21] Czyści koszyk", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Koszyk").click();
    cy.contains("Wyczyść koszyk").click();
    cy.contains("Twój koszyk jest pusty.");
    cy.get("li").should("not.exist");
  });

  it("[10/21] Koszyk sumuje cenę produktów", () => {
    cy.contains("Produkty").click();
    cy.get("button").contains("Dodaj do koszyka").each(($btn) => {
      cy.wrap($btn).click().click();
    });
    cy.contains("Koszyk").click();
    cy.contains("Razem:");
    cy.get("li").should("have.length.at.least", 1);
    cy.contains("Razem:").invoke("text").then((text) => {
      const amount = parseFloat(text.replace(/[^\d.]/g, ""));
      expect(amount).to.be.greaterThan(0);
    });
  });

  it("[11/21] Zachowuje zawartość koszyka między widokami", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Koszyk").click();
    cy.contains("Produkty").click();
    cy.contains("Koszyk").click();
    cy.get("li").should("have.length.at.least", 1);
  });

  it("[12/21] Link do Płatności działa poprawnie", () => {
    cy.contains("Płatności").click();
    cy.url().should("include", "/payments");
    cy.get("h2").should("contain", "Płatności");
    cy.get("form").should("not.exist");
  });

  it("[13/21] Wyświetla formularz płatności gdy koszyk nie jest pusty", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Płatności").click();
    cy.get("form").should("exist");
    cy.get("input").should("have.length.at.least", 1);
    cy.get("label").contains("Kwota").should("exist");
    cy.get("input[type='text']").should("have.attr", "required");
    cy.get("input[type='number']").should("have.attr", "readonly");
  });

  it("[14/21] Nie pokazuje formularza gdy koszyk jest pusty", () => {
    cy.contains("Koszyk").click();
    cy.contains("Płatności").click();
    cy.contains("Nie możesz złożyć płatności");
    cy.get("form").should("not.exist");
  });

  it("[15/21] Wysyła poprawne dane płatności", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Płatności").click();
    cy.get("input[type='text']").type("blik");
    cy.get("input[type='number']").should("have.value", "201.99");
    cy.get("button[type='submit']").click();
  });

  it("[16/21] Waliduje formularz płatności – brak danych", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Płatności").click();
    cy.get("button[type='submit']").click();
    cy.on("window:alert", (txt) => {
      expect(txt).to.include("Payment error");
    });
    cy.get("input[type='text']").should("have.value", "");
  });

  it("[17/21] Płatność resetuje koszyk", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Płatności").click();
    cy.get("input[type='text']").type("blik");
    cy.get("button[type='submit']").click();
    cy.contains("Koszyk").click();
    cy.contains("Twój koszyk jest pusty.");
  });

  it("[18/21] Nagłówek widoczny na wszystkich stronach", () => {
    ["/", "/cart", "/payments"].forEach((path) => {
      cy.visit("http://localhost:5173" + path);
      cy.contains("Sklep");
      cy.get(".header").should("exist");
    });
  });

  it("[19/21] Nie ma błędów w konsoli przeglądarki", () => {
    cy.visit("http://localhost:5173", {
      onBeforeLoad(win) {
        cy.spy(win.console, "error");
      },
    });
    cy.window().then((win) => {
      expect(win.console.error).not.to.be.called;
    });
  });

  it("[20/21] Przyciski dodaj do koszyka istnieją", () => {
    cy.contains("Produkty").click();
    cy.get("button").contains("Dodaj do koszyka").should("exist");
    cy.get("button").contains("Dodaj do koszyka").should("have.length.at.least", 1);
    cy.get("button").contains("Dodaj do koszyka").first().should("have.css", "background-color");
  });

  it("[21/21] Każdy przycisk 'Dodaj do koszyka' działa", () => {
    cy.contains("Produkty").click();
    cy.get("button")
      .filter((i, el) => el.innerText.includes("Dodaj do koszyka"))
      .each(($btn) => {
        cy.wrap($btn).click();
      });
    cy.contains("Koszyk").click();
    cy.get("li").should("have.length.at.least", 1);
    cy.get("li").each(($el) => {
      cy.wrap($el).should("contain", "zł");
    });
  });
});