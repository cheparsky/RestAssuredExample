# language: pl
Potrzeba biznesowa: Testowanie metody patch AIS w ramach psd2

  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza> z kolejnym użyciem metody patch na jedną zgodę

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
    I akceptujemy zgodę hasłem: <hasło>
    Wtedy dostejemy w KOMUNIKAT_O_ZAAKCEPTOWANEJ_ZGODZIE wiadomość 'Zgoda została zaakceptowana'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie ais
    I wysyłamy zapytanie ais
    Wtedy status odpowiedzi status jest 200

    Kiedy tworzymy zapytanie patch
    I dodamy do body zapytania metodę '<metoda>'
    I budujemy body patch
    I wysyłamy zapytanie patch
    Wtedy status odpowiedzi patch jest 204

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie ais Szczegóły rachunku
    I wysyłamy zapytanie ais
    Wtedy status odpowiedzi status jest 200

    Przykłady:
      | typ scenariusza | login  | hasło    | metoda            |
      | Listę rachunków | 887802 | 12345678 | aisAccountDetails |


  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza> z kolejnym użyciem metody patch na kilka zgód

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
    I akceptujemy zgodę hasłem: <hasło>
    Wtedy dostejemy w KOMUNIKAT_O_ZAAKCEPTOWANEJ_ZGODZIE wiadomość 'Zgoda została zaakceptowana'

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie ais
    I wysyłamy zapytanie ais
    Wtedy status odpowiedzi status jest 200

    Kiedy tworzymy zapytanie patch
    I dodamy do body zapytania metodę 'aisAccountDetails'
    I dodamy do body zapytania metodę 'aisTransactions'
    I dodamy do body zapytania metodę 'aisTransactionDetails'
    I budujemy body patch
    I wysyłamy zapytanie patch
    Wtedy status odpowiedzi patch jest 204

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie ais Szczegóły rachunku
    I wysyłamy zapytanie ais
    Wtedy status odpowiedzi status jest 200
    I odpowiedź ais zawiera pole accountNumber z wartoścą '81114010100000200301001001'

    Kiedy tworzymy zapytanie ais Listę transakcji
    I wysyłamy zapytanie ais
    Wtedy status odpowiedzi status jest 200
    I jest wartość odpowiedzi ais transactions

    Kiedy tworzymy zapytanie ais Szczegóły transakcji
    I wysyłamy zapytanie ais
    Wtedy status odpowiedzi status jest 200
    I odpowiedź ais zawiera pole baseInfo.transactionStatus z wartoścą 'DONE'

    Przykłady:
      | typ scenariusza | login  | hasło    |
      | Listę rachunków | 887802 | 12345678 |