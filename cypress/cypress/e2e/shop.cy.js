describe("Testy aplikacji sklepu", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173");
  });

  it("[1/21] Strona główna ładuje się z nagłówkiem", () => {
    cy.get("h1").should("contain", "Sklep");
  });

  it("[2/21] Renderuje nagłówek i zawartość główną", () => {
    cy.get(".header").should("exist");
    cy.get(".main-content").should("exist");
  });

  it("[3/21] Linki w nagłówku są widoczne", () => {
    cy.get(".nav-links").within(() => {
      cy.contains("Produkty");
      cy.contains("Koszyk");
      cy.contains("Płatności");
    });
  });

  it("[4/21] Wyświetla komponent produktów", () => {
    cy.contains("Produkty").click();
    cy.get("h2").should("contain", "Produkty");
    cy.get(".product-card").should("have.length.at.least", 1);
  });

  it("[5/21] Dodaje jeden produkt do koszyka", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
  });

  it("[6/21] Dodanie tego samego produktu wielokrotnie zwiększa jego ilość", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click().click();
    cy.contains("Koszyk").click();
    cy.get("li").first().should("contain", "× 2");
  });

  it("[7/21] Link do Koszyka działa poprawnie", () => {
    cy.contains("Koszyk").click();
    cy.url().should("include", "/cart");
  });

  it("[8/21] Wyświetla zawartość koszyka", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Koszyk").click();
    cy.get("li").should("have.length.at.least", 1);
  });

  it("[9/21] Czyści koszyk", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Koszyk").click();
    cy.contains("Wyczyść koszyk").click();
    cy.contains("Twój koszyk jest pusty.");
  });

  it("[10/21] Koszyk sumuje cenę produktów", () => {
    cy.contains("Produkty").click();
    cy.get("button").contains("Dodaj do koszyka").each(($btn) => {
      cy.wrap($btn).click().click();
    });
    cy.contains("Koszyk").click();
    cy.contains("Razem:");
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
  });

  it("[13/21] Wyświetla formularz płatności gdy koszyk nie jest pusty", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Płatności").click();
    cy.get("form").should("exist");
  });

  it("[14/21] Nie pokazuje formularza gdy koszyk jest pusty", () => {
    cy.contains("Koszyk").click();
    cy.contains("Płatności").click();
    cy.contains("Nie możesz złożyć płatności");
  });

  it("[15/21] Wysyła poprawne dane płatności", () => {
    cy.contains("Produkty").click();
    cy.contains("Dodaj do koszyka").first().click();
    cy.contains("Płatności").click();
    cy.get("input[type='text']").type("blik");
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
    cy.contains("Dodaj do koszyka").should("exist");
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
  });
});