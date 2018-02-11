package it.goff.np.jsonParsingController;

public class WeaponImpl implements Weapon {

    private String name;
    private String url;

    public WeaponImpl(final WeaponBuilder wb) {
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

    public static class WeaponBuilder {
        private String name;
        private String url;

        public WeaponBuilder() {

        }

        public WeaponBuilder name(String name) {
            this.name = name;
            return this;
        }

        public WeaponBuilder url(String url) {
            this.url = url;
            return this;
        }

        public Weapon build() {
            return new WeaponImpl(this);
        }
    }
}
