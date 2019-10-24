# Solvro Jak Dojade - Backend

### Solvro Jak Dojade jest backendem do aplikacji imitującej działanie JakDojade na potrzeby rekrutacji do Koła Naukowego Solvro

Backend został napisany w frameworku Spring. 
Aby można było łatwo odpalić aplikację na każdym systemie operacyjnym należy zainstalować Dockera oraz Gradle, 
a następnie wykonać następujące komendy w głównym katalogu aplikacji:

```shell script
gradle clean build
docker build -t solvro-image .
docker run --name solvro-jak-dojade -d -p 8080:8080 solvro-image
```
Po wykonaniu tych komend otrzymamy gotowy obraz Dockera z naszą aplikacją oraz uruchomiony kontener z tym obrazem. 
Aplikacja działa teraz pod [tym adresem.](http://localhost:8080/)

Z aplikacji można w pełni korzystać wykonując rejestrację nowego użytkownika lub używając danych logowania istniejącego 
użytkownika:
>username: user
>
>password: password

Do rejestracji można użyć _curla_ następującej postaci:

```shell script
curl -X POST "http://localhost:8080/registration" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"email\": \"<your_email@mail.com>\",  \"password\": \"<your_password>\",  \"username\": \"<your_username>\"}"
```

Pełna dokumentacja aplikacji jest dostępna [w tym miejscu](http://localhost:8080/swagger-ui.html).

**UWAGA:** niestety Swagger nie działa tu w pełni według mojego zamysłu. Aby dotrzeć do dokumentacji należy się zalogować,
a także automatycznie wygenerowana komenda _curl_ nie działa. Aby zadziałała należy dodać do niej flagę _--user_, np.
```shell script
curl -X GET "http://localhost:8080/stops" -H  "accept: */*" --user <your_username>:<your_password>
```

Po skończonej pracy kontener ten można zatrzymać za pomocą komendy:
```shell script
docker stop solvro-jak-dojade
```

Aby ponownie uruchomić kontener z aplikacją należy wykonać komendę:
```shell script
docker start solvro-jak-dojade
```
