# language: pl
Potrzeba biznesowa: Testowanie metod PIS w ramach psd2

  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z odrzucaniem zgody

  Przy inicjalizacji <typ przelewu> wysyłamy zapytania ze wszystkimi poprawnymi wartościami ale odrzucamy zgodę

    Zakładając że chcemy wysłać standardowy <typ przelewu>

    Kiedy tworzymy zapytanie consent
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
      | typ przelewu                      |  login  | hasło     |
      | Przelew krajowy                   |  887802 | 12345678  |
      | Przelew podatkowy                 |  887802 | 12345678  |
      | Przelew zagraniczny SEPA          |  887802 | 12345678  |
      | Przelew zagraniczny inny niż SEPA |  887802 | 12345678  |