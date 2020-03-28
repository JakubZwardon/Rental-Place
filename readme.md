# Rental Place Application

Ta aplikacja służy do obsługi zleceń w firmie zajmującej się wynajmem maszyn oraz różnego rodzaju sprzętu.


Aplikacja jest napisana w języku Java. Główna technologia jakiej użyłem do jej stworzenia do freamework Spring.
Za ustawienia odpowiada Spring Boot.
Warstwa trwałości danych to Spring Data Jpa, dane zapisane są w bazie danych MySql.
Aplikacjia oparta jest o Spring Mvc. Pliki do warstwy widoku zbudowane są za pomocą silnika szablonów Thymeleaf.

Użyłem też kilku skryptów Javascript.

Css oraz bootstrap.


W celu podbrania oraz uruchomienia aplikacji: 

```
git clone https://github.com/JakubZwardon/Rental-Place.git
cd Rental-Place
mvn package
java -jar target/*.jar
```

dodatkowo należy utworzyć bazę danych o nazwie 'rentalplace' na localhost
login: user
hasło: user
jeśli chcesz by dane były trwale przechowywane 
to po pierwszym uruchomieniu zmień pliku application.properties pole 'spring.jpa.hibernate.ddl-auto=create' na update
