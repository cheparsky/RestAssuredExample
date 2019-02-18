# language: pl
Potrzeba biznesowa: Testowanie odrzucenia zgody w ramach psd2

  @Chrome
  @Rejected
  Szablon scenariusza: Odrzucenie zgody dla <typ scenariusza>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytania ze wszystkimi poprawnymi wartościami ale odrzucamy zgodę

    Zakładając że chcemy wysłać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent aspspConsentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    I odrzucamy zgodę
    Wtedy dostejemy w KOMUNIKAT_O_ODRZUCENIU_ZGODY wiadomość 'Zgoda została odrzucona'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 404
    I odpowiedź scope zawiera pole code z wartoścą 'ResourceNotFoundError'

    Przykłady:
      | typ scenariusza                   | login  | hasło    |
      | Przelew krajowy                   | 887802 | 12345678 |
      | Przelew podatkowy                 | 887802 | 12345678 |
      | Przelew zagraniczny SEPA          | 887802 | 12345678 |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 |
      | Szczegóły rachunku                | 887802 | 12345678 |
      | Listę transakcji                  | 887802 | 12345678 |
      | Szczegóły transakcji              | 887802 | 12345678 |


  @Chrome
  @Rejected
  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza>

  Przy pobieraniu <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy pobrać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent aspspConsentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    I wybieramy KONTEKST_NOWY
    I wybieramy jeden rachunek
    I odrzucamy zgodę
    Wtedy dostejemy w KOMUNIKAT_O_ODRZUCENIU_ZGODY wiadomość 'Zgoda została odrzucona'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 404
    I odpowiedź scope zawiera pole code z wartoścą 'ResourceNotFoundError'

    Przykłady:
      | typ scenariusza | login  | hasło    | lista rachunków |
      | Listę rachunków | 887802 | 12345678 | 1               |