package cheparsky.psd2methods;

public class AISMethod extends ConsentJSONFactory implements PSD2Method {

    private AISMethod(AISMethodBuilder builder) {
        super(builder);

    }


    public static class AISMethodBuilder extends ConsentBuilder implements PSD2MethodBuilder {

        public AISMethodBuilder(String bodyTemplate) {
            super(bodyTemplate, "", "");
        }


        public AISMethod build() {
            return new AISMethod(this);
        }


    }
}
