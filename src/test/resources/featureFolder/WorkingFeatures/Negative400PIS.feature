# language: pl
Potrzeba biznesowa: Testowanie metod PIS w ramach psd2

  @Chrome
  @Negative
  @PIS
  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> gdzie <pole> zawiera wartość <wartosc>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z pustą wartością pola <pole>

    Zakładając że chcemy wysłać <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I pole <pole> ma wartość "<wartosc>"
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'FieldValidationError'

  #@Test
    Przykłady:
      | typ przelewu                      | pole   | wartosc |
      | Przelew krajowy                   | SYSTEM |         |
      | Przelew krajowy                   | SYSTEM | ELIXIRd |
      | Przelew podatkowy                 | SYSTEM |         |
      | Przelew podatkowy                 | SYSTEM | ELIXIRd |
      | Przelew zagraniczny SEPA          | SYSTEM |         |
      | Przelew zagraniczny SEPA          | SYSTEM | ELIXIRd |
      | Przelew zagraniczny inny niż SEPA | SYSTEM |         |
      | Przelew zagraniczny inny niż SEPA | SYSTEM | ELIXIRd |

  @Domestic
    Przykłady:
      | typ przelewu    | pole                   | wartosc                    |
      | Przelew krajowy | RACHUNEK_KONTRAHENTA   |                            |
      | Przelew krajowy | RACHUNEK_KONTRAHENTA   | 51109013620000000036017904 |
      | Przelew krajowy | RACHUNEK_ZLECENIODAWCY | 80114021050000920545001002 |
      | Przelew krajowy | IMIE_KONTRAHENTA       |                            |
      | Przelew krajowy | TYTUL_ZLECENIA         |                            |
      | Przelew krajowy | KWOTA                  | -10.00                     |
      | Przelew krajowy | KWOTA                  | k                          |
      | Przelew krajowy | KWOTA                  |                            |
      | Przelew krajowy | KWOTA                  | *                          |
      | Przelew krajowy | KWOTA                  | 0                          |
      | Przelew krajowy | WALUTA                 |                            |
      | Przelew krajowy | WALUTA                 | XXX                        |
      | Przelew krajowy | NAZWA_KONTRAHENTA      |                            |
      | Przelew krajowy | TYTUL_ZLECENIA         |                            |
      | Przelew krajowy | EXECUTION_DATE         | 2019-01-13                 |
      | Przelew krajowy | SYSTEM                 |                            |
      | Przelew krajowy | SYSTEM                 | ELIXIRd                    |

  @Tax
    Przykłady:
      | typ przelewu      | pole                   | wartosc                    |
      | Przelew podatkowy | RACHUNEK_KONTRAHENTA   | 51109013620000000036017904 |
      | Przelew podatkowy | RACHUNEK_ZLECENIODAWCY | 80114021050000920545001002 |
      | Przelew podatkowy | RACHUNEK_KONTRAHENTA   |                            |
      | Przelew podatkowy | IMIE_KONTRAHENTA       |                            |
      | Przelew podatkowy | KWOTA                  | -10.00                     |
      | Przelew podatkowy | KWOTA                  | k                          |
      | Przelew podatkowy | KWOTA                  | *                          |
      | Przelew podatkowy | KWOTA                  |                            |
      | Przelew podatkowy | KWOTA                  | 0                          |
      | Przelew podatkowy | WALUTA                 |                            |
      | Przelew podatkowy | WALUTA                 | XXX                        |
      | Przelew podatkowy | NAZWA_KONTRAHENTA      |                            |
      | Przelew podatkowy | EXECUTION_DATE         | 2019-01-13                 |
      | Przelew podatkowy | SYSTEM                 |                            |
      | Przelew podatkowy | SYSTEM                 | ELIXIRd                    |

  @Eaa
    Przykłady:
      | typ przelewu             | pole                   | wartosc                    |
      | Przelew zagraniczny SEPA | RACHUNEK_KONTRAHENTA   | 51109013620000000036017904 |
      | Przelew zagraniczny SEPA | RACHUNEK_ZLECENIODAWCY | 80114021050000920545001002 |
      | Przelew zagraniczny SEPA | RACHUNEK_KONTRAHENTA   |                            |
      | Przelew zagraniczny SEPA | IMIE_KONTRAHENTA       |                            |
      | Przelew zagraniczny SEPA | TYTUL_ZLECENIA         |                            |
      | Przelew zagraniczny SEPA | KWOTA                  | -10.00                     |
      | Przelew zagraniczny SEPA | KWOTA                  | k                          |
      | Przelew zagraniczny SEPA | KWOTA                  |                            |
      | Przelew zagraniczny SEPA | KWOTA                  | *                          |
      | Przelew zagraniczny SEPA | KWOTA                  | 0                          |
      | Przelew zagraniczny SEPA | WALUTA                 |                            |
      | Przelew zagraniczny SEPA | WALUTA                 | XXX                        |
      | Przelew zagraniczny SEPA | NAZWA_KONTRAHENTA      |                            |
      | Przelew zagraniczny SEPA | TYTUL_ZLECENIA         |                            |
      | Przelew zagraniczny SEPA | WALUTA                 | PLN                        |
      | Przelew zagraniczny SEPA | EXECUTION_DATE         | 2019-01-13                 |
      | Przelew zagraniczny SEPA | SYSTEM                 |                            |
      | Przelew zagraniczny SEPA | SYSTEM                 | ELIXIRd                    |

  @NonEaa
    Przykłady:
      | typ przelewu                      | pole                   | wartosc                      |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_ZLECENIODAWCY | 80114021050000920545001002   |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_KONTRAHENTA   | PL80114021050000920545001001 |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT      | HSBCCNSHSZNZ                 |
      | Przelew zagraniczny inny niż SEPA | BANK_CODE              | HSBCCNSHZZZ                  |
      | Przelew zagraniczny inny niż SEPA | TRANSFER_CHARGES       | XXX                          |
      | Przelew zagraniczny inny niż SEPA | TRANSFER_CHARGES       | Sha                          |
      | Przelew zagraniczny inny niż SEPA | TRANSFER_CHARGES       | SHAX                         |
      | Przelew zagraniczny inny niż SEPA | SCOPE                  | ais                          |
      | Przelew zagraniczny inny niż SEPA | RACHUNEK_KONTRAHENTA   |                              |
      | Przelew zagraniczny inny niż SEPA | IMIE_KONTRAHENTA       |                              |
      | Przelew zagraniczny inny niż SEPA | TYTUL_ZLECENIA         |                              |
      | Przelew zagraniczny inny niż SEPA | KWOTA                  | -10.00                       |
      | Przelew zagraniczny inny niż SEPA | KWOTA                  | k                            |
      | Przelew zagraniczny inny niż SEPA | KWOTA                  |                              |
      | Przelew zagraniczny inny niż SEPA | KWOTA                  | *                            |
      | Przelew zagraniczny inny niż SEPA | KWOTA                  | 0                            |
      | Przelew zagraniczny inny niż SEPA | WALUTA                 |                              |
      | Przelew zagraniczny inny niż SEPA | WALUTA                 | XXX                          |
      | Przelew zagraniczny inny niż SEPA | NAZWA_KONTRAHENTA      |                              |
      | Przelew zagraniczny inny niż SEPA | TYTUL_ZLECENIA         |                              |
      | Przelew zagraniczny inny niż SEPA | EXECUTION_DATE         | 2019-01-13                   |
      | Przelew zagraniczny inny niż SEPA | SYSTEM                 |                              |
      | Przelew zagraniczny inny niż SEPA | SYSTEM                 | ELIXIRd                      |


  @Chrome
  @Negative
  @PIS
  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> gdzie: <pole1> = <wartosc1> i <pole2> = <wartosc2>

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z pustą wartością pola <pole>

    Zakładając że chcemy wysłać <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I pole <pole1> ma wartość "<wartosc1>"
    I pole <pole2> ma wartość "<wartosc2>"
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'FieldValidationError'

  #@Test
    Przykłady:
      | typ przelewu                      | pole1 | wartosc1 | pole2 | wartosc2 |
      | Przelew krajowy                   |       |          |       |          |
      | Przelew podatkowy                 |       |          |       |          |
      | Przelew zagraniczny SEPA          |       |          |       |          |
      | Przelew zagraniczny inny niż SEPA |       |          |       |          |

  @Domestic
    Przykłady:
      | typ przelewu    | pole1  | wartosc1 | pole2  | wartosc2       |
      | Przelew krajowy | WALUTA | EUR      | SYSTEM | EXPRESS_ELIXIR |

  @NonEaa
    Przykłady:
      | typ przelewu                      | pole1             | wartosc1    | pole2                | wartosc2               |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT |             | BANK_NAME            |                        |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT |             | BANK_CODE            |                        |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT |             | BANK_COUNTRY         |                        |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT |             | BANK_COUNTRY         | XXX                    |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT |             | BANK_COUNTRY         | CHNQ                   |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT |             | BANK_ADDRESS         |                        |
      | Przelew zagraniczny inny niż SEPA | BANK_BIC_OR_SWIFT | HSBCCNSHSZN | RACHUNEK_KONTRAHENTA | GB29NWBK60161331926819 |



  @Chrome
  @Negative
  @PIS
  Szablon scenariusza: Testowanie inicjalizacji <typ przelewu> gdzie kasujemy <pole> z body

  Przy inicjalizacji <typ przelewu> wysyłamy zapytanie z pustą wartością pola <pole>

    Zakładając że chcemy wysłać <typ przelewu>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I skasujemy z body zapytania w miejsce 'consentScope.scopeDetails.privilegeList.pisDomestic.domesticPaymentRequest' klucze '<pole>'
    #I pole WALUTA ma wartość "EUR"
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'FieldValidationError'

  #@Test
    Przykłady:
      | typ przelewu                      | pole   |
  @Domestic
    Przykłady:
      | typ przelewu                      | pole   |
      | Przelew krajowy                   | system |