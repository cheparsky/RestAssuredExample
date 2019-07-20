# language: pl
Potrzeba biznesowa: Testowanie metody patch accounts w rest

  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza> z kolejnym użyciem metody patch na jedną zgodę

  Przy pobieraniu <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy pobrać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
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

    Kiedy tworzymy zapytanie account
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200

    Kiedy tworzymy zapytanie patch
    I dodamy do body zapytania metodę '<metoda>'
    I budujemy body patch
    I wysyłamy zapytanie patch
    Wtedy status odpowiedzi patch jest 204

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie account Szczegóły rachunku
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200

    Przykłady:
      | typ scenariusza | login  | hasło    | metoda            |
      | Listę rachunków | 887802 | 12345678 | accountAccountDetails |


  Szablon scenariusza: Testowanie pozytywnej inicjalizacji <typ scenariusza> z kolejnym użyciem metody patch na kilka zgód

  Przy pobieraniu <typ scenariusza> wysyłamy zapytania ze wszystkimi poprawnymi wartościami.

    Zakładając że chcemy pobrać <typ scenariusza>

    Kiedy tworzymy zapytanie consent
    I budujemy body consent
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 201
    I jest wartość odpowiedzi consent consentId
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

    Kiedy tworzymy zapytanie account
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200

    Kiedy tworzymy zapytanie patch
    I dodamy do body zapytania metodę 'accountAccountDetails'
    I dodamy do body zapytania metodę 'accountTransactions'
    I dodamy do body zapytania metodę 'accountTransactionDetails'
    I budujemy body patch
    I wysyłamy zapytanie patch
    Wtedy status odpowiedzi patch jest 204

    Kiedy tworzymy zapytanie scope
    I wysyłamy zapytanie scope
    Wtedy status odpowiedzi scope jest 200

    Kiedy tworzymy zapytanie account Szczegóły rachunku
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200
    I odpowiedź account zawiera pole accountNumber z wartoścą '81114010100000200301001001'

    Kiedy tworzymy zapytanie account Listę transakcji
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200
    I jest wartość odpowiedzi account transactions

    Kiedy tworzymy zapytanie account Szczegóły transakcji
    I wysyłamy zapytanie account
    Wtedy status odpowiedzi status jest 200
    I odpowiedź account zawiera pole baseInfo.transactionStatus z wartoścą 'DONE'

    Przykłady:
      | typ scenariusza | login  | hasło    |
      | Listę rachunków | 887802 | 12345678 |