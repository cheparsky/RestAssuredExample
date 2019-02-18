# language: pl
Potrzeba biznesowa: Testowanie metod PIS w ramach psd2

  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza>

  Przy inicjalizacji <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

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
    I akceptujemy zgodę hasłem: <hasło>
    Wtedy dostejemy w KOMUNIKAT_O_ZAAKCEPTOWANEJ_ZGODZIE wiadomość 'Zgoda została zaakceptowana'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200
    I odpowiedź scope zawiera pole scope z wartoścą w liście 'pis'

    Kiedy tworzymy zapytanie pis
    I budujemy body pis
    I wysyłamy zapytanie pis
    Wtedy status odpowiedzi pis jest 201
    I jest wartość odpowiedzi pis aspspPaymentId
    I jest wartość odpowiedzi pis generalStatus
    I jest wartość odpowiedzi pis detailedStatus
    I kopiujemy wartości odpowiedzi pis do schowka

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

