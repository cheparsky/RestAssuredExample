# language: pl
Potrzeba biznesowa: Testowanie metody payments w rest

  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza>

  Przy inicjalizacji <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy wysłać <typ scenariusza>

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
    I odpowiedź scope zawiera pole scope z wartoścą w liście 'payment'

    Kiedy tworzymy zapytanie payment
    I budujemy body payment
    I wysyłamy zapytanie payment
    Wtedy status odpowiedzi payment jest 201
    I jest wartość odpowiedzi payment paymentId
    I jest wartość odpowiedzi payment generalStatus
    I jest wartość odpowiedzi payment detailedStatus
    I kopiujemy wartości odpowiedzi payment do schowka

    Kiedy tworzymy zapytanie status
    I wysyłamy zapytanie status
    Wtedy status odpowiedzi status jest 200
    I jest wartość odpowiedzi status generalStatus
    I jest wartość odpowiedzi status detailedStatus

  Przykłady:
      | typ scenariusza                      |  login  | hasło     |
      | Przelew krajowy                   |  887802 | 12345678  |
      | Przelew podatkowy                 |  887802 | 12345678  |
      | Przelew zagraniczny SEPA          |  887802 | 12345678  |
      | Przelew zagraniczny inny niż SEPA |  887802 | 12345678  |

