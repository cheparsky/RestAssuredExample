# language: pl
Potrzeba biznesowa: Testowanie metody payments w rest

  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> bez środków na koncie zleceniodawcy

  Przy inicjalizacji <typ przelewu> wysyłamy zapytania ze wszystkimi poprawnymi wartościami, ale konto zlecenniodawcy nie ma środków.

    Zakładając że chcemy wysłać standardowy <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I pole <pole> ma wartość <wartosc>
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    I akceptujemy zgodę hasłem: <hasło>
    Wtedy dostejym wiadomość 'Zgoda została zaakceptowana'

    Kiedy wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200
    I odpowiedź scope zawiera pole scope z wartościami 'payment'

    Kiedy tworzymy zapytanie payment
    I pole <pole> ma wartość <wartosc>
    I wysyłamy zapytanie payment
    Wtedy status odpowiedzi payment jest 200
    I jest wartość odpowiedzi payment paymentId
    I jest wartość odpowiedzi payment generalStatus
    I jest wartość odpowiedzi payment detailedStatus
    I kopiujemy wartości odpowiedzi payment do schowka

    Kiedy wysyłamy zapytanie status
    Wtedy status odpowiedzi status jest 200
    I jest wartość odpowiedzi status generalStatus
    I jest wartość odpowiedzi status detailedStatus

    Przykłady:
      | typ przelewu                      | pole                   | wartosc                      | login  | hasło     |
      | Przelew krajowy                   | RACHUNEK_ZLECENIODAWCY | PL58114010100000419104002004 | 887802 | 12345678  |
      | Przelew podatkowy                 | RACHUNEK_ZLECENIODAWCY | PL58114010100000419104002004 | 887802 | 12345678  |
      | Przelew zagraniczny SEPA          | RACHUNEK_ZLECENIODAWCY | PL73114010100000206973001033 | 887802 | 12345678  |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_ZLECENIODAWCY | PL58114010100000419104002004 | 887802 | 12345678  |

