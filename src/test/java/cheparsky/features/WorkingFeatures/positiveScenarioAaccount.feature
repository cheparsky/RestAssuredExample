# language: pl
Potrzeba biznesowa: Testowanie metody accounts w rest

  @Chrome
  @Positive
  @Accounts
  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza>

  Przy pobieraniu <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy pobrać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    I wybieramy KONTEKST_NOWY
    I wybieramy jeden rachunek
    I akceptujemy zgodę hasłem: <hasło>
    Wtedy dostejemy w KOMUNIKAT_O_ZAAKCEPTOWANEJ_ZGODZIE wiadomość 'Zgoda została zaakceptowana'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie account
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200
    I odpowiedź account zawiera pole accounts[0].accountNumber z wartoścą '81114010100000200301001001'


    Przykłady:
      | typ scenariusza      |  login  | hasło     | lista rachunków |
      | Listę rachunków      |  887802 | 12345678  | 1               |

  @Chrome
  @Positive
  @Accounts
  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza>

  Przy pobieraniu <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy pobrać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    I akceptujemy zgodę hasłem: <hasło>
    Wtedy dostejemy w KOMUNIKAT_O_ZAAKCEPTOWANEJ_ZGODZIE wiadomość 'Zgoda została zaakceptowana'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie account
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200


    Przykłady:
      | typ scenariusza      |  login  | hasło     |
      | Szczegóły rachunku   |  887802 | 12345678  |
      | Listę transakcji     |  887802 | 12345678  |
      | Szczegóły transakcji |  887802 | 12345678  |