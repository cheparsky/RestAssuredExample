# language: pl
Potrzeba biznesowa: BodyDeserializationError 400

  @BodyDeserializationError_400
  Szablon scenariusza: Zapytanie o zgodę z nieprawidłowym json body - <TYP_PRZELEWU>

  Wysłanie prośby o zgodę z niewnypopram json body np. 'sdfhsfghdfg'

    Zakładając że chcemy wysłać standardowy <TYP_PRZELEWU>

    Kiedy tworzymy zapytanie consent
    I body ma wartość 'sdfhsfghdfg'
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'BodyDeserializationError'

  @Domestic
    Przykłady:
      | TYP_PRZELEWU    |
      | Przelew krajowy |

  @Tax
    Przykłady:
      | TYP_PRZELEWU      |
      | Przelew podatkowy |

  @Eaa
    Przykłady:
      | TYP_PRZELEWU             |
      | Przelew zagraniczny SEPA |

  @NonEaa
    Przykłady:
      | TYP_PRZELEWU                      |
      | Przelew zagraniczny inny niż SEPA |

  @AccountAccounts
    Przykłady:
      | TYP_PRZELEWU    |
      | Listę rachunków |

  @AccountTransactions
    Przykłady:
      | TYP_PRZELEWU     |
      | Listę transakcji |

  @AccountAccountDetails
    Przykłady:
      | TYP_PRZELEWU       |
      | Szczegóły rachunku |

  @AccountTransactionDetails
    Przykłady:
      | TYP_PRZELEWU         |
      | Szczegóły transakcji |

  @BodyDeserializationError_400
  Szablon scenariusza: Zapytanie o zgodę z użyciem parametru niezgodnego z kontraktem - <TYP_PRZELEWU>

  Wysyłamy zapytanie o zgodę, ktore zawiera parametr niezgodny z kontraktem

    Zakładając że chcemy wysłać standardowy <TYP_PRZELEWU>

    Kiedy tworzymy zapytanie consent
    I dodamy do zapytania consent parametr z nazwą 'NOWY_PARAMETR' i wartością 'WARTOSC_PARAMETRU'
    I wysyłamy zapytanie consent
    Wtedy status odpowiedzi consent jest 400
    I odpowiedź consent zawiera pole code z wartoścą 'BodyDeserializationError'

  @Domestic
    Przykłady:
      | TYP_PRZELEWU    |
      | Przelew krajowy |
  @Tax
    Przykłady:
      | TYP_PRZELEWU      |
      | Przelew podatkowy |
  @Eaa
    Przykłady:
      | TYP_PRZELEWU             |
      | Przelew zagraniczny SEPA |
  @NonEaa
    Przykłady:
      | TYP_PRZELEWU                      |
      | Przelew zagraniczny inny niż SEPA |

  @AccountAccounts
    Przykłady:
      | TYP_PRZELEWU    |
      | Listę rachunków |

  @AccountTransactions
    Przykłady:
      | TYP_PRZELEWU     |
      | Listę transakcji |

  @AccountAccountDetails
    Przykłady:
      | TYP_PRZELEWU       |
      | Szczegóły rachunku |

  @AccountTransactionDetails
    Przykłady:
      | TYP_PRZELEWU         |
      | Szczegóły transakcji |




