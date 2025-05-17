# uj-e-business

**Zadanie 1** Docker

:white_check_mark:  3.0 obraz ubuntu z Pythonem w wersji 3.10 [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/2b4b6f3de9ba8129556d233cac84bdb4abadb9f6)

:white_check_mark: 3.5 obraz ubuntu:24.02 z Javą w wersji 8 oraz Kotlinem [Link do commita 2 ](https://github.com/LukasWoz/uj-e-business/commit/6f9f34c6f0a0b2a1be4fcb4a228872cafaef0b4c)

:white_check_mark: 4.0 do powyższego należy dodać najnowszego Gradle’a oraz paczkę JDBC SQLite w ramach projektu na Gradle (build.gradle) [Link do commita 3](https://github.com/LukasWoz/uj-e-business/commit/535f6daaeca5a1462f4117fe6c59457992af3fc0)

:white_check_mark: 4.5 stworzyć przykład typu HelloWorld oraz uruchomienie aplikacji przez CMD oraz gradle [Link do commita 4](https://github.com/LukasWoz/uj-e-business/commit/b3e57076f07050928b438a5a7bae334eaa7c9014)

:white_check_mark: 5.0 dodać konfigurację docker-compose [Link do commita 4](https://github.com/LukasWoz/uj-e-business/commit/b3e57076f07050928b438a5a7bae334eaa7c9014)


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Docker)

Dockerhub: [Link do dockerhuba](https://hub.docker.com/repository/docker/lukaswoz/ebusiness/general)



**Zadanie 2** Scala

:white_check_mark:  3.0 Należy stworzyć kontroler do Produktów [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/f3b1b01954adc23d8500096c57101ea45c1287f4)

:white_check_mark: 3.5 Do kontrolera należy stworzyć endpointy zgodnie z CRUD - dane pobierane z listy [Link do commita 2 ](https://github.com/LukasWoz/uj-e-business/commit/7449483b50ff5969096b2bc3661c488539823803)

:white_check_mark: 4.0 Należy stworzyć kontrolery do Kategorii oraz Koszyka + endpointy zgodnie z CRUD [Link do commita 3](https://github.com/LukasWoz/uj-e-business/commit/805e4322948d9ee7b51f8f9373f7aa75603b3768)

:x: 4.5 Należy aplikację uruchomić na dockerze (stworzyć obraz) oraz dodać skrypt uruchamiający aplikację via ngrok (nie podawać tokena ngroka w gotowym rozwiązaniu)

:x: 5.0 Należy dodać konfigurację CORS dla dwóch hostów dla metod CRUD
Kontrolery mogą bazować na listach zamiast baz danych. CRUD: show all, show by id (get), update (put), delete (delete), add (post). 


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Scala)


**Zadanie 3** Kotlin

:white_check_mark:  3.0 Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor, która pozwala na przesyłanie wiadomości na platformę Discord [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/9a8364cd37a4d0a51903a9d9789d125e74f54745)

:white_check_mark: 3.5 Aplikacja jest w stanie odbierać wiadomości użytkowników z platformy Discord skierowane do aplikacji (bota) [Link do commita 1 ](https://github.com/LukasWoz/uj-e-business/commit/9a8364cd37a4d0a51903a9d9789d125e74f54745)

:white_check_mark: 4.0 Zwróci listę kategorii na określone żądanie użytkownika [Link do commita 2](https://github.com/LukasWoz/uj-e-business/commit/442eb97986dc3a3da90fa1ae5101f60fe7f2b78e)

:white_check_mark: 4.5 Zwróci listę produktów wg żądanej kategorii [Link do commita 2](https://github.com/LukasWoz/uj-e-business/commit/442eb97986dc3a3da90fa1ae5101f60fe7f2b78e)

:x: 5.0 Aplikacja obsłuży dodatkowo jedną z platform: Slack, Messenger, Webex


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Kotlin)


**Zadanie 4** Go

:white_check_mark:  3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie
miała kontroler Produktów zgodny z CRUD [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/12273dd6be62a1bda66c8c5bfc962bc8e14f822a)

:white_check_mark:  3.5 Należy stworzyć model Produktów wykorzystując gorm oraz
wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast
listy) [Link do commita 2 ](https://github.com/LukasWoz/uj-e-business/commit/0650b2f10984952fff7426f7810aaef8fcee403f)

:white_check_mark:  4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint [Link do commita 3 ](https://github.com/LukasWoz/uj-e-business/commit/a10a90630e0fbb1f797cfa36facc1c88aabfa827)

:white_check_mark:  4.5 Należy stworzyć model kategorii i dodać relację między kategorią, a produktem [Link do commita 4 ](https://github.com/LukasWoz/uj-e-business/commit/623d19ecea87f9f396934e03075a5777551e5936)

:white_check_mark:  5.0 pogrupować zapytania w gorm’owe scope'y [Link do commita 5 ](https://github.com/LukasWoz/uj-e-business/commit/65812af72c7ebc9fab759bf2dd33694ffc19f9d3)


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Go)


**Zadanie 5** Frontend

:white_check_mark:  3.0 W ramach projektu należy stworzyć dwa komponenty: Produkty oraz Płatności; Płatności powinny wysyłać do aplikacji serwerowej dane, a w
Produktach powinniśmy pobierać dane o produktach z aplikacji
serwerowej; [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/1da9b855e0c16196667832ce056aa5ccc5133edd)

:white_check_mark:  3.5 Należy dodać Koszyk wraz z widokiem; należy wykorzystać routing [Link do commita 2 ](https://github.com/LukasWoz/uj-e-business/commit/2415ae059b4bb7e01874bed9d34612c5ac56990e)

:white_check_mark:  4.0 Dane pomiędzy wszystkimi komponentami powinny być przesyłane za pomocą React hooks [Link do commita 3 ](https://github.com/LukasWoz/uj-e-business/commit/cf22759b68dbd959711dbfe6ee9ed55de812aeb4)

:white_check_mark:  4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz kliencką na dockerze via docker-compose [Link do commita 4 ](https://github.com/LukasWoz/uj-e-business/commit/9bb9da5c9f7acba9b9cf8b557403cefdca89d15e)

:x:  5.0 Należy wykorzystać axios’a oraz dodać nagłówki pod CORS


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Frontend)


**Zadanie 6** Testy

:white_check_mark:  3.0 Należy stworzyć 20 przypadków testowych w CypressJS lub Selenium
(Kotlin, Python, Java, JS, Go, Scala) [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/a2a39f7bd4ab040b13463161313e88aab88c1024)

:white_check_mark:  3.5 Należy rozszerzyć testy funkcjonalne, aby zawierały minimum 50
asercji [Link do commita 2 ](https://github.com/LukasWoz/uj-e-business/commit/af4302129c2cb6bb4a9e1d0bec162d6d76781e0c)

:x:  4.0 Należy stworzyć testy jednostkowe do wybranego wcześniejszego
projektu z minimum 50 asercjami

:x:  4.5 Należy dodać testy API, należy pokryć wszystkie endpointy z
minimum jednym scenariuszem negatywnym per endpoint

:x:  5.0 Należy uruchomić testy funkcjonalne na Browserstacku


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Cypress)


**Zadanie 7** Sonar

:white_check_mark:  3.0 Należy dodać litera do odpowiedniego kodu aplikacji serwerowej w
hookach gita [Link do commita 1](https://github.com/LukasWoz/uj-e-business-go/commit/faf2a6739a53bf06904d5e457af27311ebade1cf)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=LukasWoz_uj-e-business-go&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=LukasWoz_uj-e-business-go)

:white_check_mark:  3.5 Należy wyeliminować wszystkie bugi w kodzie w Sonarze (kod
aplikacji serwerowej) [Link do commita 2 ](https://github.com/LukasWoz/uj-e-business-go/commit/5b5771606e461a7b2ba4e6e386a1b5b882c2853e)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=LukasWoz_uj-e-business-go&metric=bugs)](https://sonarcloud.io/summary/new_code?id=LukasWoz_uj-e-business-go)

:white_check_mark:  4.0 Należy wyeliminować wszystkie zapaszki w kodzie w Sonarze (kod
aplikacji serwerowej) [Link do commita 3 ](https://github.com/LukasWoz/uj-e-business-go/commit/7347e78cb4163777d555a8a866be72dc4da8349b)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=LukasWoz_uj-e-business-go&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=LukasWoz_uj-e-business-go)

:white_check_mark:  4.5 Należy wyeliminować wszystkie podatności oraz błędy bezpieczeństwa
w kodzie w Sonarze (kod aplikacji serwerowej) [Link do commita 3 ](https://github.com/LukasWoz/uj-e-business-go/commit/7347e78cb4163777d555a8a866be72dc4da8349b)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=LukasWoz_uj-e-business-go&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=LukasWoz_uj-e-business-go)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=LukasWoz_uj-e-business-go&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=LukasWoz_uj-e-business-go)

:x: 5.0 Należy wyeliminować wszystkie błędy oraz zapaszki w kodzie
aplikacji klienckiej


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business-go/tree/Go)