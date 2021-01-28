# polSourceProject
Internship task for PolSource

Elementy niezbędne do uruchomienia programu:

Ten projekt został napisany przy użyciu Javy 11, Node.js 14.15 oraz Angulara 11.0.9.
Wykorzystano również program XAMPP w wersji 8.0.1 (w celu użycia MariaDB).

Wykorzystane IDE: IntelliJ IDEA Community Edition 2020.3.1 x64

Uruchomienie skryptów w celu utworzenia bazy danych:

Aby utworzyć bazę danych należy włączyć usługi Apache i MySQL aby uzyskać dostęp do phpMyAdmin. W folderze scripts znajdują się 2 pliki .sql zawierające skrypty inicjalizujące. W zakładce SQL należy wkleić treść skryptu create_user.sql i kliknąć wykonaj. Następnie w ten sam sposów wykonać należy skrypt create_database.sql

Build projektu:
Projekt jest zbudowany z użyciem Maven, więc uruchomienie go w IntelliJ pozwoli na zaimportowanie wszystkich zależności. W przypadku aplikacji angularowej, należy wejść do folderu polsource-interface i otworzyć tam wiersz poleceń. Aby zainstalować zależności należy wykonać komendę npm install.

Przykładowe użycia:
1. Dodanie notatki
  - Nad tabelą wyświetlającą wszystkie aktualne notatki użytkownik klika przycisk "Create new note".
  - Aby móc utworzyć notatkę należy wypełnić pola tytułu i zawartości zgodnie z uwagami dotyczącymi walidacji.
  - Po uzupełnieniu pól użytkownik klika na przycisk "Create", w przypadku błędu zostanie wyświetlony alert, w przeciwnym razie użytkownik powróci na akualną listę notatek z uwzględnieniem nowej notatki.
  
2. Edycja notatki
  - Na liście aktualnych notatek użytkownik klika przycisk "edit" i jest przekierowany do formularza zawierającego dane z wybranej notatki.
  - Użytkownik modyfikuje tytuł i/lub zawartość zgodnie z wiadomościami o walidacji.
  - Użytkonik klika na przycisk "Create", w przypadku błędu wyświetlony będzie alert, w przeciwnym razie użytkownik powróci na zaktualizowaną listę notatek.
  
 3. Odczyt notatki
  - Na liście aktualnych notatek użytkownik klika na tytuł danej notatki.
  - Zostaje wyświetlone okno modalne z detalami dotyczącymi tej notatki.
  - Użytkownik zamyka okno modalne przyciskiem X lub klikając poza obszar okna.
  
 4. Usunięcie notatki
  - Na liście aktualnych notatek użytkownik klika przycisk delete.
  - Zostaje wyświetlony alert z odpowiednią wiadomością
  - W przypadku sukcesu lista notatek jest aktualizowana (jeśli istnieje poprzednia wersja notatki, zostaje ona wtedy wyświetlona)
  
 5. Podgląd historii
  - Na liście notatek użytkownik klika przycisk "History" danej notatki
  - Użytkownik jest przekierowany na listę wersji notatki (od najstarszej do najnowszej)
  - Użytkownik może usuwać wersje notatki (jeśli wersja jest już usunięta, przycisk "Delete" jest nieaktywny)
  
UWAGA:
  - Program jest skonfigurowany tak, aby przy każdym uruchomieniu backendu baza notatek była inicjalizowana od nowa. Aby zachować notatki między kolejnymi uruchomieniami, w pliku "application.properties" należy zakomentować linię 5 z użyciem znaku #: 
            `spring.datasource.initialization-mode=always`
  - Aby uruchomić wszystkie testy jednocześnie należy utworzyć w IntelliJ konfigurację uruchomienia JUnit z parametrami VM:
            `-ea -Dspring.profiles.active=test`
