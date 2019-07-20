# language: pl
Potrzeba biznesowa: Testowanie metody payments w rest

  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z błędną wartością pola <pole> = <wartosc>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z błędną wartością pola <pole>

    Zakładając że chcemy wysłać standardowy <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I pole <pole> ma wartość "<wartosc>"
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    Wtedy dostejemy w KOMUNIKAT_O_BLEDACH_NA_FORMULARZU wiadomość 'Na formularzu wystąpiły błędy'
    I dostejemy w <webElement> wiadomość '<oczekiwany tekst bledu>'

    Kiedy wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 404
    I odpowiedź scope zawiera pole code z wartoścą 'InternalServerError'
    I odpowiedź scope zawiera pole message z wartoścą 'Brak zgody'

    Kiedy tworzymy zapytanie payment
    I pole <pole> ma wartość "<wartosc>"
    I wysyłamy zapytanie payment
    Wtedy status odpowiedzi scope jest 404
    I odpowiedź scope zawiera pole code z wartoścą 'InternalServerError'
    I odpowiedź scope zawiera pole message z wartoścą 'Brak zgody'

    Kiedy wysyłamy zapytanie status
    Wtedy status odpowiedzi scope jest 404

    Przykłady:
      | typ przelewu                      | pole                 | wartosc                      | login  | hasło     | webElement                                                     | oczekiwany tekst bledu                                                                  |
      #RACHUNEK_KONTRAHENTA
      | Przelew krajowy                   | RACHUNEK_KONTRAHENTA | PL51109013620000000036017904 | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_NUMERZE_KONTRAHENTA_NRB                    | Niepoprawna suma kontrolna numeru rachunku bankowego kontrahenta w formacie NRB.        |
      | Przelew podatkowy                 | RACHUNEK_KONTRAHENTA | PL51109013620000000036017904 | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_NUMERZE_ORGANU_PODATKOWEGO_NRB             | Niepoprawna suma kontrolna numeru rachunku bankowego organu podatkowego w formacie NRB. |
      | Przelew zagraniczny SEPA          | RACHUNEK_KONTRAHENTA | CZ8862106701003200001668     | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_NUMERZE_KONTRAHENTA_IBAN                   | Niepoprawna suma kontrolna numeru rachunku bankowego kontrahenta w formacie IBAN.       |
      | Przelew zagraniczny SEPA          | RACHUNEK_KONTRAHENTA | PL80114021050000920545001001 | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_REALIZACJI_SEPA_Z_NUMEREM_KONTRAHENTA_IBAN | Brak możliwości realizacji Przelewu SEPA dla podanego rachunku IBAN.                    |
      | Przelew zagraniczny SEPA          | RACHUNEK_KONTRAHENTA | GB29NWBK60161331926819       | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_REALIZACJI_SEPA_Z_NUMEREM_KONTRAHENTA_IBAN | Brak możliwości realizacji Przelewu SEPA dla podanego rachunku IBAN.                    |
      #Ponizszy przykład przechodzi błędnie dlatego że nie jest walidowany niepoprawny numer kontrahenta dla podanego kodu SWIFT
      | Przelew zagraniczny inny niź SEPA | RACHUNEK_KONTRAHENTA | 002099968054                 | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_NUMERZE_KONTRAHENTA | Niepoprawna suma kontrolna numeru rachunku bankowego kontrahenta w formacie NRB.                               |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_KONTRAHENTA | PL80114021050000920545001001 | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_NUMEREM_KRAJU_KONTRAHENTA_SWIFT | Kod kraju w numerze rachunku kontrahenta niezgodny z kodem kraju w numerze SWIFT.                  |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_KONTRAHENTA | CZ8862106701003200001669     | 887802 | 12345678  | KOMUNIKAT_O_BLEDNYM_NUMEREM_KRAJU_KONTRAHENTA_SWIFT | Kod kraju w numerze rachunku kontrahenta niezgodny z kodem kraju w numerze SWIFT.                  |


    Przykłady:
      | typ przelewu                      | pole  | wartosc | login  | hasło     | webElement                       | oczekiwany tekst bledu                         |
      | Przelew krajowy                   | KWOTA | -10.00  | 887802 | 12345678  | KOMUNIKAT_O_NIE_DODATNIEJ_KWOCIE | Kwota zlecenia powinna być wartością dodatnią. |
      | Przelew podatkowy                 | KWOTA | -10.00  | 887802 | 12345678  | KOMUNIKAT_O_NIE_DODATNIEJ_KWOCIE | Kwota zlecenia powinna być wartością dodatnią. |
      | Przelew zagraniczny SEPA          | KWOTA | -10.00  | 887802 | 12345678  | KOMUNIKAT_O_NIE_DODATNIEJ_KWOCIE | Kwota zlecenia powinna być wartością dodatnią. |
      | Przelew zagraniczny inny niż SEPA | KWOTA | -10.00  | 887802 | 12345678  | KOMUNIKAT_O_NIE_DODATNIEJ_KWOCIE | Kwota zlecenia powinna być wartością dodatnią. |



  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z błędną wartością pola <pole> = <wartosc>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z błędną wartością pola <pole>

    Zakładając że chcemy wysłać standardowy <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I pole <pole> ma wartość "<wartosc>"
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
    I jest wartość odpowiedzi consent authorizationRedirectUri
    I kopiujemy wartości odpowiedzi consent do schowka

    Kiedy przechodzimy na stronę autoryzacji
    I logujemy się login: <login>, hasło: <hasło>
    Wtedy dostejemy w <webElement> wiadomość '<oczekiwany tekst bledu>'

    Kiedy wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest <status>
    I odpowiedź scope zawiera pole code z wartoścą 'InternalServerError'
    I odpowiedź scope zawiera pole message z wartoścą '<wiadomosc statusu>'

    Kiedy tworzymy zapytanie payment
    I pole <pole> ma wartość "<wartosc>"
    I wysyłamy zapytanie payment
    Wtedy status odpowiedzi scope jest <status>
    I odpowiedź scope zawiera pole code z wartoścą 'InternalServerError'
    I odpowiedź scope zawiera pole message z wartoścą '<wiadomosc statusu>'

    Kiedy wysyłamy zapytanie status
    Wtedy status odpowiedzi scope jest <status>

    Przykłady:  #RACHUNEK_ZLECENIODAWCY
      | typ przelewu                      | pole                   | wartosc                      | login  | hasło     | webElement                                      | oczekiwany tekst bledu                                                | status | wiadomosc statusu |
      | Przelew krajowy                   | RACHUNEK_ZLECENIODAWCY | PL80114021050000920545001002 | 887802 | 12345678  | KOMUNIKAT_O_NIEDOSTEPNYM_RACHUNKU_ZLECENIODAWCY | Dostęp do rachunku zabroniony. Twoja sesja musiała zostać zakończona. | 400    | Brak sesji        |
      | Przelew podatkowy                 | RACHUNEK_ZLECENIODAWCY | PL80114021050000920545001002 | 887802 | 12345678  | KOMUNIKAT_O_NIEDOSTEPNYM_RACHUNKU_ZLECENIODAWCY | Dostęp do rachunku zabroniony. Twoja sesja musiała zostać zakończona. | 400    | Brak sesji        |
      | Przelew zagraniczny SEPA          | RACHUNEK_ZLECENIODAWCY | PL71114010810000528385001014 | 887802 | 12345678  | KOMUNIKAT_O_NIEDOSTEPNYM_RACHUNKU_ZLECENIODAWCY | Dostęp do rachunku zabroniony. Twoja sesja musiała zostać zakończona. | 400    | Brak sesji        |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_ZLECENIODAWCY | PL71114010810000528385001014 | 887802 | 12345678  | KOMUNIKAT_O_NIEDOSTEPNYM_RACHUNKU_ZLECENIODAWCY | Dostęp do rachunku zabroniony. Twoja sesja musiała zostać zakończona. | 400    | Brak sesji        |


    Przykłady:
      | typ przelewu                      | pole   | wartosc | login  | hasło     | webElement                        | oczekiwany tekst bledu        |status | wiadomosc statusu |
      #eea stosuje przelewy tylko w EUR bez przewalutowania tj z konta EUR na konto EUR w innym kraju,
      # non-eea to każdy inny przelew transgraniczny. Np.: z polskiego konta w PLN na indyjskie konto INR z pośrednim przewalutowaniem na EUR
      #poniższy przypadek ma blad bo domestic akceptuje usd
      | Przelew krajowy                   | WALUTA | USD     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      | Przelew krajowy                   | WALUTA | XXX     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      #poniższy przypadek ma blad bo tax akceptuje usd
      | Przelew podatkowy                 | WALUTA | USD     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      #poniższy przypadek ma blad bo tax akceptuje w tym polu dziwne wartosci
      | Przelew podatkowy                 | WALUTA | XXX     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      | Przelew zagraniczny SEPA          | WALUTA | PLN     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      | Przelew zagraniczny SEPA          | WALUTA | XXX     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      | Przelew zagraniczny inny niż SEPA | WALUTA | PLN     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |
      | Przelew zagraniczny inny niż SEPA | WALUTA | JPY     | 887802 | 12345678  | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU | Na formularzu wystąpiły błędy | 404   | Brak zgody        |