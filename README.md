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


**Zadanie 2** Kotlin

:white_check_mark:  3.0 Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor, która pozwala na przesyłanie wiadomości na platformę Discord [Link do commita 1](https://github.com/LukasWoz/uj-e-business/commit/9a8364cd37a4d0a51903a9d9789d125e74f54745)

:white_check_mark: 3.5 Aplikacja jest w stanie odbierać wiadomości użytkowników z platformy Discord skierowane do aplikacji (bota) [Link do commita 1 ](https://github.com/LukasWoz/uj-e-business/commit/9a8364cd37a4d0a51903a9d9789d125e74f54745)

:white_check_mark: 4.0 Zwróci listę kategorii na określone żądanie użytkownika [Link do commita 2](https://github.com/LukasWoz/uj-e-business/commit/442eb97986dc3a3da90fa1ae5101f60fe7f2b78e)

:white_check_mark: 4.5 Zwróci listę produktów wg żądanej kategorii [Link do commita 2](https://github.com/LukasWoz/uj-e-business/commit/442eb97986dc3a3da90fa1ae5101f60fe7f2b78e)

:x: 5.0 Aplikacja obsłuży dodatkowo jedną z platform: Slack, Messenger, Webex


Kod: [Link do brancha](https://github.com/LukasWoz/uj-e-business/tree/Kotlin)