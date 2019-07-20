package cheparsky.restMethods;

public class AccountAccountsMethod extends ConsentJSONFactory implements RestMethod {

    private AccountAccountsMethod(AccountAccountsMethodBuilder builder) {
        super(builder);
    }

    public static class AccountAccountsMethodBuilder extends ConsentBuilder implements cheparsky.restMethods.RestMethod.RestMethodBuilder {

        public AccountAccountsMethodBuilder(String bodyTemplate) {
            super(bodyTemplate,"","");
        }

        public AccountAccountsMethod build() {
            return new AccountAccountsMethod(this);
        }


    }
}
