package it.goff.np.jsonParsingController;

public class WeapponImpl implements Weappon {

    private String name;
    private String url;

    public WeapponImpl(final WeapponBuilder wb) {
        this.name = wb.name;
        this.url = wb.url;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    public static class WeapponBuilder {
        private String name;
        private String url;

        public WeapponBuilder() {

        }

        public WeapponBuilder name(String name) {
            this.name = name;
            return this;
        }

        public WeapponBuilder url(String url) {
            this.url = url;
            return this;
        }

        public Weappon build() {
            return new WeapponImpl(this);
        }
    }
}
