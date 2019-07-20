# language: pl
Potrzeba biznesowa: Testowanie metody payments w rest

  @Chrome
  @Negative
  @payment
  Szablon scenariusza: Testowanie 'clash': <pole> w zgodzie jest <wartosc1> i przy realizacji płatnowsci jest <wartosc2>

  Przy inicjalizacji <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy wysłać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I pole <pole> ma wartość "<wartosc1>"
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
    I pole <pole> ma wartość "<wartosc2>"
    I wysyłamy zapytanie payment
    Wtedy status odpowiedzi payment jest 401
    I odpowiedź consent zawiera pole code z wartoścą 'ConsentValidationError'



  #@Test
    Przykłady:
      | typ scenariusza                   | login  | hasło    | pole   | wartosc1 | wartosc2 |
      | Przelew krajowy                   | 887802 | 12345678 | WALUTA | PLN      | EUR      |
      | Przelew podatkowy                 | 887802 | 12345678 | WALUTA | PLN      | EUR      |
      | Przelew zagraniczny SEPA          | 887802 | 12345678 | WALUTA | EUR      | PLN      |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | WALUTA | EUR      | PLN      |



  @Domestic
    Przykłady:
      | typ scenariusza | login  | hasło    | pole                   | wartosc1                   | wartosc2                   |
      | Przelew krajowy | 887802 | 12345678 | NAZWA_KONTRAHENTA      | Jan Kowalski               | Manuś Kowalski             |
      | Przelew krajowy | 887802 | 12345678 | TYTUL_ZLECENIA         | transfer                   | transfer2                  |
      | Przelew krajowy | 887802 | 12345678 | NAZWA_ZLECENIODAWCY    | Adam Nowak                 | Adamuś Nowak               |
      | Przelew krajowy | 887802 | 12345678 | RECIPIENT_ADDRESS      | ul. Uchwaly 1              | ul. Uchwały 2              |
      | Przelew krajowy | 887802 | 12345678 | RACHUNEK_ZLECENIODAWCY | 88114011400000218920001001 | 80114021050000920545001002 |
      | Przelew krajowy | 887802 | 12345678 | KWOTA                  | 10.00                      | 1.00                       |
      | Przelew krajowy | 887802 | 12345678 | WALUTA                 | PLN                        | EUR                        |

  @Tax
    Przykłady:
      | typ scenariusza   | login  | hasło    | pole                   | wartosc1                   | wartosc2                   |
      | Przelew podatkowy | 887802 | 12345678 | PAYER_ID               | 5356382129                 | 5356382122                 |
      | Przelew podatkowy | 887802 | 12345678 | PAYER_TYPE             | NIP                        | PESEL                      |
      | Przelew podatkowy | 887802 | 12345678 | FORM_CODE              | VAT7                       | VAT1                       |
      | Przelew podatkowy | 887802 | 12345678 | PERIOD_ID              | 01                         | 02                         |
      | Przelew podatkowy | 887802 | 12345678 | PERIOD_TYPE            | M                          | D                          |
      | Przelew podatkowy | 887802 | 12345678 | US_INFO_YEAR           | 2018                       | 2017                       |
      | Przelew podatkowy | 887802 | 12345678 | NAZWA_KONTRAHENTA      | Jan Kowalski               | Manuś Kowalski             |
      | Przelew podatkowy | 887802 | 12345678 | NAZWA_ZLECENIODAWCY    | Adam Nowak                 | Adamuś Nowak               |
      | Przelew podatkowy | 887802 | 12345678 | RECIPIENT_ADDRESS      | ul. Uchwaly 1              | ul. Uchwały 2              |
      | Przelew podatkowy | 887802 | 12345678 | RACHUNEK_ZLECENIODAWCY | 88114011400000218920001001 | 80114021050000920545001002 |
      | Przelew podatkowy | 887802 | 12345678 | KWOTA                  | 10.00                      | 1.00                       |
      | Przelew podatkowy | 887802 | 12345678 | WALUTA                 | PLN                        | EUR                        |


  @Eaa
    Przykłady:
      | typ scenariusza          | login  | hasło    | pole                   | wartosc1                   | wartosc2                   |
      | Przelew zagraniczny SEPA | 887802 | 12345678 | NAZWA_KONTRAHENTA      | Jan Kowalski               | Manuś Kowalski             |
      | Przelew zagraniczny SEPA | 887802 | 12345678 | TYTUL_ZLECENIA         | transfer                   | transfer2                  |
      | Przelew zagraniczny SEPA | 887802 | 12345678 | NAZWA_ZLECENIODAWCY    | Adam Nowak                 | Adamuś Nowak               |
      | Przelew zagraniczny SEPA | 887802 | 12345678 | RECIPIENT_ADDRESS      | ul. Uchwaly 1              | ul. Uchwały 2              |
      | Przelew zagraniczny SEPA | 887802 | 12345678 | RACHUNEK_ZLECENIODAWCY | 71114010810000528385001013 | 80114021050000920545001002 |
      | Przelew zagraniczny SEPA | 887802 | 12345678 | KWOTA                  | 10.00                      | 1.00                       |
      | Przelew podatkowy        | 887802 | 12345678 | WALUTA                 | PLN                        | EUR                        |


  @NonEaa
    Przykłady:
      | typ scenariusza                   | login  | hasło    | pole                   | wartosc1                   | wartosc2                   |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | BANK_BIC_OR_SWIFT      | HSBCCNSHSZN                | HSBCCNSHSZQ                |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | BANK_NAME              | HSBC BANK (CHINA) COMP     | HSBC BANK (CHINA) COMPUŚ   |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | BANK_CODE              | HSBCCNSH                   | HSBCCNSV                   |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | BANK_COUNTRY           | CHN                        | XXX                        |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | BANK_ADDRESS           | 518010 SHENZHEN CHINA      | SHENZHEN CHINA             |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | TRANSFER_CHARGES       | SHA                        | OUR                        |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | NAZWA_KONTRAHENTA      | Jan Kowalski               | Manuś Kowalski             |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | TYTUL_ZLECENIA         | transfer                   | transfer2                  |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | NAZWA_ZLECENIODAWCY    | Adam Nowak                 | Adamuś Nowak               |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | RECIPIENT_ADDRESS      | ul. Uchwaly 1              | ul. Uchwały 2              |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | RACHUNEK_ZLECENIODAWCY | 88114011400000218920001001 | 80114021050000920545001002 |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | KWOTA                  | 10.00                      | 1.00                       |
      | Przelew zagraniczny inny niż SEPA | 887802 | 12345678 | WALUTA                 | EUR                        | PLN                        |