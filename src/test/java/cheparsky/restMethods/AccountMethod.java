package cheparsky.restMethods;

public class AccountMethod extends ConsentJSONFactory implements RestMethod {

    private AccountMethod(cheparsky.restMethods.AccountMethod.AccountMethodBuilder builder) {
        super(builder);

    }


    public static class AccountMethodBuilder extends ConsentBuilder implements cheparsky.restMethods.RestMethod.RestMethodBuilder {

        public AccountMethodBuilder(String bodyTemplate) {
            super(bodyTemplate, "", "");
        }


        public AccountMethod build() {
            return new AccountMethod(this);
        }


    }
}
