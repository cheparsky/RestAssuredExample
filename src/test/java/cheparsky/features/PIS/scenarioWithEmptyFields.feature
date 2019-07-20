# language: pl
Potrzeba biznesowa: Testowanie metody payments w rest


  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z dodatkową metodą 'paymentDo'

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z pustą wartością pola <pole>

    Zakładając że chcemy wysłać <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I dodamy do body zapytania metodę '<metoda>'
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'FieldValidationError'

    Przykłady:
      | typ przelewu    | metoda      |
      | Przelew krajowy | paymentDomestic |
      | Przelew krajowy | paymentTax |
      | Przelew krajowy | paymentEea |
      | Przelew krajowy | paymentNonEea |


  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z pustą wartością pola <pole>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z pustą wartością pola <pole>

    Zakładając że chcemy wysłać <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I dodamy do body zapytania w miejsce 'consentScope.scopeDetails' klucz 'privilegeList' i  wartość 'aaa'
    I skasujemy z body zapytania w miejsce 'consentScope.scopeDetails' klucze 'scopeTimeLimit,scopeTimeDuration'
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    #I odpowiedź consent zawiera pole code z wartoścą 'FieldValidationError'

    Przykłady:
      | typ przelewu    |
      | Przelew krajowy |



  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z pustą wartością pola <pole>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z pustą wartością pola <pole>

    Zakładając że chcemy wysłać standardowy <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I pole <pole> ma wartość "<wartosc>"
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'FieldValidationError'


    Przykłady:
  @DOMESTIC
  @Field
    Przykłady:
      | typ przelewu                      | pole                 | wartosc | login  | hasło    | webElement                                           | oczekiwany tekst bledu                          |
      | Przelew krajowy                   | RACHUNEK_KONTRAHENTA |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_KONTRAHENTA        | Nieokreślony numer rachunku kontrahenta.        |
      | Przelew podatkowy                 | RACHUNEK_KONTRAHENTA |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_ORGANU_PODATKOWEGO | Nieokreślony numer rachunku organu podatkowego. |
      | Przelew zagraniczny SEPA          | RACHUNEK_KONTRAHENTA |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_KONTRAHENTA        | Nieokreślony numer rachunku kontrahenta.        |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_KONTRAHENTA |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_KONTRAHENTA        | Nieokreślony numer rachunku kontrahenta.        |
      | Przelew krajowy                   | IMIE_KONTRAHENTA     |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_IMIE_KONTRAHENTA           | Nieokreślona nazwa kontrahenta.                 |
      | Przelew podatkowy                 | IMIE_KONTRAHENTA     |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_IMIE_KONTRAHENTA           | Nieokreślona nazwa kontrahenta.                 |
      | Przelew zagraniczny SEPA          | IMIE_KONTRAHENTA     |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_IMIE_KONTRAHENTA           | Nieokreślona nazwa kontrahenta.                 |
      | Przelew zagraniczny inny niż SEPA | IMIE_KONTRAHENTA     |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_IMIE_KONTRAHENTA           | Nieokreślona nazwa kontrahenta.                 |
      | Przelew krajowy                   | TYTUL_ZLECENIA       |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_TYTULE_ZLECENIA            | Nieokreślony tytuł zlecenia.                    |
      | Przelew zagraniczny SEPA          | TYTUL_ZLECENIA       |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_TYTULE_ZLECENIA            | Nieokreślony tytuł zlecenia.                    |
      | Przelew zagraniczny inny niż SEPA | TYTUL_ZLECENIA       |         | 887802 | 12345678 | KOMUNIKAT_O_NIEOKRESLONYM_TYTULE_ZLECENIA            | Nieokreślony tytuł zlecenia.                    |


  @Chrome
  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z pustą wartością pola <pole>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytania z pustą wartością pola <pole>

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
    Wtedy dostejemy w <webElement> wiadomość '<oczekiwany tekst bledu>'

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
      | typ przelewu                      | pole   | wartosc | login  | hasło    | webElement                                   | oczekiwany tekst bledu        |
      | Przelew krajowy                   | KWOTA  |         | 887802 | 12345678 | KOMUNIKAT_O_PRZEJSCIOWYM_PROBLEMOE_APLIKACJI | Przejściowy problem aplikacji |
      | Przelew podatkowy                 | KWOTA  |         | 887802 | 12345678 | KOMUNIKAT_O_PRZEJSCIOWYM_PROBLEMOE_APLIKACJI | Przejściowy problem aplikacji |
      | Przelew zagraniczny SEPA          | KWOTA  |         | 887802 | 12345678 | KOMUNIKAT_O_PRZEJSCIOWYM_PROBLEMOE_APLIKACJI | Przejściowy problem aplikacji |
      | Przelew zagraniczny inny niż SEPA | KWOTA  |         | 887802 | 12345678 | KOMUNIKAT_O_PRZEJSCIOWYM_PROBLEMOE_APLIKACJI | Przejściowy problem aplikacji |
      | Przelew krajowy                   | WALUTA |         | 887802 | 12345678 | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU            | Na formularzu wystąpiły błędy |
      | Przelew podatkowy                 | WALUTA |         | 887802 | 12345678 | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU            | Na formularzu wystąpiły błędy |
      | Przelew zagraniczny SEPA          | WALUTA |         | 887802 | 12345678 | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU            | Na formularzu wystąpiły błędy |
      | Przelew zagraniczny inny niż SEPA | WALUTA |         | 887802 | 12345678 | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU            | Na formularzu wystąpiły błędy |
      | Przelew zagraniczny inny niż SEPA | WALUTA |         | 887802 | 12345678 | KOMUNIKAT_O_BLEDACH_NA_FORMULARZU            | Na formularzu wystąpiły błędy |



  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> z pustą wartością pola <pole>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytania z pustą wartością pola <pole>

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
    I wybieramy KONTEKST_NOWY
    Wtedy dostejemy w KOMUNIKAT_O_BLEDACH_NA_FORMULARZU wiadomość 'Na formularzu wystąpiły błędy'
    I dostejemy w KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_ZLECENIODAWCY wiadomość 'Nieokreślony numer rachunku zleceniodawcy.'
    I nie widzimy na stronie KOMUNIKAT_O_NIEOKRESLONYM_TRYBIE_PILNOSCI
    I widzimy na stronie RACHUNEK_ZLECENIODAWCY

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
      | typ przelewu                      | pole                   | wartosc | login  | hasło    |
      | Przelew krajowy                   | RACHUNEK_ZLECENIODAWCY |         | 887802 | 12345678 |
      | Przelew podatkowy                 | RACHUNEK_ZLECENIODAWCY |         | 887802 | 12345678 |
      | Przelew zagraniczny SEPA          | RACHUNEK_ZLECENIODAWCY |         | 887802 | 12345678 |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_ZLECENIODAWCY |         | 887802 | 12345678 |
